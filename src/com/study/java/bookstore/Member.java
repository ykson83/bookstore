package com.study.java.bookstore;

public class Member {
    private String name;
    private String email;
    private String address;
    private String addDate;
    private boolean isActive;


    public Member(String name, String email, String address, String addDate) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.addDate = addDate;
        this.isActive = true;
    }

    // # getter ----------------------------
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getAddress() { return address;}
    public String getAddDate() { return addDate; }
    public boolean isActive() { return isActive; }

    // # setter ----------------------------
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
    public void setAddDate(String addDate) { this.addDate = addDate; }
    public void setActive(boolean active) { isActive = active; }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", addDate='" + addDate + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}