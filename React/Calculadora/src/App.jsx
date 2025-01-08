import './App.css';

function App() {
  return (
    <div className="App">
      <div className="calculator">
        {/* Pantalla de la calculadora */}
        <div className="display">0</div>

        {/* Botones de la calculadora */}
        <table className="calculator-grid">
          <tbody>
            <tr>
              <td><button>AC</button></td>
              <td><button>+/-</button></td>
              <td><button>%</button></td>
              <td className="operator"><button>÷</button></td>
            </tr>
            <tr>
              <td><button>7</button></td>
              <td><button>8</button></td>
              <td><button>9</button></td>
              <td className="operator"><button>×</button></td>
            </tr>
            <tr>
              <td><button>4</button></td>
              <td><button>5</button></td>
              <td><button>6</button></td>
              <td className="operator"><button>-</button></td>
            </tr>
            <tr>
              <td><button>1</button></td>
              <td><button>2</button></td>
              <td><button>3</button></td>
              <td className="operator"><button>+</button></td>
            </tr>
            <tr>
              <td colSpan="2"><button>0</button></td>
              <td><button>.</button></td>
              <td className="operator"><button>=</button></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default App;
