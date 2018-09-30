package com.agendasala.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.agendasala.model.Agendamento;
import com.agendasala.repository.AgendamentoRepository;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

	@Autowired
	private AgendamentoRepository dao;

	public Boolean isHorarioLivre(Agendamento agendamento) {

		List<Agendamento> lista = dao.isAgendado(agendamento.getInicio(), agendamento.getFim());
		
		System.out.println(lista);

		if (lista == null || lista.isEmpty()) {

			return true;

		} else {
			return false;
		}

	}

	@PostMapping(value = "/gravar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> gravar(@RequestBody Agendamento agendamento) {
		try {

			if (isHorarioLivre(agendamento)) {

				dao.save(agendamento);

				return new ResponseEntity<String>("Agendamento Realizado", HttpStatus.CREATED);

			} else {

				return new ResponseEntity<String>("Horário não disponível", HttpStatus.NOT_ACCEPTABLE);

			}

		} catch (Exception ex) {
			return new ResponseEntity<String>("Erro: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Agendamento>> listar() {

		List<Agendamento> lista = new ArrayList<>();

		try {

			lista = (ArrayList<Agendamento>) dao.findAll();

			return new ResponseEntity<List<Agendamento>>(lista, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<List<Agendamento>>(lista, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/listarId/{id}")
	public ResponseEntity<Optional<Agendamento>> listarId(@PathVariable("id") String cod) {

		Optional<Agendamento> agendamento = null;

		try {
			agendamento = dao.findById(new Integer(cod));

			return new ResponseEntity<Optional<Agendamento>>(agendamento, HttpStatus.FOUND);

		} catch (Exception ex) {
			return new ResponseEntity<Optional<Agendamento>>(agendamento, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/listarSala/{id}")
	public ResponseEntity<List<Agendamento>> listarSala(@PathVariable("id") String cod) {

		List<Agendamento> agendamento = null;

		try {
			agendamento = dao.findBySala(new Integer(cod));

			return new ResponseEntity<List<Agendamento>>(agendamento, HttpStatus.FOUND);

		} catch (Exception ex) {
			return new ResponseEntity<List<Agendamento>>(agendamento, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/listarData/{data}")
	public ResponseEntity<List<Agendamento>> listarData(@PathVariable("data") String data) {

		List<Agendamento> agendamento = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		try {
			Date dataFormatada = sdf.parse(data);
			
			agendamento = dao.findByDate(dataFormatada);

			return new ResponseEntity<List<Agendamento>>(agendamento, HttpStatus.FOUND);

		} catch (Exception ex) {
			return new ResponseEntity<List<Agendamento>>(agendamento, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/atualizar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> atualizar(@RequestBody Agendamento agendamento) {
		try {

			if (isHorarioLivre(agendamento)) {

				dao.save(agendamento);

				return new ResponseEntity<String>("Agendamento Alterado", HttpStatus.OK);

			} else {

				return new ResponseEntity<String>("Horário não disponível", HttpStatus.NOT_ACCEPTABLE);

			}

		} catch (Exception ex) {
			return new ResponseEntity<String>("Erro; " + ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/excluir/{id}")
	public ResponseEntity<String> excluir(@PathVariable("id") String cod) {
		try {

			dao.deleteById(new Integer(cod));

			return new ResponseEntity<String>("Agendamento excluidos", HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<String>("Erro: " + ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
