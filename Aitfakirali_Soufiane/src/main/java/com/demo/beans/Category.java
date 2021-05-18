package com.demo.beans;

import java.sql.Date;

public class Category {
    int category_id;
    String category_nom;
    Date date_update;
    
    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_nom() {
        return category_nom;
    }

    public void setCategory_nom(String category_nom) {
        this.category_nom = category_nom;
    }

    public Date getDate_update() {
        return date_update;
    }

    public void setDate_update(Date date_update) {
        this.date_update = date_update;
    }
}
