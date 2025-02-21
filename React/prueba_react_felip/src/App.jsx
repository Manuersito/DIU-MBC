import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import ProductProvider from "./context/ProductContext";
import NavBar from "./components/NavBar";
import ContactList from "./components/ProductList";
import EditProduct from "./components/EditProduct";
import AddProduct from "./components/AddProduct";
import BuyList from "./components/BuyList";


function App() {
  return (
    <ProductProvider>
      <Router>
        <NavBar />
        <Routes>
          <Route path="/" element={<Navigate to={"/products"} />} />
          <Route path="/products" element={<ContactList />} />
          <Route path="/products/buy" element={<BuyList/>}/>
          <Route path="/products/add" element={<AddProduct />} />
          <Route path="/products/edit/:productId" element={<EditProduct />} />
        </Routes>
      </Router>
    </ProductProvider>
  );
}

export default App;
