import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from 'react-router-dom';
import { Form, Button, Container, Alert } from 'react-bootstrap';
import { addContact, editContact, getTutorialsPublished } from "./apiService"; // Importar la función para obtener tutoriales
import '../styles.css'



export default function ContactGestion({ onSave }) {
    const location = useLocation();
    const navigate = useNavigate();
    const { mode, contact } = location.state || { mode: 'add' };

    const [formData, setFormData] = useState({
        nombre: '',
        apellidos: '',
        calle: '',
        codigoPostal: '',
        ciudad: '',
        fechaNacimiento: '',
        tutoriales: [] // Almacena los IDs de los tutoriales seleccionados
    });

    const [tutorialesDisponibles, setTutorialesDisponibles] = useState([]); // Lista de tutoriales desde la API
    const [error, setError] = useState('');
    const [isSubmitting, setIsSubmitting] = useState(false);

    useEffect(() => {
        // Cargar tutoriales desde la API
        const fetchTutoriales = async () => {
            try {
                const tutoriales = await getTutorialsPublished(); // Llamada a la API
                setTutorialesDisponibles(tutoriales);
            } catch (error) {
                setError("Error al cargar los tutoriales");
            }
        };

        fetchTutoriales();

        if (mode === 'edit' && contact) {
            setFormData(contact);
        }
    }, [mode, contact]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleTutorialChange = (e) => {
        const selectedOptions = Array.from(e.target.selectedOptions, option => option.value);
        setFormData(prev => ({
            ...prev,
            tutoriales: selectedOptions // Actualiza los tutoriales seleccionados
        }));
    };

    const validateForm = () => {
        if (!formData.nombre.trim()) return "El nombre es obligatorio";
        if (!formData.apellidos.trim()) return "Los apellidos son obligatorios";
        if (!formData.calle.trim()) return "La calle es obligatoria";
        if (!formData.codigoPostal) return "El código postal es obligatorio";
        if (!formData.ciudad.trim()) return "La ciudad es obligatoria";
        if (!formData.fechaNacimiento) return "La fecha de nacimiento es obligatoria";
        return null;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        const validationError = validateForm();
        if (validationError) {
            setError(validationError);
            return;
        }

        setIsSubmitting(true);

        try {
            if (mode === 'add') {
                await addContact(formData);
            } else {
                await editContact(formData.id, formData);
            }
            onSave(); // Actualizar la lista de contactos
            navigate('/');
        } catch (error) {
            setError(error.message);
            setIsSubmitting(false);
        }
    };

    const handleCancel = () => {
        if (window.confirm('¿Estás seguro de que quieres cancelar? Los cambios no guardados se perderán.')) {
            navigate('/');
        }
    };

    return (
        <Container className="mt-4 mb-4">
            <div className="d-flex justify-content-between align-items-center mb-4">
                <h2>{mode === 'add' ? 'Añadir Contacto' : 'Editar Contacto'}</h2>
                <Button variant="secondary" onClick={handleCancel}>
                    Volver
                </Button>
            </div>

            {error && <Alert variant="danger" className="mb-4">{error}</Alert>}

            <Form onSubmit={handleSubmit} className="bg-white p-4 rounded shadow-sm">
                <div className="row">
                    <div className="col-md-6">
                        <Form.Group className="mb-3">
                            <Form.Label>Nombre</Form.Label>
                            <Form.Control
                                type="text"
                                name="nombre"
                                value={formData.nombre}
                                onChange={handleInputChange}
                                required
                            />
                        </Form.Group>
                    </div>
                    <div className="col-md-6">
                        <Form.Group className="mb-3">
                            <Form.Label>Apellidos</Form.Label>
                            <Form.Control
                                type="text"
                                name="apellidos"
                                value={formData.apellidos}
                                onChange={handleInputChange}
                                required
                            />
                        </Form.Group>
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-6">
                        <Form.Group className="mb-3">
                            <Form.Label>Calle</Form.Label>
                            <Form.Control
                                type="text"
                                name="calle"
                                value={formData.calle}
                                onChange={handleInputChange}
                                required
                            />
                        </Form.Group>
                    </div>
                    <div className="col-md-3">
                        <Form.Group className="mb-3">
                            <Form.Label>Código Postal</Form.Label>
                            <Form.Control
                                type="number"
                                name="codigoPostal"
                                value={formData.codigoPostal}
                                onChange={handleInputChange}
                                required
                            />
                        </Form.Group>
                    </div>
                    <div className="col-md-3">
                        <Form.Group className="mb-3">
                            <Form.Label>Ciudad</Form.Label>
                            <Form.Control
                                type="text"
                                name="ciudad"
                                value={formData.ciudad}
                                onChange={handleInputChange}
                                required
                            />
                        </Form.Group>
                    </div>
                </div>

                <div className="row">
                    <div className="col-md-6">
                        <Form.Group className="mb-3">
                            <Form.Label>Fecha de Nacimiento</Form.Label>
                            <Form.Control
                                type="date"
                                name="fechaNacimiento"
                                value={formData.fechaNacimiento}
                                onChange={handleInputChange}
                                required
                            />
                        </Form.Group>
                    </div>
                </div>

                <Form.Group className="mb-4">
    <Form.Label>Tutoriales</Form.Label>
    <Form.Select 
        multiple 
        value={formData.tutoriales.map(String)} 
        onChange={handleTutorialChange}
        className="form-control"
    >
        {tutorialesDisponibles.length === 0 ? (
            <option disabled>No hay tutoriales disponibles</option>
        ) : (
            tutorialesDisponibles.map((tutorial) => (
                <option key={tutorial.id} value={String(tutorial.id)}>
                    {tutorial.title}
                </option>
            ))
        )}
    </Form.Select>
</Form.Group>


                <div className="d-flex gap-2 justify-content-end">
                    <Button variant="secondary" onClick={handleCancel}>
                        Cancelar
                    </Button>
                    <Button type="submit" variant="primary" disabled={isSubmitting}>
                        {isSubmitting ? 'Guardando...' : (mode === 'add' ? 'Añadir Contacto' : 'Guardar Cambios')}
                    </Button>
                </div>
            </Form>
        </Container>
    );
}
