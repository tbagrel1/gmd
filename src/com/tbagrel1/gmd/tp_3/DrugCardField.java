package com.tbagrel1.gmd.tp_3;

public class DrugCardField {
    protected String name;
    protected String value;

    public DrugCardField() {
        this.name = null;
        this.value = "";
    }

    public DrugCardField(String name, String value) {
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
