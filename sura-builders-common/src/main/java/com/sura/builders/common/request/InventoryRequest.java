package com.sura.builders.common.request;

import lombok.Data;

@Data
public class InventoryRequest {
    private long quantity;
    private long unitCost;
    private long productId;
}
