package com.wissen.repository;


import com.wissen.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    public Users findOneByUsername(String s);
}
