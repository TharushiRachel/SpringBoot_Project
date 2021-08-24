package com.example.enum_;

public enum Status {

    ACTIVE("A"),
    INACTIVE("I"),
    DELETE("D");

    private final String displayStatus;

    Status(String displayStatus) {
        this.displayStatus = displayStatus;
    }

    public String getDisplayStatus() {
        return displayStatus;
    }

    public static Status getByLetter(String displayStatus){
        return   Status.valueOf(displayStatus);
    }


}
