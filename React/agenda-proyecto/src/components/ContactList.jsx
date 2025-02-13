import { useEffect, useState } from "react";
import ContactDetails from "./ContactDetails";
import { getContacts } from "./apiService";

const ContactList = () => {
    const [contacts, setContacts] = useState([]);
    const [error, setError] = useState(null);
    const [selectedContact, setSelectedContact] = useState(null);

    useEffect(() => {
        getContacts()
            .then(setContacts)
            .catch(error => setError(error.message));
    }, []);

    return (
        <div className="p-4">
            <h2 className="text-xl font-bold mb-4">Lista de Contactos</h2>
            {error ? (
                <p className="text-red-500">{error}</p>
            ) : (
                <div className="d-flex">
                    <div className="w-25 pr-3"> {/* Reducimos el ancho de la lista de contactos */}
                        <table className="w-100 border-collapse border border-gray-300 text-center">
                            <thead>
                                <tr className="bg-gray-200">
                                    <th className="border p-2">Nombre</th>
                                    <th className="border p-2">Apellidos</th>
                                </tr>
                            </thead>
                            <tbody>
                                {contacts.map(contact => (
                                    <tr key={contact.id} className="border cursor-pointer" onClick={() => setSelectedContact(contact)}>
                                        <td className="border p-2">{contact.nombre}</td>
                                        <td className="border p-2">{contact.apellidos}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>

                    {selectedContact && (
                        <div className="flex-grow-1 pl-3"> {/* Ajustamos el espacio a la izquierda */}
                            <ContactDetails contact={selectedContact} />
                        </div>
                    )}
                </div>
            )}
        </div>
    );
};

export default ContactList;