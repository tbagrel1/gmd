package com.tbagrel1.gmd.lucene;

public class RawDrugCardField {
    protected String name;
    protected String value;

    public RawDrugCardField() {
        this.name = null;
        this.value = "";
    }

    public RawDrugCardField(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void addLine(String line) {
        this.value = this.value + line + "\n";
    }
}
