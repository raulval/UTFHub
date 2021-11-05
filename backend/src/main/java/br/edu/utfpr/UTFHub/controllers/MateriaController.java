package br.edu.utfpr.UTFHub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.edu.utfpr.UTFHub.entities.Materia;
import br.edu.utfpr.UTFHub.service.MateriaService;


@RestController
@RequestMapping(value = "/materia")
public class MateriaController {
	@Autowired
	private MateriaService materiaService;
	
	@GetMapping
	public ResponseEntity<Page<Materia>> findAll(Pageable pageable){
		Page<Materia> list = materiaService.findAll(pageable);
		
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Materia materia){
		Materia materiaCriada = materiaService.insert(materia);
		if (materiaCriada == null) {
			return ResponseEntity.badRequest().body("Dados inválidos!");
		}
		return ResponseEntity.ok("Matéria cadastrado com sucesso !");
	}
	@PutMapping
	public ResponseEntity<String> update(@RequestBody Materia materia){
		boolean res = materiaService.update(materia);
		if (!res) {
			return ResponseEntity.badRequest().body("Dados inválidos !");
		}
		return ResponseEntity.ok("Matéria atualizada com sucesso !");
	}
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody Materia materia){
		boolean res = materiaService.delete(materia);
		if (!res) {
			return ResponseEntity.badRequest().body("Dados inválidos !");
		}
		return ResponseEntity.ok("Matéria deletada com sucesso !");
	}
}
