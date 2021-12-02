package br.edu.utfpr.UTFHub.controllers;

import br.edu.utfpr.UTFHub.entities.Post;
import br.edu.utfpr.UTFHub.repositories.MateriaRepository;
import br.edu.utfpr.UTFHub.repositories.PostRepository;
import br.edu.utfpr.UTFHub.service.MateriaService;
import br.edu.utfpr.UTFHub.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
//@RequestMapping(value = "/post")
public class PostController {
	@Autowired
	private PostService postService;

//	@Autowired
//	private PostRepository postRepository;

	@Autowired
	private MateriaService materiaService;

	@GetMapping("/materia/posts")
	public ResponseEntity<Page<Post>> findAll(Pageable pageable){
		Page<Post> list = postService.findAll(pageable);

		return ResponseEntity.ok(list);
	}

	@GetMapping("/materia/{materiaId}/posts")
	public Page<Post> getAllPostsByMateriaId(@PathVariable (value = "materiaId") Long materiaId,
												Pageable pageable) {
		return postService.findByMateriaId(materiaId, pageable);
	}


	@PostMapping("/materia/{materiaId}/posts")
	public ResponseEntity<String> insert(@RequestBody Post post){
		Post postCriado = postService.insert(post);
		if (postCriado == null) {
			return ResponseEntity.badRequest().body("Dados inválidos!");
		}
		return ResponseEntity.ok("Pergunta enviado com sucesso !");
	}

	@PutMapping("/materia/{materiaId}/posts")
	public ResponseEntity<String> update(@RequestBody Post post){
		boolean res = postService.update(post);
		if (!res) {
			return ResponseEntity.badRequest().body("Dados inválidos !");
		}
		return ResponseEntity.ok("Pergunta atualizado com sucesso !");
	}

	@DeleteMapping("/materia/{materiaId}/posts/{id}")
	public ResponseEntity<String> deletePost(@PathVariable (value = "materiaId") Long materiaId, @PathVariable (value = "id") Long id, Post post) {
		boolean res = postService.deletePost(post);
		if (!res) {
			return ResponseEntity.badRequest().body("Dados inválidos !");
		}
		return ResponseEntity.ok("Pergunta deletada com sucesso !");
	}


}
