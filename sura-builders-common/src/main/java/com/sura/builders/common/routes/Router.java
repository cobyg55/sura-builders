package com.sura.builders.common.routes;

public class Router {
    public static final String API = "/api/inventories";

    public static class Inventory {
        public static final String INVENTORY_API = API + "/inventory";
        public static final String FIND_BY_ID = "/{id}";
        public static final String FIND_BY_PRODUCT_ID = "/product/{productId}";
    }

    public static class Product {
        public static final String PRODUCT_API = API + "/product";
        public static final String FIND_PRODUCT_BY_ID = "/{id}";
        public static final String UPDATE = "/{id}";
    }

    public static class ConstructionCycle {
        public static final String CONSTRUCTION_CYCLE_API = API + "/construction_cycle";
        public static final String FIND_BY_ID = "/{id}";
    }

    public static class ConstructionCycleConfig {
        public static final String CONSTRUCTION_CYCLE_CONFIG_API = API + "/cycle-config";
        public static final String FIND_BY_ID = "/{id}";
    }



}
