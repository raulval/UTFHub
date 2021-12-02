package br.edu.utfpr.UTFHub.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 128)
	private String autor;
	@Column(nullable = false, length = 500)
	private String pergunta;
	@Column(nullable = false)
	private Long materiaId;
	@Column(nullable = false)
	private Long autorId;

	
}
