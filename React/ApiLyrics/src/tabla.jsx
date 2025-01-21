import React from "react";

function Table({ lyricsList }) {
  return (
    <table>
      <thead>
        <tr>
          <th>Artista</th>
          <th>Canción</th>
          <th>Letra</th>
        </tr>
      </thead>
      <tbody>
        {lyricsList.map((item, index) => (
          <tr key={index}>
            <td>{item.band}</td>  {/* Mostramos el nombre del artista */}
            <td>{item.song}</td>  {/* Mostramos el nombre de la canción */}
            <td>
              {item.lyrics}  {/* Mostramos la letra */}
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default Table;
