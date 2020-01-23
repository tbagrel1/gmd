package com.tbagrel1.gmd.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class CsvWriter {
    public static final String DEFAULT_DELIMITER_CHAR = ",";
    public static final String DEFAULT_QUOTE_CHAR = "\"";
    public static final String DEFAULT_ESCAPE_CHAR = "\\";
    public static final String DEFAULT_ENCODING = "UTF-8";

    protected boolean first;

    protected String quoteChar;
    protected String delimiterChar;
    protected String escapeChar;

    protected BufferedWriter fileWriter;

    public CsvWriter(Path filePath, String delimiterChar, String quoteChar, String escapeChar, String encoding) throws IOException {
        this.first = true;
        this.delimiterChar = delimiterChar;
        this.quoteChar = quoteChar;
        this.escapeChar = escapeChar;
        this.fileWriter = Files.newBufferedWriter(filePath, Charset.forName(encoding));
    }

    public CsvWriter(Path filePath) throws IOException {
        this(filePath, DEFAULT_DELIMITER_CHAR, DEFAULT_QUOTE_CHAR, DEFAULT_ESCAPE_CHAR, DEFAULT_ENCODING);
    }

    public void write(List<Object> fields) throws IOException {
        String line = fields.stream()
            .map(obj -> obj.toString())
            .map(objString -> protectString(objString))
            .collect(Collectors.joining(delimiterChar));
        if (first) {
            first = false;
        } else {
            line = "\n" + line;
        }
        fileWriter.write(line);
    }

    public void finish() throws IOException {
        fileWriter.close();
    }

    private String protectString(String text) {
        return quoteChar + text.replace(escapeChar, doubleEscapeChar()).replace(quoteChar, escapedQuoteChar()) + quoteChar;
    }

    private String escapedQuoteChar() {
        return escapeChar + quoteChar;
    }

    private String doubleEscapeChar() {
        return escapeChar + escapeChar;
    }
}
