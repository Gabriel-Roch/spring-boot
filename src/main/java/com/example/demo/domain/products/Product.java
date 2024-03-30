package com.example.demo.domain.products;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Table(name="product")
@Entity(name="product")
@EqualsAndHashCode(of = "id")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private int id;

    private String p_name;

    private Float p_price;

    public Product(RequestProduct RequestProduct){
        this.p_name = RequestProduct.p_name();
        this.p_price = RequestProduct.p_price();
    }

}
