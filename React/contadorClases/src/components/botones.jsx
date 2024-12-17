import React, { Component } from "react";

export default class Botones extends Component {
  render() {
    return (
      <div className="buttons">
        <button onClick={this.props.decButton}>Decrementar</button>
        <button onClick={this.props.resButton}>Resetear</button>
        <button className="incButton" onClick={this.props.incButton}>Incrementar</button>
      </div>
    );
  }
}