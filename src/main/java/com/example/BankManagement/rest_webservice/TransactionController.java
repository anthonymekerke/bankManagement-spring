package com.example.BankManagement.rest_webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankManagement.business.dto.TransactionBasicDTO;
import com.example.BankManagement.business.dto.TransactionFullDTO;
import com.example.BankManagement.business.service.ITransactionService;

@RestController
@RequestMapping(value="/transactions")
public class TransactionController {
    
    @Autowired
    private ITransactionService transactionService;

    @GetMapping("/{id}")
    public TransactionBasicDTO getTransactionById(@PathVariable(value="id") int id){
        return this.transactionService.readById(id);
    }

    @PostMapping
    public ResponseEntity<?> postTransaction(@RequestBody TransactionFullDTO dto){
        return ResponseEntity.ok(transactionService.create(dto));
    }
}
