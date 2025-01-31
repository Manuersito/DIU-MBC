import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom/cjs/react-router-dom.min";
import { useParams } from "react-router-dom";
import TutorialDataService from "../services/tutorial.service";
import "bootstrap/dist/css/bootstrap.min.css";

const Tutorial = () => {
    const { id } = useParams(); // Obtiene el ID desde la URL
    const [tutorial, setTutorial] = useState({
        title: "",
        description: "",
        published: false
    });

    useEffect(() => {
        if (id) {
            getTutorial(id);
        }
    }, [id]);

    const getTutorial = (id) => {
        TutorialDataService.get(id)
            .then(response => {
                setTutorial(response.data);
            })
            .catch(e => {
                console.log("Error al obtener el tutorial:", e);
            });
    };

    const changeInput = (event) => {
        const { name, type, checked, value } = event.target;
        setTutorial({
            ...tutorial,
            [name]: type === "checkbox" ? checked : value
        });
    };

    const saveTutorial = async (event) => {
        event.preventDefault();
        try {
            const response = id 
                ? await TutorialDataService.update(id, tutorial) // Actualizar si hay ID
                : await TutorialDataService.create(tutorial); // Crear si no hay ID
            
            console.log("Tutorial guardado:", response.data);
        } catch (error) {
            console.error("Error al guardar el tutorial:", error);
        }
    };

    return (
        <div>
            <h3>{"Editar Tutorial"}</h3>
            <div>
                <label>Título:</label>
                <input type="text" name="title" value={tutorial.title} onChange={changeInput} />

                <label>Descripción:</label>
                <input type="text" name="description" value={tutorial.description} onChange={changeInput} />

                <label>Publicado:</label>
                <input type="checkbox" name="published" checked={tutorial.published} onChange={changeInput} />

                <Link to="/">
                    <button className="btn btn-success" onClick={saveTutorial}>
                        Actualizar
                    </button>
                </Link>
            </div>
        </div>
    );
};

export default Tutorial;
