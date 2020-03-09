package com.wissen.bootstrap;

import com.wissen.model.*;

import com.wissen.repository.*;
import com.wissen.service.ImsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private final ProductRepository productRepository;
	private final SaleInstanceRepository saleInstanceRepository;
	private final OrderInstanceRepository orderInstanceRepository;
	private final OrderRepository orderRepository;
	private final AnalyticsRepository analyticsRepository;

	@Autowired
	ImsServiceImpl imsService;
	
	public DataLoader(AnalyticsRepository analyticsRepository, ImsServiceImpl imsService, OrderRepository orderRepository, ProductRepository productRepository, SaleInstanceRepository saleInstanceRepository, OrderInstanceRepository orderInstanceRepository) {
		this.productRepository = productRepository;
		this.saleInstanceRepository = saleInstanceRepository;
		this.orderInstanceRepository = orderInstanceRepository;
		this.orderRepository = orderRepository;
		this.imsService=imsService;
		this.analyticsRepository =analyticsRepository;

	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

// for initial database loading
//		imsService.addProduct( new Product(101,"iPhoneX","Apple",1000,true,100));
//		imsService.addProduct( new Product(102,"iPhone8","Apple",900,true,100));
//		imsService.addProduct( new Product(103,"iPhone7","Apple",800,true,150));
//		imsService.addProduct( new Product(104,"iPhone11","Apple",1250,true,200));
//		imsService.addProduct( new Product(105,"S9","Samsung",700,true,100));
//		imsService.addProduct( new Product(106,"S8","Samsung",650,true,100));
//		imsService.addProduct( new Product(107,"S7","Samsung",450,true,150));
//		imsService.addProduct( new Product(108,"S6","Samsung",400,true,200));
//		imsService.addProduct( new Product(109,"1+7","Oneplus",800,true,100));
//		imsService.addProduct( new Product(110,"1+6T","Oneplus",700,true,100));
//		imsService.addProduct( new Product(111,"1+6","Oneplus",500,true,150));
//		imsService.addProduct( new Product(112,"1+5T","Oneplus",250,true,200));
//
//		imsService.saveAnalytics(new Analytics("Expenditure",20000));
//		imsService.saveAnalytics(new Analytics("Sales",3000));
//		imsService.saveAnalytics(new Analytics("Capacity",69));
//		imsService.saveAnalytics(new Analytics("Max",100));
//		imsService.saveAnalytics(new Analytics("Capital",100000));
//
//		imsService.saveNotification(new Notifications(1,1,"tester1"));
//		imsService.saveNotification(new Notifications(2,2,"tester1"));
//		imsService.saveNotification(new Notifications(3,3,"tester1"));
//		imsService.saveNotification(new Notifications(4,1,"tester1"));
//
//		imsService.saveUser(new Users("root","toor"));

	}



}
