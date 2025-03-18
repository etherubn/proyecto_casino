package com.atlantic.proyect.service;

import com.atlantic.proyect.dto.request.create.PersonaDtoRequest;
import com.atlantic.proyect.dto.request.update.PersonaUpdateDtoRequest;

import java.util.Map;


public interface IPersonaService extends ICRUDService<PersonaDtoRequest,Long> {
    PersonaDtoRequest update(PersonaUpdateDtoRequest personaUpdateDtoRequest, Long aLong);
    Map<String,String> verifyCreateDniAndTelefono(PersonaDtoRequest personaDtoRequest);
    Map<String,String> verifyUpdateDniAndTelefono(PersonaUpdateDtoRequest personaUpdateDtoRequest);

}
