package com.wissen.service;

import com.wissen.model.*;

import java.util.List;

public interface ImsService {
    List<Product> getProducts();

    List<OrderInstance> getOrderInstances();

    List<SaleInstance> getSaleInstances();

    Product getProductById(int id);

    Product getProductByName(String name);

    String checkIfAvailable(int id);

    Product buyProduct(OrderInstance orderInstance);

    Product sellProduct(SaleInstance saleInstance) throws Exception;

    boolean deleteProduct(int id);

    Product updateProduct(int id, Product product);

    Boolean addProduct(Product product);

    void addOrder(OrderInstance orderInstance);

    void addSale(SaleInstance saleInstance);

    List<Order> getOrders();

    Boolean performOrder(Order order);

    List<Sale> getSales();

    Boolean performSale(Sale sale);

    void saveAnalytics(Analytics analytics);

    List<Analytics> getAnalytics();

    void saveNotification(Notifications notifications);

    List<Notifications> getNotifications();


    void saveUser(Users users);


    Boolean authenticateUser(String username, String password);
}
