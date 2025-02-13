import React from "react";
import { Card } from "react-bootstrap";

export default function Login() {
  return(

    <Card>
        <Card.Body>
            <Card.Title>Card Title</Card.Title>
            <Card.Text>
            <div>
    <div >
        <h1>Iniciar Sesión</h1>
    </div>


    <form id="loginForm">
        <div>
            <label for="email">Email</label>
            <input type="email" name="email" id="" />
        </div>
        <div >
            <label for="password">Contraseña</label>
            <input type="password" name="contraseña" id="" />       
        </div>
        
        <button type="submit">
            Iniciar Sesión
            
        </button>
    </form>

    <div>
        ¿No tienes cuenta? <a href="">Regístrate</a>
    </div>
</div>

            </Card.Text>
            </Card.Body>
    </Card>
    



  );
}