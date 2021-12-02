package br.edu.utfpr.UTFHub.service;

import br.edu.utfpr.UTFHub.entities.Post;
import br.edu.utfpr.UTFHub.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class PostService {
	@Autowired
	private PostRepository repository;

	@Transactional(readOnly = true)
	public Page<Post> findAll(Pageable pageable) {
		return repository.findAll(pageable);

	}

	public Page<Post> findByMateriaId(Long materiaId, Pageable pageable) {
			return repository.findAllByMateriaId(materiaId, pageable);
	}


	public Post insert(Post post) {
		Post postSalva = repository.save(post);
		return postSalva;

	}

	public boolean update(Post post) {
		Optional<Post> postDB = repository.findById(post.getId());
		if (postDB.isPresent()) {
			repository.save(post);
			return true;
		}
		return false;
	}
	public boolean delete(Post post) {
		Optional<Post> postDB = repository.findById(post.getId());
		if (postDB.isPresent()) {
			repository.delete(post);
			return true;
		}
		return false;
	}
}
