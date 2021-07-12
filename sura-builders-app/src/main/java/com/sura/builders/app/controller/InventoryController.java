package com.sura.builders.app.controller;

import com.sura.builders.api.IInventoryAPI;
import com.sura.builders.common.ex.SuraException;
import com.sura.builders.common.request.*;
import com.sura.builders.common.response.*;
import com.sura.builders.common.routes.Router;
import com.sura.builders.core.service.InventoryService;
import com.sura.builders.domain.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Router.Inventory.INVENTORY_API)
public class InventoryController implements IInventoryAPI {

    @Autowired
    private InventoryService inventoryService;

    @Override
    @GetMapping
    public List<InventoryResponse> findAll() {
        return inventoryService.findAll();
    }

    @Override
    @GetMapping(Router.Inventory.FIND_BY_ID)
    public InventoryResponse findById(@PathVariable("id") long id) throws SuraException {
        return inventoryService.findById(id);
    }

    @Override
    @PostMapping
    public InventoryResponse create(@RequestBody InventoryRequest inventoryRequest) throws SuraException {
        return inventoryService.create(inventoryRequest);
    }

    @Override
    @GetMapping(Router.Inventory.FIND_BY_PRODUCT_ID)
    public Inventory findByProductId(@PathVariable("productId") long productId) throws SuraException {
        return inventoryService.findByProductId(productId);
    }

}
