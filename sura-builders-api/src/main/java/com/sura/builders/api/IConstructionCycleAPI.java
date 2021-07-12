package com.sura.builders.api;

import com.sura.builders.common.ex.SuraException;
import com.sura.builders.common.request.ConstructionCycleRequest;
import com.sura.builders.common.response.ConstructionCycleResponse;

import java.util.List;

public interface IConstructionCycleAPI {

    List<ConstructionCycleResponse> findAll();

    ConstructionCycleResponse findById(long id) throws SuraException;

    ConstructionCycleResponse create(ConstructionCycleRequest constructionCycleRequest) throws SuraException;

}
