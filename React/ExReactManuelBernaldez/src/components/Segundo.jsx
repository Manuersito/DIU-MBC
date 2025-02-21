import React, { useState, useEffect, useContext } from "react";
import productoService from "../services/producto.service";
import { ProductContext } from "../context/ProductContext";
import "../Styles/styles.css";
import "bootstrap/dist/css/bootstrap.min.css";

function Segundo() {
  const [monedas, setMonedas] = useState([]);
  const [monedaSeleccionada, setMonedaSeleccionada] = useState(null);
  const [cantidadCompra, setCantidadCompra] = useState(1);
  const [loading, setLoading] = useState(false);
  const { cargarProductos } = useContext(ProductContext);
  

  useEffect(() => {
    const fetchProductos = async () => {
      try {
        const response = await productoService.getAll();
        if (response && response.data) {
          setMonedas(response.data);
        }
      } catch (error) {
        console.error("Error al cargar monedas:", error);
      }
    };
    fetchProductos();
  }, []);

  const seleccionarProducto = (producto) => {
    setMonedaSeleccionada(producto);
    setCantidadCompra(1);
  };

  const handleCompra = async () => {
    if (!monedaSeleccionada) return;
    setLoading(true);
    try {
      const nuevoStock = monedaSeleccionada.stock - cantidadCompra;
      const productoActualizado = {
        ...monedaSeleccionada,
        stock: nuevoStock,
        active: nuevoStock > 0,
      };
      await productoService.update(monedaSeleccionada.id, productoActualizado);
      setMonedaSeleccionada(productoActualizado);
      cargarProductos();
    } catch (error) {
      console.error("Error al actualizar el stock:", error);
    }
    setLoading(false);
  };

  const handleAñadir = async()=> {
    if (!monedaSeleccionada) return;
    setLoading(true);
    try {
      const nuevoStock = monedaSeleccionada.stock + 10;
      const productoActualizado = {
        ...monedaSeleccionada,
        stock: nuevoStock,
        active: nuevoStock > 0,
      };
      await productoService.update(monedaSeleccionada.id, productoActualizado);
      setMonedaSeleccionada(productoActualizado);
      cargarProductos();
      alert('Reserva aumentada en 10');
    } catch (error) {
      console.error("Error al actualizar el stock:", error);
    }
    setLoading(false);
  };

  return (
    
    <div className="container mt-4 d-flex">
      <div className="w-50">
        <h3>Lista Monedas</h3>
        {monedas.length === 0 ? (
          <h2>No hay monedas registradas</h2>
        ) : (
          <table className="table table-striped">
            <thead>
              <tr>
                <th>nombre</th>
                <th>Descripcion</th>
                <th>Reserva</th>
              </tr>
            </thead>
            <tbody>
              {monedas.map((producto, index) => (
                <tr key={index} onClick={() => seleccionarProducto(producto)} style={{ cursor: "pointer" }}>
                  <td>{producto.name}</td>
                  <td>{producto.brand}</td>
                  <td>{producto.stock}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
      <div className="w-50">
        {monedaSeleccionada && (
          <div className="card p-4">
            {loading ? (
              <div className="spinner-border text-primary" role="status">
                <span className="visually-hidden">Cargando...</span>
              </div>
            ) : (
              <>
                <h3>Moneda: {monedaSeleccionada.name}</h3>
                <div className="container mt-3">
      <h5>Capacidad de Reserva</h5>
      <div className="progress">
        <div
          className="progress-bar"
          role="progressbar"
          style={{ width: `${monedaSeleccionada.stock}%` }}
          aria-valuenow={monedaSeleccionada.stock}
          aria-valuemin="0"
          aria-valuemax="100"
        >
          {monedaSeleccionada.stock.toFixed(1)}%
        </div>
      </div>
    </div>
    
                <p>
                  <strong>Cantidad Reserva:</strong> {monedaSeleccionada.stock} 
                  {monedaSeleccionada.stock < 100 ? <span className="text-success"> Reserva no llena</span> : <span className="text-danger"> Reserva llena</span>}
                </p>
                <p><strong>Multiplicador:</strong> {monedaSeleccionada.price}</p>
                <div className="mb-3">
                  <label className="form-label">Conversion</label>
                  <input
                    type="number"
                    className="form-control"
                    min="1"
                    max={monedaSeleccionada.stock}
                    value={cantidadCompra}
                    onChange={(e) => setCantidadCompra(Number(e.target.value))}
                  />
                </div>
                <button
                  className="btn btn-success"
                  onClick={handleAñadir}
                  disabled={monedaSeleccionada.active == false}
                >
                  Añadir Reserva
                </button>
                <button
                  className="btn btn-warning"
                  onClick={handleCompra}
                  disabled={cantidadCompra > monedaSeleccionada.stock || cantidadCompra <= 0 || monedaSeleccionada.active == false}
                >
                  Comprar
                </button>
                <p><strong>Equivalencia en euros:</strong> €{(monedaSeleccionada.price * cantidadCompra).toFixed(2)}</p>
              </>
            )}
          </div>
        )}
      </div>
    </div>
  );
}

export default Segundo;
