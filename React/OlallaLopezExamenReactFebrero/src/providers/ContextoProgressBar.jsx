import { createContext, useState, useEffect } from "react";
import ProductDataService from "../services/api.js"; // 🔥 Importar API

export const ProgresoContext = createContext();

export function ProgresoProvider({ children }) {
    const [progreso, setProgreso] = useState(0);

    useEffect(() => {
        ProductDataService.getAll().then(response => {
            setProgreso(response.data.length); // 🔥 Cargar el progreso real al iniciar
        });
    }, []);

    return (
        <ProgresoContext.Provider value={{ progreso, setProgreso }}>
            {children}
        </ProgresoContext.Provider>
    );
}
