package br.edu.utfpr.UTFHub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.UTFHub.service.TesteService;
import br.edu.utfpr.UTFHub.dto.TesteDTO;


@RestController
@RequestMapping(value = "/teste")
public class TesteController {
	@Autowired
	private TesteService testeService;
	
	@GetMapping
	public ResponseEntity<Page<TesteDTO>> findAll(Pageable pageable){
		Page<TesteDTO> list = testeService.findAll(pageable);
		
		return ResponseEntity.ok(list);
	}
	
}
