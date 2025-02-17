import { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ContactList from './components/ContactList.jsx';
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
                        <ContactList 
                            key={refreshKey} 
                            onSelectContact={setSelectedContact} 
                        />
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