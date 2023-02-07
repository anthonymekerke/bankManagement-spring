package com.example.BankManagement.rest_webservice;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.BankManagement.business.dto.TransactionBasicDTO;
import com.example.BankManagement.business.dto.TransactionFullDTO;
import com.example.BankManagement.business.service.ITransactionService;

@RestController
@RequestMapping(value="/transactions")
public class TransactionController {
    
    @Autowired
    private ITransactionService transactionService;

    @GetMapping("/{id}")
    public TransactionBasicDTO getTransactionId(@PathVariable(value="id") int id, Authentication authentication) {
        return this.transactionService.readByIdAndClientLogin(id, authentication.getName());
    }

    @PostMapping
    public ResponseEntity<?> postTransaction(@Valid @RequestBody TransactionFullDTO dto, Authentication authentication) {
        dto = transactionService.create(dto, authentication.getName());

        /*
         * Return a response entity of status 201 (created succesfully).
         * This append the URI of the new resource created (ex: /transactions/5)
         * in the header of the response with the "location" name
         */
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(dto.getId())
            .toUri(); 
        return ResponseEntity.created(location).build();
    }
}
