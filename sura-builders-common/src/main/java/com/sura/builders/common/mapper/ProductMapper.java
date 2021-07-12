package com.sura.builders.common.mapper;

import com.sura.builders.common.request.ProductRequest;
import com.sura.builders.common.response.ProductResponse;
import com.sura.builders.domain.model.Product;

import java.util.Date;

public class ProductMapper {

    public static ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setCreatedAt(product.getCreatedAt());
        return productResponse;
    }

    public static Product mapToProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName().toUpperCase());
        product.setReference(productRequest.getReference().toUpperCase());
        product.setCreatedAt(new Date());
        return product;
    }

}
