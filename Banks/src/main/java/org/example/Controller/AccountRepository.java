package org.example.Controller;

import org.example.Model.Account;

import java.util.HashMap;
import java.util.Optional;

public class AccountRepository {
    private HashMap<Long, Account> allAccounts = new HashMap<>();
    private HashMap<Long, Long> accountsByClient = new HashMap<>();

    public void create(Account account, long clientId) {
        allAccounts.put(account.getId(), account);
        accountsByClient.put(clientId, account.getId());
    }

    public Optional<Account> findAccountByClientId(Long clientId) {
        return Optional.ofNullable(allAccounts.get(accountsByClient.get(clientId)));
    }
}
