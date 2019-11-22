package com.openclassrooms.shopmanager.product;

import com.openclassrooms.shopmanager.order.Cart;
import com.openclassrooms.shopmanager.order.CartLine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Take this test method as a template to write your test methods for ProductService and OrderService.
 * A test method must check if a definite method does its job:
 *
 * Naming follows this popular convention : http://osherove.com/blog/2005/4/3/naming-standards-for-unit-tests.html
 */


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Test
    public void getAllProducts_DbHasData_allDataReturned(){

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("First product");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("First product");

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        assertEquals(1L, products.get(0).getId() , 0);
        assertEquals(2L, products.get(1).getId() , 0);
    }

    @Test
    public void getAllAdminProduct() {
        Product product3 = new Product();
        product3.setId(3L);
        product3.setName("Third product");

        Product product4 = new Product();
        product4.setId(4L);
        product4.setName("Fourth product");

        when(productRepository.findAllByOrderByIdDesc()).thenReturn(Arrays.asList(product3, product4));
        List<Product> products2 = productService.getAllAdminProducts();

        assertEquals(2, products2.size());
        assertEquals(3L, products2.get(0).getId(), 0);
        assertEquals( 4L, products2.get(1).getId(), 0);
    }

    @Test
    public void getByProductId() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setPrice(10.05);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

        Product productReturn = productService.getByProductId(1L);

        //assertEquals(product1 , productReturn);
        assertEquals(10.05, productReturn.getPrice(), 0);
        assertEquals(1L , productReturn.getId(), 0);
    }
    @Test
    public void createProduct(){

        Product newObject = new Product();
        newObject.setDescription("Sports Car");
        newObject.setName("Car");
        newObject.setPrice(150.00);
        newObject.setQuantity(1);
        newObject.setDetails("Good car");

        Product secondObject = new Product();
        //this object would be the copy of the newObject; imittion of creating an product out of productmodel
        Product thirdObject = new Product();
        thirdObject.setDescription(newObject.getDescription());
        thirdObject.setDetails(newObject.getDetails());
        thirdObject.setName(newObject.getName());
        thirdObject.setPrice(newObject.getPrice());
        thirdObject.setQuantity(newObject.getQuantity());


        when(productRepository.save(thirdObject)).thenReturn(secondObject = newObject);

        assertEquals(secondObject.getPrice(), newObject.getPrice(), 0);
    }
    /*@Test
    public void deleteProduct(){
        createProduct();
        getAllProducts_DbHasData_allDataReturned();
//        find product by id.
//        remove from list;
    }

    @Test
    public void updateProductQuantitiesTest(){
        final Product[] productToDelete = {new Product()};
        productToDelete[0].setId(7L);
        productToDelete[0].setQuantity(3);

        Cart productList = new Cart();
        productList.addItem(productToDelete[0], 1);

        doAnswer(invocation -> {
            final Product productSaved = invocation.getArgument(0);
            if(productSaved.getId().equals(productToDelete[0].getId())) {
                productToDelete[0] = null;
            }
            return null;
        }).when(productRepository).delete(any(Product.class));

        doAnswer(invocation -> {
            final Product productSaved = invocation.getArgument(0);
            if(productSaved.getId().equals(productToDelete[0].getId())) {
                productToDelete[0].setQuantity(productSaved.getQuantity());
            }
            return null;
        }).when(productRepository).save(any(Product.class));

        when(productRepository.findById(7L)).thenReturn(Optional.ofNullable(productToDelete[0]));

        productService.updateProductQuantities(productList);

        assertEquals(productToDelete[0].getQuantity(), 2);
        Cart newCartTest = new Cart();
        newCartTest.addItem(productToDelete[0], 2);
        productService.updateProductQuantities(newCartTest);
        assertNull(productToDelete[0]);
    }



}
