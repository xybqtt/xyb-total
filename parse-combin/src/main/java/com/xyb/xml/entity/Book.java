package com.xyb.xml.entity;

import java.math.BigDecimal;

public class Book {

    private String sn;

    private String author;

    private BigDecimal price;

    private String name;

    public Book() {
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Book{" +
                "sn='" + sn + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
