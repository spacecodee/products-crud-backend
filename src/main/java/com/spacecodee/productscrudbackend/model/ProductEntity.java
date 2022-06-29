package com.spacecodee.productscrudbackend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "product")
public class ProductEntity implements Serializable {

    @Id
    private String idProduct;
    private String name;
    private double price;
    private int stock;
}
