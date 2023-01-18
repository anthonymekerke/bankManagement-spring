package com.example.BankManagement.business.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BankManagement.business.entity.IEntity;

public interface IService <Entity extends IEntity, BasicDTO, MediumDTO extends BasicDTO, FullDTO extends BasicDTO, Repository extends JpaRepository<Entity, Integer>> {
    
    Entity create(MediumDTO dto);
    Entity update(MediumDTO dto);

    void deleteById(int id);
    FullDTO findById(int id);
    //<T extends BasicDTO> T findById(int id, Class<T> type);
    boolean ifEntityExistById(int id);

    List<BasicDTO> findAll();
}
