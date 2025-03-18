package com.atlantic.proyect.controller;

import com.atlantic.proyect.dto.request.create.RegistroJuegoDtoRequest;
import com.atlantic.proyect.dto.response.GenericResponse;
import com.atlantic.proyect.service.IRegistroJuegoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequestMapping("/v1/registro-juego")
@RestController
@RequiredArgsConstructor
public class RegistroJuegoController {
    private final IRegistroJuegoService registroJuegoService;

    @GetMapping
    public ResponseEntity<GenericResponse<RegistroJuegoDtoRequest>> findAll() {
        return ResponseEntity.ok(new GenericResponse<>(registroJuegoService.findAll(),200,"petición exitosa"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<RegistroJuegoDtoRequest>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(registroJuegoService.findById(id)),200,"peticion exitosa"));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<RegistroJuegoDtoRequest>> save(@RequestBody @Valid RegistroJuegoDtoRequest registroJuegoDtoRequest) {
        return new ResponseEntity<>(new GenericResponse<>(Arrays.asList(registroJuegoService.jugar(registroJuegoDtoRequest)),201,"recurso creado"), HttpStatus.CREATED);
    }

}
