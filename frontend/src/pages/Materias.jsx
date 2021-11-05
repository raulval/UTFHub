import axios from "axios";
import { useEffect, useState } from "react";
import { FaGraduationCap } from "react-icons/fa";
import Header from "../components/Header";
import HeaderContainer from "../components/HeaderContainer";
import ModalAddMaterias from "../components/ModalAddMaterias";
import Search from "../components/Search";
import "../styles/materias.css";

export function Materias() {
  const baseURL = "https://utfhub.herokuapp.com/materia";
  const [modalShow, setModalShow] = useState(false);
  const [materias, setMaterias] = useState();

  const pegarMaterias = () => {
    axios.get(baseURL).then((res) => {
      console.log(res.data.content);
      setMaterias(res.data.content);
      // deletarMaterias(res.data.content);
    });
  };

  // function deletarMaterias(materias) {
  //   axios.delete(baseURL, { data: materias[4] });
  // }

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
                <p>
                  <FaGraduationCap
                    size={50}
                    color="#29292E"
                    style={{ paddingRight: "20px" }}
                  />
                  {materias.nome}
                </p>
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
