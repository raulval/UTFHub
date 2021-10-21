import Logo from "../../assets/images/logo.svg";
import "./styles.css";

const HeaderContainer = () => {
  return (
    <header className="page-header-nlogo">
      <div className="header-content">
        <img src={Logo} alt="Logo UTFHub" />
      </div>
    </header>
  );
};

export default HeaderContainer;
