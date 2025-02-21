import React, { useEffect, useState, useContext } from "react";
import productoService from "../services/producto.service";
import { useNavigate } from "react-router-dom";
import { ProductContext } from "../context/ProductContext";
import "../Styles/styles.css";

function ListarProductos() {
  const [productos, setProductos] = useState([]);
  const [productoSeleccionado, setProductoSeleccionado] = useState(null);
  const [cantidadCompra, setCantidadCompra] = useState(1);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const { eliminarProducto, cargarProductos } = useContext(ProductContext);

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

  const Editar = (id) => {
    navigate("/editar/" + id);
  };

  const seleccionarProducto = (producto) => {
    setProductoSeleccionado(producto);
    setCantidadCompra(1);
  };

  const handleEliminar = async (id) => {
    await eliminarProducto(id);
    try {
      const response = await productoService.getAll();
      if (response && response.data) {
        setProductos(response.data);
        setProductoSeleccionado(null);
      }
    } catch (error) {
      console.error("Error al actualizar la lista de productos:", error);
    }
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
        {productos.length === 0 ? (
          <h2>No hay productos registrados</h2>
        ) : (
          <table className="table table-striped table-bordered">
            <thead className="table-dark">
              <tr>
                <th scope="col">Nombre</th>
                <th scope="col">Marca</th>
                <th scope="col">Activo</th>
              </tr>
            </thead>
            <tbody>
              {productos.map((producto, index) => (
                <tr key={index} onClick={() => seleccionarProducto(producto)} style={{ cursor: "pointer" }}>
                  <td>{producto.name}</td>
                  <td>{producto.brand}</td>
                  <td>{producto.active ? "Sí" : "No"}</td>
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
                <h3>{productoSeleccionado.name}</h3>
                <p><strong>Marca:</strong> {productoSeleccionado.brand}</p>
                <p><strong>Stock:</strong> {productoSeleccionado.stock}</p>
                <p><strong>Precio:</strong> ${productoSeleccionado.price}</p>
                <p><strong>Activo:</strong> {productoSeleccionado.active ? "Sí" : "No"}</p>
                <div className="mb-3">
                  <label className="form-label">Cantidad a comprar</label>
                  <input
                    type="number"
                    className="form-control"
                    min="1"
                    max={productoSeleccionado.stock}
                    value={cantidadCompra}
                    onChange={(e) => setCantidadCompra(Number(e.target.value))}
                  />
                </div>
                <div className="d-flex gap-2">
                  <button className="btn btn-primary" onClick={() => Editar(productoSeleccionado.id)}>Editar</button>
                  <button className="btn btn-danger" onClick={() => handleEliminar(productoSeleccionado.id)}>Eliminar</button>
                  <button className="btn btn-success" onClick={handleCompra} disabled={cantidadCompra > productoSeleccionado.stock || cantidadCompra <= 0}>
                    Comprar
                  </button>
                </div>
              </>
            )}
          </div>
        )}
      </div>
    </div>
  );
}

export default ListarProductos;