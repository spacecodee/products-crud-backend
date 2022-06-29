package com.spacecodee.productscrudbackend.repository;

import com.spacecodee.productscrudbackend.model.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IProductRepository extends MongoRepository<ProductEntity, String> {

    ProductEntity findProductByName(String productName);

    ProductEntity findProductByStock(int stock);

    void deleteByIdProduct(String idProduct);

    List<ProductEntity> findAllByStock(int stock);
}
