package br.edu.utfpr.UTFHub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import br.edu.utfpr.UTFHub.entities.Materia;
import br.edu.utfpr.UTFHub.service.MateriaService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/materia")
public class MateriaController {

	private final MateriaService materiaService;
	
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
