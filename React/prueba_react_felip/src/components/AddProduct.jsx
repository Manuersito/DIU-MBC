import React, { useState } from "react";
import DataService from "../services/service";
import { useNavigate } from "react-router-dom";

const AddProduct = () => {
  const navigate = useNavigate();

  // Estado para gestionar los datos del formulario
  const [product, setProduct] = useState({
    name: "",
    brand: "",
    price: "",
    stock: "",
    active: false,
  });

  // Maneja los cambios en los inputs de texto
  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct((prevState) => ({
      ...prevState, //Los ... guardan todo el objeto para cambiarle un solo atributo y prevState guarda el objeto que habia antes de ser modificado
      [name]: value,
    }));
  };

  // Maneja los cambios en el checkbox
  const handleChangeCheckbox = (e) => {
    setProduct((prevState) => ({
      ...prevState,
      active: e.target.checked,
    }));
  };

  // Maneja el envío del formulario
  const handleSubmit = (e) => {
    e.preventDefault();

    DataService.create(product)
      .then(() => {
        navigate("/products");
      })
      .catch((error) => {
        console.error("Error al añadir el producto:", error);
      });
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center">Agregar Tutorial</h2>
      <div className="row justify-content-center">
        <div className="col-md-8">
          <form onSubmit={handleSubmit} className="mt-4">
            <div className="row mb-3">
              <div className="col-md-6">
                <label htmlFor="name" className="form-label">Nombre</label>
                <input
                  type="text"
                  className="form-control"
                  id="name"
                  name="name"
                  value={product.name}
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="col-md-6">
                <label htmlFor="brand" className="form-label">Marca</label>
                <input
                  type="text"
                  className="form-control"
                  id="brand"
                  name="brand"
                  value={product.brand}
                  onChange={handleChange}
                  required
                />
              </div>
            </div>

            <div className="row mb-3">
              <div className="col-md-6">
                <label htmlFor="price" className="form-label">Precio</label>
                <input
                  type="text"
                  className="form-control"
                  id="price"
                  name="price"
                  value={product.price}
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="col-md-6">
                <label htmlFor="stock" className="form-label">Stock</label>
                <input
                  type="text"
                  className="form-control"
                  id="stock"
                  name="stock"
                  value={product.stock}
                  onChange={handleChange}
                  required
                />
              </div>
            </div>

            <div className="row mb-3">
              <div className="col-12">
                <div className="form-check">
                  <input
                    type="checkbox"
                    className="form-check-input"
                    id="active"
                    name="active"
                    checked={product.active}
                    onChange={handleChangeCheckbox}
                  />
                  <label className="form-check-label" htmlFor="active">
                    Disponible
                  </label>
                </div>
              </div>
            </div>

            <div className="row">
              <div className="col-12 text-center">
                <button type="submit" className="btn btn-success mt-3">
                  Agregar Tutorial
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddProduct;
