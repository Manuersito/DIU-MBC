import axios from 'axios';

const http = axios.create({
  baseURL: 'http://localhost:8080/api/v1',  // Configura la URL base aquí
  headers: {
    'Content-type': 'application/json'
  }
});

export default http;
