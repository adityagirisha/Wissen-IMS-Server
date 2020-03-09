package com.wissen.model;
import javax.persistence.*;

@Entity

public class OrderInstance {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int productId;
    private String productName;
    private String productCompany;
    private double costPrice;
    private int amount;

    public OrderInstance(){

    }

    public OrderInstance(String productName, String productCompany, double costPrice, int amount) {

        this.productId = productId;
        this.productName = productName;
        this.productCompany = productCompany;
        this.costPrice = costPrice;
        this.amount = amount;

    }

    public OrderInstance(int productId, String productName, String productCompany, double costPrice, int amount) {

        this.productId = productId;
        this.productName = productName;
        this.productCompany = productCompany;
        this.costPrice = costPrice;
        this.amount = amount;
    }

    public OrderInstance(int id, int productId, String productName, String productCompany, double costPrice, int amount) {
        this.id= id;
        this.productId = productId;
        this.productName = productName;
        this.productCompany = productCompany;
        this.costPrice = costPrice;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(String seller) {
        this.productCompany = seller;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return '\n'+"Order{" +
                "id=" + id +
                ", productId=" + productId +
                ", name='" + productName + '\'' +
                ", company='" + productCompany + '\'' +
                ", sellingPrice=" + costPrice +
                ", amount=" + amount +
                '}';
    }
}
