import { yupResolver } from "@hookform/resolvers/yup";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";
import { Button, Modal } from "react-bootstrap";
import { useForm } from "react-hook-form";
import * as yup from "yup";

const validationMat = yup.object().shape({
  nome: yup.string().required("O campo é obrigatório"),
});

function ModalAddMaterias(props) {
  const baseURL = "http://localhost:8080/materia";

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(validationMat),
  });

  const adicionarMateria = (data) => {
    const dadosMateria = {
      nome: data.nome,
      tipo: 0,
    };

    axios
      .post(baseURL, dadosMateria)
      .then((res) => {
        props.onHide();
      })
      .catch((error) => {
        alert("Erro: " + error.response.data);
      });
  };

  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">
          Adicionar Matéria
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <form>
          <div>
            <label htmlFor="nome">Nome da Matéria</label>
            <input id="nome" name="nome" {...register("nome")} />
            <span className="error-message">{errors.nome?.message}</span>
          </div>
        </form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={props.onHide}>
          Fechar
        </Button>
        <Button variant="primary" onClick={handleSubmit(adicionarMateria)}>
          Adicionar Matéria
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default ModalAddMaterias;
