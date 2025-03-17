package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.exception.ModelNotFoundException;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.ICRUDService;
import com.atlantic.proyect.utils.MapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public abstract class CRUDImpl<T,DTO,ID> implements ICRUDService<DTO,ID> {

    protected abstract GenericRepo<T,ID> genericRepo();
    protected abstract Class<T> getEntityClass();
    protected abstract Class<DTO> getDTOClass();
    protected abstract void setId(T entity,ID id);
    protected MapperUtil mapperUtil;

    @Override
    @Transactional
    public DTO save(DTO dto) {
        T t = mapperUtil.map(dto,getEntityClass());
        T savedEntity = genericRepo().save(t);
        return mapperUtil.map(savedEntity,getDTOClass());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTO> findAll() {
        return mapperUtil.map(genericRepo().findAll(),getDTOClass());
    }

    @Override
    @Transactional
    public void delete(ID id) {
        genericRepo().findById(id).orElseThrow(() -> new ModelNotFoundException(getEntityClass().getName()));
        genericRepo().deleteById(id);
    }

    @Override
    @Transactional
    public DTO update(DTO dto, ID id) {
        genericRepo().findById(id).orElseThrow(() -> new ModelNotFoundException(getEntityClass().getName()));
        T entity = mapperUtil.map(dto,getEntityClass());
        setId(entity,id);
        T updatedEntity = genericRepo().save(entity);
        return mapperUtil.map(updatedEntity,getDTOClass());
    }

    @Override
    @Transactional(readOnly = true)
    public DTO findById(ID id) {
        T t = genericRepo().findById(id).orElseThrow(() -> new ModelNotFoundException(getEntityClass().getName()));
        return mapperUtil.map(t,getDTOClass());
    }
}
