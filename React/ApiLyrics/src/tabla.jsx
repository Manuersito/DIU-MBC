import React from "react";
import "./styles.css";

function Table({ listaLetra }) {
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
        {listaLetra.map((item, index) => (
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
