package com.wissen.repository;

import com.wissen.model.Analytics;
import com.wissen.model.Notifications;
import com.wissen.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnalyticsRepository extends JpaRepository<Analytics,Integer> {

    public Analytics findOneByCol(String col);

}
