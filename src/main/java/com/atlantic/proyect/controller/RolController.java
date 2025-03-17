package com.atlantic.proyect.controller;

import com.atlantic.proyect.dto.request.RolDtoRequest;
import com.atlantic.proyect.dto.response.GenericResponse;
import com.atlantic.proyect.service.IRolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequestMapping("/v1/rol")
@RestController
@RequiredArgsConstructor
public class RolController {
    private final IRolService rolService;

    @GetMapping
    public ResponseEntity<GenericResponse<RolDtoRequest>> findAll() {
        return ResponseEntity.ok(new GenericResponse<>(rolService.findAll(),200,"petición exitosa"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<RolDtoRequest>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(rolService.findById(id)),200,"peticion exitosa"));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<RolDtoRequest>> save(@RequestBody @Valid RolDtoRequest rolDtoRequest) {
        return new ResponseEntity<>(new GenericResponse<>(Arrays.asList(rolService.save(rolDtoRequest)),201,"recurso creado"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rolService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<RolDtoRequest>> update(@PathVariable Long id, @RequestBody @Valid RolDtoRequest rolDtoRequest) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(rolService.update(rolDtoRequest,id)),200,"recurso actualizado"));
    }

}
