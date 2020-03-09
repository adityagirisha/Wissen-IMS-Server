package com.wissen.service;

import com.wissen.model.*;

import com.wissen.repository.*;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("ImsService")
@Transactional
public class ImsServiceImpl implements ImsService {

    public OrderInstanceRepository orderInstanceRepository;
    public ProductRepository productRepository;
    public SaleInstanceRepository saleInstanceRepository;
    public OrderRepository orderRepository;
    private SaleRepository saleRepository;
    private AnalyticsRepository analyticsRepository;
    private NotificationsRepository notificationsRepository;
    private UsersRepository usersRepository;

    public ImsServiceImpl(UsersRepository usersRepository,NotificationsRepository notificationsRepository,AnalyticsRepository analyticsRepository,OrderInstanceRepository orderInstanceRepository, OrderRepository orderRepository, ProductRepository productRepository, SaleRepository saleRepository, SaleInstanceRepository saleInstanceRepository) {
        this.orderInstanceRepository = orderInstanceRepository;
        this.productRepository = productRepository;
        this.saleInstanceRepository = saleInstanceRepository;
        this.orderRepository = orderRepository;
        this.saleRepository = saleRepository;
        this.analyticsRepository=analyticsRepository;
        this.notificationsRepository=notificationsRepository;
        this.usersRepository=usersRepository;
    }

    //for app

    @Override
    public List<Product> getProducts() {
        List<Product> res = null;
        res = productRepository.findAll();
        if (res == null) return new ArrayList<>();
        return res;
    }

    @Override
    public List<OrderInstance> getOrderInstances() {
        List<OrderInstance> res = null;
        res = orderInstanceRepository.findAll();
        if (res == null) return new ArrayList<>();
        return res;
    }

    @Override
    public List<SaleInstance> getSaleInstances() {
        List<SaleInstance> res = null;
        res = saleInstanceRepository.findAll();
        if (res == null) return new ArrayList<>();
        return res;
    }

    @Override
    public List<Order> getOrders(){
        List<Order> res = null;
        res = orderRepository.findAll();
        if (res == null) return new ArrayList<>();
        return res;

    }

    @Override
    public List<Sale> getSales(){
        List<Sale> res = null;
        res = saleRepository.findAll();
        if (res == null) return new ArrayList<>();
        return res;
    }

    @Override
    public Product getProductById(int id) {
        Product res = null;
        res = productRepository.findOneById(id);
        return res;
    }

    @Override
    public Product getProductByName(String name) {
        Product product = null;
        product = productRepository.findOneByName(name);
        return product;
    }

    @Override
    public String checkIfAvailable(int id) {
        Product res = productRepository.findOneById(id);
        if (res != null) {
            if (res.getStatus()) return "true";
            return "false";
        }
        return "na";
    }

    @Override
    public boolean deleteProduct(int id) {
        Product product = null;
        product = productRepository.findOneById(id);
        if (product == null) return false;
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product productToUpdate = null;
        productToUpdate = productRepository.findOneById(id);
        if (productToUpdate == null) return null;
        if (product.getAmount() == 0) productToUpdate.setStatus(false);
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setAmount(product.getAmount());
        productToUpdate = productRepository.save(productToUpdate);
        return productToUpdate;
    }

    @Override
    public Product buyProduct(OrderInstance orderInstance) {
        Product res = null;
        if (orderInstance.getAmount() <= 0) return null;
        res = productRepository.findOneByName(orderInstance.getProductName());
        if (res != null) {
            res.setAmount(res.getAmount() + orderInstance.getAmount());
            if (res.getAmount() > 0) {
                res.setStatus(true);
                productRepository.save(res);
            }
            orderInstanceRepository.save(orderInstance);

        }
        return res;
    }

    @Override
    public Product sellProduct(SaleInstance saleInstance) {
        Product res = null;
        if (saleInstance.getAmount() <= 0) return res;

        res = productRepository.findOneByName(saleInstance.getProductName());
        if (res != null) {
            if (res.getAmount() >= saleInstance.getAmount()) {
                res.setAmount(res.getAmount() - saleInstance.getAmount());
            }
            if (res.getAmount() == 0) {
                res.setStatus(false);

            }
            productRepository.save(res);
            saleInstanceRepository.save(saleInstance);
            return res;
        }
        return res;
    }

    public void buyAndRecord(OrderInstance orderInstance) {
        Product res = null;
        res = productRepository.findOneByName(orderInstance.getProductName());
        if (res != null) {
            res.setAmount(res.getAmount() + orderInstance.getAmount());
            if (res.getAmount() > 0) {
                res.setStatus(true);
                productRepository.save(res);
            }
            orderInstanceRepository.save(orderInstance);
        }
        else{
            Product product=new Product(orderInstance.getProductId(), orderInstance.getProductName(), orderInstance.getProductCompany(), orderInstance.getCostPrice(),true, orderInstance.getAmount());
            product=productRepository.save(product);
            orderInstance.setProductId(product.getId());
            orderInstanceRepository.save(orderInstance);
            res=product;
        }
    }

    public Boolean saleValidation (SaleInstance saleInstance){
        Product res = null;
        if (saleInstance.getAmount() <= 0) return false;

        res = productRepository.findOneByName(saleInstance.getProductName());
//        System.out.println(res.getAmount()+"here");
        if (res != null) {
            if(res.getAmount()< saleInstance.getAmount()){
                return false;
            }
        }

        return true;
    }

    public void sellAndRecord(SaleInstance saleInstance){
        Product res = null;
        res = productRepository.findOneByName(saleInstance.getProductName());
        System.out.println(saleInstance);
        if (res.getAmount() >= saleInstance.getAmount()) {
            res.setAmount(res.getAmount() - saleInstance.getAmount());
        }
        if (res.getAmount() == 0) {
            res.setStatus(false);
        }
        productRepository.save(res);
        saleInstanceRepository.save(saleInstance);
    }

    @Override
    public Boolean performOrder(Order order){
        if(order.getEntries().size()==0) return false;
        double total = 0;
        for(OrderInstance orderInstance : order.getEntries()){
            if (orderInstance.getAmount() <= 0) return false;
        }
        for(OrderInstance orderInstance : order.getEntries()){
            this.buyAndRecord(orderInstance);
            total+= orderInstance.getCostPrice()* orderInstance.getAmount();
        }
        order.setTotal(total);
        Analytics expenditure=analyticsRepository.findOneByCol("Expenditure");
        expenditure.setVal(expenditure.getVal()+order.getTotal());
        analyticsRepository.save(expenditure);
        System.out.println(analyticsRepository.findOneByCol("Expenditure"));
        orderRepository.save(order);
        return true;
    }

    @Override
    public Boolean performSale(Sale sale) {
        System.out.println(sale);

        double total = 0;
        for (SaleInstance saleInstance : sale.getEntries()) {
            if (!saleValidation(saleInstance)) {
                return false;
            }
        }
        for (SaleInstance saleInstance : sale.getEntries()) {
            this.sellAndRecord(saleInstance);
            total += saleInstance.getSellingPrice() * saleInstance.getAmount();
        }
        sale.setTotal(total);
        saleRepository.save(sale);
        return true;
    }

    //for loading

    @Override
    public Boolean addProduct(Product product) {
        productRepository.save(product);
        return true;
    }

    @Override
    public void addOrder(OrderInstance orderInstance) {
        orderInstanceRepository.save(orderInstance);
    }

    @Override
    public void addSale(SaleInstance saleInstance) {
        saleInstanceRepository.save(saleInstance);
    }


    @Override
    public void saveAnalytics(Analytics analytics){
        analyticsRepository.save(analytics);
    }

    @Override
    public List<Analytics> getAnalytics(){
        return analyticsRepository.findAll();
    }

    @Override
    public  void saveNotification(Notifications notifications){
        notificationsRepository.save(notifications);
    }

    @Override
    public List<Notifications> getNotifications(){
        return notificationsRepository.findAll();
    }

    @Override
    public void saveUser(Users users) {
        usersRepository.save(users);

    }

    @Override
    public Boolean authenticateUser(String username, String password) {
        System.out.println(username+" "+password);
        Users res=null;
        res=usersRepository.findOneByUsername(username);
        if(res==null){


            return false;
        }
        else{
            if(!res.getPassword().equals(password)) {
                System.out.println(res.getPassword());
                return false;
            }
        }
        return true;

    }
}
