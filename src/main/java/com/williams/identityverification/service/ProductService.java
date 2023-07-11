package com.williams.identityverification.service;

import com.williams.identityverification.exception.ConflictException;
import com.williams.identityverification.model.model.Product;
import com.williams.identityverification.model.request.ProductRequest;
import com.williams.identityverification.model.response.ProductResponse;
import com.williams.identityverification.repository.ProductRepository;
import com.williams.identityverification.util.CustomResponseCode;
import com.williams.identityverification.util.EncrytionUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    public ProductResponse createProduct(ProductRequest request){
        log.info("ProductRequest ......{}",request);
        Product savedProduct = productRepository.findProductByName(request.getName());
        if (savedProduct != null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION,"Product already exist");
        }
        generateProductSerialNumber(request);
        Product product = mapper.map(request,Product.class);
        product =productRepository.save(product);
        return mapper.map(product, ProductResponse.class);
    }

    public ProductResponse updateProduct(ProductRequest request){
        Product savedProduct = productRepository.findBySerialNo(request.getSerialNo());
        if (savedProduct == null){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION,"Product does exist");
        }
        savedProduct = mapper.map(request,Product.class);
//        savedProduct.setAge(request.getAge());
        savedProduct =productRepository.save(savedProduct);
        return mapper.map(savedProduct, ProductResponse.class);
    }

    public ProductResponse getProduct(String productName){
        Product savedProduct = productRepository.findProductByName(productName);
        if (savedProduct.equals(null)){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION,"Product does not exist");
        }
        return mapper.map(savedProduct, ProductResponse.class);
    }

    public List<ProductResponse> getProducts(){
        List<Product> savedProduct = productRepository.findAll();
        if (savedProduct.equals(null)){
            throw new ConflictException(CustomResponseCode.CONFLICT_EXCEPTION,"Product does not exist");
        }
        return savedProduct.stream().map(product -> mapToProductResponse(product)).collect(Collectors.toList());
    }

    public void generateProductSerialNumber(ProductRequest request){
        String rawKey = request.getName() + request.getAge() + new Date() + Math.random();
        String encodedKey = EncrytionUtil.hashWithSha256(rawKey);
        request.setSerialNo("PRD" + encodedKey.substring(0, 20));
        log.info("product Serial no is generated");
    }

    public ProductResponse mapToProductResponse(Product product){
        ProductResponse response = new ProductResponse();
        response.setName(product.getName());
        response.setAge(product.getAge());
        response.setSerialNo(product.getSerialNo());
        return response;
    }

    public void checker(ProductRequest productRequest){
        ProductResponse productResponse = new ProductResponse();
                productResponse.equals(productRequest);
    }
}
