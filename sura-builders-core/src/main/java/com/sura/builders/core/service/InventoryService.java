package com.sura.builders.core.service;

import com.sura.builders.common.ex.SuraException;
import com.sura.builders.common.ex.ErrorCodes;
import com.sura.builders.common.ex.ErrorMessages;
import com.sura.builders.common.mapper.InventoryMapper;
import com.sura.builders.domain.model.Inventory;
import com.sura.builders.domain.repository.InventoryRepository;
import com.sura.builders.common.request.*;
import com.sura.builders.common.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private MessageService messageService;

    public List<InventoryResponse> findAll() {
        return inventoryRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream().map(InventoryMapper::mapToInventoryResponse).collect(Collectors.toList());
    }

    public InventoryResponse findById(long id) throws SuraException {
        try {
            Inventory inventory = getById(id);
            if (inventory !=null) {
                throw new SuraException(ErrorCodes.BAD_REQUEST, messageService.getMessage(ErrorMessages.INVENTORY_NOT_FOUNT, new Object[]{inventory.getId()}), HttpStatus.BAD_REQUEST);
            }
            return InventoryMapper.mapToInventoryResponse(inventory);
        } catch (SuraException ex) {
            log.error("InventoryService:findById() --Error:[{}]", ex.getMessage());
            throw new SuraException(ErrorCodes.BAD_REQUEST, ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            log.error("InventoryService:findById() --Error:[{}]", ex.getMessage());
            throw ex;
        }
    }

    public Inventory findByProductId(long productId) throws SuraException {
        try {
            return inventoryRepository.findByProductId(productId).orElse(null);
        } catch (Exception ex) {
            log.error("InventoryService:findByProductId() --Error:[{}]", ex.getMessage());
            throw ex;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public InventoryResponse create(InventoryRequest inventoryRequest) throws SuraException {
        try {
            ProductResponse product = productService.findById(inventoryRequest.getProductId());
            Optional<Inventory> inventory = inventoryRepository.findByProductId(inventoryRequest.getProductId());
            if (inventory != null) {
                throw new SuraException(ErrorCodes.VALIDATION, messageService.getMessage(ErrorMessages.INVENTORY_ALREADY_EXISTS, new Object[]{product.getName()}), HttpStatus.BAD_REQUEST);
            } else {
                Inventory newInventory = inventoryRepository.save(InventoryMapper.mapToInventory(inventoryRequest, productService.getById(product.getId())));
                return InventoryMapper.mapToInventoryResponse(newInventory);
            }
        } catch (SuraException ex) {
            log.error("InventoryService:create() --Error:[{}]", ex.getMessage());
            throw new SuraException(ErrorCodes.BAD_REQUEST, ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            log.error("InventoryService:create() --Error:[{}]", ex.getMessage());
            throw ex;
        }
    }

    public Inventory getById(long id) throws SuraException {
        return inventoryRepository.findById(id).orElseThrow(() -> new SuraException(ErrorCodes.VALIDATION, messageService.getMessage(ErrorMessages.INVENTORY_NOT_FOUNT, new Object[]{id}), HttpStatus.BAD_REQUEST));
    }



}
