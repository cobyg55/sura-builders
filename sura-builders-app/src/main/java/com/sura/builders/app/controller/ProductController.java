package com.sura.builders.app.controller;

import com.sura.builders.api.IProductAPI;
import com.sura.builders.common.ex.SuraException;
import com.sura.builders.common.request.ProductRequest;
import com.sura.builders.common.response.ProductResponse;
import com.sura.builders.common.routes.Router;
import com.sura.builders.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Router.Product.PRODUCT_API)
public class ProductController implements IProductAPI {

    @Autowired
    private ProductService productService;

    @Override
    @GetMapping
    public List<ProductResponse> findAll() {
        return productService.findAll();
    }

    @Override
    @GetMapping(Router.Product.FIND_PRODUCT_BY_ID)
    public ProductResponse findById(@PathVariable("id") long id) throws SuraException {
        return productService.findById(id);
    }

    @Override
    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest productRequest) throws SuraException {
        return productService.create(productRequest);
    }

    @Override
    @PutMapping(Router.Product.UPDATE)
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse update(@PathVariable("id") long id, @RequestBody ProductRequest request) {
        return productService.update(id, request);
    }
}
