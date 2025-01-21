import { useState } from "react";
import SearchForm from "./SearchForm";
import Table from "./tabla";  // Importamos el componente Table
import "./App.css";

function App() {
  const [lyricsList, setLyricsList] = useState([]);  // Array para almacenar las letras

  // Función para buscar las letras
  const fetchLyrics = (band, song) => {
    if (!band || !song) {
      alert("Por favor, ingresa tanto el artista como la canción.");
      return;
    }

    const url = `https://api.lyrics.ovh/v1/${(band)}/${(song)}`;

    fetch(url)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Error al obtener la letra: ${response.status} - ${response.statusText}`);
        }
        return response.json();
      })
      .then((data) => {
        if (data.lyrics) {
          // Verificamos si la letra ya existe en la lista antes de agregarla
          const newLyrics = { band, song, lyrics: data.lyrics };
          setLyricsList((prevLyrics) => {
            // Comprobamos si esta letra ya está en el array (por canción y banda)
            if (!prevLyrics.some(item => item.band === band && item.song === song)) {
              return [...prevLyrics, newLyrics];  // Si no existe, agregamos la letra nueva
            }
            return prevLyrics;  // Si ya existe, no agregamos de nuevo
          });
        } else {
          alert("No se encontraron letras para esta canción.");
        }
      })
      .catch((error) => {
        alert("Ocurrió un error al buscar la letra.");
        console.error("Error en la solicitud:", error);
      });
  };

  return (
    <div className="App">
      <h1>Busca tu letra de cancion favorita</h1>
      <SearchForm onSearch={fetchLyrics} />
      
      {/* Componente Table para mostrar todas las letras */}
      <Table lyricsList={lyricsList} />
    </div>
  );
}

export default App;
