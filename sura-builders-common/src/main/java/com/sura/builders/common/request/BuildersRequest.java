package com.sura.builders.common.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BuildersRequest {

        private List<ConstructionCycleConfigRequest> constructionCycleConfig;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
}
