package com.openclassrooms.shopmanager.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

        Product thirdObject = new Product();
        thirdObject.setDescription(newObject.getDescription());
        thirdObject.setDetails(newObject.getDetails());
        thirdObject.setName(newObject.getName());
        thirdObject.setPrice(newObject.getPrice());
        thirdObject.setQuantity(newObject.getQuantity());


        when(productRepository.save(thirdObject)).thenReturn(secondObject = newObject);

        assertEquals(secondObject.getPrice(), newObject.getPrice(), 0);



        /*when(productRepository.save(product).thenReturn(Optional.of(product))); //addItem(ProductEntity product, int quantity)

        create an object of class Product;

        create another product entity class and pass product there
                productService.createProduct(product);
        ProductEntity createdProduct = productService.createProduct(product);*/
    }
    /*@Test
    public void deleteProduct(){
        createProduct();
        getAllProducts_DbHasData_allDataReturned();
//        find product by id.
//        remove from list;
    }

    @Test
    public void updateProductQuantities(){

//        remove;
    }*/


}
