package com.sura.builders.core.service;

import com.google.common.base.Strings;
import com.sura.builders.common.enums.BuildersStatus;
import com.sura.builders.common.ex.ErrorCodes;
import com.sura.builders.common.ex.ErrorMessages;
import com.sura.builders.common.ex.SuraException;
import com.sura.builders.common.mapper.BuildersMapper;
import com.sura.builders.common.mapper.ConstructionCycleConfigMapper;
import com.sura.builders.common.request.BuildersRequest;
import com.sura.builders.common.response.BuildersResponse;
import com.sura.builders.domain.model.Builders;
import com.sura.builders.domain.model.ConstructionCycleConfig;
import com.sura.builders.domain.repository.BuildersRepository;
import com.sura.builders.domain.repository.ConstructionCycleConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BuildersService {

    @Autowired
    private BuildersRepository buildersRepository;

    @Autowired
    private ConstructionCycleService constructionCycleService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ConstructionCycleConfigRepository constructionCycleConfigRepository;

    public List<BuildersResponse> findAll() {
        return buildersRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream().map(BuildersMapper::mapToBuildersResponse).collect(Collectors.toList());
    }

     public BuildersResponse findById(long id) throws SuraException {
        try {
            return BuildersMapper.mapToBuildersResponse(getById(id));
        }catch (SuraException ex){
            log.error("BuildersService:findById() --Error[{}]",ex.getMessage());
            throw new SuraException(ErrorCodes.BAD_REQUEST, ex.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            log.error("BuildersService:findById() --Error[{}]", ex.getMessage());
            throw ex;
        }
    }

    public Builders getById(long id) throws SuraException {
        return buildersRepository.findById(id).orElseThrow(() -> new SuraException(ErrorCodes.VALIDATION, messageService.getMessage(ErrorMessages.BUILDERS_NOT_FOUND, new Object[]{id}), HttpStatus.BAD_REQUEST));
    }

    @Transactional(rollbackOn = Exception.class)
    public BuildersResponse create(BuildersRequest buildersRequest) throws SuraException {
        try {
            String buildersCode = Strings.padStart(buildersRepository.getBuildersCodeSeq().toString(), 8, '0');
            Optional<Builders> buildersByCode = buildersRepository.findByCode(buildersCode);
            if (buildersByCode.isPresent()) {
                throw new SuraException(ErrorCodes.VALIDATION, messageService.getMessage(ErrorMessages.BUILDERS_NOT_FOUND, new Object[]{buildersByCode}), HttpStatus.BAD_REQUEST);
            }

            if (buildersRequest.getEndDate().isBefore(buildersRequest.getStartDate())) {
                throw new SuraException(ErrorCodes.VALIDATION, messageService.getMessage(ErrorMessages.BUILDERS_END_BEFORE_START_DATE), HttpStatus.BAD_REQUEST);
            }

            if (buildersRequest.getConstructionCycleConfig().isEmpty()) {
                throw new SuraException(ErrorCodes.VALIDATION, messageService.getMessage(ErrorMessages.BUILDERS_CYCLES_NOT_FOUND), HttpStatus.BAD_REQUEST);
            }
            List<ConstructionCycleConfig> rentalCycleConfigs = buildersRequest.getConstructionCycleConfig().stream().map(constructionCycleContractRequest -> ConstructionCycleConfigMapper.mapToConstructionCycleConfig(constructionCycleContractRequest, constructionCycleService.getById(constructionCycleContractRequest.getId()))).collect(Collectors.toList());
            List<ConstructionCycleConfig> savedConstructionCycleConfigs = constructionCycleConfigRepository.saveAll(rentalCycleConfigs);
            return BuildersMapper.mapToBuildersResponse(buildersRepository.save(BuildersMapper.mapToBuilders(buildersCode, buildersRequest, savedConstructionCycleConfigs)));
        }catch (SuraException ex){
            log.error("BuildersService:create() --Error[{}]",ex.getMessage());
            throw new SuraException(ErrorCodes.BAD_REQUEST, ex.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            log.error("BuildersService:create() --Error[{}]", ex.getMessage());
            throw ex;
        }
    }

}
