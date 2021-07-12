package com.sura.builders.core.service;


import com.sura.builders.common.ex.SuraException;
import com.sura.builders.common.ex.ErrorCodes;
import com.sura.builders.common.ex.ErrorMessages;
import com.sura.builders.common.mapper.ProductMapper;
import com.sura.builders.common.request.ProductRequest;
import com.sura.builders.common.response.ProductResponse;
import com.sura.builders.domain.model.Product;
import com.sura.builders.domain.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MessageService messageService;

    public List<ProductResponse> findAll() {
        try {
            return productRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream().map(ProductMapper::mapToProductResponse).collect(Collectors.toList());
        } catch (SuraException ex) {
            log.error("ProductService:findAll() --Error:[{}]", ex.getMessage());
            throw new SuraException(ErrorCodes.BAD_REQUEST, ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            log.error("ProductService:findAll() --Error:[{}]", ex.getMessage());
            throw ex;
        }
    }

    public ProductResponse findById(long id) throws SuraException {
        try {
            Product product = getById(id);
            return ProductMapper.mapToProductResponse(product);
        } catch (SuraException ex) {
            log.error("ProductService:findById() --Error:[{}]", ex.getMessage());
            throw new SuraException(ErrorCodes.BAD_REQUEST, ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            log.error("ProductService:findById() --Error:[{}]", ex.getMessage());
            throw ex;
        }
    }

    public ProductResponse create(ProductRequest productRequest) throws SuraException {
        try {
            Optional<Product> productByName = productRepository.findByName(productRequest.getName().toUpperCase());
            if (productByName.isPresent()) {
                throw new SuraException(ErrorCodes.VALIDATION, messageService.getMessage(ErrorMessages.PRODUCT_NAME_DUPLICATED, new Object[]{productRequest.getName()}), HttpStatus.BAD_REQUEST);
            }
            Optional<Product> productByReference = productRepository.findByReference(productRequest.getReference());
            if (productByReference.isPresent()) {
                throw new SuraException(ErrorCodes.VALIDATION, messageService.getMessage(ErrorMessages.PRODUCT_REFERENCE_DUPLICATED, new Object[]{productRequest.getReference()}), HttpStatus.BAD_REQUEST);
            }
            return ProductMapper.mapToProductResponse(productRepository.save(ProductMapper.mapToProduct(productRequest)));
        } catch (SuraException ex) {
            log.error("ProductService:create() --Error:[{}]", ex.getMessage());
            throw new SuraException(ErrorCodes.BAD_REQUEST, ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            log.error("ProductService:create() --Error:[{}]", ex.getMessage());
            throw ex;
        }
    }

    public ProductResponse update(long id, ProductRequest request) {
        try {
            Product current = getById(id);
            return ProductMapper.mapToProductResponse(productRepository.save(current));
        } catch (SuraException ex) {
            log.error("ProductService:update() --Error:[{}]", ex.getMessage());
            throw new SuraException(ErrorCodes.BAD_REQUEST, ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            log.error("ProductService:update() --Error:[{}]", ex.getMessage());
            throw ex;
        }
    }

    public Product getById(long id) throws SuraException {
        return productRepository.findById(id).orElseThrow(() -> new SuraException(ErrorCodes.VALIDATION, messageService.getMessage(ErrorMessages.PRODUCT_NOT_FOUND, new Object[]{id}), HttpStatus.BAD_REQUEST));
    }

}
