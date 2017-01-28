package com.rajendarreddyj.hibernate.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer stockId;
    private String stockCode;
    private String stockName;

    public Stock() {
    }

    public Stock(final String stockCode, final String stockName) {
        this.stockCode = stockCode;
        this.stockName = stockName;
    }

    public Integer getStockId() {
        return this.stockId;
    }

    public void setStockId(final Integer stockId) {
        this.stockId = stockId;
    }

    public String getStockCode() {
        return this.stockCode;
    }

    public void setStockCode(final String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return this.stockName;
    }

    public void setStockName(final String stockName) {
        this.stockName = stockName;
    }
}
