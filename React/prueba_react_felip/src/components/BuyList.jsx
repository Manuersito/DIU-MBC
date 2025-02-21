import React, { useState, useEffect, useContext } from "react";
import DataService from "../services/service";
import { Link } from "react-router-dom";
import { ProductContext } from "../context/ProductContext";
import { ProgressBar } from "react-bootstrap";

const BuyList = () => {
  //Variables de Estado que vamos a usar
  const [products, setProducts] = useState([]);
  const [currentProduct, setCurrentProduct] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);
  const [buscarNombre, setBuscarNombre] = useState("");
  const [progreso, setProgreso] = useState(0);
  const [cantidadComprar, setCantidadComprar] = useState("");

  //Variable de estado obtenidas del ProductContext
  const { setNumeroProductos } = useContext(ProductContext);
  const { productosMaximos } = useContext(ProductContext);

  //Actua cuando se monta el componente
  useEffect(() => {
    retrieveProducts();
  }, []);

  //Recoge todos los productos
  const retrieveProducts = () => {
    DataService.getAll()
      .then(response => {
        setProducts(response.data);
        actualizarProgreso(response.data.length);
        setNumeroProductos(response.data.length);
      })
      .catch(e => console.log("Error al recuperar productos:", e));
  };

  //Actualiza el progreso de la barra de progreso
  const actualizarProgreso = (productCount) => {
    if (productosMaximos > 0) {
      const progresoActual = (productCount / productosMaximos) * 100;
      setProgreso(Math.min(progresoActual, 100));
    } else {
      setProgreso(0);
    }
  };

  //Metodo que elimina un producto cuando le damos a Eliminar
  const deleteProduct = (id) => {
    DataService.delete(id)
      .then(() => retrieveProducts())
      .catch(e => console.log("Error al eliminar:", e));
  };

  //Muestra la informacion de un producto cuando clickamos en él
  const setActiveProduct = (product, index) => {
    setCurrentProduct(product);
    setCurrentIndex(index);
    setCantidadComprar("");
  };

  //Maneja el cambio en el input de la barra de busqueda
  const onchangeNombre = (e) => {
    setBuscarNombre(e.target.value);
  };

  //Maneja el cambio en el input de la cantidad a comprar
  const handleCantidadChange = (e) => {
    setCantidadComprar(e.target.value);
  };

  //Metodo que se activa cuando pulsamos comprar, actualiza el stock
  const handleBuyProduct = () => {
    if (!currentProduct) return;
    
    const cantidad = parseInt(cantidadComprar);
    if (isNaN(cantidad) || cantidad <= 0) {
      alert("Ingrese una cantidad válida.");
      return;
    }
  
    if (cantidad > currentProduct.stock) {
      alert("No hay suficiente stock disponible.");
      return;
    }
  
    const nuevoStock = currentProduct.stock - cantidad;
  
    // Actualizar stock en la API
    DataService.update(currentProduct.id, { ...currentProduct, stock: nuevoStock })
      .then(() => {
        // Actualizar el estado de productos
        setProducts(prevProducts =>
          prevProducts.map(product =>
            product.id === currentProduct.id ? { ...product, stock: nuevoStock } : product
          )
        );
  
        setCurrentProduct(prevProduct => ({
          ...prevProduct,
          stock: nuevoStock
        }));
  
        setCantidadComprar("");
      })
      .catch(e => console.log("Error al actualizar stock:", e));
  };
  
  //Muestra solo los productos cuyo nombre incluye el input de la barra de busqueda
  const productosFiltrados = products.filter((producto) =>
    producto.name.toLowerCase().includes(buscarNombre.toLowerCase())
  );

  return (
    <div className="container mt-4">
      <div className="row justify-content-center mb-3">
        <div className="col-md-8">
          <h2 className="text-center">Capacidad para Tutoriales</h2>
          <ProgressBar now={progreso} label={`${progreso.toFixed(2)}%`} />
        </div>
      </div>

      <div className="row justify-content-center">
        <div className="col-md-8">
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Buscar por nombre"
              value={buscarNombre}
              onChange={onchangeNombre}
            />
            <div className="input-group-append">
              <button className="btn btn-outline-dark" type="button">
                Buscar
              </button>
            </div>
          </div>
        </div>
      </div>

      <div className="row">
        <div className="col-md-6">
          <h4>Lista de Tutoriales</h4>
          <ul className="list-group">
            {productosFiltrados.length > 0 ? (
              productosFiltrados.map((product, index) => (
                <li
                  key={index}
                  className={"list-group-item " + (index === currentIndex ? "active" : "")}
                  onClick={() => setActiveProduct(product, index)}
                >
                  {product.name}
                </li>
              ))
            ) : (
              <li className="list-group-item text-center">No se encontraron productos</li>
            )}
          </ul>
        </div>

        <div className="col-md-6">
          {currentProduct ? (
            <div>
              <h4>Tutorial</h4>
              <div><strong>Nombre:</strong> {currentProduct.name}</div>
              <div><strong>Stock:</strong> {currentProduct.stock}</div>
              <div><strong>Precio:</strong> {currentProduct.price}</div>
              <div className="d-flex align-items-center mt-2">
                <strong className="me-2">Unidades a comprar:</strong>
                <input
                  type="text"
                  min="1"
                  className="form-control form-control-sm w-25"
                  style={{ width: "80px" }}
                  value={cantidadComprar}
                  onChange={handleCantidadChange}
                />
              </div>
              <div><strong>Total:</strong> {cantidadComprar ? cantidadComprar * currentProduct.price : 0}</div>
              <Link to={`/products/edit/${currentProduct.id}`} className="btn btn-warning">
                Editar
              </Link>
              <button
                className="btn btn-danger ms-2"
                onClick={() => deleteProduct(currentProduct.id)}
              >
                Eliminar
              </button>
              <button
                className="btn btn-success ms-2"
                onClick={handleBuyProduct}
              >
                Comprar
              </button>
            </div>
          ) : (
            <div>
              <br />
              <p>Seleccione un producto...</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default BuyList;
