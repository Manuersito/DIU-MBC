import React, { useState, useEffect } from 'react';
import ProductsDataService from '../services/api.js';
import { useParams, useNavigate } from 'react-router-dom';

function EditarProducto() {
    const [name, setName] = useState('');
    const [stock, setStock] = useState('');
    const [price, setPrice] = useState('');
    const { id } = useParams();
    const navigate = useNavigate();
    
    useEffect(() => {
        ProductsDataService.getAll() // Suponiendo que `getAll()` devuelve una lista de productos
            .then((response) => {
                const filteredProduct = response.data.find(product => product.id.toString() === id); 
                
                if (filteredProduct) {
                    setName(filteredProduct.name);
                    setPrice(filteredProduct.price);
                    setStock(filteredProduct.stock);
                } else {
                    console.log(`No se encontró un producto con el ID: ${id}`);
                }
            })
            .catch((e) => {
                console.log("Error al obtener los productos:", e);
            });
    }, [id]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        if (name === "name") setName(value);
        if (name === "stock") setStock(value);
        if (name === "price") setPrice(value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        
        const formData = {
            id: id,
            name: name,
            stock: stock,
            price: price,
        };
        
        try { 
            ProductsDataService.update(id, formData);
            alert("Producto actualizado correctamente");
        } catch (error) {
            console.log(error);
            alert("Error al actualizar el producto");
        }
        navigate('/');
    };

    return (
        <div className="container mt-5">
            <h2 className="mb-4">Actualizar producto</h2>
            <form onSubmit={handleSubmit}>
                {/* Nombre */}
                <div className="mb-3">
                    <label className="form-label">Nombre</label>
                    <input
                        type="text"
                        className="form-control"
                        name="name"
                        value={name}
                        onChange={handleChange}
                        required
                    />
                </div>
                
                {/* Stock */}
                <div className="mb-3">
                    <label className="form-label">Stock</label>
                    <input
                        type="text"
                        className="form-control"
                        name="stock"
                        value={stock}
                        onChange={handleChange}
                        required
                    />
                </div>

                {/* Precio */}
                <div className="mb-3">
                    <label className="form-label">Precio (€)</label>
                    <input
                        type="number"
                        className="form-control"
                        name="price"
                        value={price}
                        onChange={handleChange}
                        required
                    />
                </div>

                {/* Botón de Enviar */}
                <button type="submit" className="btn btn-primary">Actualizar</button>
            </form>
        </div>
    );
}

export default EditarProducto;