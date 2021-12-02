package br.edu.utfpr.UTFHub.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "materia")
public class Materia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 128)
	private String nome;
	@Column(nullable = false, length = 4)
	private Long tipo;

	@OneToMany(
			cascade = CascadeType.ALL
	)
	@JoinColumn(name = "materiaId", referencedColumnName = "id")
	@JsonIgnore
	private List<Post> posts = new ArrayList<>();


}
