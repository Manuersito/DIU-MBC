import { useEffect, useState } from "react";
import { getContacts } from "./apiService";
import ContactDetails from "./ContactDetails";

const ContactList = ({ onSelectContact }) => {
    const [contacts, setContacts] = useState([]);
    const [error, setError] = useState(null);
    const [selectedContact, setSelectedContact] = useState(null);

    useEffect(() => {
        loadContacts();
    }, []);

    const loadContacts = async () => {
        try {
            const data = await getContacts();
            setContacts(data);
            setError(null);
        } catch (err) {
            setError(err.message);
        }
    };

    return (
        <div className="flex flex-col h-screen">
            <div className="p-4 flex-grow">
                <h2 className="text-xl font-bold mb-4">Lista de Contactos</h2>
                {error ? (
                    <p className="text-red-500">{error}</p>
                ) : (
                    <div className="flex gap-4">
                        <div className="w-1/3">
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
                                            className={`border cursor-pointer hover:bg-gray-100 ${
                                                selectedContact?.id === contact.id ? 'bg-blue-100' : ''
                                            }`}
                                            onClick={() => {
                                                setSelectedContact(contact);
                                                onSelectContact(contact);
                                            }}
                                        >
                                            <td className="border p-2">{contact.nombre}</td>
                                            <td className="border p-2">{contact.apellidos}</td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                        {selectedContact && (
                            <div className="w-2/3">
                                <ContactDetails contact={selectedContact} 
                                totalContacts={contacts.length} />

                            </div>
                        )}
                    </div>
                )}
            </div>
        </div>
    );
};

export default ContactList;