package ru.ivannikov.transfers.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.ivannikov.transfers.TestData;
import ru.ivannikov.transfers.dao.AccountDAO;
import ru.ivannikov.transfers.data.Data;
import ru.ivannikov.transfers.models.Account;
import ru.ivannikov.transfers.models.Money;
import ru.ivannikov.transfers.models.Transfer;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.fail;

public class TransferServiceTest {
    private final AccountDAO accountDAO = new AccountDAO();
    private final TransferService transferService = new TransferService();

    @Before
    public void init() {
        final TestData testData = new TestData();
        Data.accountList = TestData.accountList;
    }

    @Test
    public void transferTestOk() {
        String msg = transferService.transfer(1, 2, 10, "USD");
        Assert.assertEquals(msg, "success");
    }

    @Test
    public void transferTestExceptionSameAccount() {
        String msg = transferService.transfer(1, 1, 10, "USD");
        Assert.assertEquals(msg, "you can not transfer money to the same account");
    }

    @Test
    public void transferTestExceptionBalance() {
        String msg = transferService.transfer(1, 2, 1000000, "USD");
        Assert.assertEquals(msg, "you don't have enough balance to transfer");
    }

    @Test
    public void transferTestExceptionCurrency() {
        String msg = transferService.transfer(1, 4, 10, "RUB");
        Assert.assertEquals(msg, "you can't have this currency");
    }

    @Test
    public void transferTestExceptionInvalidAccount() {
        String msg = transferService.transfer(1, 100, 1000000, "CAD");
        Assert.assertEquals(msg, "invalid account or accounts");
    }

    @Test
    public void getHistoryTestOk() {
        Optional<Account> optionalAccount = accountDAO.get(1);
        Optional<Account> optionalAccountSecond = accountDAO.get(2);

        if (optionalAccount.isPresent() && optionalAccountSecond.isPresent()) {
            Money money = new Money("USD", 100);
            Transfer transfer = new Transfer(optionalAccount.get(), optionalAccountSecond.get(), money);
            accountDAO.update(optionalAccount.get(), transfer);

            List<Transfer> history = transferService.getHistory();
            if (history.contains(transfer)) {
                Assert.assertTrue(true);
            } else fail();
        } else fail();
    }
}
