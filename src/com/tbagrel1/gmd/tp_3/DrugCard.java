package com.tbagrel1.gmd.tp_3;

import java.util.ArrayList;
import java.util.List;

public class DrugCard {
    protected String id;
    protected List<DrugCardField> fields;

    public DrugCard() {
        this.id = null;
        this.fields = new ArrayList<>();
    }

    public DrugCard(String id, List<DrugCardField> fields) {
        this.id = id;
        this.fields = fields;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<DrugCardField> getFields() {
        return fields;
    }

    public void addField(DrugCardField field) {
        this.fields.add(field);
    }

    @Override
    public String toString() {
        return String.format("DrugCard#%s", id);
    }
}
