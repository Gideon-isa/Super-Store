package com.ltp.globalsuperstore;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.ltp.globalsuperstore.validation.Category;

public class Item {
    //@Category(message = "Please choose a category")
    @NotBlank(message = "Please choose a category")
    private String category;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Min(value = 0, message = "Price cannot be negative")
    private Double price;

    @Min(value = 0, message = "Discount cannot be negative")
    private Double discount;

    @Past(message = "Date must be of the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String id;

    public Item() {
        this.
        id = UUID.randomUUID().toString();
    }


    public Item(String category, String name, Double price, Double discount, Date date) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.date = date;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
}

