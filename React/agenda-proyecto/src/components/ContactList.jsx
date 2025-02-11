import { useEffect, useState } from "react";

const ContactList = () => {
    const [contacts, setContacts] = useState([]);
    const [error, setError] = useState(null);
    const [selectedContact, setSelectedContact] = useState(null);
    const [tutorials, setTutorials] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8099/api/v1/agenda")
            .then(response => {
                if (!response.ok) {
                    throw new Error("Error al obtener los contactos");
                }
                return response.json();
            })
            .then(data => setContacts(data))
            .catch(error => setError(error.message));
    }, []);

    const fetchTutorials = (tutorialIds) => {
        Promise.all(tutorialIds.map(id => 
            fetch(`http://localhost:8098/api/v1/tutorials/${id}`)
                .then(response => response.json())
        ))
        .then(data => setTutorials(data))
        .catch(error => setError(error.message));
    };

    const handleContactClick = (contact) => {
        setSelectedContact(contact);
        fetchTutorials(contact.tutoriales);
    };

    return (
        <div className="p-4">
            <h2 className="text-xl font-bold mb-4">Lista de Contactos</h2>
            {error ? (
                <p className="text-red-500">{error}</p>
            ) : (
                <div className="d-flex">
                    <div className="w-50 pr-4">
                        <table className="w-75 border-collapse border border-gray-300 text-center">
                            <thead>
                                <tr className="bg-gray-200">
                                    <th className="border p-2">Nombre</th>
                                    <th className="border p-2">Apellidos</th>
                                </tr>
                            </thead>
                            <tbody>
                                {contacts.map(contact => (
                                    <tr key={contact.id} className="border cursor-pointer" onClick={() => handleContactClick(contact)}>
                                        <td className="border p-2">{contact.nombre}</td>
                                        <td className="border p-2">{contact.apellidos}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>

                    {selectedContact && (
                        <div className="w-50 pl-4">

                            <h3 className="text-lg font-bold mb-4">Detalles del contacto</h3>
                            <ul>
                                    <li>
                                        Nombre : {selectedContact.nombre} <br />
                                        Apellidos : {selectedContact.apellidos} <br />
                                        Calle : {selectedContact.calle} <br />
                                        Codigo Postal : {selectedContact.codigoPostal} <br />
                                        Ciudad : {selectedContact.ciudad} <br />
                                        Fecha de Nacimiento : {selectedContact.fechaNacimiento} <br />
                                    </li>
                                
                            </ul>
                        </div>
                    )}


                        {selectedContact && (
                        <div className="w-50 pl-4">
                            <h3 className="text-lg font-bold mb-4">Detalles de los Tutoriales</h3>
                            <ul>
                                {tutorials.map(tutorial => (
                                    <li key={tutorial.id} className="p-2 border-b">
                                        id: {tutorial.id} <br />
                                        titulo: {tutorial.title} <br />
                                        descripcion: {tutorial.description}
                                    </li>
                                ))}
                            </ul>
                        </div>
                    )}
                </div>
            )}
        </div>
    );
};

export default ContactList;
