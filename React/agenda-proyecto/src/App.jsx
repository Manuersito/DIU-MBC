import { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ContactList from './components/ContactList.jsx';
import ContactDetails from './components/ContactDetails.jsx';
import NavBarComponent from './components/NavBarComponent.jsx';
import Login from './components/Login.jsx';
import ContactGestion from './components/ContactGestion.jsx';
import 'bootstrap/dist/css/bootstrap.min.css';

export default function App() {
    const [selectedContact, setSelectedContact] = useState(null);
    const [refreshKey, setRefreshKey] = useState(0);

    const handleRefresh = () => {
        setRefreshKey(prev => prev + 1); // Forzar actualización de la lista de contactos
    };

    return (
        <Router>
            <NavBarComponent 
                selectedContact={selectedContact} 
                refreshContacts={handleRefresh} 
            />
            <Routes>
                <Route path="/login" element={<Login />} />
                <Route 
                    path="/" 
                    element={
                        <div className="d-flex"> {/* Usamos flex para mostrar lado a lado */}
                            {/* Lista de contactos */}
                            <ContactList 
                                key={refreshKey} 
                                onSelectContact={setSelectedContact} // Seleccionamos el contacto desde aquí
                            />

                            {/* Detalles del contacto seleccionado */}
                            {selectedContact && (
                                <div style={{ marginLeft: '20px', marginTop: '100px', flex: 1 }}>
                                    <ContactDetails contact={selectedContact} />
                                </div>
                            )}
                        </div>
                    } 
                />
                <Route 
                    path="/contact-gestion" 
                    element={
                        <ContactGestion 
                            onSave={handleRefresh} 
                        />
                    } 
                />
            </Routes>
        </Router>
    );
}
