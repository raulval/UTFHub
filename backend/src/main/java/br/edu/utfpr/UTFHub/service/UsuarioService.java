package br.edu.utfpr.UTFHub.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.edu.utfpr.UTFHub.dto.UsuarioDTO;
import br.edu.utfpr.UTFHub.entities.Usuario;
import br.edu.utfpr.UTFHub.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService{
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		return Optional.ofNullable(repository.findByUsername(username))
				.orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado !"));
	}
	
	@Transactional(readOnly = true)
	public Page<UsuarioDTO> findAll(Pageable pageable) {
		Page<Usuario> result = repository.findAll(pageable);
		return result.map(x -> new UsuarioDTO(x.getId(), x.getNome(), x.getUsername(), x.getCampus(), x.getCurso()));
	}
	
	public UsuarioDTO findById(long id) {
		Optional<Usuario> user = repository.findById(id);
		return new UsuarioDTO(user.get().getId(), user.get().getNome(), user.get().getUsername(), user.get().getCampus(), user.get().getCurso());
	}
	
	public UsuarioDTO insert(Usuario usuario) {
		Integer count = repository.countEmail(usuario.getUsername());
		if(count > 0) {
			return null;
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(encodedPassword);
		Usuario usuarioSalvo = repository.save(usuario);
		return new UsuarioDTO(usuarioSalvo.getId(),usuarioSalvo.getNome(),usuarioSalvo.getUsername(),usuarioSalvo.getCampus(),usuarioSalvo.getCurso());
		
	}
	
	public boolean update(Usuario usuario) {
		Optional<Usuario> usuarioDB = repository.findById(usuario.getId());
		if (usuarioDB.isPresent()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if(encoder.matches(usuario.getPassword(),usuarioDB.get().getPassword())){
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String encodedPassword = passwordEncoder.encode(usuario.getPassword());
				usuario.setPassword(encodedPassword);
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
			if(encoder.matches(usuario.getPassword(),usuarioDB.get().getPassword())){
				repository.delete(usuario);
				return true;
			}
		}
		return false;
	}

	
	
}
