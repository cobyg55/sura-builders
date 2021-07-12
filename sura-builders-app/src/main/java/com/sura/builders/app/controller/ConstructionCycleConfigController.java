package com.sura.builders.app.controller;

import com.sura.builders.api.IConstructionCycleConfigAPI;
import com.sura.builders.common.ex.SuraException;
import com.sura.builders.common.response.ConstructionCycleConfigResponse;
import com.sura.builders.common.routes.Router;
import com.sura.builders.core.service.ConstructionCycleConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Router.ConstructionCycleConfig.CONSTRUCTION_CYCLE_CONFIG_API)
public class ConstructionCycleConfigController implements IConstructionCycleConfigAPI {

    @Autowired
    private ConstructionCycleConfigService constructionCycleConfigService;

    @GetMapping(Router.ConstructionCycleConfig.FIND_BY_ID)
    public ConstructionCycleConfigResponse findById(@PathVariable("id") long id) throws SuraException {
        return constructionCycleConfigService.findById(id);
    }
}
