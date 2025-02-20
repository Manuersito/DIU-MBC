import React, { useState, useEffect } from 'react';
import productService from './../service/examen.service.js';
import { useParams, useNavigate } from 'react-router-dom';
import './../styles/ProductAdd.css';

function ProductEdit() {
  const [name, setName] = useState('');
  const [stock, setStock] = useState('');
  const [price, setPrice] = useState('');
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const products = await productService.getAll();
        const product = products.find((p) => p.id.toString() === id);
        if (product) {
          setName(product.name);
          setStock(product.stock);
          setPrice(product.price);
        } else {
          console.log(`No se encontró un producto con el ID: ${id}`);
        }
      } catch (e) {
        console.log("Error al obtener los productos:", e);
      }
    };
    fetchProduct();
  }, [id]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validación para evitar precios o stock negativos
    if (price < 0 || stock < 0) {
      alert("El precio y el stock deben ser valores positivos.");
      return;
    }

    const formData = { id, name, stock, price };
    try {
      await productService.update(id, formData);
      alert("Producto actualizado correctamente");
      navigate('/productos');
    } catch (error) {
      console.log(error);
      alert("Error al actualizar el producto");
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4">Actualizar Producto</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Nombre</label>
          <input
            type="text"
            className="form-control"
            name="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Precio</label>
          <input
            type="number"
            className="form-control"
            name="price"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
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
            value={stock}
            onChange={(e) => setStock(e.target.value)}
            required
            min="0"  // Asegura que el stock sea al menos 1
          />
        </div>
        <button type="submit" className="btn btn-primary">Actualizar</button>
      </form>
    </div>
  );
}

export default ProductEdit;
