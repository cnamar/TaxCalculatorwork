package net.spring.restapi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "products")
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column( name = "productName" , nullable = false)
    private String pName;

    @Column( name = "productCount")
    private Integer pCount;

    @Column( name = "productPrice")
    private Double pPrice;


}
