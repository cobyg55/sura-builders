package com.sura.builders.common.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BuildersResponse {

    private long id;
    private String code;
    private String status;
    private List<ConstructionCycleConfigResponse> constructionCycleConfig;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
}
