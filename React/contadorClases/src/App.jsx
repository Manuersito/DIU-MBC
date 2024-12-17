import React, { Component } from "react";
import Contador from "./components/contador";
import Botones from "./components/botones";
import "./App.css"

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      contador: 0, // Estado inicial
    };
  }

  // Función para incrementar el contador
  incrementar = () => {
    this.setState((prevState) => ({
      contador: prevState.contador + 1,
    }));
  };

  // Función para decrementar el contador
  decrementar = () => {
    this.setState((prevState) => ({
      contador: prevState.contador - 1,
    }));
  };

  // Función para resetear el contador
  resetear = () => {
    this.setState({
      contador: 0,
    });
  };

  render() {
    return (
      <div className="container-counter">
        <Contador valor={this.state.contador} />
        <Botones
          incButton={this.incrementar}
          decButton={this.decrementar}
          resButton={this.resetear}
        />
      </div>
    );
  }
}
