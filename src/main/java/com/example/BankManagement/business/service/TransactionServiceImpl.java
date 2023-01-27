package com.example.BankManagement.business.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankManagement.business.dto.TransactionBasicDTO;
import com.example.BankManagement.business.dto.TransactionFullDTO;
import com.example.BankManagement.business.entity.Transaction;
import com.example.BankManagement.business.repository.ITransactionRepository;
import com.example.BankManagement.exception.BankManagementBusinessException;
import com.example.BankManagement.util.AppConstants;
import com.example.BankManagement.util.DTOConverter;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    ITransactionRepository transactionRepository;

    @Override
    public TransactionBasicDTO readByIdAndClientLogin(int tr_id, String client_login) throws BankManagementBusinessException{
        Transaction entity = this.transactionRepository.findByIdAndAccount_Client_Login(tr_id, client_login).orElse(null);

        if(entity == null) {throw new BankManagementBusinessException("The Transaction n°"+tr_id+" is not owned by this client");}

        return DTOConverter.TransactionEntitytoBasicDTO(entity);
    }

    @Override
    public TransactionFullDTO create(TransactionFullDTO dto) throws BankManagementBusinessException {
        Transaction entity = getLastTransactionByAccountId(dto.getAccount().getId());
        float prevBalance = 0.0f, nextBalance;

        if(entity != null){prevBalance = entity.getBalance();}
        nextBalance = dto.getPayment() != null ? prevBalance + dto.getPayment() : prevBalance - dto.getWithdraw();
        dto.setBalance(nextBalance);

        dto.setExecutionDate(new Date());

        isValidTransaction(dto);

        entity = DTOConverter.TransactionFullDTOtoEntity(dto);
        entity = this.transactionRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    private Transaction getLastTransactionByAccountId(int account_id){
        return transactionRepository.findFirstByAccountIdOrderByValueDateDesc(account_id).orElse(null);
    }

    private void isValidTransaction(TransactionFullDTO dto) throws BankManagementBusinessException{
        //checkValues(dto);
        //checkDates(dto);
        //checkPrefix(dto);
    }

    private void checkValues(TransactionFullDTO dto) throws BankManagementBusinessException{
        if(dto.getPayment() == null && dto.getWithdraw() == null){throw new BankManagementBusinessException("Both 'payment' & 'withdraw' can't be null");}
        if(dto.getPayment() != null && dto.getWithdraw() != null){throw new BankManagementBusinessException("Both 'payment' & 'withdraw' can't be set");}

        if(dto.getPayment() != null){
            if(dto.getPayment() <= 0){throw new BankManagementBusinessException("amount is lower or equal to zero");}
        }
        if(dto.getWithdraw() != null){
            if(dto.getWithdraw() <= 0){throw new BankManagementBusinessException("amount is lower or equal to zero");}
        }
    }

    private void checkPrefix(TransactionFullDTO dto) throws BankManagementBusinessException{
        String prefixToCheck = dto.getWording().split(" ", 2)[0];
        for(String prefix: AppConstants.TransactionPrefix){
            if(prefix == prefixToCheck){
                return;
            }
        }
        throw new BankManagementBusinessException("Prefix of transaction is not correct");
    }

    private void checkDates(TransactionFullDTO dto) throws BankManagementBusinessException{
        if(dto.getExecutionDate().after(dto.getValueDate())){ 
            throw new BankManagementBusinessException("Value date can't be lower than execution date");
        }
    }
}