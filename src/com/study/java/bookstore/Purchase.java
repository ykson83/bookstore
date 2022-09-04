package com.study.java.bookstore;

public class Purchase {
    // private int seqNo = 0; //TODO: auto generate..
    private String title;        // 책 제목
    private String customer;     // 고객명
    private int quantity;        // 수량
    private String purchaseDate; // 구매일

    public Purchase(String title, String customer, int quantity, String purchaseDate) {
        this.title = title;
        this.customer = customer;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }

    // # getter ----------------------------
    public String getTitle() {
        return title;
    }

    public String getCustomer() {
        return customer;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    // # setter ----------------------------
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "title='" + title + '\'' +
                ", customer='" + customer + '\'' +
                ", quantity=" + quantity +
                ", purchaseDate='" + purchaseDate + '\'' +
                '}';
    }
}
