package com.sura.builders.api;

import com.sura.builders.common.ex.SuraException;
import com.sura.builders.common.request.ProductRequest;
import com.sura.builders.common.response.ProductResponse;

import java.util.List;

public interface IProductAPI {

    List<ProductResponse> findAll();

    ProductResponse findById(long id) throws SuraException;

    ProductResponse create(ProductRequest productRequest) throws SuraException;

    ProductResponse update(long id, ProductRequest request);

   // List<ProductResponse> findRepeatStock(ValidationRequest validationRequest);

}
