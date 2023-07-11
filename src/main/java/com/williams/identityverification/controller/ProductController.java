package com.williams.identityverification.controller;

import com.williams.identityverification.model.request.ProductRequest;
import com.williams.identityverification.model.response.ProductResponse;
import com.williams.identityverification.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("products")
public class ProductController {

    private final ProductService service;

    @PostMapping("")
    private ProductResponse createProduct(@RequestBody ProductRequest request){
        ProductResponse response = service.createProduct(request);
        return response;
    }

    @PutMapping("")
    private ProductResponse updateProduct(@RequestBody ProductRequest request){
        ProductResponse response = service.updateProduct(request);
        return response;
    }

    @GetMapping("/{productName}")
    private ProductResponse getProduct(@PathVariable String  productName){
        ProductResponse response = service.getProduct(productName);
        return response;
    }

    @GetMapping("")
    private List<ProductResponse> getProducts(){
        List<ProductResponse> response = service.getProducts();
        return response;
    }
}
