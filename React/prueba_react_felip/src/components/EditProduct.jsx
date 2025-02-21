import React, { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import DataService from "../services/service";

const EditProduct = () => {
  const { productId } = useParams(); //Coge el id de la url, para ello tiene que estar especificado en el app.jsx como :productId
  const navigate = useNavigate(); //Funciona como el Link, para poder navegar a otras urls
  
  // Estado para gestionar los datos del formulario
  const [product, setProduct] = useState({
    name: "",
    brand: "",
    price: "",
    stock: "",
    active: false,
  });

  //Actua cuando se monta el componente y hace un getAll a la API y filtra la respuesta con el id del producto de la url
  useEffect(() => {
    DataService.getAll()
      .then((response) => {
        const foundProduct = response.data.find((p) => p.id.toString() === productId);
        if (foundProduct) {
          setProduct(foundProduct);
        } else {
          console.error("Producto no encontrado");
        }
      })
      .catch((error) => {
        console.error("Error al obtener el producto:", error);
      });
  }, [productId]);

  // Maneja los cambios en los inputs de texto
  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct((prevState) => ({
      ...prevState,
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
    DataService.update(productId, product)
      .then(() => {
        navigate("/products");
      })
      .catch((error) => {
        console.error("Error al actualizar el producto:", error);
      });
  };

  return (
    <section className="edit-product p-4">
      <div className="container">
        <div className="row text-center">
          <div className="col">
            <p className="h3 fw-bold text-dark">Editar Tutorial</p>
          </div>
        </div>

        <div className="row mt-3 justify-content-center">
          <div className="col-md-6">
            <form className="" onSubmit={handleSubmit}>
              <div className="row mb-2">
                <div className="col-12">
                  <input
                    type="text"
                    name="name"
                    className="form-control"
                    placeholder="Nombre"
                    value={product.name}
                    onChange={handleChange}
                    required
                  />
                </div>
              </div>

              <div className="row mb-2">
                <div className="col-12">
                  <input
                    type="text"
                    name="brand"
                    className="form-control"
                    placeholder="Marca"
                    value={product.brand}
                    onChange={handleChange}
                    required
                  />
                </div>
              </div>

              <div className="row mb-2">
                <div className="col-12">
                  <input
                    type="text"
                    name="price"
                    className="form-control"
                    placeholder="Precio"
                    value={product.price}
                    onChange={handleChange}
                    required
                  />
                </div>
              </div>

              <div className="row mb-2">
                <div className="col-12">
                  <input
                    type="text"
                    name="stock"
                    className="form-control"
                    placeholder="Stock"
                    value={product.stock}
                    onChange={handleChange}
                    required
                  />
                </div>
              </div>

              <div className="row mb-2">
                <div className="col-12">
                  <div className="form-check">
                    <input
                      type="checkbox"
                      name="active"
                      className="form-check-input"
                      checked={product.active}
                      onChange={handleChangeCheckbox}
                    />
                    <label className="form-check-label">Disponible</label>
                  </div>
                </div>
              </div>

              <div className="row mt-4">
                <div className="col text-center">
                  <input type="submit" className="btn btn-dark" value="Guardar Cambios" />
                  <Link to={"/products"} className="btn btn-outline-dark ms-2">
                    Cancelar
                  </Link>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </section>
  );
};

export default EditProduct;
