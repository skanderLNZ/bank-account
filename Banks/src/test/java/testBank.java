import org.example.BankService;
import org.example.Model.Account;
import org.example.Model.Client;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.Assert.*;

public class testBank {

    private BankService bank = new BankService("Société Générale");

    @Before
    public void init() {
    }

    @Test
    public void shouldCreateClient() {
        Long clientId = bank.createClient("Doe", "John");
        Optional<Client> optClient = bank.getClient(clientId);
        assertTrue(optClient.isPresent());
        Client c = optClient.get();
        assertEquals("Doe", c.getName());
        assertEquals("John", c.getFirstName());
        Account ac = bank.getAccount(clientId).get();
        assertEquals(clientId, ac.getClientId());
    }

    @Test
    public void shouldDepositWhenOk() {
        Long clientId = bank.createClient("Bob", "Dupont");
        assertEquals(true, bank.deposit(clientId, 10));
        var operations = bank.consultOperations(clientId);
        assertEquals(1, operations.size());
    }

    @Test
    public void shouldWithdrawWhenKo(){
        Long clientId = bank.createClient("Doe", "John");
        bank.deposit(clientId,250);
        assertFalse(bank.withdraw(clientId,260));
        var operations = bank.consultOperations(clientId);
        assertEquals(1,operations.size());
    }

    @Test
    public void shouldDepositWhenNegativeAmount() {
        Long clientId = bank.createClient("Bob", "Dupont");
        assertEquals(false, bank.deposit(clientId, -10));
        assertEquals(0d, bank.getAccount(clientId).get().getBalance().doubleValue(), 0.001);
        var operations = bank.consultOperations(clientId);
        assertEquals(0, operations.size());

    }


}
