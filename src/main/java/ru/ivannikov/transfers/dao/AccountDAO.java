package ru.ivannikov.transfers.dao;

import ru.ivannikov.transfers.data.Data;
import ru.ivannikov.transfers.models.Account;
import ru.ivannikov.transfers.models.Transfer;

import java.util.List;
import java.util.Optional;

public class AccountDAO implements DAO<Account> {
    private final Data data = new Data();

    @Override
    public Optional<Account> get(final int id) {
        Optional<Account> optionalAccount = Optional.empty();
        for (Account account :
                Data.accountList) {
            if (account.getId() == id) {
                optionalAccount = Optional.of(account);
            }
        }

        return optionalAccount;
    }

    @Override
    public List<Account> getAll() {
        return Data.accountList;
    }

    @Override
    public void save(final Account account) {
        Data.accountList.add(account);
    }

    @Override
    public void update(Account account, Object value) {
        if (value instanceof Transfer) {
            account.setTransfer((Transfer) value);
        }
    }

    @Override
    public void delete(final Account account) {
        Data.accountList.remove(account);
    }
}
