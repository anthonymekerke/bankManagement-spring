package com.example.BankManagement.business.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankManagement.business.dto.TransactionBasicDTO;
import com.example.BankManagement.business.dto.TransactionFullDTO;
import com.example.BankManagement.business.entity.Transaction;
import com.example.BankManagement.business.repository.ITransactionRepository;
import com.example.BankManagement.exception.UnauthorizedException;
import com.example.BankManagement.util.DTOConverter;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    ITransactionRepository transactionRepository;

    @Override
    public TransactionBasicDTO readByIdAndClientLogin(int tr_id, String client_login) throws UnauthorizedException{
        Transaction entity = this.transactionRepository.findByIdAndAccount_Client_Login(tr_id, client_login).orElse(null);
        if(entity == null) {throw new UnauthorizedException("The Transaction["+tr_id+"] is not owned by this client or doesn't exist");}
        return DTOConverter.TransactionEntitytoBasicDTO(entity);
    }

    @Override
    public TransactionFullDTO create(TransactionFullDTO dto, String client_login) throws UnauthorizedException {
        int account_id = dto.getAccount().getId();
        Transaction entity = getLastTransactionByAccountId(account_id);
        float prevBalance = 0.0f, nextBalance;

        ///////////////////////////////////////////////
        // Check if connected client own the account //
        ///////////////////////////////////////////////
        boolean exist = transactionRepository.existsByAccount_IdAndAccount_Client_Login(account_id, client_login);
        if(!exist){
            throw new UnauthorizedException("The Account["+account_id+"] is not owned by connected client");
        }

        ///////////////////////////////////////////
        // Compute field executionDate & balance //
        ///////////////////////////////////////////
        if(entity != null){prevBalance = entity.getBalance();}
        nextBalance = dto.getPayment() != null ? prevBalance + dto.getPayment() : prevBalance - dto.getWithdraw();
        dto.setBalance(nextBalance);
        dto.setExecutionDate(new Date());

        ///////////////////////////////////////////
        // convert DTO to entity & save it in DB //
        ///////////////////////////////////////////
        entity = DTOConverter.TransactionFullDTOtoEntity(dto);
        entity = this.transactionRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    /*
     * Issue In case of value date in future
     * Maybe replace with execution date ?
     */
    private Transaction getLastTransactionByAccountId(int account_id){
        return transactionRepository.findFirstByAccountIdOrderByValueDateDesc(account_id).orElse(null);
    }
}