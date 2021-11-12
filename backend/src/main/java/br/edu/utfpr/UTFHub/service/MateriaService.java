package br.edu.utfpr.UTFHub.service;

import br.edu.utfpr.UTFHub.entities.Materia;
import br.edu.utfpr.UTFHub.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MateriaService {
	@Autowired
	private MateriaRepository repository;

	@Transactional(readOnly = true)
	public Page<Materia> findAll(Pageable pageable) {
		return repository.findAll(pageable);

	}

	public Materia insert(Materia materia) {
		Materia materiaSalva = repository.save(materia);
		return materiaSalva;

	}

	public boolean update(Materia materia) {
		Optional<Materia> materiaDB = repository.findById(materia.getId());
		if (materiaDB.isPresent()) {
			repository.save(materia);
			return true;
		}
		return false;
	}
	public boolean delete(Materia materia) {
		Optional<Materia> materiaDB = repository.findById(materia.getId());
		if (materiaDB.isPresent()) {
			repository.delete(materia);
			return true;
		}
		return false;
	}
}
