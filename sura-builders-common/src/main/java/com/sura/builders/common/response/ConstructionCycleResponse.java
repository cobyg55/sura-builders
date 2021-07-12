package com.sura.builders.common.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConstructionCycleResponse {

    private long id;
    private String name;
    private int totalDays;
    private LocalDateTime createdAt;
}
