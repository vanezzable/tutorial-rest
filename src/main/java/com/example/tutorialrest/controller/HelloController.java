package com.example.tutorialrest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tutorialrest.dao.IPersonaDao;
import com.example.tutorialrest.model.Persona;

@RestController
public class HelloController {

	@Autowired
	private IPersonaDao persDao;

	@GetMapping("/persona/find-by-name")
	public Persona getPersonaByNome(@RequestParam("name") String name) {
		Persona p = persDao.getPersonaByName(name);
		return p;
	}

//	@RequestMapping(value = "/persona", method = RequestMethod.POST)
	@PostMapping("/persona")
	public Persona test(@RequestParam("eta") Integer eta) {
		Persona p = new Persona();
		p.setNome("Unvecchio");
		p.setCognome("Stagista");
		p.setEta(eta);
		return p;
	}

	@GetMapping("/persona/{eta}/find-by-age")
	public Persona setEtaPersona(@PathVariable("eta") Integer age) {
		Persona p = new Persona();
		p.setNome("Unvecchio");
		p.setCognome("Stagista");
		p.setEta(age);

		return p;
	}

	@PostMapping("/persona-mod") // su postman ricordardi di impostare Header -> Content-Type = application/json
									// e selezionare dal tab Body la casella "raw"
	public Persona returnPersonaMod(@RequestBody Persona p, HttpServletResponse response) {
		p.setNome(p.getNome() + " Modificato!");
		if (p.getCognome().length() < 3) {
			p = null;
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return p;
	}

	@PostMapping("/persona-mod-status") // su postman ricordardi di impostare Header -> Content-Type = application/json
										// e selezionare dal tab Body la casella "raw"
	public ResponseEntity<Persona> returnPersonaModStatus(@RequestBody Persona p) {
		p.setNome(p.getNome() + " Modificato!");
		ResponseEntity<Persona> resp = null;
		if (p.getCognome().length() < 3) {
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			resp = new ResponseEntity<>(p, HttpStatus.OK);
		}
		return resp;
	}

	@GetMapping("/lista-persone")
	public List<Persona> getPersone() {
		List<Persona> list = new ArrayList<>();
		Persona p = new Persona();
		p.setNome("Boh");
		p.setCognome("Bih");
		p.setEta(122);
		list.add(p);

		Persona pe = new Persona();
		pe.setNome("ivan");
		pe.setCognome("maltese");
		pe.setEta(134);
		list.add(pe);

		return list;
	}

}
