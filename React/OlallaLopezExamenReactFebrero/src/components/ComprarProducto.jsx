import React, { useState, useEffect } from 'react';
import ProductsDataService from '../services/api.js';
import { useParams, useNavigate } from 'react-router-dom';

function ComprarProducto() {
    const [name, setName] = useState('');
    const [stock, setStock] = useState('');
    const [price, setPrice] = useState('');
    const [cantidad, setCantidad] = useState(''); // 🔥 Nueva variable para la cantidad de compra
    const { id } = useParams();
    const navigate = useNavigate();
    
    useEffect(() => {
        ProductsDataService.getAll() 
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
        setCantidad(e.target.value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const cantidadNumerica = parseInt(cantidad, 10);

        if (isNaN(cantidadNumerica) || cantidadNumerica <= 0) {
            alert("Por favor, ingrese una cantidad válida mayor a 0.");
            return;
        }
        if (cantidadNumerica > stock) {
            alert(`Total de unidades en stock: ${stock}.`);
            return;
        }

        // 🔥 Actualizar stock restando la cantidad comprada
        const nuevoStock = stock - cantidadNumerica;

        const formData = {
            id: id,
            name: name,
            stock: nuevoStock,
            price: price,
        };

        try { 
            await ProductsDataService.update(id, formData);
            alert(`Compra realizada con éxito. Nuevo stock: ${nuevoStock}`);
            navigate('/');
        } catch (error) {
            console.log(error);
            alert("Error al actualizar el stock");
        }
    };

    return (
        <div className="container mt-5 p-4 rounded w-50" style={{ backgroundColor: "#fce4ec" }}>
            <h2 className="mb-4 text-center text-dark">Comprar Producto</h2>

            <div className="mb-3">
                <h4>Producto:  {name}</h4>
                <h5>Stock Disponible:  {stock}</h5>
                <h5>Precio:  {price}€</h5>
            </div>

            <form onSubmit={handleSubmit}>
                {/* Cantidad a comprar */}
                <div className="mb-3">
                    <label className="form-label">Cantidad a comprar</label>
                    <input
                        type="number"
                        className="form-control"
                        value={cantidad}
                        onChange={handleChange}
                        min="1"
                        required
                    />
                </div>

                <button type="submit" className="btn btn-success w-100">
                    Comprar
                </button>
            </form>
        </div>
    );
}

export default ComprarProducto;
