import React, { useEffect, useState, useContext } from 'react';
import ProductDataService from "../services/api.js";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link } from 'react-router-dom';
import '../styles/ListaProductos.css';
import { ProgresoContext } from '../providers/ContextoProgressBar';

function ListaProductos() {
  const [productos, setProductos] = useState([]);
  const [productoActual, setProductoActual] = useState(null);
  const { setProgreso } = useContext(ProgresoContext);

  const obtenerListaProductos = () => {
    ProductDataService.getAll()
      .then(response => {
        setProductos(response.data);
        setProgreso(response.data.length);
      })
      .catch(error => console.error("Error al obtener los productos:", error)); // 🔥 Evita crasheo si la API falla
  };

  const handleDelete = (id, event) => {
    event.preventDefault();
    ProductDataService.delete(id)
      .then(() => {
        obtenerListaProductos();
        setProductoActual(null);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const handleComprar = (id,event) =>{
    event.preventDefault();
    ProductDataService.update(id)
    .then(()=>{
      obtenerListaProductos();
    })
  }

  useEffect(() => {
    obtenerListaProductos();
  }, []); // No usar [setProgreso] porque puede causar re-render infinitos

  const setProductoActivo = (producto) => {
    setProductoActual(producto);
  };

  return (
    <div className="container mt-5">
      <h2 className="text-center text-dark mb-4">Lista de Productos</h2>

      <div className="row">
        <div className="col-md-6">
          <ul className="list-group">
            {productos.map((producto) => (
              <li key={producto.id}
                className="list-group-item list-group-item-action text-dark"
                style={{ backgroundColor: "#f8bbd0", cursor: "pointer" }}
                onClick={() => setProductoActivo(producto)}>
                <h5 className="mb-0">{producto.name}</h5>
              </li>
            ))}
          </ul>
        </div>

        <div className="col-md-6">
          {productoActual ? (
            <div className="card p-3" style={{ backgroundColor: "#fce4ec" }}>
              <h4 className="text-dark">Detalles del Producto</h4>
              <p><strong>ID:</strong> {productoActual.id}</p>
              <p><strong>Nombre:</strong> {productoActual.name}</p>
              <p><strong>Stock:</strong> {productoActual.stock}</p>
              <p><strong>Precio:</strong> {productoActual.price}€</p>
              <p><strong>Estado:</strong> {productoActual.active ? "Activado" : "Desactivado"}</p>

              <div>
                {productoActual.id && (
                  <button className='rounded btn btn-pink espacio'>
                    <Link to={`/editarProducto/${productoActual.id}`} className="nav-link">Editar</Link>
                  </button>
                )}
                <button className='btn btn-danger espacio'>
                  <Link to="#" className="nav-link" onClick={(e) => handleDelete(productoActual.id, e)}>Eliminar</Link>
                </button>

                <button className='btn btn-success espacio'>
                  <Link to={`/comprarProducto/${productoActual.id}`} className='nav-link'>Comprar</Link>
                </button>
              </div>
            </div>
          ) : (
            <h5 className="text-muted">Seleccione un producto</h5>
          )}
        </div>
      </div>
    </div>
  );
}

export default ListaProductos;
