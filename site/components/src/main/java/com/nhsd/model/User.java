package com.nhsd.model;

public class User {

    private String name;
    private String displayName;
    private String email;

    public User() {
    }

    public User(String name, String displayName, String email) {
        this.name = name;
        this.displayName = displayName;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
