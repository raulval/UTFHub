import axios from "axios";
import { useEffect, useState } from "react";
import { FaGraduationCap } from "react-icons/fa";
import { useDispatch } from "react-redux";
import { useHistory } from "react-router";
import Header from "../components/Header";
import HeaderContainer from "../components/HeaderContainer";
import ModalAddMaterias from "../components/ModalAddMaterias";
import Search from "../components/Search";
import "../styles/materias.css";

export function Materias() {
  const history = useHistory();
  const dispatch = useDispatch();

  const baseURL = "http://localhost:8080/materia";
  const [modalShow, setModalShow] = useState(false);
  const [materias, setMaterias] = useState();

  const pegarMaterias = () => {
    axios
      .get(baseURL)
      .then((res) => {
        console.log(res.data.content);
        setMaterias(res.data.content);
        // deletarMaterias(res.data.content);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  // function deletarMaterias(materias) {
  //   axios.delete(baseURL, { data: materias[4] });
  // }

  const abrirMateria = (materia) => {
    dispatch({
      type: "MATERIA",
      nomeMateria: materia.nome,
    });
    history.push(`/materias/${materia.id}`);
  };

  useEffect(() => {
    pegarMaterias();
  }, []);

  return (
    <div id="page-materias">
      <HeaderContainer />
      <Header />
      <Search className="search-materias" />
      <div className="main-container">
        <main>
          <fieldset>
            <legend>
              Matérias
              <button onClick={() => setModalShow(true)}>
                Adicionar matéria
              </button>
              <ModalAddMaterias
                show={modalShow}
                onHide={() => {
                  setModalShow(false);
                  pegarMaterias();
                }}
              />
            </legend>
          </fieldset>
          {materias ? (
            materias.map((materias) => (
              <div className="container-list-materias">
                <button onClick={() => abrirMateria(materias)}>
                  <p>
                    <FaGraduationCap
                      size={50}
                      color="#29292E"
                      style={{ paddingRight: "20px" }}
                    />
                    {materias.nome}
                  </p>
                </button>
              </div>
            ))
          ) : (
            <div className="container-list-materias">
              <p>
                <FaGraduationCap
                  size={50}
                  color="#29292E"
                  style={{ paddingRight: "20px" }}
                />
                Nenhuma matéria cadastrada
              </p>
            </div>
          )}
        </main>
      </div>
    </div>
  );
}
