package com.sura.builders.core.service;

import com.sura.builders.common.ex.ErrorCodes;
import com.sura.builders.common.ex.ErrorMessages;
import com.sura.builders.common.ex.SuraException;
import com.sura.builders.common.mapper.ConstructionCycleConfigMapper;
import com.sura.builders.common.response.ConstructionCycleConfigResponse;
import com.sura.builders.domain.model.ConstructionCycleConfig;
import com.sura.builders.domain.repository.ConstructionCycleConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConstructionCycleConfigService {

    @Autowired
    private ConstructionCycleConfigRepository constructionCycleConfigRepository;

    @Autowired
    private MessageService messageService;

    public ConstructionCycleConfigResponse findById(long id) throws SuraException {
        try{
            return ConstructionCycleConfigMapper.mapToConstructionCycleConfigResponse(getById(id));
        }catch (SuraException ex){
            log.error("ConstructionCycleConfigService:findById() --Error[{}]", ex.getMessage());
            throw new SuraException(ErrorCodes.BAD_REQUEST, ex.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            log.error("ConstructionCycleConfigService:findById() --Error[{}]", ex.getMessage());
            throw ex;
        }
    }

    public ConstructionCycleConfig getById(long id) throws SuraException {
        return constructionCycleConfigRepository.findById(id).orElseThrow(() -> new SuraException(ErrorCodes.VALIDATION, messageService.getMessage(ErrorMessages.CONSTRUCTION_CYCLE_NOT_FOUND, new Object[]{id}), HttpStatus.BAD_REQUEST));
    }
}
