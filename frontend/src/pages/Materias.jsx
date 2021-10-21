import { useHistory } from "react-router";
import Header from "../components/Header";
import HeaderContainer from "../components/HeaderContainer";
import Search from "../components/Search";
import "../styles/materias.css";

export function Materias() {
  const history = useHistory();

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
              <button onClick={() => history.push("/add-materias")}>
                Adicionar matéria
              </button>
            </legend>
          </fieldset>
        </main>
      </div>
    </div>
  );
}
