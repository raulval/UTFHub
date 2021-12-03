import { yupResolver } from "@hookform/resolvers/yup";
import axios from "axios";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { FaArrowLeft, FaExclamationCircle } from "react-icons/fa";
import { Link, useHistory } from "react-router-dom";
import * as yup from "yup";
import HeaderContainerLogo from "../components/HeaderContainerLogo";
import "../styles/cadastro.css";

const validationCad = yup.object().shape({
  nome: yup.string().required("O nome é obrigatório"),
  email: yup
    .string()
    .required("A e-mail é obrigatório")
    .email("E-mail inválido")
    .matches("@alunos.utfpr.edu.br", "Use o e-mail institucional"),
  senha: yup
    .string()
    .required("A senha é obrigatória")
    .min(6, "Senha menor que 6 caracteres"),
  campus: yup.string().required("O Campus é obrigatório"),
  curso: yup.string().required("O Curso é obrigatório"),
});

export function Cadastro() {
  const baseURL = "http://localhost:8080/usuario";
  const history = useHistory();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(validationCad),
  });

  const [error, setError] = useState("");

  const fazerCadastro = (data) => {
    axios
      .post(baseURL, data)
      .then((res) => {
        console.log(res.data.content);
        alert("Cadastro realizado com sucesso!");
        history.push("/");
      })
      .catch((error) => {
        setError(error.response.data);
      });
  };

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
        <form onSubmit={handleSubmit(fazerCadastro)}>
          <fieldset>
            <legend>Seus dados</legend>

            <div className="input-block">
              <label htmlFor="nome">Nome</label>
              <input id="nome" name="nome" {...register("nome")} />
              <span className="error-message">{errors.nome?.message}</span>
            </div>

            <div className="input-block">
              <label htmlFor="email">E-mail Institucional</label>
              <input
                id="email"
                placeholder="nome@alunos.utfpr.edu.br"
                name="email"
                {...register("email")}
              />
              <span className="error-message">{errors.email?.message}</span>
            </div>

            <div className="input-block">
              <label htmlFor="senha">Senha</label>
              <input
                type="password"
                id="senha"
                name="senha"
                {...register("senha")}
              />
              <span className="error-message">{errors.senha?.message}</span>
            </div>
          </fieldset>

          <fieldset>
            <legend>Sobre seu curso</legend>

            <div className="select-block">
              <label htmlFor="campus">Campus</label>
              <select
                className="form-select"
                id="select-campus"
                name="campus"
                {...register("campus")}
              >
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
              <span className="error-message">{errors.campus?.message}</span>
            </div>

            <div className="input-block">
              <label htmlFor="nome">Curso</label>
              <input id="curso" name="curso" {...register("curso")} />
              <span className="error-message">{errors.curso?.message}</span>
            </div>
          </fieldset>

          <footer>
            {error ? (
              <p>
                <FaExclamationCircle
                  color="#F44336"
                  size={50}
                  className="icone"
                />
                {error}
              </p>
            ) : (
              <p>
                <FaExclamationCircle
                  color="#FF941A"
                  size={50}
                  className="icone"
                />
                Importante! <br />
                Preencha todos os dados
              </p>
            )}
            <button type="submit">Salvar cadastro</button>
          </footer>
        </form>
      </main>
    </div>
  );
}
