package br.edu.utfpr.UTFHub;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UtfHubApplicationTests {

	@Test
	void testeInicial() {
		String texto = "Esse é um teste utilizando Junit";
		assertEquals("Esse é um teste utilizando Junit", texto);
	}

}
