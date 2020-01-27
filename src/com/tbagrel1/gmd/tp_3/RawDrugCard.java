package com.tbagrel1.gmd.tp_3;

import java.util.HashMap;
import java.util.Map;

public class RawDrugCard {
    protected String id;
    protected Map<String, String> fields;

    public RawDrugCard() {
        this.id = null;
        this.fields = new HashMap<>();
    }

    public RawDrugCard(String id, Map<String, String> fields) {
        this.id = id;
        this.fields = fields;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void addField(RawDrugCardField field) {
        this.fields.put(field.getName(), field.getValue());
    }

    @Override
    public String toString() {
        return String.format("RawDrugCard#%s", id);
    }

    public String getField(String genericNameFieldName) {
        return this.fields.get(genericNameFieldName);
    }
}
