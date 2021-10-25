import { ReactSVG } from "react-svg";
import Logo from "../../assets/images/logo.svg";
import "./styles.css";

const HeaderContainerLogo = () => {
  return (
    <header className="page-header">
      <div className="header-content">
        <ReactSVG src={Logo} alt="Logo UTFHub" />
        <p>Conectando os alunos da melhor universidade</p>
      </div>
    </header>
  );
};

export default HeaderContainerLogo;
