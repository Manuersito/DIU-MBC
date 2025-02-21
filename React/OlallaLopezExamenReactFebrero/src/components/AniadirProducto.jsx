import React, { useState, useEffect, useContext } from 'react';
import ProductDataService from '../services/api.js';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import ProgressBar from './ProgressBar.jsx';
import { ProgresoContext } from '../providers/ContextoProgressBar';

function AñadirProducto() {
  const MAX_PRODUCTOS = 7;
  const navigate = useNavigate();
  const { progreso, setProgreso } = useContext(ProgresoContext);

  const [formData, setFormData] = useState({
    name: '',
    stock: '',
    price: '',
    active: false // ✅ Usamos "active" en lugar de "activo"
  });

  useEffect(() => {
    ProductDataService.getAll().then(response => {
      setProgreso(response.data.length);
    });
  }, [setProgreso]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({
      ...formData,
      [name]: type === "checkbox" ? checked : value // ✅ Enviamos "true" o "false"
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (progreso >= MAX_PRODUCTOS) {
      alert("No puedes añadir más productos.");
      return;
    }

    const newProduct = {
      name: formData.name,
      stock: formData.stock,
      price: formData.price,
      active: formData.active // ✅ Se envía correctamente a la API
    };

    try {
      await ProductDataService.create(newProduct);
      alert("Producto añadido correctamente");
      setProgreso(progreso + 1);
      navigate('/');
    } catch (error) {
      console.log(error);
      alert("Error al añadir el producto");
    }
  };

  return (
    <div className="container mt-5 p-4 rounded w-75" style={{ backgroundColor: "#fce4ec" }}>
      <h2 className="mb-4 text-center text-dark">Añadir Producto</h2>

      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Nombre</label>
          <input type="text" className="form-control" name="name" value={formData.name} onChange={handleChange} required />
        </div>

        <div className="mb-3">
          <label className="form-label">Precio (€)</label>
          <input type="number" className="form-control" name="price" value={formData.price} onChange={handleChange} required />
        </div>

        <div className="mb-3">
          <label className="form-label">Stock</label>
          <input type="number" className="form-control" name="stock" value={formData.stock} onChange={handleChange} required />
        </div>

        {/* Checkbox para indicar si el producto está activo */}
        <div className="mb-3 form-check">
          <input
            type="checkbox"
            className="form-check-input"
            name="active"
            checked={formData.active}
            onChange={handleChange}
          />
          <label className="form-check-label">Producto Activo</label>
        </div>

        <button type="submit" className="btn btn-pink w-100" disabled={progreso >= MAX_PRODUCTOS}>
          {progreso >= MAX_PRODUCTOS ? "Límite Alcanzado" : "Guardar"}
        </button>
      </form>
    </div>
  );
}

export default AñadirProducto;
