package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.create.AreaDtoRequest;
import com.atlantic.proyect.entity.Area;
import com.atlantic.proyect.repository.AreaRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.IAreaService;
import com.atlantic.proyect.utils.MapperUtil;
import org.springframework.stereotype.Service;


@Service
public class AreaServiceImpl extends CRUDImpl<Area, AreaDtoRequest,Long> implements IAreaService {
    private final AreaRepo areaRepo;

    public AreaServiceImpl(MapperUtil mapperUtil,AreaRepo areaRepo) {
        super(mapperUtil);
        this.areaRepo = areaRepo;
    }

    @Override
    protected GenericRepo<Area, Long> genericRepo() {
        return areaRepo;
    }

    @Override
    protected Class<Area> getEntityClass() {
        return Area.class;
    }

    @Override
    protected Class<AreaDtoRequest> getDTOClass() {
        return AreaDtoRequest.class;
    }

    @Override
    protected void setId(Area entity, Long aLong) {
        entity.setIdArea(aLong);
    }
}
