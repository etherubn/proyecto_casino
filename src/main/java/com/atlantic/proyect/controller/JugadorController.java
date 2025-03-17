package com.atlantic.proyect.controller;

import com.atlantic.proyect.dto.request.JugadorDtoRequest;
import com.atlantic.proyect.dto.response.GenericResponse;
import com.atlantic.proyect.service.IJugadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequestMapping("/v1/jugador")
@RestController
@RequiredArgsConstructor
public class JugadorController {
    private final IJugadorService jugadorService;

    @GetMapping
    public ResponseEntity<GenericResponse<JugadorDtoRequest>> findAll() {
        return ResponseEntity.ok(new GenericResponse<>(jugadorService.findAll(),200,"petición exitosa"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<JugadorDtoRequest>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(jugadorService.findById(id)),200,"peticion exitosa"));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<JugadorDtoRequest>> save(@RequestBody @Valid JugadorDtoRequest jugadorDtoRequest) {
        return new ResponseEntity<>(new GenericResponse<>(Arrays.asList(jugadorService.crearJugador(jugadorDtoRequest)),201,"recurso creado"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jugadorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<JugadorDtoRequest>> update(@PathVariable Long id, @RequestBody @Valid JugadorDtoRequest jugadorDtoRequest) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(jugadorService.update(jugadorDtoRequest,id)),200,"recurso actualizado"));
    }

}
