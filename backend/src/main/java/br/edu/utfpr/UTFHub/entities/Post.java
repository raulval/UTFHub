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


//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "materia_id", nullable = false)
//	@JsonIgnore
//	private Materia materia;


	
}
