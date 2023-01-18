package com.example.BankManagement.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankManagement.business.dto.TransactionBasicDTO;
import com.example.BankManagement.business.dto.TransactionFullDTO;
import com.example.BankManagement.business.entity.Transaction;
import com.example.BankManagement.business.repository.ITransactionRepository;
import com.example.BankManagement.util.DTOConverter;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    ITransactionRepository transactionRepository;

    @Override
    public TransactionBasicDTO readById(int id){
        Transaction entity;
        entity = this.transactionRepository.findById(id).orElse(null);
        if(entity == null) {return null;}
        return DTOConverter.TransactionEntitytoBasicDTO(entity);
    }

    @Override
    public TransactionFullDTO create(TransactionFullDTO dto) {
        Transaction entity;

        float prevBalance = getLastTransactionByAccountId(dto.getAccount().getId()).getBalance();
        float nextBalance = dto.getPayment() != null ? prevBalance + dto.getPayment() : prevBalance - dto.getWithdraw();
        dto.setBalance(nextBalance);

        entity = DTOConverter.TransactionFullDTOtoEntity(dto);
        entity = this.transactionRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    private Transaction getLastTransactionByAccountId(int account_id){
        return transactionRepository.findFirstByAccountIdOrderByValueDateDesc(account_id);
    }

    //////////////////////////////////////////
    // Implementation des regles de gestion //
    //////////////////////////////////////////
    /*
     * - date de valeur >= date d'execution
     * - date d'execution >= aujourd'hui
     * - payment == null & withdraw == null -> interdit
     * - payment != null & withdraw != null -> interdit
     * - libelle commence par: (PRLV | VIR | ...)
     */

    // private boolean checkDate(TransactionFullDTO transaction){
        
    //     if(transaction.getExecutionDate().before(transaction.getValueDate())){ 
    //         return false;
    //     }

    //     return true;
    // }

}