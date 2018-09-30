	package com.agendasala.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.agendasala.model.Sala;
import com.agendasala.repository.SalaRepository;

@RestController
@RequestMapping("/salas")
public class SalaController {

	@Autowired
	private SalaRepository dao;

	@PostMapping(value = "/criar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> create(@RequestBody Sala sala) {
		try {

			dao.save(sala);

			return new ResponseEntity<String>("Sala Criada", HttpStatus.CREATED);

		} catch (Exception ex) {
			return new ResponseEntity<String>("Erro: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sala>> findAll() {

		List<Sala> lista = new ArrayList<>();

		try {

			lista = (ArrayList<Sala>) dao.findAll();

			return new ResponseEntity<List<Sala>>(lista, HttpStatus.OK);

		} catch (Exception ex) {

			return new ResponseEntity<List<Sala>>(lista, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = "/listarId/{id}")
	public ResponseEntity<Optional<Sala>> listarId(@PathVariable("id") String cod) {

		Optional<Sala> sala = null;

		try {

			sala = dao.findById(new Integer(cod));

			return new ResponseEntity<Optional<Sala>>(sala, HttpStatus.FOUND);

		} catch (Exception ex) {
			return new ResponseEntity<Optional<Sala>>(sala, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/atualizar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> atuzalizar(@RequestBody Sala sala) {
		try {

			dao.save(sala);

			return new ResponseEntity<String>("Sala Alterada", HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<String>("Erro; " + ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/excluir/{id}")
	public ResponseEntity<String> excluir(@PathVariable("id") String cod) {
		try {

			dao.deleteById(new Integer(cod));

			return new ResponseEntity<String>("Sala excluída", HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<String>("Erro: " + ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
