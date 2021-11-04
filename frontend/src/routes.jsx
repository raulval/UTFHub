import { Provider } from "react-redux";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import { PersistGate } from "redux-persist/integration/react";
import { Cadastro } from "./pages/Cadastro";
import { Home } from "./pages/Home";
import { Login } from "./pages/Login";
import { Materias } from "./pages/Materias";
import { persistor, store } from "./store";

function Routes() {
  return (
    <Provider store={store}>
      <PersistGate loading={null} persistor={persistor}>
        <BrowserRouter>
          <Switch>
            <Route exact path="/" component={Login} />
            <Route exact path="/cadastro" component={Cadastro} />
            <Route exact path="/home" component={Home} />
            <Route exact path="/materias" component={Materias} />
          </Switch>
        </BrowserRouter>
      </PersistGate>
    </Provider>
  );
}
export default Routes;
