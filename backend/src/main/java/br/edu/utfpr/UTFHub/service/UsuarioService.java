package br.edu.utfpr.UTFHub.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(encodedPassword);
		Usuario usuarioSalvo = repository.save(usuario);
		return new UsuarioDTO(usuarioSalvo.getId(),usuarioSalvo.getNome(),usuarioSalvo.getEmail(),usuarioSalvo.getCampus(),usuarioSalvo.getCurso());
		
	}
	
	public boolean update(Usuario usuario) {
		Optional<Usuario> usuarioDB = repository.findById(usuario.getId());
		if (usuarioDB.isPresent()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if(encoder.matches(usuario.getSenha(),usuarioDB.get().getSenha())){
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String encodedPassword = passwordEncoder.encode(usuario.getSenha());
				usuario.setSenha(encodedPassword);
				repository.save(usuario);
				return true;
			}
		}
		return false;
	}
	
	public boolean delete(Usuario usuario) {
		Optional<Usuario> usuarioDB = repository.findById(usuario.getId());
		if (usuarioDB.isPresent()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if(encoder.matches(usuario.getSenha(),usuarioDB.get().getSenha())){
				repository.delete(usuario);
				return true;
			}
		}
		return false;
	}
	public UsuarioDTO login(String email, String senha) {
		Optional<Usuario> usuarioDB = Optional.ofNullable(repository.findByEmail(email));
		if (usuarioDB.isPresent()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if(encoder.matches(senha,usuarioDB.get().getSenha())){
				return new UsuarioDTO(usuarioDB.get().getId(),usuarioDB.get().getNome(),usuarioDB.get().getEmail(),usuarioDB.get().getCampus(),usuarioDB.get().getCurso());
			}
		}
		return null;
	}
}
