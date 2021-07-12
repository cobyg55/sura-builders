package com.sura.builders.common.mapper;

import com.sura.builders.common.request.ConstructionCycleConfigRequest;
import com.sura.builders.common.response.ConstructionCycleConfigResponse;
import com.sura.builders.domain.model.ConstructionCycle;
import com.sura.builders.domain.model.ConstructionCycleConfig;

import java.time.LocalDateTime;

public class ConstructionCycleConfigMapper {

    public static ConstructionCycleConfigResponse mapToConstructionCycleConfigResponse(ConstructionCycleConfig constructionCycleConfig) {

        ConstructionCycleConfigResponse constructionCycleConfigResponse = new ConstructionCycleConfigResponse();
        constructionCycleConfigResponse.setId(constructionCycleConfig.getId());
        constructionCycleConfigResponse.setName(constructionCycleConfig.getConstructionCycle().getName());
        constructionCycleConfigResponse.setTotalDays(constructionCycleConfig.getConstructionCycle().getTotalDays());
        return constructionCycleConfigResponse;
    }

    public static ConstructionCycleConfig mapToConstructionCycleConfig(ConstructionCycleConfigRequest constructionCycleConfigRequest, ConstructionCycle constructionCycle) {
        ConstructionCycleConfig constructionCycleConfig = new ConstructionCycleConfig();
        constructionCycleConfig.setConstructionCycle(constructionCycle);
        constructionCycleConfig.setDayLate(constructionCycleConfigRequest.getDayLate());
        constructionCycleConfig.setCreatedAt(LocalDateTime.now());
        return constructionCycleConfig;
    }

}
