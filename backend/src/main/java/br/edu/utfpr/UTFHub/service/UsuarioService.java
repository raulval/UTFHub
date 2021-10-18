package br.edu.utfpr.UTFHub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.utfpr.UTFHub.dto.UsuarioDTO;
import br.edu.utfpr.UTFHub.entities.Usuario;
import br.edu.utfpr.UTFHub.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;

	@Transactional(readOnly = true)
	public Page<UsuarioDTO> findAll(Pageable pageable) {
		Page<Usuario> result = repository.findAll(pageable);
		return result.map(x -> new UsuarioDTO(x.getId(), x.getNome(), x.getEmail(), x.getCampus(), x.getCurso()));
	}
	
	public UsuarioDTO insert(Usuario usuario) {
		Usuario usuarioSalvo = repository.save(usuario);
		return new UsuarioDTO(usuarioSalvo.getId(),usuarioSalvo.getNome(),usuarioSalvo.getEmail(),usuarioSalvo.getCampus(),usuarioSalvo.getCurso());
		
	}
}
