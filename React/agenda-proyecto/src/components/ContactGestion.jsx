import React from "react";
import { Container, Nav, Navbar, Button, Image } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom'; // Para redirigir al usuario

export default function ContactGestion() {
    const[contacts, setContacts] = useState([]); // Estado para almacenar los contactos
    const[id ,setId] = useState("");
    const[nombre ,setNombre] = useState("");
    const[calle, setCalle]=useState("");
    const[codigoPostal, setCodigoPostal]=useState("");
    const[ciudad, setCiudad]=useState("");
    const[fechaNacimiento, setFechaNacimiento]=useState("");
    const[tutoriales, setTutoriales]=useState([]);
handleAddContact = () => {
    // Lógica para agregar un nuevo contacto
    


}

  return(

    <div>
        hola
    
    </div>

  );
}