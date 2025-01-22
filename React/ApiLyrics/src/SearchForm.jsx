import { useState } from "react";
import "./styles.css";


function SearchForm({ buscar }) {
  const [artista, setArtista] = useState("");
  const [cancion, setCancion] = useState("");

  const botonBuscar = () => {
    buscar(artista, cancion);  // Llamamos a la función pasada por prop con los valores
  };

  return (
    <div className="search-form">
      <input
        type="text"
        placeholder="Artista"
        value={artista}
        onChange={(e) => setArtista(e.target.value)}
      />
      <input
        type="text"
        placeholder="Cancion"
        value={cancion}
        onChange={(e) => setCancion(e.target.value)}
      />
      <button onClick={botonBuscar}>Buscar</button>
    </div>
  );
}

export default SearchForm;
