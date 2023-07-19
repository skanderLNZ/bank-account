package org.example.Controller;

import org.example.Model.Client;

import java.util.HashMap;
import java.util.Optional;

public class ClientRepository {
    private HashMap<Long, Client> allClients = new HashMap<>();

    public void create(Client cl) {
        allClients.put(cl.getId(), cl);
    }

    public Optional<Client> find(Long id) {
        return Optional.ofNullable(allClients.get(id));
    }
}