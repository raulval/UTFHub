import { useState } from "react";
import Header from "../components/Header";
import HeaderContainer from "../components/HeaderContainer";
import ModalAddMaterias from "../components/ModalAddMaterias";
import Search from "../components/Search";
import "../styles/materias.css";

export function Materias() {
  const [modalShow, setModalShow] = useState(false);

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
                onHide={() => setModalShow(false)}
              />
            </legend>
          </fieldset>
        </main>
      </div>
    </div>
  );
}
