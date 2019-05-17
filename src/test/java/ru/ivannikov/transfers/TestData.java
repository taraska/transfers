package ru.ivannikov.transfers;

import ru.ivannikov.transfers.models.Account;
import ru.ivannikov.transfers.models.Money;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static List<Account> accountList = new ArrayList<>();

    public TestData() {
        Money usdMoney = new Money("USD", 1000);
        Money usdMoney2 = new Money("USD", 2000);
        Money usdMoney3 = new Money("USD", 3000);
        Money usdMoney4 = new Money("USD", 40000);

        Money rubMoney = new Money("RUB", 1000);
        Money rubMoney2 = new Money("RUB", 2000);

        ArrayList<Money> moneyArrayList = new ArrayList<>();
        moneyArrayList.add(usdMoney);
        moneyArrayList.add(rubMoney);

        ArrayList<Money> moneyArrayList2 = new ArrayList<>();
        moneyArrayList2.add(usdMoney2);
        moneyArrayList2.add(rubMoney2);

        ArrayList<Money> moneyArrayList3 = new ArrayList<>();
        moneyArrayList3.add(usdMoney3);

        ArrayList<Money> moneyArrayList4 = new ArrayList<>();
        moneyArrayList4.add(usdMoney4);

        Account account = new Account(1, "test1", "1test", moneyArrayList);
        Account account2 = new Account(2, "test2", "2test", moneyArrayList2);
        Account account3 = new Account(3, "test3", "3test", moneyArrayList3);
        Account account4 = new Account(4, "test4", "4test", moneyArrayList4);

        accountList.add(account);
        accountList.add(account2);
        accountList.add(account3);
        accountList.add(account4);
    }
}
