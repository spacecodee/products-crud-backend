package com.spacecodee.productscrudbackend.service;

import com.spacecodee.productscrudbackend.model.ProductEntity;
import com.spacecodee.productscrudbackend.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl {

    private final IProductRepository iProductRepository;

    public ProductServiceImpl(IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
    }

    public List<ProductEntity> getProducts() {
        return this.iProductRepository.findAll();
    }

    public ProductEntity findProductByName(String productName) {
        return this.iProductRepository.findProductByName(productName);
    }

    public ProductEntity findProductById(String idProduct) {
        return this.iProductRepository.findById(idProduct).orElse(null);
    }

    public Optional<ProductEntity> findProductByIdProduct(String idProduct) {
        return this.iProductRepository.findById(idProduct).stream().findFirst();
    }

    public boolean addProduct(ProductEntity productEntity) {
        try {
            this.iProductRepository.save(productEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public boolean updateProduct(ProductEntity productEntity) {
        try {
            this.iProductRepository.save(productEntity);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteProduct(String idProduct) {
        try {
            this.iProductRepository.deleteByIdProduct(idProduct);
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
