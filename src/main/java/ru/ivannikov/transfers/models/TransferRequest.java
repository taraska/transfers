package ru.ivannikov.transfers.models;

public class TransferRequest {
    private int from;
    private int to;
    private double amount;
    private String currency;

    public TransferRequest(int from, int to, double amount, String currency) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.currency = currency;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
