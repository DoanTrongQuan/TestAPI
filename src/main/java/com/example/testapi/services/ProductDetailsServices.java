package com.example.testapi.services;

import com.example.testapi.controller.Request;
import com.example.testapi.helper.ObjectResponse;
import com.example.testapi.models.ProductDetailPropertyDetails;
import com.example.testapi.models.ProductDetails;
import com.example.testapi.models.Products;
import com.example.testapi.repository.ProductDetailPropertyDetailsRepo;
import com.example.testapi.repository.ProductDetailsRepo;
import com.example.testapi.repository.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class ProductDetailsServices implements IProductDetailsServices {
    @Autowired
    private ProductDetailsRepo productDetailsRepo;
    @Autowired
    private ProductsRepo productsRepo;
    @Autowired
    private ProductDetailPropertyDetailsRepo productDetailPropertyDetailsRepo;


    @Override
    public ResponseEntity<ObjectResponse> displayErrorQuantityProduct(Request request) {
        for (ProductDetailPropertyDetails pdpd : productDetailPropertyDetailsRepo.findAll()) {
            for (ProductDetailPropertyDetails rq : request.getProducts().getProductDetailPropertyDetailsSet()) {
                if (pdpd.getId() == rq.getId() && pdpd.getProductdetails().getProductDetail_id() == rq.getProductdetails().getProductDetail_id()) {
                    if (pdpd.getProductdetails().getQuantity() == 0) {
                        return new ResponseEntity<>(new ObjectResponse("Hết hàng", ""), HttpStatus.BAD_REQUEST);

                    } else if (pdpd.getProductdetails().getQuantity() < request.getQuantityBuy()) {
                        return new ResponseEntity<>(new ObjectResponse("Không đủ sản phẩm " + pdpd.getProductdetails().getProductPropertyName(), ""), HttpStatus.BAD_REQUEST);

                    } else if (pdpd.getProductdetails().getQuantity() >= request.getQuantityBuy()) {
                        pdpd.getProductdetails().setQuantity(pdpd.getProductdetails().getQuantity() - request.getQuantityBuy());
                        for (ProductDetails x : productDetailsRepo.findAll()) {
                            if (x.getParentid() < pdpd.getProductdetails().getParentid() && pdpd.getProductdetails().getProductPropertyName().contains(x.getProductPropertyName())) {
                                x.setQuantity(x.getQuantity() - request.getQuantityBuy());
                                productDetailsRepo.save(x);
                            }
                        }
                        productDetailsRepo.save(pdpd.getProductdetails());
                    }
                    break;
                }
            }
        }
        return new ResponseEntity<>(new ObjectResponse("Mua thành công", ""), HttpStatus.OK);
    }

    @Override
    public boolean updateQuantityProductDetail(Products products) {
        Set<ProductDetailPropertyDetails> productDetailPropertyDetailsSet = products.getProductDetailPropertyDetailsSet();
        if (checkProductDetail(products)) {
            for (ProductDetailPropertyDetails x : productDetailPropertyDetailsSet) {
                ProductDetails productDetails = productDetailsRepo.findById(x.getProductdetails().getProductDetail_id()).orElse(null);
                if (x.getProductdetails().getQuantity() > productDetails.getQuantity()) {
                    for (ProductDetails i : productDetailsRepo.findAll()) {
                        if (i.getParentid() < productDetails.getParentid() && productDetails.getProductPropertyName().contains(i.getProductPropertyName())) {
                            i.setQuantity(i.getQuantity() + (x.getProductdetails().getQuantity() - productDetails.getQuantity()));
                            productDetailsRepo.save(i);
                        }
                    }
                    productDetails.setQuantity(x.getProductdetails().getQuantity());
                    productDetailsRepo.save(productDetails);
                } else if (x.getProductdetails().getQuantity() < productDetails.getQuantity()) {
                    for (ProductDetails i : productDetailsRepo.findAll()) {
                        if (i.getParentid() < productDetails.getParentid() && productDetails.getProductPropertyName().contains(i.getProductPropertyName())) {
                            i.setQuantity(i.getQuantity() - (productDetails.getQuantity() - x.getProductdetails().getQuantity()));
                            productDetailsRepo.save(i);
                        }
                    }
                    productDetails.setQuantity(x.getProductdetails().getQuantity());
                }
            }
        }
        else if(!checkProductDetail(products) ){
            return false;
        }
        return true;
    }

    @Override
    public List<ProductDetails> displayLastGenerationProductDetails() {
        List<ProductDetails> resultList = new ArrayList<>();
        for (ProductDetails prd: productDetailsRepo.findAll()) {
            boolean isCheck = true;
            for (ProductDetails prdcheck: productDetailsRepo.findAll()) {
                if( prdcheck.getProductDetail_id() != prd.getProductDetail_id()&&
                        prdcheck.getProductPropertyName().contains(prd.getProductPropertyName())){
                    isCheck = false;
                    break;
                }
            }
            if(isCheck){
                resultList.add(prd);
            }
        }
        return resultList;
    }


    public boolean checkProductDetail(Products products) {
        Set<ProductDetailPropertyDetails> productDetailPropertyDetailsSet = products.getProductDetailPropertyDetailsSet();
        for (ProductDetailPropertyDetails x : productDetailPropertyDetailsSet) {
            Optional<ProductDetails> productDetails = productDetailsRepo.findById(x.getProductdetails().getProductDetail_id());
            if (productDetails.isPresent()) {
                return true;
            }
        }
        return false;
    }


//        for (ProductDetailPropertyDetails x : productDetailPropertyDetailsRepo.findAll()) {
//            for (ProductDetailPropertyDetails input : products.getProductDetailPropertyDetailsSet()) {
//                if (input.getProductdetails().getProductDetail_id() == x.getProductdetails().getProductDetail_id()) {
//                    if (input.getProductdetails().getQuantity() > x.getProductdetails().getQuantity()) {
//                        for (ProductDetails productDetails : productDetailsRepo.findAll()) {
//                            if (productDetails.getParentid() < x.getProductdetails().getParentid() && x.getProductdetails().getProductPropertyName().contains(productDetails.getProductPropertyName())) {
//                                productDetails.setQuantity(productDetails.getQuantity() + (input.getProductdetails().getQuantity() - x.getProductdetails().getQuantity()));
//                                productDetailsRepo.save(productDetails);
//                            }
//                        }
//                        x.getProductdetails().setQuantity(input.getProductdetails().getQuantity());
//                    } else if (input.getProductdetails().getQuantity() < x.getProductdetails().getQuantity()) {
//                        for (ProductDetails productDetails : productDetailsRepo.findAll()) {
//                            if (productDetails.getParentid() < x.getProductdetails().getParentid() && x.getProductdetails().getProductPropertyName().contains(productDetails.getProductPropertyName())) {
//                                productDetails.setQuantity(productDetails.getQuantity() - (x.getProductdetails().getQuantity() - input.getProductdetails().getQuantity()));
//                                productDetailsRepo.save(productDetails);
//                            }
//                        }
//                        x.getProductdetails().setQuantity(input.getProductdetails().getQuantity());
//                    }
//                }
//            }
//        }
//        return true;
//    }

}


//        for (int i = 0; i < productdetailName.size(); i++) {
//            String productPropertyName = productdetailName.get(i);
//            int quantitybuy = quantityBuy.get(i);
//            ProductDetails product = productDetailsRepo.findByProductPropertyName(productPropertyName);
//            if (product != null) {
//                if (product.getQuantity() < quantitybuy) {
//                    return new ResponseEntity<>(new ObjectResponse("Không đủ sản phẩm " + product.getProductPropertyName(), ""), HttpStatus.BAD_REQUEST);
//                } else if (product.getQuantity() == 0) {
//                    return new ResponseEntity<>(new ObjectResponse("Hết hàng", ""), HttpStatus.BAD_REQUEST);
//                }
//            } else {
//                return new ResponseEntity<>(new ObjectResponse("Sản phẩm " + productdetailName.get(i) + " không tồn tại", ""), HttpStatus.NOT_FOUND);
//            }
//        }
//        for (int i = 0; i < productdetailName.size(); i++) {
//            String productPropertyName = productdetailName.get(i);
//            int quantitybuy = quantityBuy.get(i);
//            ProductDetails product = productDetailsRepo.findByProductPropertyName(productPropertyName);
//            product.setQuantity(product.getQuantity()- quantitybuy);
//            for (ProductDetails x:productDetailsRepo.findAll()) {
//                if(x.getParent() != null   && x.getParentid() < product.getParentid() && product.getProductPropertyName().contains(x.getProductPropertyName())){
//                    x.setQuantity(x.getQuantity()-quantitybuy);
//                    productDetailsRepo.save(x);
//                }else if(x.getParent() == null && x.getParentid() < product.getParentid() && product.getProductPropertyName().contains(x.getProductPropertyName())){
//                    x.setQuantity(x.getQuantity()-quantitybuy);
//                    productDetailsRepo.save(x);
//                }
//            }
//            productDetailsRepo.save(product);
//        }
//        return new ResponseEntity<>(new ObjectResponse("Mua thành công", ""), HttpStatus.OK);
//    }


