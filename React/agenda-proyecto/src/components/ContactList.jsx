import { useEffect, useState } from "react";
import { getContacts } from "./apiService";
import ProgressBar from 'react-bootstrap/ProgressBar';
import '../styles.css'


const ContactList = ({ onSelectContact }) => {
    const [contacts, setContacts] = useState([]);
    const [error, setError] = useState(null);
    const [selectedContact, setSelectedContact] = useState(null);

    const progreso = Math.min(contacts.length, 50);
    const progresoPorcentaje = (progreso / 50) * 100;

    useEffect(() => {
        loadContacts();
    }, []);

    const loadContacts = async () => {
        try {
            const data = await getContacts();
            setContacts(data.slice(0, 50));
            setError(null);
        } catch (err) {
            setError(err.message);
        }
    };

    return (
        <div className="p-4 flex-grow">
            <h2 className="text-xl font-bold mb-4">Lista de Contactos</h2>
            {error ? (
                <p className="text-red-500">{error}</p>
            ) : (
                <div className="flex gap-4">
                    {/* Barra de progreso */}
                    <div className="w-1/3">
                        <ProgressBar 
                            variant={
                                progresoPorcentaje <= 50 ? "success" : 
                                progresoPorcentaje <= 75 ? "warning" : "danger"
                            }
                            animated 
                            now={progresoPorcentaje} 
                            label={`${progresoPorcentaje.toFixed(0)}%`} 
                        />
                        {progresoPorcentaje >= 100 && (
                            <p className="text-red-600 font-bold mt-2">
                                ⚠️ Límite alcanzado: No puedes agregar más contactos.
                            </p>
                        )}
                    </div>

                    {/* Tabla de contactos */}
                    <div style={{ width: '300px', maxHeight: '500px', overflowY: 'auto' }}>
                        <table className="w-full border-collapse border border-gray-300">
                            <thead>
                                <tr className="bg-gray-200">
                                    <th className="border p-2">Nombre</th>
                                    <th className="border p-2">Apellidos</th>
                                </tr>
                            </thead>
                            <tbody>
                                {contacts.map(contact => (
                                    <tr 
                                        key={contact.id} 
                                        className={`border cursor-pointer hover:bg-gray-100`}
                                        onClick={() => onSelectContact(contact)} // Solo seleccionamos y pasamos al App.jsx
                                    >
                                        <td className="border p-2">{contact.nombre}</td>
                                        <td className="border p-2">{contact.apellidos}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            )}
        </div>
    );
};

export default ContactList;
