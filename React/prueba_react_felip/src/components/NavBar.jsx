import React, { useContext } from "react";
import { Link } from "react-router-dom";
import { ProductContext } from "../context/ProductContext";

const Navbar = () => {
  //Variable de estado obtenidas del ProductContext
  const { numeroProductos } = useContext(ProductContext);
  const { productosMaximos } = useContext(ProductContext)

  return (
    <nav className="navbar navbar-expand navbar-dark bg-dark">
      <Link to="/products" className="navbar-brand ms-2">Tutoriales</Link>
      <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to="/products/buy" className="nav-link">Lista de Compra</Link>
          </li>
        {numeroProductos < productosMaximos && (
          <li className="nav-item">
            <Link to="/products/add" className="nav-link">Añadir Tutorial</Link>
          </li>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
