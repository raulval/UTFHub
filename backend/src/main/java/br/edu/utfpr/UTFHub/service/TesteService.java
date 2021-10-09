package br.edu.utfpr.UTFHub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.utfpr.UTFHub.dto.TesteDTO;
import br.edu.utfpr.UTFHub.entities.Teste;
import br.edu.utfpr.UTFHub.repositories.TesteRepository;

@Service
public class TesteService {
	
	@Autowired
	private TesteRepository repository;
	
	@Transactional(readOnly = true)
	public Page<TesteDTO> findAll(Pageable pageable){ 
		Page<Teste> result = repository.findAll(pageable);
		return result.map(x -> new TesteDTO(x.getId(),x.getNome()));
	}
	
}
