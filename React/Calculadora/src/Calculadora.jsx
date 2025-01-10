import React from "react";
import 'bootstrap/dist/css/bootstrap.css';
import "./App.css";

function Calculadora({ handleInput, handleClear, handleCalculate, handleSignChange }) {
  return (
    <div className="App">
   
      <div className="container-fluid p-3">
        
        {/* Fila de botones */}
        <div className="row">
          <div className="col-3 mb-2">
            <button className="operator w-100" onClick={handleClear}>AC</button>
          </div>
          <div className="col-3 mb-2">
            <button className="w-100" onClick={handleSignChange}>+/-</button>
          </div>
          <div className="col-3 mb-2">
            <button className=" w-100" onClick={() => handleInput("%")}>%</button>
          </div>
          <div className="col-3 mb-2">
            <button className=" w-100" onClick={() => handleInput("/")}>÷</button>
          </div>
        </div>
        <div className="row">
          <div className="col-3 mb-2">
            <button className=" w-100" onClick={() => handleInput("7")}>7</button>
          </div>
          <div className="col-3 mb-2">
            <button className=" w-100" onClick={() => handleInput("8")}>8</button>
          </div>
          <div className="col-3 mb-2">
            <button className=" w-100" onClick={() => handleInput("9")}>9</button>
          </div>
          <div className="col-3 mb-2">
            <button className=" w-100" onClick={() => handleInput("*")}>×</button>
          </div>
        </div>
        <div className="row">
          <div className="col-3 mb-2">
            <button className=" w-100" onClick={() => handleInput("4")}>4</button>
          </div>
          <div className="col-3 mb-2">
            <button className=" w-100" onClick={() => handleInput("5")}>5</button>
          </div>
          <div className="col-3 mb-2">
            <button className=" w-100" onClick={() => handleInput("6")}>6</button>
          </div>
          <div className="col-3 mb-2">
            <button className=" w-100" onClick={() => handleInput("-")}>-</button>
          </div>
        </div>
        <div className="row">
          <div className="col-3 mb-2">
            <button className=" w-100" onClick={() => handleInput("1")}>1</button>
          </div>
          <div className="col-3 mb-2">
            <button className=" w-100" onClick={() => handleInput("2")}>2</button>
          </div>
          <div className="col-3 mb-2">
            <button className=" w-100" onClick={() => handleInput("3")}>3</button>
          </div>
          <div className="col-3 mb-2">
            <button className=" w-100" onClick={() => handleInput("+")}>+</button>
          </div>
        </div>
        <div className="row">
          <div className="col-6 mb-2">
            <button className=" w-100" onClick={() => handleInput("0")}>0</button>
          </div>
          <div className="col-3 mb-2">
            <button className=" w-100" onClick={() => handleInput(".")}>.</button>
          </div>
          <div className="col-3 mb-2">
            <button className=" w-100" onClick={handleCalculate}>=</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Calculadora;
