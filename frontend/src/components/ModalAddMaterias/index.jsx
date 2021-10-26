import { yupResolver } from "@hookform/resolvers/yup";
import "bootstrap/dist/css/bootstrap.min.css";
import { Button, Modal } from "react-bootstrap";
import { useForm } from "react-hook-form";
import * as yup from "yup";

const validationMat = yup.object().shape({
  nomeMateria: yup.string().required("O campo é obrigatório"),
});

function ModalAddMaterias(props) {
  // const baseURL = "https://utfhub.herokuapp.com/usuario";

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(validationMat),
  });

  const adicionarMateria = (data) => {
    // axios
    //   .post(baseURL, data)
    //   .then((res) => {
    //     console.log(res.data.content);
    //     alert("Cadastro realizado com sucesso!");
    //     history.push("/");
    //   })
    //   .catch((error) => {
    //     setError(error.response.data);
    //   });
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
            <label htmlFor="nomeMateria">Nome da Matéria</label>
            <input
              id="nomeMateria"
              name="nomeMateria"
              {...register("nomeMateria")}
            />
            <span className="error-message">{errors.nomeMateria?.message}</span>
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
