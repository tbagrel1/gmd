package com.tbagrel1.gmd.tp_3;

import java.util.ArrayList;
import java.util.List;

public class RawDrugCardParser {
    private static final byte[] BEGIN_CARD_TEXT = "#BEGIN_DRUGCARD ".getBytes();
    private static final byte[] END_CARD_TEXT = "#END_DRUGCARD ".getBytes();
    private static final byte[] DOUBLE_NEWLINE = "\n\n".getBytes();
    private static final byte[] NEWLINE = "\n".getBytes();
    private static final byte[] BEGIN_FIELD_TEXT = "# ".getBytes();
    private static final byte[] SEP_FIELD_TEXT = ":\n".getBytes();

    protected byte[] data;
    protected int readPosition;
    protected int beginPosition;
    protected int length;
    protected RawDrugCardField inProgressField;
    protected RawDrugCard inProgressCard;

    public RawDrugCardParser(byte[] data) {
        this.data = data;
    }

    protected boolean readExpected(byte[] expected) {
        beginPosition = readPosition;
        int i;
        for (i = 0; i < expected.length && readPosition < data.length; i++) {
            if (data[readPosition] == expected[i]) {
                readPosition++;
            } else {
                return false;
            }
        }
        length = readPosition - beginPosition;
        return !(readPosition == data.length && i < expected.length);
    }

    protected void rewind() {
        readPosition = beginPosition;
    }

    protected boolean readUntil(byte[] expectedEnd) {
        beginPosition = readPosition;
        int i;
        int subBeginPosition;
        while (true) {
            subBeginPosition = readPosition;
            for (i = 0; i < expectedEnd.length & readPosition < data.length; i++) {
                if (data[readPosition] == expectedEnd[i]) {
                    readPosition++;
                } else {
                    break;
                }
            }
            if (i == expectedEnd.length) {
                length = readPosition - beginPosition - expectedEnd.length;
                return true;
            } else {
                if (readPosition == data.length) {
                    return false;
                } else {
                    readPosition = subBeginPosition + 1;
                }
            }
        }
    }

    protected boolean readField() {
        inProgressField = new RawDrugCardField();
        if (!readExpected(BEGIN_FIELD_TEXT)) {
            return false;
        }
        if (!readUntil(SEP_FIELD_TEXT)) {
            return false;
        }
        inProgressField.setName(gatherString());
        String gathered;
        if (!readUntil(NEWLINE)) {
            return false;
        }
        gathered = gatherString();
        while (gathered.length() > 0) {
            inProgressField.addLine(gathered);
            if (!readUntil(NEWLINE)) {
                return false;
            }
            gathered = gatherString();
        }
        return true;
    }

    protected RawDrugCardField gatherField() {
        return inProgressField;
    }

    protected String gatherString() {
        return new String(data, beginPosition, length);
    }

    protected boolean readCard() {
        inProgressCard = new RawDrugCard();
        if (!readExpected(BEGIN_CARD_TEXT)) {
            return false;
        }
        if (!readUntil(DOUBLE_NEWLINE)) {
            return false;
        }
        inProgressCard.setId(gatherString());
        while (readField()) {
            inProgressCard.addField(gatherField());
        }
        rewind();
        if (!readExpected(END_CARD_TEXT)) {
            return false;
        }
        if (!readUntil(NEWLINE)) {
            return false;
        }
        return true;
    }

    protected RawDrugCard gatherCard() {
        return inProgressCard;
    }

    public List<RawDrugCard> parse() throws Exception {
        List<RawDrugCard> parsedCards = new ArrayList<>();
        while (readCard()) {
            parsedCards.add(gatherCard());
        }
        if (readPosition != data.length && data[readPosition] != '\n') {
            throw new Exception("Invalid input file");
        }
        return parsedCards;
    }
}
