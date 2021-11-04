import { useSelector } from "react-redux";
import { useHistory } from "react-router";
import Header from "../components/Header";
import HeaderContainerLogo from "../components/HeaderContainerLogo";
import Search from "../components/Search";
import "../styles/home.css";

export function Home() {
  const history = useHistory();
  const userNome = useSelector((state) => state.userNome);

  return (
    <div id="page-home">
      <HeaderContainerLogo />
      <Header />
      <button id="btn-materias" onClick={() => history.push("/materias")}>
        Ver matérias
      </button>
      <Search />
      <div className="main-container">
        <main>
          <fieldset>
            <legend>Últimos Tópicos</legend>
          </fieldset>
        </main>
      </div>
    </div>
  );
}
