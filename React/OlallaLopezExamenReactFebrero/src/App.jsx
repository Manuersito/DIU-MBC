import './App.css';
import './index.css';
import React from 'react';
import ListaProductos from './components/ListaProductos';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import AniadirProducto from './components/AniadirProducto';
import EditarProducto from './components/EditarProducto';
import ComprarProductos from './components/ComprarProducto';
import { ProgresoProvider } from './providers/ContextoProgressBar'; // Importar el Proveedor de contexto
import ProgressBar from './components/ProgressBar'; // Importar la barra de progreso
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <ProgresoProvider> {/* Envuelve toda la app */}
      <ProgressBar />  {/* Ahora se mostrará en toda la aplicación */}

      <Router>
        <nav className="navbar navbar-expand-lg navbar-pink fixed-top">
          <div className="container-fluid">
            <button className="navbar-toggler d-lg-none" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                <li className="nav-item">
                  <Link to="/aniadirProducto" className="nav-link text-white">Añadir Producto</Link> 
                </li>
                <li>
                  <Link to="/" className='nav-link text-white'>Lista de Productos</Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>

        <div className="container mt-1">
          <Routes>
            <Route path='/' element={<ListaProductos />} />
            <Route path='/aniadirProducto' element={<AniadirProducto />} />
            <Route path='/editarProducto/:id' element={<EditarProducto />} />
            <Route path='/comprarProducto/:id' element={<ComprarProductos/>}></Route>
          </Routes>
        </div>
      </Router>
    </ProgresoProvider>
  );
}

export default App;
