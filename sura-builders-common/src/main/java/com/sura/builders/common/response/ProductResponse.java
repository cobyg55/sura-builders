package com.sura.builders.common.response;

import lombok.Data;

import java.util.Date;

@Data
public class ProductResponse {
    private long id;
    private String name;
    private String reference;
    private Date createdAt;
}
