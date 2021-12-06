package br.edu.utfpr.UTFHub.controllers;

import br.edu.utfpr.UTFHub.entities.Post;
import br.edu.utfpr.UTFHub.repositories.MateriaRepository;
import br.edu.utfpr.UTFHub.repositories.PostRepository;
import br.edu.utfpr.UTFHub.service.MateriaService;
import br.edu.utfpr.UTFHub.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
//@RequestMapping(value = "/post")
public class PostController {
//	@Autowired
	private final PostService postService;


//	@Autowired
//	private MateriaService materiaService;

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

	@PutMapping("/posts/{id}/edit")
	public ResponseEntity<String> update(@PathVariable (value = "id") Long id, @RequestBody Post post){
		boolean res = postService.update(post, id);
		if (!res) {
			return ResponseEntity.badRequest().body("Dados inválidos !");
		}
		return ResponseEntity.ok("Pergunta atualizado com sucesso !");
	}

	@DeleteMapping("/posts/{id}/delete")
	public ResponseEntity<String> deletePost(@PathVariable (value = "id") Long id) {
		boolean res = postService.deletePost(id);
		if (!res) {
			return ResponseEntity.badRequest().body("Dados inválidos !");
		}
		return ResponseEntity.ok("Pergunta deletada com sucesso !");
	}


}
