import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './../styles/ProductAdd.css';
import productService from './../service/examen.service.js';

function ProductAdd() {
  const [formData, setFormData] = useState({
    stock: '',
    price: '',
    name: '',
  });
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validación para evitar precios o stock negativos
    if (formData.price < 0 || formData.stock < 0) {
      alert("El precio y el stock deben ser valores positivos.");
      return;
    }

    try {
      await productService.create(formData);
      alert("Producto añadido correctamente");
      navigate('/productos');
    } catch (error) {
      console.log(error);
      alert("Error al añadir el producto");
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4">Añadir Producto</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Nombre</label>
          <input
            type="text"
            className="form-control"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Precio</label>
          <input
            type="number"
            className="form-control"
            name="price"
            value={formData.price}
            onChange={handleChange}
            required
            min="1"  // Asegura que el precio no sea 0 o negativo
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Stock</label>
          <input
            type="number"
            className="form-control"
            name="stock"
            value={formData.stock}
            onChange={handleChange}
            required
            min="0"  // Asegura que el stock sea al menos 1
          />
        </div>
        <button type="submit" className="btn btn-primary">Añadir</button>
      </form>
    </div>
  );
}

export default ProductAdd;
