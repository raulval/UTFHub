package br.edu.utfpr.UTFHub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.utfpr.UTFHub.entities.Teste;

public interface TesteRepository extends JpaRepository<Teste, Long>{

}
