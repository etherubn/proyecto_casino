package com.atlantic.proyect.controller;

import com.atlantic.proyect.dto.request.create.TarjetaDtoRequest;
import com.atlantic.proyect.dto.request.update.TarjetaRecargaDtoRequest;
import com.atlantic.proyect.dto.response.GenericResponse;
import com.atlantic.proyect.service.ITarjetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequestMapping("/v1/tarjeta")
@RestController
@RequiredArgsConstructor
public class TarjetaController {
    private final ITarjetaService tarjetaService;

    @GetMapping
    public ResponseEntity<GenericResponse<TarjetaDtoRequest>> findAll() {
        return ResponseEntity.ok(new GenericResponse<>(tarjetaService.findAll(),200,"petición exitosa"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<TarjetaDtoRequest>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(tarjetaService.findById(id)),200,"peticion exitosa"));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<TarjetaDtoRequest>> save(@RequestBody @Valid TarjetaDtoRequest tarjetaDtoRequest) {
        return new ResponseEntity<>(new GenericResponse<>(Arrays.asList(tarjetaService.save(tarjetaDtoRequest)),201,"recurso creado"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tarjetaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<TarjetaDtoRequest>> update(@PathVariable Long id, @RequestBody @Valid TarjetaDtoRequest tarjetaDtoRequest) {
        return ResponseEntity.ok(new GenericResponse<>(Arrays.asList(tarjetaService.update(tarjetaDtoRequest,id)),200,"recurso actualizado"));
    }

    @PatchMapping("/recargar/{codigo}")
    public ResponseEntity<GenericResponse<TarjetaDtoRequest>> recargarTarjeta(@PathVariable String codigo, @RequestBody @Valid TarjetaRecargaDtoRequest tarjetaRecargaDtoRequest) {
        return new ResponseEntity<>(new GenericResponse<>(Arrays.asList(tarjetaService.recargarTarjeta(codigo,tarjetaRecargaDtoRequest)),200,"tarjeta actualizada"), HttpStatus.OK);
    }

}
