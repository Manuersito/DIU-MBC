import React, { useContext } from "react";
import { ProductContext } from "../context/ProductContext";

function BarraProgreso() {
  const { totalProductos } = useContext(ProductContext);
  const porcentaje = (totalProductos / 50) * 100;

  return (
    <div className="container mt-3">
      <h5>Capacidad del Inventario</h5>
      <div className="progress">
        <div
          className="progress-bar"
          role="progressbar"
          style={{ width: `${porcentaje}%` }}
          aria-valuenow={porcentaje}
          aria-valuemin="0"
          aria-valuemax="100"
        >
          {porcentaje.toFixed(1)}%
        </div>
      </div>
    </div>
  );
}

export default BarraProgreso;
