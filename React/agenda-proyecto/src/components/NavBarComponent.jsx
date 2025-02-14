import React, { useContext } from "react";
import { Container, Nav, Navbar, Button, Image } from 'react-bootstrap';
import { UserContext } from '../providers/UserProvider'; // Usa el contexto de usuario
import { auth } from '../firebase'; // Usa la instancia correcta de Firebase Auth
import { useNavigate } from 'react-router-dom'; // Para redirigir al usuario
import { deleteContact } from "./apiService";

export default function NavBarComponent() {
  const { user } = useContext(UserContext); // Obtén el usuario actual del contexto
  const navigate = useNavigate(); // Para redirigir al usuario
  
  // Función para cerrar sesión
  const handleLogout = async () => {
    try {
      await auth.signOut(); // Cierra la sesión
      navigate('/login'); // Redirige al usuario a la página de login
    } catch (error) {
      console.error('Error al cerrar sesión:', error);
    }
  };

  const handleDeleteContact = async ({contact}) => {
    try {
      await deleteContact(selectedContact.id); // Elimina el contacto
      navigate('/login'); // Redirige al usuario a la página de login
    } catch (error) {
      console.error('Error al eliminar el contacto:', error);
    }
  }


  

  return (
    <Navbar bg="dark" variant="dark">
      <Container>
        <Navbar.Brand href="/">Agenda</Navbar.Brand>
        <Navbar.Toggle />
        <Navbar.Collapse>
          {user && (
            <Nav className="me-auto">
              <Nav.Link>
                <Button variant="outline-light" size="sm" onClick={() => navigate('/AddContact')}>
                  Añadir
                </Button>
              </Nav.Link>
              <Nav.Link>
                <Button variant="outline-light" size="sm" onClick={() => navigate('/EditContact')}>
                  Editar
                </Button>
              </Nav.Link>
              <Nav.Link>
                <Button variant="outline-light" size="sm" onClick={handleDeleteContact}>
                  Eliminar
                </Button>
              </Nav.Link>
            </Nav>
          )}
        </Navbar.Collapse>
        <Navbar.Collapse className="justify-content-end">
          <Navbar.Text>
            {user ? (
              <div style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
                <Image
                  src={user.photoURL || "https://muhimu.es/wp-content/uploads/2017/04/FRENTE-NITIDA.jpg"} 
                  roundedCircle
                  style={{ width: '30px', height: '30px' }}
                />
                <span>{user.displayName || user.email}</span>
                <Button variant="outline-light" size="sm" onClick={handleLogout}>
                  Cerrar Sesión
                </Button>
              </div>
            ) : (
              <Button variant="outline-light" size="sm" onClick={() => navigate('/login')}>
                Iniciar Sesión
              </Button>
            )}
          </Navbar.Text>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}