package com.akthon.SunSka.DTO;

import java.util.Date;

public class OrderDTO {

    private String status;
    private Date dateRestock;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateRestock() {
        return dateRestock;
    }

    public void setDateRestock(Date dateRestock) {
        this.dateRestock = dateRestock;
    }
}
