package com.spacecodee.productscrudbackend.controller;

import com.spacecodee.productscrudbackend.model.ProductEntity;
import com.spacecodee.productscrudbackend.service.ProductServiceImpl;
import com.spacecodee.productscrudbackend.utils.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "http://localhost:4200/")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/list-products")
    public ResponseEntity<List<ProductEntity>> loadCustomers() {
        List<ProductEntity> products = this.productService.getProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/find-by-name/{productName}")
    public ResponseEntity<ProductEntity> findProductByName(@PathVariable(name = "productName") String productName) {
        var product = this.productService.findProductByName(productName);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/find-product-by-id/{idProduct}")
    public ResponseEntity<ProductEntity> findProductById(@PathVariable(name = "idProduct") String idProduct) {
        var product = this.productService.findProductById(idProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/add")
    public ResponseEntity<Message> addProduct(@RequestBody ProductEntity productEntity) {
        var newProductDto = new ProductEntity();
        newProductDto.setName(productEntity.getName());
        newProductDto.setPrice(productEntity.getPrice());
        newProductDto.setStock(productEntity.getStock());
        if (this.productService.addProduct(newProductDto)) {
            return new ResponseEntity<>(new Message("Registro agregado con exito"), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(new Message("Al parecer ocurrio un error"), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = "/update/{idProduct}")
    public ResponseEntity<Message> updateProduct(@PathVariable(name = "idProduct") String idProduct,
                                                 @RequestBody ProductEntity productEntity) {

        var product = this.productService.findProductById(idProduct);
        product.setName(productEntity.getName());
        product.setPrice(productEntity.getPrice());
        product.setStock(productEntity.getStock());

        if (this.productService.updateProduct(product)) {
            return new ResponseEntity<>(new Message("Registro actualizado con exito"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new Message("Al parecer ocurrio un error"), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value = "/delete/{idProduct}")
    public ResponseEntity<Message> deleteProduct(@PathVariable(name = "idProduct") String idProduct) {
        if (this.productService.deleteProduct(idProduct)) {
            return new ResponseEntity<>(new Message("Registro eliminado con exito"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new Message("Al parecer ocurrio un error"), HttpStatus.BAD_REQUEST);
        }
    }
}
