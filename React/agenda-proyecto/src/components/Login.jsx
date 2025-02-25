import React, { useState } from 'react';
import { getAuth, signInWithEmailAndPassword } from 'firebase/auth';
import { useNavigate } from 'react-router-dom';
import { Card, Container, Button, Form } from 'react-bootstrap';
import '../styles.css'


const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const auth = getAuth();
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      await signInWithEmailAndPassword(auth, email, password);
      navigate('/'); // Redirige al usuario a la página principal después del login
    } catch (error) {
      setError(error.message);
    }
  };

  return (
    <Container className="d-flex justify-content-center align-items-center min-vh-100">
      <Card style={{ width: '100%', maxWidth: '400px' }}>
        <Card.Body>
          <h2 className="text-center mb-4">Iniciar Sesión</h2>
          {error && <p style={{ color: 'red', textAlign: 'center' }}>{error}</p>}
          <Form onSubmit={handleLogin}>
            <Form.Group controlId="email" className="mb-3">
              <Form.Label>Correo Electrónico</Form.Label>
              <Form.Control
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
                placeholder="Ingrese su correo"
              />
            </Form.Group>
            <Form.Group controlId="password" className="mb-3">
              <Form.Label>Contraseña</Form.Label>
              <Form.Control
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                placeholder="Ingrese su contraseña"
              />
            </Form.Group>
            <Button variant="primary" type="submit" block>
              Iniciar Sesión
            </Button>
          </Form>
        </Card.Body>
      </Card>
    </Container>
  );
};

export default Login;
