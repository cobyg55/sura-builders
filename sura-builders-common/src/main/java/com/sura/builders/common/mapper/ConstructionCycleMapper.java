package com.sura.builders.common.mapper;

import com.sura.builders.common.request.ConstructionCycleRequest;
import com.sura.builders.common.response.ConstructionCycleResponse;
import com.sura.builders.domain.model.ConstructionCycle;

import java.time.LocalDateTime;

public class ConstructionCycleMapper {

    public static ConstructionCycleResponse mapToConstructionCycleResponse(ConstructionCycle constructionCycle) {

        ConstructionCycleResponse constructionCycleResponse = new ConstructionCycleResponse();
        constructionCycleResponse.setId(constructionCycle.getId());
        constructionCycleResponse.setName(constructionCycle.getName());
        constructionCycleResponse.setTotalDays(constructionCycle.getTotalDays());
        constructionCycleResponse.setCreatedAt(LocalDateTime.now());
        return constructionCycleResponse;
      }

    public static ConstructionCycle mapToConstructionCycle(ConstructionCycleRequest constructionCycleRequest) {
        ConstructionCycle constructionCycle = new ConstructionCycle();
        constructionCycle.setName(constructionCycleRequest.getName());
        constructionCycle.setTotalDays(constructionCycleRequest.getTotalDays());
        constructionCycle.setCreatedAt(LocalDateTime.now());
        return constructionCycle;
    }


}
