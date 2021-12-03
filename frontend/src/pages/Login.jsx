import { yupResolver } from "@hookform/resolvers/yup";
import axios from "axios";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { FaExclamationCircle, FaRegEnvelope } from "react-icons/fa";
import { useDispatch, useSelector } from "react-redux";
import { Redirect, useHistory } from "react-router";
import { ReactSVG } from "react-svg";
import * as yup from "yup";
import illustrationImg from "../assets/images/illustration.svg";
import logo from "../assets/images/logo.svg";
import "../styles/login.css";

const validationLogin = yup.object().shape({
  email: yup
    .string()
    .required("O e-mail é obrigatório")
    .email("E-mail inválido")
    .matches("@alunos.utfpr.edu.br", "Use o e-mail institucional"),
  senha: yup
    .string()
    .required("A senha é obrigatória")
    .min(6, "Senha menor que 6 caracteres"),
});

export function Login() {
  const baseURL = "http://localhost:8080/usuario/login";
  const history = useHistory();
  const dispatch = useDispatch();

  const [error, setError] = useState();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(validationLogin),
  });

  const fazerLogin = (data) => {
    axios
      .post(baseURL, data)
      .then((res) => {
        console.log(res.data);
        dispatch({
          type: "LOGIN",
          userId: res.data.id,
          userNome: res.data.nome,
          userEmail: res.data.email,
          userCampus: res.data.campus,
          userCurso: res.data.curso,
        });
        history.push("/home");
      })
      .catch((error) => {
        console.log(error.response);
        setError(error.response.status);
      });
  };

  return (
    <div id="page">
      {useSelector((state) => state.usuarioLogado) > 0 ? (
        <Redirect to="/home" />
      ) : null}
      <aside>
        <ReactSVG
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
          <ReactSVG src={logo} alt="Logo UTFHub" />

          <form onSubmit={handleSubmit(fazerLogin)}>
            <div className="label">
              <label>E-mail Institucional</label>
            </div>
            <input
              type="email"
              id="email"
              placeholder="nome@alunos.utfpr.edu.br"
              name="email"
              {...register("email")}
            />
            <span className="error-message">{errors.email?.message}</span>
            <div className="label">
              <label>Senha</label>
            </div>
            <input
              type="password"
              id="senha"
              placeholder="*******"
              name="senha"
              {...register("senha")}
            />
            <span className="error-message">{errors.senha?.message}</span>
            <button type="submit">Entrar</button>
            {error === 400 ? (
              <p style={{ paddingTop: 10 }}>
                <FaExclamationCircle
                  color="#F44336"
                  size={30}
                  className="icone"
                />
                E-mail ou senha incorretos!
              </p>
            ) : null}
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
