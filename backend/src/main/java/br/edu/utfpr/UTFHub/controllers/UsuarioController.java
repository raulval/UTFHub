package br.edu.utfpr.UTFHub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.UTFHub.dto.UsuarioDTO;
import br.edu.utfpr.UTFHub.entities.Usuario;
import br.edu.utfpr.UTFHub.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<Page<UsuarioDTO>> findAll(Pageable pageable) {
		Page<UsuarioDTO> list = usuarioService.findAll(pageable);
		return ResponseEntity.ok(list);
	}
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Usuario usuario){
		UsuarioDTO usuarioCriado = usuarioService.insert(usuario);
		if (usuarioCriado == null) {
			return ResponseEntity.badRequest().body("O email fornecido está indisponível !");
		}
		return ResponseEntity.ok("Usuário cadastrado com sucesso");
	}
}
