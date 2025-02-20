import React, { useState, useEffect, useContext } from 'react';
import { Link } from 'react-router-dom';
import './../styles/ProductList.css';
import { ProgressContext } from './../providers/ProgressContext.jsx';
import productService from './../service/examen.service.js';

function ProductList() {
  const [products, setProducts] = useState([]);
  const [currentProduct, setCurrentProduct] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);
  const [quantity, setQuantity] = useState(1);  // Estado para la cantidad seleccionada
  const { setProgress } = useContext(ProgressContext);

  const retrieveProducts = async () => {
    try {
      const data = await productService.getAll();
      setProducts(data);
      setProgress(data.length);
    } catch (e) {
      console.log(e);
    }
  };

  useEffect(() => {
    retrieveProducts();
  }, []);

  const handleDelete = async (id, event) => {
    event.preventDefault();
    try {
      await productService.delete(id);
      retrieveProducts();
      setCurrentProduct(null);
    } catch (e) {
      console.log(e);
    }
  };

  const handleQuantityChange = (e) => {
    setQuantity(e.target.value);
  };

  const handleAddToCart = async () => {
    if (currentProduct) {
      // Verifica que la cantidad no supere el stock
      if (quantity > currentProduct.stock) {
        alert('No puedes seleccionar más cantidad de la disponible en stock.');
      } else {
        // Reduce el stock localmente
        const updatedProduct = { ...currentProduct, stock: currentProduct.stock - quantity };
        setCurrentProduct(updatedProduct);

        // Actualiza el stock en el servidor
        try {
          await productService.update(currentProduct.id, updatedProduct);
          alert(`Añadido ${quantity} ${currentProduct.name} al carrito.`);
        } catch (e) {
          console.log('Error al actualizar el stock en el servidor', e);
        }
      }
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center mb-4">Lista de Producto</h2>
      <div className="row">
        <div className="col-md-6">
          <ul className="list-group">
            {products.length > 0 ? (
              products.map((product, index) => (
                <li
                  key={index}
                  className={`list-group-item d-flex justify-content-between align-items-center ${
                    index === currentIndex ? 'list-group-item-primary' : ''
                  }`}
                  onClick={() => {
                    setCurrentProduct(product);
                    setCurrentIndex(index);
                    setQuantity(1);  // Reset quantity when selecting a new product
                  }}
                >
                  <span>{product.name}</span>
                </li>
              ))
            ) : (
              <p className="text-center">No hay productos disponibles</p>
            )}
          </ul>
        </div>

        {currentProduct ? (
          <div className="col-md-6">
            <div className="border p-3">
              <h4 className="text-center">Detalles del Producto</h4>
              <p><strong>Stock:</strong> {currentProduct.stock}</p>
              <p><strong>Precio:</strong> {currentProduct.price} €</p>
              <div className="mb-3">
                <label htmlFor="quantity" className="form-label">Cantidad</label>
                <input
                  type="number"
                  id="quantity"
                  className="form-control"
                  value={quantity}
                  onChange={handleQuantityChange}
                  min="1"
                  max={currentProduct.stock}  // Impide seleccionar más de lo que hay en stock
                />
              </div>
              <button className="btn btn-secondary" onClick={() => setCurrentProduct(null)}>Volver a la lista</button>
              <Link to={`/editar/${currentProduct.id}`} className="btn btn-warning mt-2">Editar</Link>
              <button className="btn btn-danger mt-2" onClick={(e) => handleDelete(currentProduct.id, e)}>Eliminar</button>
              <button className="btn btn-primary mt-2" onClick={handleAddToCart}>Comprar</button>
            </div>
          </div>
        ) : (
          <div className="col-md-6">
            <div className="border p-3">
              <h4 className="text-center">Selecciona un producto para ver detalles</h4>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default ProductList;
