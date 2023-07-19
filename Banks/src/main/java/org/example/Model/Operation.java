package org.example.Model;

import org.example.Utils.Sequence;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class Operation {
    private Long id;
    private Long accountId;
    private BigDecimal amount;
    private BigDecimal balance;
    private ZonedDateTime date;
    private OperationType type;

    public Operation(Long accountId, BigDecimal amount, BigDecimal balance, ZonedDateTime date, OperationType type) {
        setAccountId(accountId);
        setAmount(amount);
        setBalance(balance);
        setDate(date);
        setType(type);
        setId(Sequence.next());
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(OperationType type) {
        this.type = type;
    }
}