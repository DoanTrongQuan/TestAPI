package com.example.testapi.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "ProductDetailPropertyDetail")
public class ProductDetailPropertyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "productDetail_id", foreignKey = @ForeignKey(name = "fk_ProductDetailPropertyDetail_ProductDetail"))
    private ProductDetails productdetails;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_ProductDetailPropertyDetail_products"))
    private Products products;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "propertyDetails_id", foreignKey = @ForeignKey(name = "fk_ProductDetailPropertyDetail_PropertyDetail"))
    private PropertyDetails propertydetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductDetails getProductdetails() {
        return productdetails;
    }

    public void setProductdetails(ProductDetails productdetails) {
        this.productdetails = productdetails;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public PropertyDetails getPropertydetails() {
        return propertydetails;
    }

    public void setPropertydetails(PropertyDetails propertydetails) {
        this.propertydetails = propertydetails;
    }
}
