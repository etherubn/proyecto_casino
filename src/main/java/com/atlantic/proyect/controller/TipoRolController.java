package com.atlantic.proyect.controller;

import com.atlantic.proyect.dto.request.create.TipoRolDtoRequest;
import com.atlantic.proyect.dto.response.GenericResponse;
import com.atlantic.proyect.service.ITipoRolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequestMapping(value = "/v1/tipo-rol",produces = "application/json")
@RestController
@RequiredArgsConstructor
public class TipoRolController {
    private final ITipoRolService tipoRolService;

    @GetMapping
    public ResponseEntity<GenericResponse<TipoRolDtoRequest>> findAll() {
        return ResponseEntity.ok(new GenericResponse<>(tipoRolService.findAll(),200,"petición exitosa"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<TipoRolDtoRequest>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(tipoRolService.findById(id)),200,"peticion exitosa"));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<TipoRolDtoRequest>> save(@RequestBody @Valid TipoRolDtoRequest tipoRolDtoRequest) {
        return new ResponseEntity<>(new GenericResponse<>(Arrays.asList(tipoRolService.save(tipoRolDtoRequest)),201,"recurso creado"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipoRolService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<TipoRolDtoRequest>> update(@PathVariable Long id, @RequestBody @Valid TipoRolDtoRequest tipoRolDtoRequest) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(tipoRolService.update(tipoRolDtoRequest,id)),200,"recurso actualizado"));
    }

}
