import { useState } from "react";
import { FaRegEnvelope } from "react-icons/fa";
import { useHistory } from "react-router";
import illustrationImg from "../assets/images/illustration.svg";
import logo from "../assets/images/logo.svg";
import "../styles/login.css";

export function Login() {
  const history = useHistory();
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");

  async function fazerLogin() {}

  return (
    <div id="page">
      <aside>
        <img
          src={illustrationImg}
          alt="Ilustração simbolizando perguntas e respostas"
        />
        <strong>
          Toda pergunta tem <br /> uma resposta
        </strong>
        <p>
          Aprenda e compartilhe conhecimento <br /> com outros alunos
        </p>
      </aside>
      <main>
        <div className="main-content">
          <img src={logo} alt="Logo UTFHub" />

          <form onSubmit={fazerLogin}>
            <div className="label">
              <label>E-mail Institucional</label>
            </div>
            <input
              type="email"
              placeholder="nome@alunos.utfpr.edu.br"
              onChange={(event) => setEmail(event.target.value)}
              value={email}
              required
            />
            <div className="label">
              <label>Senha</label>
            </div>
            <input
              type="password"
              placeholder="********"
              onChange={(event) => setSenha(event.target.value)}
              value={senha}
              required
            />
            <button type="submit">Entrar</button>
          </form>

          <div className="separator">Não tem uma conta?</div>

          <button
            className="cadastrar"
            onClick={() => history.push("/cadastro")}
          >
            <FaRegEnvelope color="white" size={20} className="icone" />
            Cadastre-se
          </button>
        </div>
      </main>
    </div>
  );
}
