package com.study.java.bookstore;

public class Book {
    private String title;       // 책 제목
    private String genre;        // 장르
    private String author;      // 저자
    private String publisher;   // 출판사
    private String pubDate;     // 출간일
    private int price;          // 가격
    private String location;    // 위치
    private int stock;          // 재고

    public Book(String title, String genre, String author, String publisher, String pubDate, int price, String location, int stock) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
        this.pubDate = pubDate;
        this.price = price;
        this.location = location;
        this.stock = stock;
    }

    // public Book() { }

    // # getter ----------------------------
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPubDate() {
        return pubDate;
    }

    public int getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public int getStock() {
        return stock;
    }

    // # setter ----------------------------
    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", stock='" + stock + '\'' +
                '}';
    }
}
