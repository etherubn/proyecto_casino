package com.atlantic.proyect.controller;

import com.atlantic.proyect.dto.request.create.JuegoDtoRequest;
import com.atlantic.proyect.dto.request.update.JuegoUpdateDtoRequest;
import com.atlantic.proyect.dto.response.GenericResponse;
import com.atlantic.proyect.service.IJuegoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequestMapping("/v1/juego")
@RestController
@RequiredArgsConstructor
public class JuegoController {
    private final IJuegoService juegoService;

    @GetMapping
    public ResponseEntity<GenericResponse<JuegoDtoRequest>> findAll() {
        return ResponseEntity.ok(new GenericResponse<>(juegoService.findAll(),200,"petición exitosa"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<JuegoDtoRequest>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(juegoService.findById(id)),200,"peticion exitosa"));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<JuegoDtoRequest>> save(@RequestBody @Valid JuegoDtoRequest juegoDtoRequest) {
        return new ResponseEntity<>(new GenericResponse<>(Arrays.asList(juegoService.save(juegoDtoRequest)),201,"recurso creado"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        juegoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GenericResponse<JuegoDtoRequest>> update(@PathVariable Long id, @RequestBody @Valid JuegoUpdateDtoRequest juegoUpdateDtoRequest) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(juegoService.update(juegoUpdateDtoRequest,id)),200,"recurso actualizado"));
    }

}
