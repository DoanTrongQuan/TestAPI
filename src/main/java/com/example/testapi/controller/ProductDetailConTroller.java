package com.example.testapi.controller;
import com.example.testapi.helper.ObjectResponse;
import com.example.testapi.models.ProductDetails;
import com.example.testapi.models.Products;
import com.example.testapi.services.ProductDetailsServices;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductDetailConTroller {
    @Autowired
    private ProductDetailsServices productDetailsServices;

    @RequestMapping(value = "buy/product", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ObjectResponse> displayErrorQuantityProduct(@RequestBody String request) {
        Gson gson = new Gson();
        Request rq = gson.fromJson(request,Request.class);
        return productDetailsServices.displayErrorQuantityProduct(rq);
    }
    @RequestMapping(value = "update/quantity/productdetail", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateQuantityProductDeytail(@RequestBody String product){
        Gson gson = new Gson();
        Products pd = gson.fromJson(product, Products.class);
        boolean check = productDetailsServices.updateQuantityProductDetail(pd);
        if(check){
            return " success";
        }
        return "fails";
    }

    @RequestMapping(value = "display/prodeuctdetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDetails> displayLastGenerationProductDetails(){
        return productDetailsServices.displayLastGenerationProductDetails();
    }
}
