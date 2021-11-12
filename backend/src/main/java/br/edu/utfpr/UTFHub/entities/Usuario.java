package br.edu.utfpr.UTFHub.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 128)
	private String nome;
	@Column(nullable = false, unique = true, length = 64)
	private String email;
	@Column(nullable = false, length = 128)
	private String senha;
	@Column(nullable = false, length = 64)
	private String campus;
	@Column(nullable = false, length = 64)
	private String curso;
}
