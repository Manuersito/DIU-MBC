import React, { useState, useEffect, useContext } from "react";
import productoService from "../services/producto.service";
import { ProductContext } from "../context/ProductContext";
import "../Styles/styles.css";

function Segundo() {
  const [productos, setProductos] = useState([]);
  const [productoSeleccionado, setProductoSeleccionado] = useState(null);
  const [cantidadCompra, setCantidadCompra] = useState(1);
  const [loading, setLoading] = useState(false);
  const { cargarProductos } = useContext(ProductContext);

  useEffect(() => {
    const fetchProductos = async () => {
      try {
        const response = await productoService.getAll();
        if (response && response.data) {
          setProductos(response.data);
        }
      } catch (error) {
        console.error("Error al cargar productos:", error);
      }
    };
    fetchProductos();
  }, []);

  const seleccionarProducto = (producto) => {
    setProductoSeleccionado(producto);
    setCantidadCompra(1);
  };

  const handleCompra = async () => {
    if (!productoSeleccionado) return;
    setLoading(true);
    try {
      const nuevoStock = productoSeleccionado.stock - cantidadCompra;
      const productoActualizado = {
        ...productoSeleccionado,
        stock: nuevoStock,
        active: nuevoStock > 0,
      };
      await productoService.update(productoSeleccionado.id, productoActualizado);
      setProductoSeleccionado(productoActualizado);
      cargarProductos();
    } catch (error) {
      console.error("Error al actualizar el stock:", error);
    }
    setLoading(false);
  };

  return (
    <div className="container mt-4 d-flex">
      <div className="w-50">
        <h3>Product's list</h3>
        {productos.length === 0 ? (
          <h2>No hay productos registrados</h2>
        ) : (
          <table className="table table-dark table-striped">
            <thead>
              <tr>
                <th>Name</th>
                <th>Price</th>
              </tr>
            </thead>
            <tbody>
              {productos.map((producto, index) => (
                <tr key={index} onClick={() => seleccionarProducto(producto)} style={{ cursor: "pointer" }}>
                  <td>{producto.name}</td>
                  <td>{producto.price}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
      <div className="w-50">
        {productoSeleccionado && (
          <div className="card p-4">
            {loading ? (
              <div className="spinner-border text-primary" role="status">
                <span className="visually-hidden">Cargando...</span>
              </div>
            ) : (
              <>
                <h3>Product: {productoSeleccionado.name}</h3>
                <p>
                  <strong>Units in stock:</strong> {productoSeleccionado.stock} 
                  {productoSeleccionado.stock > 0 ? <span className="text-success"> Units ok</span> : <span className="text-danger"> Out of stock</span>}
                </p>
                <p><strong>Price:</strong> ${productoSeleccionado.price}</p>
                <div className="mb-3">
                  <label className="form-label">Units to buy</label>
                  <input
                    type="number"
                    className="form-control"
                    min="1"
                    max={productoSeleccionado.stock}
                    value={cantidadCompra}
                    onChange={(e) => setCantidadCompra(Number(e.target.value))}
                  />
                </div>
                <button
                  className="btn btn-success"
                  onClick={handleCompra}
                  disabled={cantidadCompra > productoSeleccionado.stock || cantidadCompra <= 0}
                >
                  Comprar
                </button>
                <p><strong>Total:</strong> €{(productoSeleccionado.price * cantidadCompra).toFixed(2)}</p>
              </>
            )}
          </div>
        )}
      </div>
    </div>
  );
}

export default Segundo;
