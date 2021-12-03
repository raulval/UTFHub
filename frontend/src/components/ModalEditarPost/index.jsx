import axios from "axios";
import { useEffect, useState } from "react";
import { Button, Modal } from "react-bootstrap";
import { useSelector } from "react-redux";
import { useParams } from "react-router";
import "./styles.css";

const ModalEditarPost = (props) => {
  const nomeUsuario = useSelector((state) => state.userNome);
  const usuarioId = useSelector((state) => state.userId);
  const postId = useSelector((state) => state.postId);
  const postPergunta = useSelector((state) => state.postPergunta);

  const params = useParams();
  const materiaId = params.id;

  const [novaPergunta, setNovaPergunta] = useState("");

  useEffect(() => {
    setNovaPergunta(postPergunta);
  }, [materiaId]);

  function editarPost() {
    const baseURL = `http://localhost:8080/posts/${postId}/edit/`;

    const dadosPost = {
      pergunta: novaPergunta,
      autor: nomeUsuario,
      materiaId: materiaId,
      autorId: usuarioId,
    };

    console.log(dadosPost);

    axios
      .put(baseURL, dadosPost)
      .then((res) => {
        props.onHide();
      })
      .catch((error) => {
        console.log("Erro: " + error);
      });
  }

  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">
          Editar Pergunta
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <form>
          <div>
            <textarea
              id="novaPergunta"
              name="novaPergunta"
              placeholder="Digite sua nova pergunta"
              value={novaPergunta}
              onChange={(event) => setNovaPergunta(event.target.value)}
              required
            />
          </div>
        </form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={props.onHide}>
          Fechar
        </Button>
        <Button variant="primary" onClick={editarPost}>
          Salvar
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default ModalEditarPost;
