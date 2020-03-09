package com.wissen.repository;

import com.wissen.model.SaleInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SaleInstanceRepository extends JpaRepository<SaleInstance,Integer> {
}
