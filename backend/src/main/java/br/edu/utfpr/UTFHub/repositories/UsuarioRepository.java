package br.edu.utfpr.UTFHub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.UTFHub.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
