import { useContext, useEffect } from "react";
import { ProgresoContext } from "../providers/ContextoProgressBar";
import ProductDataService from "../services/api.js"; // 🔥 Importar API

function ProgressBar() {
  const { progreso, setProgreso } = useContext(ProgresoContext);

  useEffect(() => {
    // 🔥 Cuando la barra se monta, asegurarse de que tiene el valor correcto
    ProductDataService.getAll().then(response => {
      setProgreso(response.data.length);
    });
  }, [setProgreso]); // 🔥 Solo se ejecuta cuando `setProgreso` cambia

  return (
    <div className="container mt-3">
      <div className="progress">
        <div
          className="progress-bar bg-warning progress-bar-striped progress-bar-animated bg-primary"
          role="progressbar"
          style={{ width: `${(progreso / 7) * 100}%`, height: "20px" }}
          aria-valuenow={progreso}
          aria-valuemin="0"
          aria-valuemax="7"
        >
          {progreso} / 7 productos
        </div>
      </div>
    </div>
  );
}

export default ProgressBar;
