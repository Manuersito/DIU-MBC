import React, { createContext, useState } from "react"; 

export const ProductContext = createContext();

const ProductProvider = ({ children }) => {
  const [numeroProductos, setNumeroProductos] = useState(0);
  const [productosMaximos, setProductosMaximos] = useState(6)

  return (
    <ProductContext.Provider value={{ numeroProductos, setNumeroProductos, productosMaximos, setProductosMaximos }}>
      {children}
    </ProductContext.Provider>
  );
};

export default ProductProvider;