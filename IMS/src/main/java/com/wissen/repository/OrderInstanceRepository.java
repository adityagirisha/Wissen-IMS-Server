package com.wissen.repository;

import com.wissen.model.OrderInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderInstanceRepository extends JpaRepository<OrderInstance,Integer> {
}
