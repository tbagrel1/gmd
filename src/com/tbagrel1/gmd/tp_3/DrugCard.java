package com.tbagrel1.gmd.tp_3;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

public class DrugCard {
    public static final String GENERIC_NAME_FIELD_NAME = "Generic_Name";
    public static final String UNKNOWN = "<unknown>";

    protected String genericName;

    public DrugCard(RawDrugCard rawDrugCard) {
        this.genericName = rawDrugCard.getField(GENERIC_NAME_FIELD_NAME);
        if (this.genericName == null) {
            this.genericName = UNKNOWN;
        }
    }

    public Document toDocument() {
        Document document = new Document();
        document.add(new Field("genericName", this.genericName, TextField.TYPE_STORED));
        return document;
    }
}
