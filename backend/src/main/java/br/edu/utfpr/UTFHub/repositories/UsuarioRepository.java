package br.edu.utfpr.UTFHub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.utfpr.UTFHub.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByUsername(String username);
	
	@Query(
			value = "SELECT COUNT(*) FROM usuario WHERE email = :email",
			nativeQuery = true)
	Integer countEmail(@Param(value= "email") String email);
}
