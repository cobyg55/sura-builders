package com.sura.builders.core.service;

import com.sura.builders.common.ex.ErrorCodes;
import com.sura.builders.common.ex.ErrorMessages;
import com.sura.builders.common.ex.SuraException;
import com.sura.builders.common.mapper.ConstructionCycleMapper;
import com.sura.builders.common.request.ConstructionCycleRequest;
import com.sura.builders.common.response.ConstructionCycleResponse;
import com.sura.builders.domain.model.ConstructionCycle;
import com.sura.builders.domain.repository.ConstructionCycleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ConstructionCycleService {

    @Autowired
    private ConstructionCycleRepository constructionCycleRepository;

    @Autowired
    private MessageService messageService;

    public List<ConstructionCycleResponse> findAll() {
        try {
            return constructionCycleRepository.findAll().stream().map(ConstructionCycleMapper::mapToConstructionCycleResponse).collect(Collectors.toList());
        }catch (Exception ex){
            log.error("ConstructionCycleService:findAll() --Error[{}]",ex.getMessage());
            throw ex;

        }
    }

    public ConstructionCycleResponse findById(long id) throws SuraException {
        try{
            return ConstructionCycleMapper.mapToConstructionCycleResponse(getById(id));
        }catch (SuraException ex){
            log.error("ConstructionCycleService:findById() --Error[{}]", ex.getMessage());
            throw new SuraException(ErrorCodes.BAD_REQUEST, ex.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            log.error("ConstructionCycleService:findById() --Error[{}]", ex.getMessage());
            throw ex;
        }
    }

    public ConstructionCycleResponse create(ConstructionCycleRequest constructionCycleRequest) throws SuraException {
        try {
            Optional<ConstructionCycle> constructionCycleByName = constructionCycleRepository.findByName(constructionCycleRequest.getName());
            if (constructionCycleByName.isPresent()) {
                throw new SuraException(ErrorCodes.VALIDATION, messageService.getMessage(ErrorMessages.CONSTRUCTION_CYCLE_NAME_EXISTS, new Object[]{constructionCycleRequest.getName()}), HttpStatus.BAD_REQUEST);
            }
            return ConstructionCycleMapper.mapToConstructionCycleResponse(constructionCycleRepository.save(ConstructionCycleMapper.mapToConstructionCycle(constructionCycleRequest)));
        }catch (SuraException ex){
            log.error("ConstructionCycleService:create() --Error[{}]", ex.getMessage());
            throw new SuraException(ErrorCodes.BAD_REQUEST, ex.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            log.error("ConstructionCycleService:create() --Error[{}]", ex.getMessage());
            throw ex;
        }
    }

    public ConstructionCycle getById(long id) throws SuraException {
        return constructionCycleRepository.findById(id).orElseThrow(() -> new SuraException(ErrorCodes.VALIDATION, messageService.getMessage(ErrorMessages.CONSTRUCTION_CYCLE_NOT_FOUND, new Object[]{id}), HttpStatus.BAD_REQUEST));
    }
}
