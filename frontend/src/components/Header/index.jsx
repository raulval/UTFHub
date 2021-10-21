import { FaSistrix } from "react-icons/fa";
import { Link } from "react-router-dom";
import User from "../../assets/images/user.svg";
import "./styles.css";

const Header = () => {
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
        <div>
          <img src={User} alt="Ícone de usuário" />
        </div>
        <Link to="">Perfil</Link>
      </div>
    </header>
  );
};

export default Header;
