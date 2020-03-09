package com.wissen.model;

import javax.persistence.*;

@Entity
@Table(name = "Notifications")
public class Notifications {

    @Override
    public String toString() {
        return "Notifications{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Notifications() {
    }

    public Notifications(int ownerId,int type,String ownerName) {
        this.ownerId=ownerId;
        this.ownerName=ownerName;
        this.type=type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private int ownerId;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type;
    private String ownerName;


}
