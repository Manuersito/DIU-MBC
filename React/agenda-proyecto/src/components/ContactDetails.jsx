import { useEffect, useState } from "react";
import { getTutorialsByIds } from "./apiService";
import { Row, Col, Card, Carousel } from "react-bootstrap";

const ContactDetails = ({ contact }) => {
    const [tutorials, setTutorials] = useState([]);
    const [error, setError] = useState(null);
    const [activeIndex, setActiveIndex] = useState(0); // Estado para controlar el índice activo

    useEffect(() => {
        if (contact.tutoriales?.length) {
            getTutorialsByIds(contact.tutoriales)
                .then((data) => {
                    setTutorials(data);
                    setActiveIndex(0); // Reinicia el carrusel al primer ítem
                })
                .catch((error) => setError(error.message));
        }
    }, [contact]);

    const handleSelect = (selectedIndex, e) => {
        setActiveIndex(selectedIndex);
    };


    

    return (
        <Row>
            {/* Columna para los detalles del contacto */}
            <Col md={6} className="d-flex justify-content-center">
                <Card style={{ width: '80%' }}> {/* Ajustamos el ancho de la tarjeta */}
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

            {/* Columna para los detalles de los tutoriales */}
            <Col md={6} className="d-flex justify-content-center">
                <Card style={{ width: '100%' }}> {/* Ajustamos el ancho de la tarjeta */}
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
                                    <Carousel.Item key={tutorial.id} >
                                        <Card>
                                            <Card.Body>
                                                <Card.Title className="mb-3">{tutorial.title}</Card.Title>
                                                <Card.Text className="mb-3">{tutorial.description}</Card.Text>
                                                <img 
                                                    src={tutorial.url} 
                                                    alt={tutorial.title} 
                                                    className="img-fluid mt-2 display-block mx-auto" 
                                                    style={{ maxHeight: '200px', objectFit: 'cover' }} // Controla el tamaño de las imágenes
                                                />
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
    );
};

export default ContactDetails;