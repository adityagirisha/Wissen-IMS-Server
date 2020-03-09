package com.wissen.model;

import javax.persistence.*;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    private String company;
    private double price;
    private boolean status;
    private int amount;


    public Product(){

    }
    public Product(int id, String name, String company, double price, boolean status, int amount) {
        super();
        this.id = id;
        this.name = name;
        this.company = company;
        this.status = status;
        this.price=price;

        if (status) {
            this.amount = amount;
        } else {
            this.amount = 0;
        }
    }

    @Override
    public String toString() {
        return '\n'+"Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", costPrice=" + price +
                ", status=" + status +
                ", amount=" + amount +
                '}';
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }


    public void setPrice(double costPrice) {
        this.price = costPrice;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public void setId(String id) {
    }
}
