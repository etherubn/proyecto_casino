package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.create.LocalDtoRequest;
import com.atlantic.proyect.entity.Local;
import com.atlantic.proyect.exception.AlreadyEntityExistException;
import com.atlantic.proyect.repository.LocalRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.ILocalService;
import com.atlantic.proyect.utils.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class LocalServiceImpl extends CRUDImpl<Local, LocalDtoRequest,Long> implements ILocalService {
    private final LocalRepo localRepo;

    public LocalServiceImpl(MapperUtil mapperUtil, LocalRepo localRepo) {
        super(mapperUtil);
        this.localRepo = localRepo;
    }

    @Override
    protected GenericRepo<Local, Long> genericRepo() {
        return localRepo;
    }

    @Override
    protected Class<Local> getEntityClass() {
        return Local.class;
    }

    @Override
    protected Class<LocalDtoRequest> getDTOClass() {
        return LocalDtoRequest.class;
    }

    @Override
    protected void setId(Local entity, Long aLong) {
        entity.setIdLocal(aLong);
    }

    @Override
    @Transactional
    public LocalDtoRequest save(LocalDtoRequest localDtoRequest) {
        if (localRepo.existsByNombreIgnoreCase(localDtoRequest.getNombre())) {
            Map<String,String> errors = new HashMap<>();
            errors.put("nombre", localDtoRequest.getNombre());
            throw new AlreadyEntityExistException("local", errors);
        }
        return super.save(localDtoRequest);
    }

    @Override
    public LocalDtoRequest update(LocalDtoRequest localDtoRequest, Long aLong) {
        if (localRepo.existsByNombreIgnoreCaseAndIdLocalIsNot(localDtoRequest.getNombre(), aLong)) {
            Map<String,String> errors = new HashMap<>();
            errors.put("nombre", localDtoRequest.getNombre());
            throw new AlreadyEntityExistException("local", errors);
        }

        return super.update(localDtoRequest, aLong);
    }
}
