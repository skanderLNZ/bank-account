package org.example.Controller;

import org.example.Model.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OperationRepository {
    private HashMap<Long, Operation> allOperations = new HashMap<>();
    private HashMap<Long, List<Long>> operationsByAccount = new HashMap<>();
    final List<Long> emptyList = List.of(); // unmodifiable

    public void create(Operation op) {
        allOperations.put(op.getId(), op);
        if (operationsByAccount.get(op.getAccountId()) == null) {
            operationsByAccount.put(op.getAccountId(), new ArrayList<>());
        }
        operationsByAccount.get(op.getAccountId()).add(op.getId());
    }

    public List<Operation> findOperationsByAccountId(Long accountId) {
        List<Operation> lst = operationsByAccount.getOrDefault(accountId, emptyList).stream()
                .map(e -> allOperations.get(e)).collect(Collectors.toList());
        return lst;
    }

}
