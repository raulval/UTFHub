package br.edu.utfpr.UTFHub.repositories;

import br.edu.utfpr.UTFHub.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByMateriaId(Long materia_id, Pageable pageable);
}
