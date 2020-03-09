package com.wissen.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<OrderInstance> entries = new ArrayList<>();

    private double total;

    private String seller;

    public Order() {

    }

    public Order(int id, List<OrderInstance> entries, double total, String seller) {
        this.seller = seller;
        this.entries = entries;
        this.id = id;
        this.total = total;
    }

    public Order(List<OrderInstance> entries, double total, String seller) {
        this.seller = seller;
        this.entries = entries;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderInstance> getEntries() {
        return entries;
    }

    public void setEntries(List<OrderInstance> products) {
        this.entries = products;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "OrderBill{" +
                "id=" + id +
                ", products=" + entries +
                ", total=" + total +
                ", seller='" + seller + '\'' +
                '}';
    }

    public void addEntry(OrderInstance o1) {
        this.entries.add(o1);
    }
}
