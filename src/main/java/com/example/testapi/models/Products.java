package com.example.testapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;

    @Column(name = "productName")
    private String productname;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "products")
    @JsonBackReference
    private Set<Properties> properties;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "products")
    @JsonBackReference
    private Set<ProductDetailPropertyDetails> productDetailPropertyDetailsSet;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Set<Properties> getProperties() {
        return properties;
    }

    public void setProperties(Set<Properties> properties) {
        this.properties = properties;
    }

    public Set<ProductDetailPropertyDetails> getProductDetailPropertyDetailsSet() {
        return productDetailPropertyDetailsSet;
    }

    public void setProductDetailPropertyDetailsSet(Set<ProductDetailPropertyDetails> productDetailPropertyDetailsSet) {
        this.productDetailPropertyDetailsSet = productDetailPropertyDetailsSet;
    }
}
