import { FaSistrix } from "react-icons/fa";
import "./styles.css";

const Search = () => {
  function fazerBusca() {}

  return (
    <div id="search-content">
      <form onSubmit={fazerBusca}>
        <input
          className="form-search"
          type="text"
          placeholder="Pesquise aqui"
        />
        <span className="icone-search">
          <FaSistrix size={33} color="black" />
        </span>
      </form>
      <button>Buscar</button>
    </div>
  );
};

export default Search;
