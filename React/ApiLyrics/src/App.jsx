import { useState } from "react";
import SearchForm from "./SearchForm";
import Table from "./tabla";
import "./styles.css";

// Función para construir la URL de la API
const buildUrl = (band, song) => `https://api.lyrics.ovh/v1/${band}/${song}`;

// Función para manejar duplicados en la lista de letras
const duplicados = (prev, band, song, lyrics) => {
  const exists = prev.find((item) => item.band === band && item.song === song);
  console.log(prev);
  return exists ? prev : [...prev, { band, song, lyrics }];

};

// Función para manejar errores
const Error = (error) => {
  alert("Ocurrió un error al buscar la letra.");
  console.error("Error en la solicitud:", error);
};

function App() {
  const [listaLetra, setlistaLetra] = useState([]);

  const fetchLyrics = (band, song) => {
    if (!band || !song) {
      alert("Por favor, ingresa tanto el artista como la canción.");
      return;
    }

    const url = buildUrl(band, song);

    fetch(url)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Error al obtener la letra: ${response.statusText}`);
        }
        return response.json();
      })
      .then((data) => {
        if (data.lyrics) {
          setlistaLetra((prev) => duplicados(prev, band, song, data.lyrics));
          console.log(listaLetra);
        } else {
          alert("No se encontraron letras para esta canción.");
        }
      })
      .catch(Error);
  };

  return (
    <div className="App">
      <h1>Busca tu letra de canción favorita</h1>
      <SearchForm buscar={fetchLyrics} />
      <Table listaLetra={listaLetra} />
    </div>
  );
}

export default App;
