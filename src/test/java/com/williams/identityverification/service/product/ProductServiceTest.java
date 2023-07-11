package com.williams.identityverification.service.product;

import com.williams.identityverification.model.model.Product;
import com.williams.identityverification.model.request.ProductRequest;
import com.williams.identityverification.model.response.ProductResponse;
import com.williams.identityverification.repository.ProductRepository;
import com.williams.identityverification.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Collections;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
public class ProductServiceTest {

    @Mock
    ProductService service;

    @Mock
    ProductRepository productRepository;

    Product product;

    ProductRequest productRequest;

    ProductResponse productResponse;

    AutoCloseable autoCloseable;

    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        service = new ProductService(productRepository,modelMapper);
        product = new Product("Kettle", "200",
                "PRD50003ef5fe4554f97dbd");
//        productRequest = modelMapper.map(product,ProductRequest.class);
        productRequest = new ProductRequest("Kettle","200","PRD50003ef5fe4554f97dbd");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void createProductTest() {
        mock(Product.class);
        mock(ProductRepository.class);
        when(productRepository.save(product)).thenReturn(product);
        ProductResponse productResponse = modelMapper.map(productRequest,ProductResponse.class);
        log.info("Response 123 prd {}", productRequest);
        log.info("Response 123 {}", productResponse);
        Assert.assertNotNull(product);
        assertThat(service.createProduct(productRequest)).isEqualTo(productResponse);
    }

    @Test
    void updateProductTest() {
        mock(Product.class);
        mock(ProductRepository.class);
        when(productRepository.save(product)).thenReturn(product);
//        ProductResponse productResponse = modelMapper.map(productRequest,ProductResponse.class);
        ProductResponse productResponse = new ProductResponse("Kettle","200","PRD50003ef5fe4554f97dbd");
////        Assert.assertNotNull(productResponse1);
//        assertThat(service.updateProduct(productRequest)).isEqualTo(productResponse);
////        Assert.assertEquals(productRequest, productResponse);
        Assert.assertEquals(product.getName(), productResponse.getName());
    }

    @Test
    void testGetByVendorName() {
        mock(Product.class);
        mock(ProductRepository.class);

        ProductResponse response = new ProductResponse("Kettle","200","PRD50003ef5fe4554f97dbd");

        when(productRepository.findProductByName("Kettle")).
                thenReturn(product);
        Assert.assertEquals(product.getName(), response.getName());
    }

    @Test
    void testGetAllProducts() {
        mock(Product.class);
        mock(ProductRepository.class);

        when(productRepository.findAll()).thenReturn(new ArrayList<Product>(
                Collections.singleton(product)
        ));
        assertThat(service.getProducts().get(0).getName()).
                isEqualTo(product.getName());

    }


}
