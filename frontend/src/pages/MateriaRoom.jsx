import axios from "axios";
import { useEffect, useState } from "react";
import { FaArrowLeft } from "react-icons/fa";
import { useSelector } from "react-redux";
import { Link, useParams } from "react-router-dom";
import { ReactSVG } from "react-svg";
import User from "../assets/images/user.svg";
import Header from "../components/Header";
import HeaderContainer from "../components/HeaderContainer";
import "../styles/materiaRoom.css";

export function MateriaRoom() {
  const nomeMateria = useSelector((state) => state.nomeMateria);
  const nomeUsuario = useSelector((state) => state.userNome);
  const params = useParams();

  const [novaPergunta, setNovaPergunta] = useState("");
  const [post, setPost] = useState();

  const materiaId = params.id;
  const baseURL = `http://localhost:8080/materia/${materiaId}/posts`;

  useEffect(() => {
    axios
      .get(baseURL)
      .then((res) => {
        console.log(res.data.content);
        setPost(res.data.content);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  function adicionarPost(e) {
    e.preventDefault();

    const dadosPost = {
      autor: nomeUsuario,
      pergunta: novaPergunta,
      materiaId: materiaId,
    };

    console.log(dadosPost);

    axios
      .post(baseURL, dadosPost)
      .then((res) => {
        setNovaPergunta("");
        window.location.reload();
      })
      .catch((error) => {
        console.log("Erro: " + error);
      });
  }

  return (
    <div id="page-materiaRoom">
      <HeaderContainer />
      <Header />
      <Link to="/materias">
        <FaArrowLeft className="voltar" />
      </Link>
      <div className="main-container">
        <main>
          <fieldset>
            <legend>{nomeMateria}</legend>
          </fieldset>
          <form onSubmit={adicionarPost}>
            <textarea
              placeholder="O que você quer perguntar?"
              name="pergunta"
              id="pergunta"
              onChange={(event) => setNovaPergunta(event.target.value)}
              value={novaPergunta}
              required
            />
            <div className="form-footer">
              <button type="submit">Enviar Pergunta</button>
            </div>
          </form>
          {post ? (
            post.map((post) => (
              <div className="container-posts" key={post.id}>
                <div className="pergunta-container">
                  <p>{post.pergunta}</p>
                </div>

                <div className="autor-container">
                  <ReactSVG
                    src={User}
                    alt="Ícone de usuário"
                    className="user-img"
                  />
                  <span>{post.autor}</span>
                </div>
              </div>
            ))
          ) : (
            <p style={{ marginTop: "40px", fontSize: "18px" }}>
              Seja o primeiro a fazer uma pergunta
            </p>
          )}
        </main>
      </div>
    </div>
  );
}
