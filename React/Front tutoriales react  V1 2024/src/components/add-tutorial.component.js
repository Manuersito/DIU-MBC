import React from "react";
import { Component } from "react";
import TutorialDataService from "../services/tutorial.service";
import "bootstrap/dist/css/bootstrap.min.css";

export default class AddTutorial extends Component {

    constructor(props) {
        super(props);
        this.state = {
            id: "",
            title: "",
            description: "",
            published: false
        }
    }

changeInput = (event) => {
    const {name,type, checked, value} = event.target;
    this.setState({
        [name]:type === "checkbox"? checked: value
    })
}

addTutorial = async (event) => {
    event.preventDefault();
    const {title, description, published} = this.state;
    const data = {
        title,
        description,
        published
    };
    try {
        const response = await TutorialDataService.create(data);
        console.log("Tutorial creado:", response.data);

        this.setState({
            title: "",
            description: "",
            published: false
        });
    } catch (error) {
        console.error("Error al crear el tutorial:", error);
    }
};

    render() {
        return (
            <div>
                <h3>Add Tutorial</h3>
                <div>
                    <label>Titulo:</label>
                        <input type="text" name="title"  placeholder="title" value={this.state.title} 
                        onChange={this.changeInput}/>
                    <label>Descripcion:</label>
                        <input type="text" name="description" placeholder="description" value={this.state.description} 
                        onChange={this.changeInput}/>
                    <label>Publicado:</label>
                        <input type="checkbox" name="published" placeholder="published" checked={this.state.published} 
                        onChange={this.changeInput}/>
                        <button className="btn btn-success" onClick={this.addTutorial}>Subir</button>
                </div>
            </div>
            
        );
    }
    
}