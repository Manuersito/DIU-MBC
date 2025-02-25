import React, { useContext } from "react";
import { Container, Nav, Navbar, Button, Image } from 'react-bootstrap';
import { UserContext } from '../providers/UserProvider';
import { auth } from '../firebase';
import { useNavigate } from 'react-router-dom';
import { deleteContact } from "./apiService";
import '../styles.css'


export default function NavBarComponent({ selectedContact, refreshContacts }) {
    const { user } = useContext(UserContext);
    const navigate = useNavigate();

    const handleLogout = async () => {
        try {
            await auth.signOut();
            navigate('/login');
        } catch (error) {
            console.error('Error al cerrar sesión:', error);
        }
    };

    const handleDeleteContact = async () => {
        if (!selectedContact) {
            alert('Por favor, selecciona un contacto para eliminar');
            return;
        }

        if (window.confirm('¿Estás seguro de que quieres eliminar este contacto?')) {
            try {
                await deleteContact(selectedContact.id);
                refreshContacts(); // Actualizar la lista de contactos
            } catch (error) {
                console.error('Error al eliminar el contacto:', error);
                alert('Error al eliminar el contacto');
            }
        }
    };

    return (
        <Navbar bg="dark" variant="dark" className="mb-4">
            <Container>
                <Navbar.Brand href="/">Agenda</Navbar.Brand>
                <Navbar.Toggle aria-controls="navbar-nav" />
                <Navbar.Collapse id="navbar-nav">
                    {user && (
                        <Nav className="me-auto">
                            <Button 
                                variant="outline-light" 
                                size="sm" 
                                className="me-2"
                                onClick={() => navigate('/contact-gestion', { state: { mode: 'add' } })}
                            >
                                Añadir
                            </Button>
                            <Button 
                                variant="outline-light" 
                                size="sm"
                                className="me-2"
                                onClick={() => {
                                    if (!selectedContact) {
                                        alert('Por favor, selecciona un contacto para editar');
                                        return;
                                    }
                                    navigate('/contact-gestion', { state: { mode: 'edit', contact: selectedContact } });
                                }}
                            >
                                Editar
                            </Button>
                            <Button 
                                variant="outline-light" 
                                size="sm"
                                onClick={handleDeleteContact}
                            >
                                Eliminar
                            </Button>
                        </Nav>
                    )}
                    <Nav className="ms-auto">
                        {user ? (
                            <div className="d-flex align-items-center">
                                <Image
                                    src={user.photoURL || "https://muhimu.es/wp-content/uploads/2017/04/FRENTE-NITIDA.jpg"}
                                    roundedCircle
                                    width={30}
                                    height={30}
                                    className="me-2"
                                />
                                <span className="text-white me-3">{user.displayName || user.email}</span>
                                <Button variant="outline-light" size="sm" onClick={handleLogout}>
                                    Cerrar Sesión
                                </Button>
                            </div>
                        ) : (
                            <Button variant="outline-light" size="sm" onClick={() => navigate('/login')}>
                                Iniciar Sesión
                            </Button>
                        )}
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}