package com.wissen.model;
import javax.persistence.*;

@Entity
public class SaleInstance {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private int productId;
    private String productName;
    private String productCompany;
    private double sellingPrice;
    private int amount;

    public SaleInstance(){

    }
    public SaleInstance(int productId, String productName, String productCompany, double sellingPrice, int amount) {

        this.productId = productId;
        this.productName = productName;
        this.productCompany = productCompany;
        this.sellingPrice = sellingPrice;
        this.amount = amount;
    }
    public SaleInstance(int id, int productId, String productName, String productCompany, double sellingPrice, int amount) {
        this.id=id;
        this.productId = productId;
        this.productName = productName;
        this.productCompany = productCompany;
        this.sellingPrice = sellingPrice;
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

    public void setProductCompany(String productCompany) {
        this.productCompany = productCompany;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return '\n'+"Sale{" +
                "id=" + id +
                ", productId=" + productId +
                ", name='" + productName + '\'' +
                ", company='" + productCompany + '\'' +
                ", costPrice=" + sellingPrice +
                ", amount=" + amount +
                '}';
    }
}
