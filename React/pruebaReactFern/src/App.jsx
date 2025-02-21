import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { ProductProvider } from "../src/context/ProductContext"

import InsertarProducto from "../src/components/InsertarProducto"
import ListarProductos from "../src/components/ListarProductos";
import EditarProductos from "../src/components/EditarProductos";
import BarraProgreso from "../src/components/BarraProgreso";
import Segundo from "./components/Segundo";
import Navbar from "./components/Navbar";

function App() {
  return (
    <ProductProvider>
      <Navbar/>
        <BarraProgreso />
        <Routes>
          <Route path="/" element={<InsertarProducto />} />
          <Route path="listado" element={<ListarProductos />} />
          <Route path="editar/:id" element={<EditarProductos />} />
          <Route path="segundo" element={<Segundo />} />
        </Routes>
    </ProductProvider>
  );
}

export default App;
