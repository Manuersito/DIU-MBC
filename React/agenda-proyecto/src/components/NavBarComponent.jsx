import  React  from "react";

import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';

export default function NavBarComponent() {
    return(
      <Navbar className="bg-body-tertiary" bg="dark">
      <Container>
        <Navbar.Brand href="/">Agenda</Navbar.Brand>
        <Navbar.Toggle />
        <Navbar.Collapse className="justify-content-end">
          <Navbar.Text>
          <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#000000"><path d="M480-497q-81 0-137.5-56.5T286-691q0-81 56.5-137T480-884q81 0 137.5 56T674-691q0 81-56.5 137.5T480-497ZM126-109v-148q0-43.3 22.7-79.6 22.69-36.3 60.3-55.4 65-32 132.96-48.5Q409.92-457 480-457q72 0 140 16t131 48q37.61 18.96 60.3 54.98Q834-302 834-257.05V-109H126Z"/></svg>
            Signed in as: <a href="#login">Mark Otto</a>
          </Navbar.Text>
        </Navbar.Collapse>
      </Container>
    </Navbar>
    );
}