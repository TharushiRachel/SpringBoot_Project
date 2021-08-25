package com.example.enums;

public enum Status {

    ACTIVE("Active", "A"), INACTIVE("Inactive", "I"), DELETED("Deleted", "D");

    private String label;
    private String value;

    Status(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static Status getEnum(String value) {
        for (Status clusterStatus : Status.values()) {
            if (clusterStatus.getValue().equalsIgnoreCase(value)) {
                return clusterStatus;
            }
        }
        return null;
    }


}
