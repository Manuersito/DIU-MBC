import React, { useEffect, useState } from "react";
import { getAllProducts } from "../services/api.services.js";

export default function ProductList() {
  const [products, setProducts] = useState([]); // Estado para los productos
  const [total, setTotal] = useState(0); // Estado para el total de la compra
  const [selectedProducts, setSelectedProducts] = useState([]); // Estado para los productos seleccionados


  function handleCalcularTotal() {
    let newTotal = 0;
    products.forEach(product => {
      newTotal += product.price;
    });
    setTotal(newTotal); // Usamos setTotal para actualizar el estado
  }
  


  useEffect(() => {
    getAllProducts()
      .then((response) => {
        console.log("Productos obtenidos:", response.data); // Depuración
        setProducts(response.data); // Almacena los productos en el estado
      })
      .catch((error) => {
        console.error("Error al obtener productos:", error);
      });
  }, []);

  return (
    <div>
      <h1>Lista de productos</h1>

      <table border="1">
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Precio</th>
          </tr>
        </thead>
        <tbody>
          {products.length > 0 ? (
            products.map((product) => (
              <tr key={product.id}
              onClick={() => setSelectedProducts(product)}
              >
                <td>{product.name}</td>
                <td>${product.price.toFixed(2)}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="2">No hay productos disponibles</td>
            </tr>
          )}
        </tbody>
      </table>
      <h3>Producto seleccionado: {selectedProducts?.name ? `${selectedProducts.name} - $${selectedProducts.price} - ${selectedProducts.stock}` : "Ninguno"}</h3>

      <h2>Total: ${total}</h2>
      <button onClick={handleCalcularTotal}>Calcular total</button>
    </div>
  );
}
