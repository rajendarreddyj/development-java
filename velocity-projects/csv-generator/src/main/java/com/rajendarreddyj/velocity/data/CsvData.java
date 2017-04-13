package com.rajendarreddyj.velocity.data;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class CsvData {
    private int transactionId;
    private int amount;
    private String currency;
    private String executionDate;
    private String status;

    /**
     * @return the transactionId
     */
    public int getTransactionId() {
        return this.transactionId;
    }

    /**
     * @param transactionId
     *            the transactionId to set
     */
    public void setTransactionId(final int transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(final int amount) {
        this.amount = amount;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return this.currency;
    }

    /**
     * @param currency
     *            the currency to set
     */
    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    /**
     * @return the executionDate
     */
    public String getExecutionDate() {
        return this.executionDate;
    }

    /**
     * @param executionDate
     *            the executionDate to set
     */
    public void setExecutionDate(final String executionDate) {
        this.executionDate = executionDate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final String status) {
        this.status = status;
    }

}
