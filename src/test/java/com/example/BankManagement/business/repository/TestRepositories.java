package com.example.BankManagement.business.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import bankManagement.accountService.domain.Account;
import bankManagement.accountService.domain.Client;
import bankManagement.accountService.domain.Transaction;
import bankManagement.accountService.repository.IAccountRepository;
import bankManagement.accountService.repository.IClientRepository;
import bankManagement.accountService.repository.ITransactionRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class TestRepositories {

    @Autowired
    IClientRepository clientRepository;

    @Autowired
    IAccountRepository accountRepository;

    @Autowired
    ITransactionRepository transactionRepository;

    private Integer idClientTest = null;
    private Integer idAccountTest = null;
    private Integer idTransactionTest = null;

    @Test
    public void testInsertionAndDeletionAccount(){
        Client client = clientRepository.findById(this.idClientTest).orElse(null);
        Account account = new Account();
        account.setClient(client);
        account.setCreationDate(new Date());
        account.setIban("45769836428");
        account.setLabel("Account n°896742");
        account = accountRepository.save(account);
        assertNotNull(account);

        Integer idAccount = account.getId();
        accountRepository.deleteById(account.getId());
        account = accountRepository.findById(idAccount).orElse(null);
        assertNull(account);
    }

    @Test
    public void testUpdateAccount(){
        Account account = accountRepository.findById(idAccountTest).orElse(null);
        String oldIban = account.getIban();
        String newIban = "0987 6543 2109 8765";
        assertNotEquals(oldIban, newIban);

        account.setIban(newIban);
        account = accountRepository.save(account);
        assertEquals(newIban, account.getIban());
    }


    @Test
    @Transactional
    public void testGetTransactions(){
        Account account = accountRepository.findById(this.idAccountTest).orElse(null);
        List<Transaction> transactions = account.getTransactions();
        assertEquals(transactions.size(), 1);
        assertEquals(transactions.get(0).getId(), this.idTransactionTest);
    }
    
    @BeforeAll
    public void prepareEntitiesForTesting(){
        Client client = new Client();
        client.setAccessionDate(new Date());
        client.setFirstname("firstname");
        client.setLastname("lastname");
        client.setEmail("firstname.lastname@jmail.mock");
        client.setLogin("login");
        client.setPassword("password");
        
        client = clientRepository.save(client);
        this.idClientTest = client.getId();

        Account account = new Account();
        account.setCreationDate(new Date());
        account.setIban("1234 5678 9012 3456");
        account.setLabel("Account Label");
        account.setClient(client);

        account = accountRepository.save(account);
        this.idAccountTest = account.getId();

        Transaction transaction = new Transaction();
        transaction.setBalance(1000f);
        transaction.setPayment(1000f);
        transaction.setExecutionDate(new Date());
        transaction.setValueDate(new Date());
        transaction.setWording("wording of the transaction");
        transaction.setAccount(account);

        transaction = transactionRepository.save(transaction);
        this.idTransactionTest = transaction.getId();
    }

    @AfterAll
    public void deleteEntitiesForTesting(){
        if (!Objects.isNull(this.idAccountTest)) {
			this.accountRepository.deleteById(this.idAccountTest);
		}
		if (!Objects.isNull(this.idClientTest)) {
			this.clientRepository.deleteById(this.idClientTest);
		}
    }
}
