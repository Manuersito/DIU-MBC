import React, { useState, useContext } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { useNavigate } from "react-router-dom";
import productoService from "../services/producto.service";
import { ProductContext } from "../context/ProductContext";
import BarraProgreso from "../components/BarraProgreso";

function InsertarProducto() {
  const [producto, setProducto] = useState({
    id: "",
    stock: 0,
    name: "",
    brand: "",
    price: 0.0,
    active: true,
  });
  const [error, setError] = useState("");
  const navigate = useNavigate();
  const { totalProductos, cargarProductos } = useContext(ProductContext);

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      const form = e.target;
      const id = form.id.value;
      const stock = form.stock.value;
      const name = form.name.value;
      const brand = form.brand.value;
      const price = form.price.value;
      const active = form.active.checked;

      // Verificar si el ID ya existe
      const response = await productoService.getAll();
      const productos = response.data;
      const existeProducto = productos.some((p) => p.id === id);
      if (existeProducto) {
        setError("El ID del producto ya existe. Por favor, use otro ID.");
        return;
      }

      const newProducto = { id, stock, name, brand, price, active };
      await productoService.create(newProducto);
      console.log("Producto registrado:", newProducto);

      // Actualizar el total de productos
      cargarProductos();
      navigate("/listado");
    } catch (error) {
      console.error("Error en el registro:", error);
    }
  };

  return (
    <>
      <form onSubmit={handleRegister} className="container mt-4 card p-4">
        <h2>Insertar Producto</h2>
        {error && <div className="alert alert-danger">{error}</div>}
        <div className="mb-3">
          <label className="form-label">ID</label>
          <input type="text" className="form-control" name="id" required />
        </div>
        <div className="mb-3">
          <label className="form-label">Stock</label>
          <input type="number" className="form-control" name="stock" required />
        </div>
        <div className="mb-3">
          <label className="form-label">Nombre</label>
          <input type="text" className="form-control" name="name" required />
        </div>
        <div className="mb-3">
          <label className="form-label">Marca</label>
          <input type="text" className="form-control" name="brand" required />
        </div>
        <div className="mb-3">
          <label className="form-label">Precio</label>
          <input type="number" step="0.01" className="form-control" name="price" required />
        </div>
        <div className="form-check mb-3">
          <input type="checkbox" className="form-check-input" name="active" defaultChecked />
          <label className="form-check-label">Activo</label>
        </div>
        <button type="submit" className="btn btn-primary">Guardar Producto</button>
      </form>
    </>
  );
}

export default InsertarProducto;
