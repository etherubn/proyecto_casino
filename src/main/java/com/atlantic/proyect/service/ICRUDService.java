package com.atlantic.proyect.service;

import java.util.List;

public interface ICRUDService<DTO,ID> {
    DTO save(DTO dto);
    List<DTO> findAll();
    void delete(ID id);
    DTO update(DTO dto, ID id);
    DTO findById(ID id);
}
