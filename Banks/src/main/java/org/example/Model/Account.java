package org.example.Model;

import org.example.Utils.Sequence;

import java.math.BigDecimal;



enum AccountType {
    Courant, Epargne
};

public class Account {
    private Long id;
    private Long clientId;
    private AccountType type;
    private BigDecimal balance;

    public Account(Long clientId) {
        setClientId(clientId);
        setType(AccountType.Courant);
        setBalance(new BigDecimal(0));
        setId(Sequence.next());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

