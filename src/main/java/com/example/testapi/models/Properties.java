package com.example.testapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "properties")
public class Properties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int property_id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_properties_products"))
    private Products products;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "properties")
    @JsonBackReference
    private Set<PropertyDetails> propertyDetailsSet;

    @Column(name = "propertyName")
    private String propertyname;

    @Column(name = "propertySort")
    private int propertysort;

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Set<PropertyDetails> getPropertyDetailsSet() {
        return propertyDetailsSet;
    }

    public void setPropertyDetailsSet(Set<PropertyDetails> propertyDetailsSet) {
        this.propertyDetailsSet = propertyDetailsSet;
    }

    public String getPropertyname() {
        return propertyname;
    }

    public void setPropertyname(String propertyname) {
        this.propertyname = propertyname;
    }

    public int getPropertysort() {
        return propertysort;
    }

    public void setPropertysort(int propertysort) {
        this.propertysort = propertysort;
    }
}
