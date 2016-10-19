package com.commin.pro.commin_pig_1000.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user on 2016-10-19.
 */
public class Model4Chart implements Serializable {
    private String deposit_value;
    private String total_deposit_value;
    private Date deposit_date;

    public String getDeposit_value() {
        return deposit_value;
    }

    public void setDeposit_value(String deposit_value) {
        this.deposit_value = deposit_value;
    }

    public Date getDeposit_date() {
        return deposit_date;
    }

    public void setDeposit_date(Date deposit_date) {
        this.deposit_date = deposit_date;
    }

    public String getTotal_deposit_value() {
        return total_deposit_value;
    }

    public void setTotal_deposit_value(String total_deposit_value) {
        this.total_deposit_value = total_deposit_value;
    }
}
