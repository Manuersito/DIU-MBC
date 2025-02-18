import axios from "axios";

const API_CONTACTS = "http://localhost:8099/api/v1/agenda";
const API_TUTORIALS = "http://localhost:8098/api/v1/tutorials";

// Obtener todos los contactos
export const getContacts = async () => {
    try {
        const response = await axios.get(API_CONTACTS);
        return response.data;
    } catch (error) {
        throw new Error("Error al obtener los contactos");
    }
};

// Agregar un nuevo contacto
export const addContact = async (contact) => {
    try {
        const response = await axios.post(API_CONTACTS, contact);
        return response.data;
    } catch (error) {
        throw new Error("Error al agregar el contacto");
    }
};

// Editar un contacto existente
export const editContact = async (id, contact) => {
    try {
        const response = await axios.put(`${API_CONTACTS}/${id}`, contact);
        return response.data;
    } catch (error) {
        throw new Error("Error al editar el contacto");
    }
};

// Eliminar un contacto
export const deleteContact = async (id) => {
    try {
        const response = await axios.delete(`${API_CONTACTS}/${id}`);
        return response.data;
    } catch (error) {
        throw new Error("Error al eliminar el contacto");
    }
};

// Obtener un tutorial por ID
export const getTutorialById = async (id) => {
    try {
        const response = await axios.get(`${API_TUTORIALS}/${id}`);
        return response.data;
    } catch (error) {
        throw new Error(`Error al obtener el tutorial con ID ${id}`);
    }
};

// Obtener múltiples tutoriales
export const getTutorialsByIds = async (tutorialIds) => {
    try {
        const tutoriales = await Promise.all(tutorialIds.map(id => getTutorialById(id)));
        return tutoriales;
    } catch (error) {
        throw new Error("Error al obtener los tutoriales");
    }
};

// Obtener múltiples tutoriales
export const getTutorials = async () => {
    try {
        const response = await axios.get(API_TUTORIALS);
        return response.data;
    } catch (error) {
        throw new Error("Error al obtener los tutoriales");
    }
};