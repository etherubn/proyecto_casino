package com.atlantic.proyect.controller;

import com.atlantic.proyect.dto.request.create.TipoJuegoDtoRequest;
import com.atlantic.proyect.dto.response.GenericResponse;
import com.atlantic.proyect.service.ITipoJuegoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequestMapping("/v1/tipo-juego")
@RestController
@RequiredArgsConstructor
public class TipoJuegoController {
    private final ITipoJuegoService tipoJuegoService;

    @GetMapping
    public ResponseEntity<GenericResponse<TipoJuegoDtoRequest>> findAll() {
        return ResponseEntity.ok(new GenericResponse<>(tipoJuegoService.findAll(),200,"petición exitosa"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<TipoJuegoDtoRequest>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(tipoJuegoService.findById(id)),200,"peticion exitosa"));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<TipoJuegoDtoRequest>> save(@RequestBody @Valid TipoJuegoDtoRequest tipoJuegoDtoRequest) {
        return new ResponseEntity<>(new GenericResponse<>(Arrays.asList(tipoJuegoService.save(tipoJuegoDtoRequest)),201,"recurso creado"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipoJuegoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<TipoJuegoDtoRequest>> update(@PathVariable Long id, @RequestBody @Valid TipoJuegoDtoRequest tipoJuegoDtoRequest) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(tipoJuegoService.update(tipoJuegoDtoRequest,id)),200,"recurso actualizado"));
    }

}
