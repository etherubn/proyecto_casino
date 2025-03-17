package com.atlantic.proyect.controller;

import com.atlantic.proyect.dto.request.LocalDtoRequest;
import com.atlantic.proyect.dto.response.GenericResponse;
import com.atlantic.proyect.service.ILocalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequestMapping("/v1/local")
@RestController
@RequiredArgsConstructor
public class LocalController {
    private final ILocalService localService;

    @GetMapping
    public ResponseEntity<GenericResponse<LocalDtoRequest>> findAll() {
        return ResponseEntity.ok(new GenericResponse<>(localService.findAll(),200,"petición exitosa"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<LocalDtoRequest>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(localService.findById(id)),200,"peticion exitosa"));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<LocalDtoRequest>> save(@RequestBody @Valid LocalDtoRequest localDtoRequest) {
        return new ResponseEntity<>(new GenericResponse<>(Arrays.asList(localService.save(localDtoRequest)),201,"recurso creado"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        localService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<LocalDtoRequest>> update(@PathVariable Long id, @RequestBody @Valid LocalDtoRequest localDtoRequest) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(localService.update(localDtoRequest,id)),200,"recurso actualizado"));
    }

}
