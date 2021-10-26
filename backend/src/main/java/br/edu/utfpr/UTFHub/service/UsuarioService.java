package br.edu.utfpr.UTFHub.service;

import java.util.Optional;

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
		Integer count = repository.countEmail(usuario.getEmail());
		if(count > 0) {
			return null;
		}
		
		Usuario usuarioSalvo = repository.save(usuario);
		return new UsuarioDTO(usuarioSalvo.getId(),usuarioSalvo.getNome(),usuarioSalvo.getEmail(),usuarioSalvo.getCampus(),usuarioSalvo.getCurso());
		
	}
	
	public boolean update(Usuario usuario) {
		Optional<Usuario> usuarioDB = repository.findById(usuario.getId());
		if (usuarioDB.isPresent()) {
			if(usuarioDB.get().getSenha().equals(usuario.getSenha())){
				repository.save(usuario);
				return true;
			}
		}
		return false;
	}
	
	public boolean delete(Usuario usuario) {
		Optional<Usuario> usuarioDB = repository.findById(usuario.getId());
		if (usuarioDB.isPresent()) {
			if(usuarioDB.get().getSenha().equals(usuario.getSenha())){
				repository.delete(usuario);
				return true;
			}
		}
		return false;
	}
	
}
