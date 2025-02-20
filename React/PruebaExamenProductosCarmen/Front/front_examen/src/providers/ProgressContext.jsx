import { createContext, useState } from 'react';
import PropTypes from 'prop-types';

// Contexto para manejar el progreso de la aplicación
export const ProgressContext = createContext();

// Proveedor de contexto que envuelve los componentes hijos
export function ProgressProvider({ children }) {
  const [progress, setProgress] = useState(0);

  return (
    <ProgressContext.Provider value={{ progress, setProgress }}>
      {children}
    </ProgressContext.Provider>
  );
}

// Validación de props para mayor robustez
ProgressProvider.propTypes = {
  children: PropTypes.node.isRequired,
};
