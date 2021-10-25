import { BrowserRouter, Route, Switch } from "react-router-dom";
import { Cadastro } from "./pages/Cadastro";
import { Home } from "./pages/Home";
import { Login } from "./pages/Login";
import { Materias } from "./pages/Materias";

function Routes() {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/" component={Login} />
        <Route exact path="/cadastro" component={Cadastro} />
        <Route exact path="/home" component={Home} />
        <Route exact path="/materias" component={Materias} />
      </Switch>
    </BrowserRouter>
  );
}
export default Routes;
