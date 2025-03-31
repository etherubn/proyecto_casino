package com.atlantic.proyect.controller;

import com.atlantic.proyect.dto.request.create.TrabajadorDtoRequest;
import com.atlantic.proyect.dto.response.GenericResponse;
import com.atlantic.proyect.service.ITrabajadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequestMapping("/v1/trabajador")
@RestController
@RequiredArgsConstructor
public class TrabajadorController {
    private final ITrabajadorService trabajadorService;

    @GetMapping
    public ResponseEntity<GenericResponse<TrabajadorDtoRequest>> findAll() {
        return ResponseEntity.ok(new GenericResponse<>(trabajadorService.findAll(),200,"petición exitosa"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<TrabajadorDtoRequest>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(trabajadorService.findById(id)),200,"peticion exitosa"));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<TrabajadorDtoRequest>> save(@RequestBody @Valid TrabajadorDtoRequest trabajadorDtoRequest) {
        return new ResponseEntity<>(new GenericResponse<>(Arrays.asList(trabajadorService.save(trabajadorDtoRequest)),201,"recurso creado"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trabajadorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<TrabajadorDtoRequest>> update(@PathVariable Long id, @RequestBody @Valid TrabajadorDtoRequest trabajadorDtoRequest) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(trabajadorService.update(trabajadorDtoRequest,id)),200,"recurso actualizado"));
    }

}
