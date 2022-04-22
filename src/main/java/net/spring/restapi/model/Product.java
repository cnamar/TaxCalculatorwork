package net.spring.restapi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.attoparser.trace.MarkupTraceEvent;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table( name = "products")
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column( name = "product_name")
    private String pName;

    @Column( name = "product_count")
    private Integer pCount;

    @Column( name = "product_price")
    private Double pPrice;



    public Product(){

    }

    public Product(String productName, Integer productCount, Double pPrice) {
        super();
        this.pName = productName;
        this.pCount = productCount;
        this.pPrice = pPrice;
    }

    //Getters and Setters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getpCount() {
        return pCount;
    }

    public void setpCount(Integer pCount) {
        this.pCount = pCount;
    }

    public Double getpPrice() {
        return pPrice;
    }

    public void setpPrice(Double pPrice) {
        this.pPrice = pPrice;
    }


   public Double getTotalPrice(){
       double tax=0.0;
       double totalPrice=0.0;
       double taxEach=0.0;
       if (pName.contains("imported")) {
           if (pName.contains("book") || pName.contains("food") || pName.contains("medicine")) {
               taxEach = pPrice * 0.05;
               tax += (taxEach* pCount);
               totalPrice += (pPrice + taxEach) * pCount;
           }
           else {
               taxEach= pPrice * 0.15;
               tax += (taxEach * pCount);
               totalPrice += (pPrice + taxEach)*pCount;
           }
       }
       else {
           if (pName.contains("book") || pName.contains("food") || pName.contains("medicine")) {
               totalPrice += (pPrice * pCount);
           }
           else {
               taxEach = pPrice * 0.10;
               tax += (taxEach * pCount);
               totalPrice += (pPrice + taxEach)*pCount;;
           }
       }
       return totalPrice;
   }

    public Double getTax(){
        double tax=0.0;
        double totalPrice=0.0;
        double taxEach=0.0;
        if (pName.contains("imported")) {
            if (pName.contains("book") || pName.contains("food") || pName.contains("medicine")) {
                taxEach = pPrice * 0.05;
                tax += (taxEach* pCount);
                totalPrice += (pPrice + taxEach) * pCount;
            }
            else {
                taxEach= pPrice * 0.15;
                tax += (taxEach * pCount);
                totalPrice += (pPrice + taxEach)*pCount;
            }
        }
        else {
            if (pName.contains("book") || pName.contains("food") || pName.contains("medicine")) {
                totalPrice += (pPrice * pCount);
            }
            else {
                taxEach = pPrice * 0.10;
                tax += (taxEach * pCount);
                totalPrice += (pPrice + taxEach)*pCount;;
            }
        }
        return tax;
    }




}
