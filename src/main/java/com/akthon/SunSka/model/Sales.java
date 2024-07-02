package com.akthon.SunSka.model;

import jakarta.persistence.Entity;
import java.util.Date;

@Entity
public class Sales {

    private Date date;

    private Integer nbSold;

    public Sales() {
    }

    public Sales(Date date, Integer nbSold) {
        this.date = date;
        this.nbSold = nbSold;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNbSold() {
        return nbSold;
    }

    public void setNbSold(Integer nbSold) {
        this.nbSold = nbSold;
    }
}
