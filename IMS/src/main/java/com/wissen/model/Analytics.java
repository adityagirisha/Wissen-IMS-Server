package com.wissen.model;

import javax.persistence.*;

@Entity
@Table(name = "Analytics")
public class Analytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Override
    public String toString() {
        return "Analytics{" +
                "id=" + id +
                ", col='" + col + '\'' +
                ", val=" + val +
                '}';
    }

    private String col;

    private double val;

    public Analytics(String col, double val) {
        this.col = col;
        this.val = val;
    }

    public Analytics() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }
}
