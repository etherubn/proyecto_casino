package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.PersonaDtoRequest;
import com.atlantic.proyect.dto.request.PersonaUpdateDtoRequest;
import com.atlantic.proyect.entity.Persona;
import com.atlantic.proyect.exception.AlreadyEntityExistException;
import com.atlantic.proyect.exception.ModelNotFoundException;
import com.atlantic.proyect.repository.PersonaRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.IPersonaService;
import com.atlantic.proyect.utils.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PersonaServiceImpl extends CRUDImpl<Persona, PersonaDtoRequest, Long> implements IPersonaService {
    private final PersonaRepo personaRepo;

    public PersonaServiceImpl(MapperUtil mapperUtil, PersonaRepo personaRepo) {
        super(mapperUtil);
        this.personaRepo = personaRepo;
    }

    @Override
    protected GenericRepo<Persona, Long> genericRepo() {
        return personaRepo;
    }

    @Override
    protected Class<Persona> getEntityClass() {
        return Persona.class;
    }

    @Override
    protected Class<PersonaDtoRequest> getDTOClass() {
        return PersonaDtoRequest.class;
    }

    @Override
    protected void setId(Persona entity, Long aLong) {
        entity.setIdPersona(aLong);
    }

    @Override
    @Transactional
    public PersonaDtoRequest save(PersonaDtoRequest personaDtoRequest) {

        Persona createdPersona = personaRepo.save(mapperUtil.map(personaDtoRequest, Persona.class));

        return mapperUtil.map(createdPersona, PersonaDtoRequest.class);
    }


    @Override
    @Transactional
    public PersonaDtoRequest udpate(PersonaUpdateDtoRequest personaUpdateDtoRequest, Long aLong) {
        Persona persona = personaRepo.findById(aLong).orElseThrow(() -> new ModelNotFoundException("usuario"));
        PersonaDtoRequest personaDtoRequest = mapperUtil.map(personaUpdateDtoRequest, PersonaDtoRequest.class);


        Optional.ofNullable(personaUpdateDtoRequest.getNombre())
                .ifPresent(nombre -> persona.setNombre(nombre));

        Optional.ofNullable(personaUpdateDtoRequest.getApellidoPaterno())
                .ifPresent(apellido -> persona.setApellidoPaterno(apellido));

        Optional.ofNullable(personaUpdateDtoRequest.getApellidoMaterno())
                .ifPresent(apellido -> persona.setApellidoMaterno(apellido));

        Optional.ofNullable(personaUpdateDtoRequest.getDni())
                .ifPresent(dni -> persona.setDni(dni));

        Optional.ofNullable(personaUpdateDtoRequest.getGenero())
                .ifPresent(genero -> persona.setGenero(genero));

        Optional.ofNullable(personaUpdateDtoRequest.getTelefono())
                .ifPresent(telefono -> persona.setTelefono(telefono));

        Optional.ofNullable(personaUpdateDtoRequest.getDireccion())
                .ifPresent(direccion -> persona.setDireccion(direccion));

        Optional.ofNullable(personaUpdateDtoRequest.getFechaNacimiento())
                .ifPresent(fechaNacimiento -> persona.setFechaNacimiento(fechaNacimiento));

        return mapperUtil.map(personaRepo.save(persona), PersonaDtoRequest.class);
    }

    public Map<String, String> verifyCreateDniAndTelefono(PersonaDtoRequest personaDtoRequest) {
        String dni = personaDtoRequest.getDni();
        String telefono = personaDtoRequest.getTelefono();

        return Stream.of(
                        Optional.ofNullable(dni)
                                .filter(dniExist -> personaRepo.existsByDni(dniExist))
                                .map(d-> Map.entry("dni",dni))
                        ,
                        Optional.ofNullable(telefono)
                                .filter(telefonoExist -> personaRepo.existsByTelefono(telefonoExist))
                                .map(d-> Map.entry("telefono",telefono))

                ).flatMap(optional -> optional.isPresent() ? Stream.of(optional.get()) : Stream.empty())
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
    }


}
