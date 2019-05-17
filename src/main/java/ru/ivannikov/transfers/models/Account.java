package ru.ivannikov.transfers.models;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int id;

    private String name;

    private String surname;

    private List<Money> money;

    private List<Transfer> transferHistory = new ArrayList<>();

    public Account(int id, String name, String surname, List<Money> money) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Money> getMoney() {
        return money;
    }

    public void setMoney(List<Money> money) {
        this.money = money;
    }

    public List<Transfer> getTransferHistory() {
        return transferHistory;
    }

    public void setTransfer(Transfer transfer) {
        this.transferHistory.add(transfer);
    }
}
