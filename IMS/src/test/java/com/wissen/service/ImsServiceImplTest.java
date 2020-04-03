package com.wissen.service;

import com.wissen.model.*;
import com.wissen.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
public class ImsServiceImplTest {

    @Mock
    OrderInstanceRepository orderInstanceRepository;
    @Mock
    SaleInstanceRepository saleInstanceRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    OrderRepository orderRepository;
    @Mock
    SaleRepository saleRepository;
    @Mock
    AnalyticsRepository analyticsRepository;
    @Mock
    NotificationsRepository notificationsRepository;
    @InjectMocks
    private ImsServiceImpl imsService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProductsTestWhenNull() {
        List<Product> res = null;
        when(productRepository.findAll()).thenReturn(res);
        List<Product> ls = imsService.getProducts();
        assertEquals(0, ls.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void getProductsTestWhenPresent() {
        List<Product> res = new ArrayList<>();
        res.add(new Product(1, "name1", "company1", 100, true, 100));
        res.add(new Product(2, "name2", "company2", 100, false, 0));
        res.add(new Product(3, "name3", "company3", 100, true, 100));

        when(productRepository.findAll()).thenReturn(res);
        List<Product> ls = imsService.getProducts();
        assertEquals(3, ls.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void getOrdersWhenNull() {
        List<OrderInstance> res = null;
        when(orderInstanceRepository.findAll()).thenReturn(res);
        List<OrderInstance> ls = imsService.getOrderInstances();
        assertEquals(0, ls.size());
        verify(orderInstanceRepository, times(1)).findAll();
    }

    @Test
    public void getOrdersWhenPresent() {
        List<OrderInstance> res = new ArrayList<>();
        res.add(new OrderInstance(1, 1, "name1", "company1", 100, 100));
        res.add(new OrderInstance(2, 2, "name2", "company2", 100, 0));
        res.add(new OrderInstance(3, 2, "name3", "company3", 100, 100));

        when(orderInstanceRepository.findAll()).thenReturn(res);
        List<OrderInstance> ls = imsService.getOrderInstances();
        assertEquals(3, ls.size());
        verify(orderInstanceRepository, times(1)).findAll();
    }

    @Test
    public void getSalesWhenNull() {
        List<SaleInstance> res = null;
        when(saleInstanceRepository.findAll()).thenReturn(res);
        List<SaleInstance> ls = imsService.getSaleInstances();
        assertEquals(0, ls.size());
        verify(saleInstanceRepository, times(1)).findAll();
    }

    @Test
    public void getSalesWhenPresent() {
        List<SaleInstance> res = new ArrayList<>();
        res.add(new SaleInstance(1, 1, "name1", "company1", 100, 100));
        res.add(new SaleInstance(2, 2, "name2", "company2", 100, 0));
        res.add(new SaleInstance(3, 2, "name3", "company3", 100, 100));

        when(saleInstanceRepository.findAll()).thenReturn(res);
        List<SaleInstance> ls = imsService.getSaleInstances();
        assertEquals(3, ls.size());
        verify(saleInstanceRepository, times(1)).findAll();
    }

    @Test
    public void getProductByIdWhenNull() {
        Product res = null;
        when(productRepository.findOneById(1)).thenReturn(res);
        assertNull(imsService.getProductById(1));
        verify(productRepository, times(1)).findOneById(Mockito.anyInt());
    }

    @Test
    public void getProductByIdWhenAbsent() {
        Product res = new Product(1, "name1", "company1", 100, true, 100);
        when(productRepository.findOneById(1)).thenReturn(res);
        assertNull(imsService.getProductById(2));
        verify(productRepository, times(1)).findOneById(Mockito.anyInt());
    }

    @Test
    public void getProductByIdWhenPresent() {
        Product res = new Product(1, "name1", "company1", 100, true, 100);
        when(productRepository.findOneById(1)).thenReturn(res);
        Product test = imsService.getProductById(1);
        assertNotNull(test);
        assertEquals(1, test.getId());
        verify(productRepository, times(1)).findOneById(Mockito.anyInt());
    }

    @Test
    public void getProductByNameWhenNull() {
        Product res = null;
        when(productRepository.findOneByName("name1")).thenReturn(res);
        assertNull(imsService.getProductByName("name1"));
        verify(productRepository, times(1)).findOneByName(Mockito.anyString());
    }

    @Test
    public void getProductByNameWhenAbsent() {
        Product res = new Product(1, "name1", "company1", 100, true, 100);
        when(productRepository.findOneByName("name1")).thenReturn(res);
        assertNull(imsService.getProductByName("name2"));
        verify(productRepository, times(1)).findOneByName(Mockito.anyString());
    }

    @Test
    public void getProductByNameWhenPresent() {
        Product res = new Product(1, "name1", "company1", 100, true, 100);
        when(productRepository.findOneByName("name1")).thenReturn(res);
        Product test = imsService.getProductByName("name1");
        assertNotNull(test);
        assertEquals("name1", test.getName());
        verify(productRepository, times(1)).findOneByName(Mockito.anyString());
    }

    @Test
    public void checkIfAvailableWhenAbsent() {
        Product res = null;
        when(productRepository.findOneById(1)).thenReturn(res);
        String test = imsService.checkIfAvailable(1);
        assertEquals("na", test);
        verify(productRepository, times(1)).findOneById(Mockito.anyInt());
    }

    @Test
    public void checkIfAvailableWhenPresentAndAvailable() {
        Product res = new Product(1, "name1", "company1", 100, true, 100);
        when(productRepository.findOneById(1)).thenReturn(res);
        String test = imsService.checkIfAvailable(1);
        assertEquals("true", test);
        verify(productRepository, times(1)).findOneById(Mockito.anyInt());
    }

    @Test
    public void checkIfAvailableWhenPresentAndUnavailable() {
        Product res = new Product(1, "name1", "company1", 100, false, 100);
        when(productRepository.findOneById(1)).thenReturn(res);
        String test = imsService.checkIfAvailable(1);
        assertEquals("false", test);
        verify(productRepository, times(1)).findOneById(Mockito.anyInt());
    }

    @Test
    public void buyProductWhenAvailable() {

        OrderInstance orderInstance = new OrderInstance(1, 1, "name1", "company1", 100, 100);
        Product product = new Product(1, "name1", "company1", 100, true, 100);

        when(productRepository.findOneByName("name1")).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(orderInstanceRepository.save(orderInstance)).thenReturn(orderInstance);

        Product res = imsService.buyProduct(orderInstance);
        assertNotNull(res);

        verify(orderInstanceRepository, times(1)).save(Mockito.any(OrderInstance.class));
        verify(productRepository, times(1)).save(Mockito.any(Product.class));
        verify(productRepository, times(1)).findOneByName(Mockito.anyString());

    }

    @Test
    public void buyProductWhenNull() {

        OrderInstance orderInstance = new OrderInstance(1, 1, "name1", "company1", 100, 100);
        Product product = null;

        when(productRepository.findOneByName("name1")).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(orderInstanceRepository.save(orderInstance)).thenReturn(orderInstance);

        Product res = imsService.buyProduct(orderInstance);
        assertNull(res);

        verify(orderInstanceRepository, times(0)).save(Mockito.any(OrderInstance.class));
        verify(productRepository, times(0)).save(Mockito.any(Product.class));
        verify(productRepository, times(1)).findOneByName(Mockito.anyString());

    }

    @Test
    public void buyProductWhenUnavailable() {

        OrderInstance orderInstance = new OrderInstance(1, 1, "name1", "company1", 100, 0);
        Product product = new Product(1, "name1", "company1", 100, true, 100);

        when(productRepository.findOneByName("name1")).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(orderInstanceRepository.save(orderInstance)).thenReturn(orderInstance);

        Product res = imsService.buyProduct(orderInstance);
        assertNull(res);

        verify(orderInstanceRepository, times(0)).save(Mockito.any(OrderInstance.class));
        verify(productRepository, times(0)).save(Mockito.any(Product.class));
        verify(productRepository, times(0)).findOneByName(Mockito.anyString());


    }

    @Test
    public void sellProductWhenAvailable() {

        SaleInstance saleInstance = new SaleInstance(1, 1, "name1", "company1", 100, 90);
        Product product = new Product(1, "name1", "company1", 100, true, 100);

        when(productRepository.findOneByName("name1")).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(saleInstanceRepository.save(saleInstance)).thenReturn(saleInstance);

        Product res = imsService.sellProduct(saleInstance);
        assertNotNull(res);

        verify(saleInstanceRepository, times(1)).save(Mockito.any(SaleInstance.class));
        verify(productRepository, times(1)).save(Mockito.any(Product.class));
        verify(productRepository, times(1)).findOneByName("name1");

    }

    @Test
    public void sellProductWhenAvailableAndToggleStatus() {

        SaleInstance saleInstance = new SaleInstance(1, 1, "name1", "company1", 100, 100);
        Product product = new Product(1, "name1", "company1", 100, true, 100);

        when(productRepository.findOneByName("name1")).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(saleInstanceRepository.save(saleInstance)).thenReturn(saleInstance);

        Product res = imsService.sellProduct(saleInstance);
        assertEquals(false, res.getStatus());
        assertNotNull(res);

        verify(saleInstanceRepository, times(1)).save(Mockito.any(SaleInstance.class));
        verify(productRepository, times(1)).save(Mockito.any(Product.class));
        verify(productRepository, times(1)).findOneByName(Mockito.anyString());

    }

    @Test
    public void sellProductWhenNull() {

        SaleInstance saleInstance = new SaleInstance(1, 1, "name1", "company1", 100, 100);
        Product product = null;

        when(productRepository.findOneByName("name1")).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(saleInstanceRepository.save(saleInstance)).thenReturn(saleInstance);

        Product res = imsService.sellProduct(saleInstance);
        assertNull(res);

        verify(saleInstanceRepository, times(0)).save(Mockito.any(SaleInstance.class));
        verify(productRepository, times(0)).save(Mockito.any(Product.class));
        verify(productRepository, times(1)).findOneByName(Mockito.anyString());

    }

    @Test
    public void sellProductWhenUnavailable() {

        SaleInstance saleInstance = new SaleInstance(1, 1, "name1", "company1", 100, 0);
        Product product = new Product(1, "name1", "company1", 100, true, 100);

        when(productRepository.findOneByName("name1")).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(saleInstanceRepository.save(saleInstance)).thenReturn(saleInstance);

        Product res = imsService.sellProduct(saleInstance);
        assertNull(res);

        verify(saleInstanceRepository, times(0)).save(Mockito.any(SaleInstance.class));
        verify(productRepository, times(0)).save(Mockito.any(Product.class));
        verify(productRepository, times(0)).findOneByName(Mockito.anyString());

    }

    @Test
    public void deleteProductWhenPresent() {

        Product toDelete = new Product(1, "name1", "company1", 100, false, 100);
        when(productRepository.findOneById(1)).thenReturn(toDelete);
        imsService.deleteProduct(1);
        verify(productRepository, times(1)).findOneById(Mockito.anyInt());
        verify(productRepository, times(1)).deleteById(Mockito.anyInt());
    }

    @Test
    public void deleteProductNotNull() {

        Product toDelete = null;
        when(productRepository.findOneById(1)).thenReturn(toDelete);
        imsService.deleteProduct(1);
        verify(productRepository, times(1)).findOneById(Mockito.anyInt());
        verify(productRepository, times(0)).deleteById(Mockito.anyInt());

    }

    @Test
    public void updateProductWhenNull() {

        Product toUpdate = null;
        Product afterUpdate = new Product(1, "name1", "company1", 99, false, 0);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(afterUpdate);
        when(productRepository.findOneById(1)).thenReturn(toUpdate);
        Product res = imsService.updateProduct(1, afterUpdate);
        assertNull(res);
        verify(productRepository, times(0)).save(Mockito.any(Product.class));
        verify(productRepository, times(1)).findOneById(Mockito.anyInt());

    }

    @Test
    public void updateProductWhenNotNullAndAvailable() {

        Product toUpdate = new Product(1, "name1", "company1", 100, true, 100);
        Product afterUpdate = new Product(1, "name1", "company1", 99, true, 99);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(afterUpdate);
        when(productRepository.findOneById(1)).thenReturn(toUpdate);
        Product res = imsService.updateProduct(1, afterUpdate);
        assertEquals("name1", res.getName());
        assertEquals(99, res.getPrice(), 0.001);
        assertEquals(99, res.getAmount());
        verify(productRepository, times(1)).save(Mockito.any(Product.class));
        verify(productRepository, times(1)).findOneById(Mockito.anyInt());

    }

    @Test
    public void updateProductWhenNotNullAndNotAvailable() {

        Product toUpdate = new Product(1, "name1", "company1", 100, true, 100);
        Product afterUpdate = new Product(1, "name1", "company1", 99, false, 0);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(afterUpdate);
        when(productRepository.findOneById(1)).thenReturn(toUpdate);
        Product res = imsService.updateProduct(1, afterUpdate);
        assertEquals("name1", res.getName());
        assertEquals(99, res.getPrice(), 0.001);
        assertEquals(0, res.getAmount());
        assertEquals(false, res.getStatus());
        verify(productRepository, times(1)).save(Mockito.any(Product.class));
        verify(productRepository, times(1)).findOneById(Mockito.anyInt());

    }

    @Test
    public void performOrderWhenValid() {

        List<OrderInstance> ls =new ArrayList<>();
        OrderInstance o1= new OrderInstance( 1, "name1", "company1", 100, 100);
        OrderInstance o2= new OrderInstance(2, "name2", "company2", 100, 100);
        Product p1=new Product(1, "name1", "company1", 100, true, 100);
        Product p2=new Product(2, "name2", "company2", 100, true, 100);
        Order order = new Order(new ArrayList<>(),100,"test");
        order.setEntries(ls);
        order.addEntry(o1);
        order.addEntry(o2);

        when(productRepository.findOneByName("name1")).thenReturn(p1);
        when(productRepository.findOneByName("name2")).thenReturn(p2);
        when(productRepository.save(p1)).thenReturn(p1);
        when(productRepository.save(p2)).thenReturn(p2);
        when(orderInstanceRepository.save(o1)).thenReturn(o1);
        when(orderInstanceRepository.save(o2)).thenReturn(o2);
        when(orderRepository.save(order)).thenReturn(order);
        when(analyticsRepository.findOneByCol("Expenditure")).thenReturn(new Analytics("Expenditure",20000));
        assertTrue(imsService.performOrder(order));
        verify(orderRepository,times(1)).save(Mockito.any(Order.class));
        verify(productRepository,times(order.getEntries().size())).findOneByName(anyString());
        verify(productRepository,times(order.getEntries().size())).save(Mockito.any(Product.class));
        verify(orderInstanceRepository,times(order.getEntries().size())).save(any(OrderInstance.class));

    }

    @Test
    public void performOrderWhenAmountInvalid() {

        List<OrderInstance> ls =new ArrayList<>();
        OrderInstance o1= new OrderInstance( 1, "name1", "company1", 100, 100);
        OrderInstance o2= new OrderInstance(2, "name2", "company2", 100, 0);
        Product p1=new Product(1, "name1", "company1", 100, true, 100);
        Product p2=new Product(2, "name2", "company2", 100, true, 100);
        Order order = new Order(new ArrayList<>(),100,"test");
        order.setEntries(ls);
        order.addEntry(o1);
        order.addEntry(o2);

        when(productRepository.findOneByName("name1")).thenReturn(p1);
        when(productRepository.findOneByName("name2")).thenReturn(p2);
        when(productRepository.save(p1)).thenReturn(p1);
        when(productRepository.save(p2)).thenReturn(p2);
        when(orderInstanceRepository.save(o1)).thenReturn(o1);
        when(orderInstanceRepository.save(o2)).thenReturn(o2);
        when(orderRepository.save(order)).thenReturn(order);

        assertFalse(imsService.performOrder(order));
        verify(orderRepository,times(0)).save(Mockito.any(Order.class));
        verify(productRepository,times(0)).findOneByName(anyString());
        verify(productRepository,times(0)).save(Mockito.any(Product.class));
        verify(orderInstanceRepository,times(0)).save(any(OrderInstance.class));

    }

    @Test
    public void performOrderWhenListEmpty() {

        List<OrderInstance> ls =new ArrayList<>();
        OrderInstance o1= new OrderInstance( 1, "name1", "company1", 100, 100);
        OrderInstance o2= new OrderInstance(2, "name2", "company2", 100, 0);
        Product p1=new Product(1, "name1", "company1", 100, true, 100);
        Product p2=new Product(2, "name2", "company2", 100, true, 100);
        Order order = new Order(new ArrayList<>(),100,"test");
        order.setEntries(ls);
//        order.addEntry(o1);
//        order.addEntry(o2);

        when(productRepository.findOneByName("name1")).thenReturn(p1);
        when(productRepository.findOneByName("name2")).thenReturn(p2);
        when(productRepository.save(p1)).thenReturn(p1);
        when(productRepository.save(p2)).thenReturn(p2);
        when(orderInstanceRepository.save(o1)).thenReturn(o1);
        when(orderInstanceRepository.save(o2)).thenReturn(o2);
        when(orderRepository.save(order)).thenReturn(order);

        assertFalse(imsService.performOrder(order));
        verify(orderRepository,times(0)).save(Mockito.any(Order.class));
        verify(productRepository,times(0)).findOneByName(anyString());
        verify(productRepository,times(0)).save(Mockito.any(Product.class));
        verify(orderInstanceRepository,times(0)).save(any(OrderInstance.class));

    }

    @Test
    public void performSaleWhenValid() {

        List<SaleInstance> ls2 =new ArrayList<>();
        SaleInstance s1= new SaleInstance( 1, "name1", "company1", 100, 10);
        SaleInstance s2= new SaleInstance(2, "name2", "company2", 100, 10);
        Product p1=new Product(1, "name1", "company1", 100, true, 100);
        Product p2=new Product(2, "name2", "company2", 100, true, 100);
        Sale sale = new Sale(new ArrayList<>(),100,"test");
        sale.addEntry(s1);
        sale.addEntry(s2);

        when(productRepository.findOneByName("name1")).thenReturn(p1);
        when(productRepository.findOneByName("name2")).thenReturn(p2);
        when(productRepository.save(p1)).thenReturn(p1);
        when(productRepository.save(p2)).thenReturn(p2);
        when(saleInstanceRepository.save(s1)).thenReturn(s1);
        when(saleInstanceRepository.save(s2)).thenReturn(s2);
        when(saleRepository.save(sale)).thenReturn(sale);

        assertTrue(imsService.performSale(sale));;

        verify(saleRepository,times(1)).save(Mockito.any(Sale.class));
        verify(productRepository,times(2*sale.getEntries().size())).findOneByName(anyString());
        verify(productRepository,times(sale.getEntries().size())).save(Mockito.any(Product.class));
        verify(saleInstanceRepository,times(sale.getEntries().size())).save(any(SaleInstance.class));

    }

    @Test
    public void performSaleWhenValidWhenListEmpty() {

        List<SaleInstance> ls2 =new ArrayList<>();
		SaleInstance s1= new SaleInstance( 1, "name1", "company1", 100, 10);
		SaleInstance s2= new SaleInstance(2, "name2", "company2", 100, 10);
        Product p1=new Product(1, "name1", "company1", 100, true, 100);
        Product p2=new Product(2, "name2", "company2", 100, true, 100);
		Sale sale = new Sale(new ArrayList<>(),100,"test");
//		sale.addEntry(s1);
//		sale.addEntry(s2);

        when(productRepository.findOneByName("name1")).thenReturn(p1);
        when(productRepository.findOneByName("name2")).thenReturn(p2);
        when(productRepository.save(p1)).thenReturn(p1);
        when(productRepository.save(p2)).thenReturn(p2);
        when(saleInstanceRepository.save(s1)).thenReturn(s1);
        when(saleInstanceRepository.save(s2)).thenReturn(s2);
        when(saleRepository.save(sale)).thenReturn(sale);
        when(analyticsRepository.findOneByCol("Expenditure")).thenReturn(new Analytics("Expenditure",20000));
        assertFalse(imsService.performSale(sale));;

        verify(saleRepository,times(0)).save(Mockito.any(Sale.class));
        verify(productRepository,times(0)).findOneByName(anyString());
        verify(productRepository,times(0)).save(Mockito.any(Product.class));
        verify(saleInstanceRepository,times(0)).save(any(SaleInstance.class));

    }

    @Test
    public void performSaleWhenInvalidAmount() {

        List<SaleInstance> ls2 =new ArrayList<>();
        SaleInstance s1= new SaleInstance( 1, "name1", "company1", 100, 0);
        SaleInstance s2= new SaleInstance(2, "name2", "company2", 100, 10);
        Product p1=new Product(1, "name1", "company1", 100, true, 100);
        Product p2=new Product(2, "name2", "company2", 100, true, 100);
        Sale sale = new Sale(new ArrayList<>(),100,"test");
        sale.addEntry(s1);
        sale.addEntry(s2);

        when(productRepository.findOneByName("name1")).thenReturn(p1);
        when(productRepository.findOneByName("name2")).thenReturn(p2);
        when(productRepository.save(p1)).thenReturn(p1);
        when(productRepository.save(p2)).thenReturn(p2);
        when(saleInstanceRepository.save(s1)).thenReturn(s1);
        when(saleInstanceRepository.save(s2)).thenReturn(s2);
        when(saleRepository.save(sale)).thenReturn(sale);

        assertFalse(imsService.performSale(sale));;

        verify(saleRepository,times(0)).save(Mockito.any(Sale.class));
        verify(productRepository,times(0)).findOneByName(anyString());
        verify(productRepository,times(0)).save(Mockito.any(Product.class));
        verify(saleInstanceRepository,times(0)).save(any(SaleInstance.class));

    }

    @Test
    public void performSaleWhenGreatAmount() {

        List<SaleInstance> ls2 =new ArrayList<>();
        SaleInstance s1= new SaleInstance( 1, "name1", "company1", 100, 100);
        SaleInstance s2= new SaleInstance(2, "name2", "company2", 100, 101);
        Product p1=new Product(1, "name1", "company1", 100, true, 100);
        Product p2=new Product(2, "name2", "company2", 100, true, 100);
        Sale sale = new Sale(new ArrayList<>(),100,"test");
        sale.addEntry(s1);
        sale.addEntry(s2);

        when(productRepository.findOneByName("name1")).thenReturn(p1);
        when(productRepository.findOneByName("name2")).thenReturn(p2);
        when(productRepository.save(p1)).thenReturn(p1);
        when(productRepository.save(p2)).thenReturn(p2);
        when(saleInstanceRepository.save(s1)).thenReturn(s1);
        when(saleInstanceRepository.save(s2)).thenReturn(s2);
        when(saleRepository.save(sale)).thenReturn(sale);

        assertFalse(imsService.performSale(sale));;

        verify(saleRepository,times(0)).save(Mockito.any(Sale.class));
        verify(productRepository,atLeast(1)).findOneByName(anyString());
        verify(productRepository,times(0)).save(Mockito.any(Product.class));
        verify(saleInstanceRepository,times(0)).save(any(SaleInstance.class));

    }

    @Test
    public void performSaleWhenExactAmount() {

        List<SaleInstance> ls2 =new ArrayList<>();
        SaleInstance s1= new SaleInstance( 1, "name1", "company1", 100, 100);
        SaleInstance s2= new SaleInstance(2, "name2", "company2", 100, 10);
        Product p1=new Product(1, "name1", "company1", 100, true, 100);
        Product p2=new Product(2, "name2", "company2", 100, true, 100);
        Sale sale = new Sale(new ArrayList<>(),100,"test");
        sale.addEntry(s1);
        sale.addEntry(s2);

        when(productRepository.findOneByName("name1")).thenReturn(p1);
        when(productRepository.findOneByName("name2")).thenReturn(p2);
        when(productRepository.save(p1)).thenReturn(p1);
        when(productRepository.save(p2)).thenReturn(p2);
        when(saleInstanceRepository.save(s1)).thenReturn(s1);
        when(saleInstanceRepository.save(s2)).thenReturn(s2);
        when(saleRepository.save(sale)).thenReturn(sale);

        assertTrue(imsService.performSale(sale));;
        assertFalse(p1.getStatus());
        verify(saleRepository,times(1)).save(Mockito.any(Sale.class));
        verify(productRepository,times(2*sale.getEntries().size())).findOneByName(anyString());
        verify(productRepository,times(sale.getEntries().size())).save(Mockito.any(Product.class));
        verify(saleInstanceRepository,times(sale.getEntries().size())).save(any(SaleInstance.class));

    }


}