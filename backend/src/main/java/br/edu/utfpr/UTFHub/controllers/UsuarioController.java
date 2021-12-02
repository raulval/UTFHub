package br.edu.utfpr.UTFHub.controllers;

import lombok.RequiredArgsConstructor;
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

import br.edu.utfpr.UTFHub.dto.UsuarioDTO;
import br.edu.utfpr.UTFHub.entities.Login;
import br.edu.utfpr.UTFHub.entities.Usuario;
import br.edu.utfpr.UTFHub.service.UsuarioService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	private final UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<Page<UsuarioDTO>> findAll(Pageable pageable) {
		Page<UsuarioDTO> list = usuarioService.findAll(pageable);
		return ResponseEntity.ok(list);
	}
	@PostMapping(value="/login")
	public ResponseEntity<UsuarioDTO> login(@RequestBody Login login){
		UsuarioDTO usuarioLogado = usuarioService.login(login.getEmail(),login.getSenha());
		if(usuarioLogado == null) {
			return ResponseEntity.badRequest().body(usuarioLogado);
		}
		return ResponseEntity.ok(usuarioLogado);
	}
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Usuario usuario){
		UsuarioDTO usuarioCriado = usuarioService.insert(usuario);
		if (usuarioCriado == null) {
			return ResponseEntity.badRequest().body("O email fornecido está indisponível !");
		}
		return ResponseEntity.ok("Usuário cadastrado com sucesso !");
	}
	@PutMapping
	public ResponseEntity<String> update(@RequestBody Usuario usuario){
		boolean res = usuarioService.update(usuario);
		if (!res) {
			return ResponseEntity.badRequest().body("A senha informada é inválida !");
		}
		return ResponseEntity.ok("Usuário atualizado com sucesso !");
	}
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody Usuario usuario){
		boolean res = usuarioService.delete(usuario);
		if (!res) {
			return ResponseEntity.badRequest().body("A senha informada é inválida !");
		}
		return ResponseEntity.ok("Usuário deletado com sucesso !");
	}
}
