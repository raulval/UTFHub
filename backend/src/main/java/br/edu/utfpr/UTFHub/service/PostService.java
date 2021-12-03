package br.edu.utfpr.UTFHub.service;

import br.edu.utfpr.UTFHub.entities.Post;
import br.edu.utfpr.UTFHub.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public boolean update(Post post, Long id) {
		Optional<Post> postDB = repository.findById(id);
		if (postDB.isPresent()) {
			post.setId(id);
			repository.save(post);
			return true;
		}
		return false;
	}

	public boolean deletePost(Long id) {
		Optional<Post> postDB = repository.findById(id);
		if (postDB.isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
