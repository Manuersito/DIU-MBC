import { useContext } from 'react';
import { ProgressContext } from './../providers/ProgressContext.jsx';
import './../styles/ProgressBar.css';

function ProgressBar() {
  const { progress } = useContext(ProgressContext);

  // Calcular el progreso basado en un máximo de 50 productos
  const progressPercentage = Math.min(progress, 50) * 2;  // Esto asegura que el máximo sea 100%

  return (
    <div className="progress mb-3">
      <div
        className="progress-bar progress-bar-striped progress-bar-animated bg-primary"
        role="progressbar"
        style={{ width: `${progressPercentage}%` }}
        aria-valuenow={progressPercentage}
        aria-valuemin="0"
        aria-valuemax="100"
      ></div>
    </div>
  );
}

export default ProgressBar;
