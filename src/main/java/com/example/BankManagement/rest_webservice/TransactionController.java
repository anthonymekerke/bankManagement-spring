package com.example.BankManagement.rest_webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankManagement.business.dto.TransactionBasicDTO;
import com.example.BankManagement.business.dto.TransactionFullDTO;
import com.example.BankManagement.business.service.ITransactionService;
import com.example.BankManagement.exception.BankManagementBusinessException;

@RestController
@RequestMapping(value="/transactions")
public class TransactionController {
    
    @Autowired
    private ITransactionService transactionService;

    @GetMapping("/{id}")
    public TransactionBasicDTO getTransactionId(@PathVariable(value="id") int id, Authentication authentication){
        try {
            return this.transactionService.readByIdAndClientLogin(id, authentication.getName());
        } catch (BankManagementBusinessException e) {
            return null;
        }
    }

    @PostMapping
    public ResponseEntity<?> postTransaction(@RequestBody TransactionFullDTO dto){
        try {
            return ResponseEntity.ok(transactionService.create(dto));
        } catch (BankManagementBusinessException e) {
            return null;
        }
    }
}
