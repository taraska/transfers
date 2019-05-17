package ru.ivannikov.transfers.models;

public class Transfer {
    private int from;
    private int to;
    private double fromBalance;
    private double toBalance;
    private Money money;

    public Transfer(Account from, Account to, Money money) {
        this.from = from.getId();
        this.to = to.getId();
        this.money = money;
        this.setFromBalance(from, money.getCurrency());
        this.setToBalance(to, money.getCurrency());
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

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public double getFromBalance() {
        return fromBalance;
    }

    public void setFromBalance(double fromBalance) {
        this.fromBalance = fromBalance;
    }

    public double getToBalance() {
        return toBalance;
    }

    public void setToBalance(double toBalance) {
        this.toBalance = toBalance;
    }

    private void setToBalance(Account to, String currency) {
        to.getMoney().forEach((e) -> {
            if (e.getCurrency().equals(currency)) {
                this.setToBalance(e.getAmount());
            }
        });
    }

    private void setFromBalance(Account from, String currency) {
        from.getMoney().forEach((e) -> {
            if (e.getCurrency().equals(currency)) {
                this.setFromBalance(e.getAmount());
            }
        });
    }
}
