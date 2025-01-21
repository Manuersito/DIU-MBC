import { useState } from "react";

function SearchForm({ onSearch }) {
  const [band, setBand] = useState("");
  const [song, setSong] = useState("");

  const handleSearch = () => {
    onSearch(band, song);  // Llamamos a la función pasada por prop con los valores
  };

  return (
    <div>
      <input
        type="text"
        placeholder="Artista"
        value={band}
        onChange={(e) => setBand(e.target.value)}
      />
      <input
        type="text"
        placeholder="Cancion"
        value={song}
        onChange={(e) => setSong(e.target.value)}
      />
      <button onClick={handleSearch}>Buscar</button>
    </div>
  );
}

export default SearchForm;
