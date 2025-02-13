import React from "react";
import { Container, Nav, Navbar, Button, Image } from 'react-bootstrap';
import { useAuth } from '../AuthProvider'; // Importa el contexto de autenticación
import { getAuth, signOut } from 'firebase/auth'; // Importa Firebase Auth
import { useNavigate } from 'react-router-dom'; // Para redirigir al usuario

export default function NavBarComponent() {
  const { currentUser } = useAuth(); // Obtén el usuario actual del contexto
  const navigate = useNavigate(); // Para redirigir al usuario
  const auth = getAuth(); // Obtén la instancia de autenticación

  // Función para cerrar sesión
  const handleLogout = async () => {
    try {
      await signOut(auth); // Cierra la sesión
      navigate('/login'); // Redirige al usuario a la página de login
    } catch (error) {
      console.error('Error al cerrar sesión:', error);
    }
  };

  return (
    <Navbar bg="dark" variant="dark">
      <Container>
        <Navbar.Brand href="/">Agenda</Navbar.Brand>
        <Navbar.Toggle />
        <Navbar.Collapse>
          {/* Botones adicionales si el usuario está autenticado */}
          {currentUser && (
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
                <Button variant="outline-light" size="sm" onClick={() => navigate('/DeleteContact')}>
                  Eliminar
                </Button>
              </Nav.Link>
            </Nav>
          )}
        </Navbar.Collapse>
        <Navbar.Collapse className="justify-content-end">
          <Navbar.Text>
            {currentUser ? (
              // Si el usuario está autenticado
              <div style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
                <Image
                  src={currentUser.photoURL || "https://via.placeholder.com/150"} // Foto de perfil del usuario
                  roundedCircle
                  style={{ width: '30px', height: '30px' }}
                />
                <span>{currentUser.displayName || currentUser.email}</span>
                <Button variant="outline-light" size="sm" onClick={handleLogout}>
                  Cerrar Sesión
                </Button>
              </div>
            ) : (
              // Si el usuario no está autenticado
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
