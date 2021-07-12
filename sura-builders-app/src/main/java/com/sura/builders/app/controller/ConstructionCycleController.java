package com.sura.builders.app.controller;

import com.sura.builders.api.IConstructionCycleAPI;
import com.sura.builders.common.ex.SuraException;
import com.sura.builders.common.request.ConstructionCycleRequest;
import com.sura.builders.common.response.ConstructionCycleResponse;
import com.sura.builders.common.routes.Router;
import com.sura.builders.core.service.ConstructionCycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Router.ConstructionCycle.CONSTRUCTION_CYCLE_API)
public class ConstructionCycleController implements IConstructionCycleAPI {

    @Autowired
    private ConstructionCycleService constructionCycleService;

    @Override
    @GetMapping
    public List<ConstructionCycleResponse> findAll() {
        return constructionCycleService.findAll();
    }

    @Override
    @GetMapping(Router.ConstructionCycle.FIND_BY_ID)
    public ConstructionCycleResponse findById(@PathVariable("id") long id) throws SuraException {
        return constructionCycleService.findById(id);
    }

    @Override
    @PostMapping
    public ConstructionCycleResponse create(@RequestBody ConstructionCycleRequest constructionCycleRequest) throws SuraException {
        return constructionCycleService.create(constructionCycleRequest);
    }
}
