package br.edu.utfpr.UTFHub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
	private Long id;
	private String nome;
	private String email;
	private String campus;
	private String curso;
}
