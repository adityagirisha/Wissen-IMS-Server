package com.wissen.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<SaleInstance> entries = new ArrayList<>();

    private double total;

    private String buyer;

    public Sale() {

    }

    public Sale(int id, List<SaleInstance> entries, double total, String buyer) {
        this.buyer = buyer;
        this.entries = entries;
        this.id = id;
        this.total = total;
    }

    public Sale(List<SaleInstance> entries, double total, String buyer) {
        this.buyer = buyer;
        this.entries = entries;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SaleInstance> getEntries() {
        return entries;
    }

    public void setEntries(List<SaleInstance> products) {
        this.entries = products;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String seller) {
        this.buyer = seller;
    }

    @Override
    public String toString() {
        return "SaleBill{" +
                "id=" + id +
                ", products=" + entries +
                ", total=" + total +
                ", seller='" + buyer + '\'' +
                '}';
    }

    public void addEntry(SaleInstance s1) {
        this.entries.add(s1);
    }
}