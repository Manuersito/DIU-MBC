import React, { createContext, useState, useEffect } from "react";
import productoService from "../services/producto.service";

export const ProductContext = createContext();

export const ProductProvider = ({ children }) => {
  const [totalProductos, setTotalProductos] = useState(0);

  useEffect(() => {
    cargarProductos();
  }, []);

  const cargarProductos = async () => {
    try {
      const response = await productoService.getAll();
      if (response && response.data) {
        setTotalProductos(response.data.length);
      }
    } catch (error) {
      console.error("Error al cargar productos:", error);
    }
  };

  const eliminarProducto = async (id) => {
    try {
      await productoService.delete(id);
      cargarProductos(); // Actualizar la lista de productos después de eliminar
    } catch (error) {
      console.error("Error al eliminar producto:", error);
    }
  };

  return (
    <ProductContext.Provider value={{ totalProductos, cargarProductos, eliminarProducto }}>
      {children}
    </ProductContext.Provider>
  );
};
