package ru.ivannikov.transfers.services;

import ru.ivannikov.transfers.dao.AccountDAO;
import ru.ivannikov.transfers.models.Account;
import ru.ivannikov.transfers.models.Money;
import ru.ivannikov.transfers.models.Transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class TransferService {
    private AccountDAO accountDAO;

    public TransferService() {
        accountDAO = new AccountDAO();
    }

    public String transfer(final int from, final int to, final double amount, final String currency) {
        Optional<Account> accountFrom = accountDAO.get(from);
        Optional<Account> accountTo = accountDAO.get(to);

        if (accountFrom.isPresent() && accountTo.isPresent()) {

            Account accFrom = accountFrom.get();
            Account accTo = accountTo.get();

            if (accFrom.getId() == accTo.getId()) {
                return "you can not transfer money to the same account";
            }

            List<Money> moneyListFrom = accFrom.getMoney();
            AtomicReference<Money> moneyFrom = new AtomicReference<>();

            AtomicBoolean readyToTransfer = new AtomicBoolean(false);

            moneyListFrom.forEach((e) -> {
                if (e.getCurrency().equals(currency) && amount <= e.getAmount()) {
                    moneyFrom.set(e);
                    readyToTransfer.set(true);
                }
            });

            if (!readyToTransfer.get()) {
                return "you don't have enough balance to transfer";
            }

            readyToTransfer.set(false);

            List<Money> moneyListTo = accTo.getMoney();
            AtomicReference<Money> moneyTo = new AtomicReference<>();

            moneyListTo.forEach((e) -> {
                if (e.getCurrency().equals(currency)) {
                    moneyTo.set(e);
                    readyToTransfer.set(true);
                }
            });

            if (!readyToTransfer.get()) {
                return "you can't have this currency";
            }

            this.moneyTransfer(moneyFrom.get(), moneyTo.get(), amount);

            Transfer newTransfer = new Transfer(accFrom, accTo, new Money(currency, amount));

            accountDAO.update(accFrom, newTransfer);
            accountDAO.update(accFrom, newTransfer);

            return "success";
        } else return "invalid account or accounts";

    }

    public List<Transfer> getHistory() {
        List<Account> all = accountDAO.getAll();

        List<Transfer> transfers = new ArrayList<>();

        all.forEach((e) -> e.getTransferHistory().forEach((t) -> {
            if (!transfers.contains(t)) {
                transfers.add(t);
            }
        }));

        return transfers;
    }

    private void moneyTransfer(final Money moneyFrom, final Money moneyTo, final double amount) {
        moneyFrom.setAmount(moneyFrom.getAmount() - amount);
        moneyTo.setAmount(moneyTo.getAmount() + amount);
    }
}
