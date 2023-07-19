package org.example;

import org.example.Controller.AccountRepository;
import org.example.Controller.ClientRepository;
import org.example.Controller.OperationRepository;
import org.example.Model.Account;
import org.example.Model.Client;
import org.example.Model.Operation;
import org.example.Model.OperationType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public class BankService {

    private ClientRepository clientRepository = new ClientRepository();
    private AccountRepository accountRepository = new AccountRepository();
    private OperationRepository operationRepository = new OperationRepository();

    final List<Operation> emptyListOperation = List.of();

    public BankService(String sociétéGénérale) {
    }

    public Optional<Client> getClient(Long clientId) {
        return clientRepository.find(clientId);
    }

    public Optional<Account> getAccount(Long clientId) {
        return accountRepository.findAccountByClientId(clientId);
    }

    public Long createClient(String lastname, String firstName) {
        var cl = new Client(lastname, firstName);
        var ac = new Account(cl.getId());
        accountRepository.create(ac, cl.getId());
        clientRepository.create(cl);
        return cl.getId();
    }

    public boolean deposit(Long clientId, double amount) {
        return doOperation(clientId, amount, OperationType.Deposit);
    }

    public boolean withdraw(Long clientId, double amount) {
        return doOperation(clientId, amount, OperationType.Withdraw);
    }

    public boolean doOperation(Long clientId, double amount, OperationType type){
        if (amount < 0) {
            return false;
        }
        if (clientRepository.find(clientId).isEmpty()) {
            return false;
        }
        Account account = accountRepository.findAccountByClientId(clientId).get();
        var time = ZonedDateTime.now();
        var newBalance = new BigDecimal(0);
        if (type == OperationType.Withdraw) {
            newBalance = account.getBalance().subtract(BigDecimal.valueOf(amount));
        } else {
            newBalance = account.getBalance().add(BigDecimal.valueOf(amount));
        }
        if (newBalance.doubleValue() < 0) {
            return false;
        }
        account.setBalance(newBalance);
        addOperation(amount, account, time, newBalance, type);
        return true;
    }

    private void addOperation(double amount, Account ac, ZonedDateTime time, BigDecimal newBalance,
                              OperationType type) {
        var accountId = ac.getId();
        var op = new Operation(accountId, BigDecimal.valueOf(amount), newBalance, time, type);
        operationRepository.create(op);
    }

    public List<Operation> consultOperations(Long clientId) {
        if (clientRepository.find(clientId).isEmpty()) {
            return emptyListOperation;
        }
        var ac = accountRepository.findAccountByClientId(clientId).get();
        return operationRepository.findOperationsByAccountId(ac.getId());
    }
}
