import { useState } from "react";
import { FaArrowLeft, FaExclamationCircle } from "react-icons/fa";
import { Link } from "react-router-dom";
import HeaderContainerLogo from "../components/HeaderContainerLogo";
import "../styles/cadastro.css";

export function Cadastro() {
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [campus, setCampus] = useState("");
  const [curso, setCurso] = useState("");

  function fazerCadastro() {}

  return (
    <div id="page-form" className="container">
      <HeaderContainerLogo />

      <Link to="/">
        <FaArrowLeft className="voltar" />
      </Link>

      <div className="textoCad">
        <p>Cadastre-se</p>
      </div>
      <main>
        <form onSubmit={fazerCadastro}>
          <fieldset>
            <legend>Seus dados</legend>

            <div className="input-block">
              <label htmlFor="nome">Nome</label>
              <input
                id="nome"
                value={nome}
                onChange={(event) => setNome(event.target.value)}
              />
            </div>

            <div className="input-block">
              <label htmlFor="email">E-mail Institucional</label>
              <input
                id="email"
                placeholder="nome@alunos.utfpr.edu.br"
                value={email}
                onChange={(event) => setEmail(event.target.value)}
              />
            </div>

            <div className="input-block">
              <label htmlFor="senha">Senha</label>
              <input
                type="password"
                id="senha"
                value={senha}
                onChange={(event) => setSenha(event.target.value)}
              />
            </div>
          </fieldset>

          <fieldset>
            <legend>Sobre seu curso</legend>

            <div className="select-block">
              <label htmlFor="campus">Campus</label>
              <select
                className="form-select"
                id="select-campus"
                value={campus}
                defaultValue={"inicial"}
                onChange={(event) => {
                  setCampus(event.target.value);
                }}
              >
                <option value="inicial">Escolha seu Campus</option>
                <option value="Apucarana">Apucarana</option>
                <option value="Campo Mourão">Campo Mourão</option>
                <option value="Cornélio Procópio">Cornélio Procópio</option>
                <option value="Curitiba">Curitiba</option>
                <option value="Dois Vizinhos">Dois Vizinhos</option>
                <option value="Francisco Beltrão">Francisco Beltrão</option>
                <option value="Guarapuava">Guarapuava</option>
                <option value="Londrina">Londrina</option>
                <option value="Medianeira">Medianeira</option>
                <option value="Pato Branco">Pato Branco</option>
                <option value="Ponta Grossa">Ponta Grossa</option>
                <option value="Santa Helena">Santa Helena</option>
                <option value="Toledo">Toledo</option>
              </select>
            </div>

            <div className="input-block">
              <label htmlFor="nome">Curso</label>
              <input
                id="curso"
                value={curso}
                onChange={(event) => setCurso(event.target.value)}
              />
            </div>
          </fieldset>

          <footer>
            <p>
              <FaExclamationCircle
                color="#FF941A"
                size={50}
                className="icone"
              />
              Importante! <br />
              Preencha todos os dados
            </p>
            <button type="submit">Salvar cadastro</button>
          </footer>
        </form>
      </main>
    </div>
  );
}
