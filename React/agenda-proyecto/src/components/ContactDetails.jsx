import { useEffect, useState } from "react";
import { getTutorialsByIds } from "./apiService";
import { Card, Carousel, Row, Col } from "react-bootstrap";
import '../styles.css'

const ContactDetails = ({ contact }) => {
    const [tutorials, setTutorials] = useState([]);
    const [error, setError] = useState(null);
    const [activeIndex, setActiveIndex] = useState(0);

    useEffect(() => {
        if (contact.tutoriales?.length) {
            getTutorialsByIds(contact.tutoriales)
                .then((data) => {
                    setTutorials(data);
                    setActiveIndex(0);
                })
                .catch((error) => setError(error.message));
        }
    }, [contact]);

    const handleSelect = (selectedIndex) => {
        setActiveIndex(selectedIndex);
    };

    return (
        <div>
            {/* Contenedor principal con Row para alinear los elementos */}
            <Row>
                {/* Detalles del contacto */}
                <Col md={6} style={{ marginBottom: '20px' }}>
                    <Card style={{ width: '100%', minHeight: '450px'}}>
                        <Card.Body>
                            <Card.Title className="mb-3">Detalles del Contacto</Card.Title>
                            <ul>
                                <li><strong>Nombre:</strong> {contact.nombre}</li>
                                <li><strong>Apellidos:</strong> {contact.apellidos}</li>
                                <li><strong>Calle:</strong> {contact.calle}</li>
                                <li><strong>Código Postal:</strong> {contact.codigoPostal}</li>
                                <li><strong>Ciudad:</strong> {contact.ciudad}</li>
                                <li><strong>Fecha de Nacimiento:</strong> {contact.fechaNacimiento}</li>
                            </ul>
                        </Card.Body>
                    </Card>
                </Col>

                {/* Detalles de los tutoriales */}
                <Col md={6}>
                    <Card style={{ width: '95%', minHeight: '450px' }}>
                        <Card.Body>
                            <Card.Title className="mb-3">Detalles de los Tutoriales</Card.Title>
                            {error ? (
                                <p className="text-danger">{error}</p>
                            ) : (
                                <Carousel 
                                    activeIndex={activeIndex} 
                                    onSelect={handleSelect} 
                                    interval={null} 
                                    style={{ minHeight: '300px' }}
                                    slide={false}
                                >
                                    {tutorials.map((tutorial) => (
                                        <Carousel.Item key={tutorial.id}>
                                        <Card>
                                            <Card.Body className="text-center"> 
                                                <Card.Title className="mb-3">{tutorial.title}</Card.Title>
                                                <Card.Text className="mb-3">{tutorial.description}</Card.Text>
                                                <div className="d-flex justify-content-center">
                                                    <img 
                                                        src={tutorial.url} 
                                                        alt={tutorial.title} 
                                                        className="img-fluid mt-2" 
                                                        style={{ maxHeight: '200px', objectFit: 'cover' }} 
                                                    />
                                                </div>
                                            </Card.Body>
                                        </Card>
                                    </Carousel.Item>
                                    
                                    ))}
                                </Carousel>
                            )}
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </div>
    );
};

export default ContactDetails;
