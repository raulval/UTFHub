import axios from "axios";
import { useEffect, useState } from "react";
import { FaArrowLeft, FaPen, FaTrash } from "react-icons/fa";
import { FiThumbsUp } from "react-icons/fi";
import { useDispatch, useSelector } from "react-redux";
import { Link, useParams } from "react-router-dom";
import { ReactSVG } from "react-svg";
import User from "../assets/images/user.svg";
import Header from "../components/Header";
import HeaderContainer from "../components/HeaderContainer";
import ModalEditarPost from "../components/ModalEditarPost";
import "../styles/materiaRoom.css";

export function MateriaRoom() {
  const nomeMateria = useSelector((state) => state.nomeMateria);
  const nomeUsuario = useSelector((state) => state.userNome);
  const usuarioId = useSelector((state) => state.userId);
  const dispatch = useDispatch();
  const params = useParams();

  const [post, setPost] = useState();
  const [novaPergunta, setNovaPergunta] = useState("");
  const [modalShow, setModalShow] = useState(false);

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
  }, [modalShow]);

  function adicionarPost(e) {
    e.preventDefault();

    const dadosPost = {
      autor: nomeUsuario,
      pergunta: novaPergunta,
      materiaId: materiaId,
      autorId: usuarioId,
    };

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

  function deletarPost(id) {
    axios
      .delete(`http://localhost:8080/posts/${id}/delete`)
      .then((res) => {
        window.location.reload();
      })
      .catch((error) => {
        console.log("Erro: " + error);
      });
  }

  function editarPost(post) {
    dispatch({
      type: "POST",
      postId: post.id,
      postPergunta: post.pergunta,
    });
    setModalShow(true);
  }

  return (
    <div id="page-materiaRoom">
      <HeaderContainer />
      <Header />
      <ModalEditarPost
        show={modalShow}
        onHide={() => {
          setModalShow(false);
        }}
      />
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

                <div className="like-button">
                  <button aria-label="Marcar como gostei">
                    <FiThumbsUp className="like" />
                  </button>
                  <span>5</span>
                </div>
                {post.autorId === usuarioId && (
                  <>
                    <div className="edit-button">
                      <button
                        onClick={() => editarPost(post)}
                        aria-label="Editar Post"
                      >
                        <FaPen className="edit" />
                      </button>
                    </div>
                    <div className="delete-button">
                      <button
                        onClick={() => deletarPost(post.id)}
                        aria-label="Deletar Post"
                      >
                        <FaTrash className="delete" />
                      </button>
                    </div>
                  </>
                )}
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
