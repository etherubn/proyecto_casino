package com.atlantic.proyect.controller;

import com.atlantic.proyect.dto.request.create.AreaDtoRequest;
import com.atlantic.proyect.dto.response.GenericResponse;
import com.atlantic.proyect.service.IAreaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequestMapping("/v1/area")
@RestController
@RequiredArgsConstructor
public class AreaController {
    private final IAreaService areaService;

    @GetMapping
    public ResponseEntity<GenericResponse<AreaDtoRequest>> findAll() {
        return ResponseEntity.ok(new GenericResponse<>(areaService.findAll(),200,"petición exitosa"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<AreaDtoRequest>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(areaService.findById(id)),200,"peticion exitosa"));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<AreaDtoRequest>> save(@RequestBody @Valid AreaDtoRequest areaDtoRequest) {
        return new ResponseEntity<>(new GenericResponse<>(Arrays.asList(areaService.save(areaDtoRequest)),201,"recurso creado"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        areaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<AreaDtoRequest>> update(@PathVariable Long id, @RequestBody @Valid AreaDtoRequest areaDtoRequest) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(areaService.update(areaDtoRequest,id)),200,"recurso actualizado"));
    }

}
