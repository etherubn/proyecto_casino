package com.atlantic.proyect.service;

import com.atlantic.proyect.dto.request.PersonaDtoRequest;
import com.atlantic.proyect.dto.request.PersonaUpdateDtoRequest;

import java.util.Map;


public interface IPersonaService extends ICRUDService<PersonaDtoRequest,Long> {
    PersonaDtoRequest udpate(PersonaUpdateDtoRequest personaUpdateDtoRequest, Long aLong);
    Map<String,String> verifyCreateDniAndTelefono(PersonaDtoRequest personaDtoRequest);
}
