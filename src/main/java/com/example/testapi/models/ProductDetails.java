package com.example.testapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "ProductDetails")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productDetail_id;

    @Column(name = "ProductPropertyName")
    private String productPropertyName;

    @Column(name = "Quantity")
    private int quantity;
    @Column(name = "Price")
    private double price;
    @Column(name = "ShellPrice")
    private double shellPrice;
    @Column(name = "Parent_id")
    private int parentid;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productdetails")
    @JsonBackReference
    private Set<ProductDetailPropertyDetails> productDetailPropertyDetailsSet;



    public int getProductDetail_id() {
        return productDetail_id;
    }

    public void setProductDetail_id(int productDetail_id) {
        this.productDetail_id = productDetail_id;
    }

    public String getProductPropertyName() {
        return productPropertyName;
    }

    public void setProductPropertyName(String productPropertyName) {
        this.productPropertyName = productPropertyName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getShellPrice() {
        return shellPrice;
    }

    public void setShellPrice(double shellPrice) {
        this.shellPrice = shellPrice;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public Set<ProductDetailPropertyDetails> getProductDetailPropertyDetailsSet() {
        return productDetailPropertyDetailsSet;
    }

    public void setProductDetailPropertyDetailsSet(Set<ProductDetailPropertyDetails> productDetailPropertyDetailsSet) {
        this.productDetailPropertyDetailsSet = productDetailPropertyDetailsSet;
    }

}
