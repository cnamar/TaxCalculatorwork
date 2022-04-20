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

@Getter
@Setter
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


    private double tax=0;
    private double finalPrice=0;
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

    //set of items to be excluded from sales tax
    Set<String> itemToBeExcluded = new HashSet<String>(Arrays.asList("book","chocolate bar","chocolate","medicine","food"));



    public void calculateTax(){
        String nameInLowerCase = pName.toLowerCase();

        if(isImported(nameInLowerCase)){
            nameInLowerCase = getTruncatedName(nameInLowerCase);
            tax+=pPrice*0.05;
        }
        if(isSalesTaxApplicable(nameInLowerCase))
            tax+=pPrice*0.1;
        finalPrice=pPrice+tax;

    }

    public boolean isSalesTaxApplicable(String name){
        return (!itemToBeExcluded.contains(name));
    }
    public boolean isImported(String name){
        return name.contains("imported");
    }

    //removing imported tag
    public String getTruncatedName(String name){
        return(name.replaceAll("imported ",""));
    }
    //getters
    public double getFinalPrice(){
        return(finalPrice);
    }

    public double getTotalTaxOfItem(){
        return(pCount*tax);
    }
    public double getTotalPriceOfItem(){
        return(finalPrice*pCount);
    }

    public String productInitialPriceDetails(){
        return(pName+':'+pCount+':'+pPrice);
    }
    public String productFinalPriceDetails(){
        return(pName+':'+pCount+':'+finalPrice);
    }

}
