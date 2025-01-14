import React, { useState } from "react";
import Calculadora from "./Calculadora";

function App() {
  const [displayValue, setDisplayValue] = useState(""); // Almacena la expresión
  const [calculated, setCalculated] = useState(false); // Nuevo estado para saber si se presionó '='

  // Función para manejar los clics en los botones
  const handleInput = (value) => {
    if (calculated && !isNaN(value)) {
      // Si se presiona un número después de calcular, sobrescribir el valor
      setDisplayValue(value);
      setCalculated(false); // Permitir una nueva operación
    } else if (calculated && isNaN(value)) {
      // Si se presiona un operador después de calcular, continuar la operación
      setDisplayValue(displayValue + value);
      setCalculated(false);
    } else {
      // Continuar añadiendo números y operadores normalmente
      setDisplayValue((prev) => prev + value);
    }
  };

  // Función para calcular el resultado con Math.js usando fetch
  const handleCalculate = async () => {
    try {
      // Realizando la solicitud POST con fetch
      const response = await fetch("http://api.mathjs.org/v4/", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          expr: displayValue, // Enviar la expresión matemática
        }),
      });

      // Verificando si la solicitud fue exitosa
      if (!response.ok) {
        throw new Error("Error en la solicitud");
      }

      const result = await response.json(); // Parsear la respuesta JSON
      setDisplayValue(String(result.result)); // Mostrar el resultado
      setCalculated(true); // Marcar que se ha calculado
    } catch (error) {
      setDisplayValue("Error"); // Manejo de errores
      setCalculated(false); // No permitir más cálculos hasta que se cambie la expresión
    }
  };

  // Función para borrar la pantalla
  const handleClear = () => {
    setDisplayValue(""); // Reinicia la expresión
    setCalculated(false); // Restablece el estado de la operación calculada
  };

  // Función para cambiar el signo de la expresión
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
