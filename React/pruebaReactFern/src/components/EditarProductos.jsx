import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import productoService from "../services/producto.service";
import "../Styles/styles.css";

function EditarProductos() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [producto, setProducto] = useState({
    id: "",
    name: "",
    brand: "",
    stock: 0,
    price: 0.0,
    active: true,
  });

  useEffect(() => {
    cargarProducto();
  }, []);

  const cargarProducto = async () => {
    try {
      const response = await productoService.getAll();
      const productoEncontrado = response.data.find(p => p.id === id);
      if (productoEncontrado) {
        setProducto(productoEncontrado);
      } else {
        console.error("Producto no encontrado");
        navigate("/listado");
      }
    } catch (error) {
      console.error("Error al cargar el producto:", error);
    }
  };

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setProducto({
      ...producto,
      [name]: type === "checkbox" ? checked : value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await productoService.update(id, producto);
      navigate("/listado");
    } catch (error) {
      console.error("Error al actualizar el producto:", error);
    }
  };

  return (
    <div className="container mt-4">
      <h2>Editar Producto</h2>
      <form onSubmit={handleSubmit} className="card p-4">
        <div className="mb-3">
          <label className="form-label">Nombre</label>
          <input type="text" name="name" className="form-control" value={producto.name} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Marca</label>
          <input type="text" name="brand" className="form-control" value={producto.brand} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Stock</label>
          <input type="number" name="stock" className="form-control" value={producto.stock} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Precio</label>
          <input type="number" step="0.01" name="price" className="form-control" value={producto.price} onChange={handleChange} required />
        </div>
        <div className="form-check mb-3">
          <input type="checkbox" name="active" className="form-check-input" checked={producto.active} onChange={handleChange} />
          <label className="form-check-label">Activo</label>
        </div>
        <button type="submit" className="btn btn-primary">Guardar Cambios</button>
      </form>
    </div>
  );
}

export default EditarProductos;
