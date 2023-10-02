package com.example.testapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "propertyDetails")
public class PropertyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int propertyDetails_id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "property_id", foreignKey = @ForeignKey(name = "fk_propertyDetails_properties"))
    private  Properties properties;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "propertydetails")
    @JsonBackReference
    private Set<ProductDetailPropertyDetails> productDetailPropertyDetailsSet;

    @Column(name = "PropertyDetailCode")
    private String propertyDetailCode;
    @Column(name = "PropertyDetailDetail")
    private String propertyDetailDetail;

    public int getPropertyDetails_id() {
        return propertyDetails_id;
    }

    public void setPropertyDetails_id(int propertyDetails_id) {
        this.propertyDetails_id = propertyDetails_id;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Set<ProductDetailPropertyDetails> getProductDetailPropertyDetailsSet() {
        return productDetailPropertyDetailsSet;
    }

    public void setProductDetailPropertyDetailsSet(Set<ProductDetailPropertyDetails> productDetailPropertyDetailsSet) {
        this.productDetailPropertyDetailsSet = productDetailPropertyDetailsSet;
    }

    public String getPropertyDetailCode() {
        return propertyDetailCode;
    }

    public void setPropertyDetailCode(String propertyDetailCode) {
        this.propertyDetailCode = propertyDetailCode;
    }

    public String getPropertyDetailDetail() {
        return propertyDetailDetail;
    }

    public void setPropertyDetailDetail(String propertyDetailDetail) {
        this.propertyDetailDetail = propertyDetailDetail;
    }
}
