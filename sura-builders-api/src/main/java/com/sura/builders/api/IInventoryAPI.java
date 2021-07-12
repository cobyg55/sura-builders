package com.sura.builders.api;

import com.sura.builders.common.ex.SuraException;
import com.sura.builders.domain.model.Inventory;
import com.sura.builders.common.request.*;
import com.sura.builders.common.response.InventoryResponse;

import java.util.List;

public interface IInventoryAPI {

    List<InventoryResponse> findAll();

    InventoryResponse findById(long id) throws SuraException;

    InventoryResponse create(InventoryRequest inventoryRequest) throws SuraException;

    Inventory findByProductId(long productId) throws SuraException;

}
