package com.tbagrel1.gmd.lucene;

import org.apache.lucene.document.*;

public class DrugCard {
    public static final String GENERIC_NAME_FIELD_NAME = "Generic_Name";
    public static final String SYNONYMS_FIELD_NAME = "Synonyms";
    public static final String BRAND_NAMES_FIELD_NAME = "Brand_Names";
    public static final String DESCRIPTION_FIELD_NAME = "Description";
    public static final String INDICATION_FIELD_NAME = "Indication";
    public static final String PHARMACOLOGY_FIELD_NAME = "Pharmacology";
    public static final String DRUG_INTERACTIONS_FIELD_NAME = "Drug_Interactions";

    public static final String UNKNOWN = "Not Available";

    protected String id;
    protected String genericName;
    protected String synonyms;
    protected String brandNames;
    protected String description;
    protected String indication;
    protected String pharmacology;
    protected String drugInteractions;

    public DrugCard(RawDrugCard rawDrugCard) {
        id = rawDrugCard.getId();
        genericName = rawDrugCard.getField(GENERIC_NAME_FIELD_NAME);
        if (genericName == null) {
            genericName = UNKNOWN;
        }
        synonyms = rawDrugCard.getField(SYNONYMS_FIELD_NAME);
        if (synonyms == null) {
            synonyms = UNKNOWN;
        }
        brandNames = rawDrugCard.getField(BRAND_NAMES_FIELD_NAME);
        if (brandNames == null) {
            brandNames = UNKNOWN;
        }
        description = rawDrugCard.getField(DESCRIPTION_FIELD_NAME);
        if (description == null) {
            description = UNKNOWN;
        }
        indication = rawDrugCard.getField(INDICATION_FIELD_NAME);
        if (indication == null) {
            indication = UNKNOWN;
        }
        pharmacology = rawDrugCard.getField(PHARMACOLOGY_FIELD_NAME);
        if (pharmacology == null) {
            pharmacology = UNKNOWN;
        }
        drugInteractions = rawDrugCard.getField(DRUG_INTERACTIONS_FIELD_NAME);
        if (drugInteractions == null) {
            drugInteractions = UNKNOWN;
        }
    }

    public String getId() {
        return id;
    }

    public String getGenericName() {
        return genericName;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public String getBrandNames() {
        return brandNames;
    }

    public String getDescription() {
        return description;
    }

    public String getIndication() {
        return indication;
    }

    public String getPharmacology() {
        return pharmacology;
    }

    public String getDrugInteractions() {
        return drugInteractions;
    }

    public Document toDocument() {
        Document document = new Document();
        document.add(new Field(
            "id", id, StoredField.TYPE
        ));
        document.add(new Field(
            "genericName", genericName, StringField.TYPE_STORED
        ));
        document.add(new Field(
            "synonyms", synonyms, TextField.TYPE_NOT_STORED
        ));
        document.add(new Field(
            "brandNames", brandNames, TextField.TYPE_NOT_STORED
        ));
        document.add(new Field(
            "description", description, TextField.TYPE_NOT_STORED
        ));
        document.add(new Field(
            "indication", indication, TextField.TYPE_NOT_STORED
        ));
        document.add(new Field(
            "pharmacology", pharmacology, TextField.TYPE_NOT_STORED
        ));
        document.add(new Field(
            "drugInteractions", drugInteractions, TextField.TYPE_NOT_STORED
        ));
        return document;
    }

    @Override
    public String toString() {
        return String.format("DrugCard#%s(genericName=%s)", id, genericName);
    }
}
