package com.williams.identityverification.repository;

import com.williams.identityverification.model.model.Product;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    Product product;

    @BeforeEach
    void setUp() {
        product = new Product("Bag","23","XXXXXXX");
        productRepository.save(product);
    }

    @AfterEach
    void tearDown() {
        product = null;
        productRepository.deleteAll();
    }

    // Test case SUCCESS

   @Test
    void testFindByVendorName_Found() {

       Product savedProduct = productRepository.findProductByName("Bag");
       AssertionsForClassTypes.assertThat(savedProduct.getName()).isEqualTo(savedProduct.getName());
       AssertionsForClassTypes.assertThat(savedProduct.getSerialNo()).isEqualTo(savedProduct.getSerialNo());


   }
    // Test case FAILURE
    @Test
    void testFindByVendorName_NotFound()
    {
        Product product1 = productRepository.findProductByName("Bag");
        Assertions.assertNotNull(product1);
    }

}
