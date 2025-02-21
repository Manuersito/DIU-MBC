import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { ProductProvider } from "../src/context/ProductContext"

import Segundo from "./components/Segundo";


function App() {
  return (
    <ProductProvider>
     

        <Segundo />
        
    </ProductProvider>
  );
}

export default App;
