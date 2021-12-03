import { NavDropdown } from "react-bootstrap";
import { FaSistrix } from "react-icons/fa";
import { useDispatch } from "react-redux";
import { useHistory } from "react-router";
import { Link } from "react-router-dom";
import { ReactSVG } from "react-svg";
import User from "../../assets/images/user.svg";
import "./styles.css";

const Header = () => {
  const dispatch = useDispatch();
  const history = useHistory();

  function fazerLogout() {
    dispatch({
      type: "LOGOUT",
      usuarioLogado: 0,
    });
    history.push("/");
  }

  return (
    <header className="header">
      <div className="conteudo-header">
        <Link to="/home">Home</Link>
        <Link to="/materias">Matérias</Link>
        <Link to="/sobre">Sobre</Link>
        <Link to="/contato">Contato</Link>
        <Link to="/pesquisar">
          <FaSistrix size={33} />
        </Link>
        <ReactSVG
          src={User}
          alt="Ícone de usuário"
          className="header-user-img"
        />

        {/* <Link to="">Perfil</Link> */}
        <NavDropdown
          id="nav-dropdown-light-example"
          title="Perfil"
          menuVariant="dark"
        >
          <NavDropdown.Item onClick={fazerLogout}>Logout</NavDropdown.Item>
        </NavDropdown>
      </div>
    </header>
  );
};

export default Header;
