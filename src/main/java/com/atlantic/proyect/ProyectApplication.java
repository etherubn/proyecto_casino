package com.atlantic.proyect;

import com.atlantic.proyect.dto.request.AreaDtoRequest;
import com.atlantic.proyect.dto.request.PersonaDtoRequest;
import com.atlantic.proyect.dto.request.TipoRolDtoRequest;
import com.atlantic.proyect.entity.Area;
import com.atlantic.proyect.entity.Persona;
import com.atlantic.proyect.entity.Trabajador;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
@RequiredArgsConstructor
public class ProyectApplication implements CommandLineRunner {
	private final ModelMapper modelMapper;

	public static void main(String[] args) {
		SpringApplication.run(ProyectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PersonaDtoRequest personaDtoRequest = new PersonaDtoRequest(null,"Rafael","Moreno","Postg","47386583",false,"922665937","casdasdasdasdasdasdsada", LocalDate.now());
		System.out.println(personaDtoRequest);

		System.out.println(modelMapper.map(personaDtoRequest, Persona.class));
	}
}
