package com.sura.builders.common.mapper;

import com.sura.builders.common.enums.BuildersStatus;
import com.sura.builders.common.request.BuildersRequest;
import com.sura.builders.common.response.BuildersResponse;
import com.sura.builders.domain.model.Builders;
import com.sura.builders.domain.model.ConstructionCycleConfig;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BuildersMapper {

    public static BuildersResponse mapToBuildersResponse(Builders builders) {
        BuildersResponse buildersResponse = new BuildersResponse();
        buildersResponse.setId(builders.getId());
        buildersResponse.setCode(builders.getCode());
        buildersResponse.setStatus(builders.getStatus());
        buildersResponse.setCreatedAt(builders.getCreatedAt());
        return buildersResponse;
    }

    public static Builders mapToBuilders(String buildersCode, BuildersRequest buildersRequest, List<ConstructionCycleConfig> constructionCycleConfigs) {
        Builders builders = new Builders();
        builders.setCode(buildersCode);
        builders.setStatus(BuildersStatus.ACTIVE.name());
        builders.setConstructionCycleConfigs(constructionCycleConfigs);
        builders.setStartDate(buildersRequest.getStartDate());
        builders.setEndDate(buildersRequest.getEndDate());
        builders.setCreatedAt(LocalDateTime.now());
        return builders;
    }
}
