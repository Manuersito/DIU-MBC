import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { ProgressProvider } from './providers/ProgressContext.jsx';
import ProductList from './components/ProductList.jsx';
import ProductAdd from './components/ProductAdd.jsx';
import ProductEdit from './components/ProductEdit.jsx';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import ProgressBar from './components/ProgressBar.jsx';
import Navbar from './components/Navbar.jsx';

function App() {
  return (
    <ProgressProvider>
      <Router>
        <Navbar />
        <div className="container mt-5 pt-4">
          <ProgressBar />
          <Routes>
            <Route path="/añadir" element={<ProductAdd />} />
            <Route path="/editar/:id" element={<ProductEdit />} />
            <Route path="/productos" element={<ProductList />} />
            <Route path="/" element={<ProductList />} />
          </Routes>
        </div>
      </Router>
    </ProgressProvider>
  );
}

export default App;
