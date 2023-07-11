package com.williams.identityverification.repository;

import com.williams.identityverification.model.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Product findProductByName(String name);

    Product findBySerialNo(String serialNo);
}
