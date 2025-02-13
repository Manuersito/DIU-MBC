import { useState } from 'react'
import ContactList from './components/ContactList.jsx';
import ContactDetails from './components/ContactDetails.jsx';
import NavBarComponent from './components/NavBarComponent.jsx'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/Login.jsx';
import 'bootstrap/dist/css/bootstrap.min.css';
import ContactGestion from './components/ContactGestion.jsx';


export default function App() {
  const [selectedContact, setSelectedContact] = useState(null);

  return (
    <Router>
      <NavBarComponent /> {/* La barra de navegación siempre visible */}
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<ContactList onSelectContact={setSelectedContact} />} />
        <Route path="/AddContact" element={<ContactDetails />} />
        <Route path="/EditContact" element={<ContactGestion />} />
        <Route path="/DeleteContact" element={<ContactGestion />} />
      </Routes>
      {selectedContact && <ContactDetails contact={selectedContact} />}
    </Router>
  );
}
