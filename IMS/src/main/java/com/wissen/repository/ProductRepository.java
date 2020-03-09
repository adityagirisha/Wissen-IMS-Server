package com.wissen.repository;

import com.wissen.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface ProductRepository  extends JpaRepository<Product,Integer> {

    public Product findOneByName(String s);

    public Product findOneById(Integer id);

}
