import React, { useState } from "react";
import * as math from "mathjs";
import Calculadora from "./Calculadora";
import 'bootstrap/dist/css/bootstrap.css';
import "./App.css";

function App() {
  const [displayValue, setDisplayValue] = useState(""); // Almacena la expresión

  // Función para manejar los clics en los botones
  const handleInput = (value) => {
    setDisplayValue((prev) => prev + value); // Añade el valor a la expresión
  };

  // Función para calcular el resultado con mathjs
  const handleCalculate = () => {
    try {
      const result = math.evaluate(displayValue); // Evalúa la expresión completa
      setDisplayValue(String(result)); // Muestra el resultado
    } catch (error) {
      setDisplayValue("Error"); // Manejo de errores
    }
  };

  // Función para borrar la pantalla
  const handleClear = () => {
    setDisplayValue(""); // Reinicia la expresión
  };

  // Funcion cambiar signo
  const handleSignChange = () => {
    if (displayValue.charAt(0) === "-") {
      setDisplayValue(displayValue.slice(1)); // Elimina el signo negativo
    } else {
      setDisplayValue("-" + displayValue); // Agrega el signo negativo
    }
  };

  return (
    <div className="calculator container-fluid p-3">
      {/* Pantalla de la calculadora */}
      <div className="display w-100 mb-3 p-3 text-right">{displayValue || "0"}</div>
      {/* Pasando las funciones de la calculadora a los botones */}
      <Calculadora
        handleInput={handleInput}
        handleClear={handleClear}
        handleCalculate={handleCalculate}
        handleSignChange={handleSignChange}
      />
    </div>
  );
}

export default App;
