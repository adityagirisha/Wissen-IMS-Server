package com.wissen.controller;

import com.wissen.model.*;

import com.wissen.service.ImsService;
import com.wissen.service.ImsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@Transactional
public class Controller {

    @Autowired
    private final ImsService imsService;

    public Controller(ImsServiceImpl imsService) {
        this.imsService = imsService;
    }

    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public ResponseEntity getProducts(Model model) {
        return ResponseEntity.accepted().body(imsService.getProducts());
    }

    @RequestMapping(value = "/ordersHistory" ,method=RequestMethod.GET)
    public ResponseEntity getOrderBill(){
        return ResponseEntity.accepted().body(imsService.getOrders());
    }

    @RequestMapping(value = "/orderInstanceHistory", method = RequestMethod.GET)
    public ResponseEntity getOrders(Model model) {
        return ResponseEntity.accepted().body(imsService.getOrderInstances());
    }

    @RequestMapping(value = "/salesHistory" ,method = RequestMethod.GET)
    public  ResponseEntity getSaleBill(){
        return ResponseEntity.accepted().body(imsService.getSales());
    }

    @RequestMapping(value = "/salesInstanceHistory", method = RequestMethod.GET)
    public ResponseEntity getSales(Model model) {
        return ResponseEntity.accepted().body(imsService.getSaleInstances());
    }

    @RequestMapping(value = "/findProduct/{id}", method = RequestMethod.GET)
    public ResponseEntity getProductById(Model model, @PathVariable Integer id) {
        if (id < 0) return new ResponseEntity((HttpStatus.BAD_REQUEST));
        System.out.println(id);
        Product res = null;
        res = imsService.getProductById(id);
        if (res == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return ResponseEntity.accepted().body(res);
    }

    @RequestMapping(value = "/findProductByName/{name}", method = RequestMethod.GET)
    public ResponseEntity getProductByName(Model model, @PathVariable String name) {
        Product product = null;
        product = imsService.getProductByName(name);
        if (product == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return ResponseEntity.accepted().body(product);
    }

    @RequestMapping(value = "/checkIfAvailable/{id}", method = RequestMethod.GET)
    public ResponseEntity checkIfAvailable(Model model, @PathVariable Integer id) {
        if (id <= 0) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        String res = imsService.checkIfAvailable(id);
        if (res.equals("true")) return ResponseEntity.accepted().body(true);
        if (res.equals("false")) return ResponseEntity.accepted().body(false);

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/buyProduct", method = RequestMethod.POST)
    public ResponseEntity buyProduct(Model model, @RequestBody OrderInstance orderInstance) {
        if (orderInstance.getAmount() <= 0) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Product res = imsService.buyProduct(orderInstance);
        if (res != null)
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/sellProduct", method = RequestMethod.POST)
    public ResponseEntity sellProduct(Model model, @RequestBody SaleInstance saleInstance) {
        if (saleInstance.getAmount() <= 0) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Product res = null;
        try {
            imsService.sellProduct(saleInstance);
        }
        catch (Exception e){

        }
        if (res != null)
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        boolean deleted = imsService.deleteProduct(id);
        if (deleted) return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(value = "/updateProduct/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        if (id <= 0) return new ResponseEntity(HttpStatus.NOT_FOUND);
        Product res = imsService.updateProduct(id, product);
        if (res == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return ResponseEntity.accepted().body(res);
    }

    //beta

    @RequestMapping(value ="/placeOrder",method=RequestMethod.POST)
    public boolean placeOrder(@RequestBody Order order){
        return imsService.performOrder(order);
    }

    @RequestMapping(value = "/performSale", method = RequestMethod.POST)
    public boolean placeSale(@RequestBody Sale sale) {
        return imsService.performSale(sale);
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public boolean addProduct(@RequestBody Product product) {
        return imsService.addProduct(product);
    }

    @RequestMapping(value = "/getAnalytics", method = RequestMethod.GET)
    public List<Analytics> getAnalytics(){
        return imsService.getAnalytics();
    }

    @RequestMapping(value = "/getNotifications", method = RequestMethod.GET)
    public List<Notifications> getNotifications(){
        return imsService.getNotifications();
    }

    @RequestMapping(value = "/authenticateUser", method = RequestMethod.POST)
    public Boolean authenticateUser(@RequestBody Users users){
        return imsService.authenticateUser(users.getUsername(),users.getPassword());
    }
}
