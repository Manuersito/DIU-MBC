import { useState } from 'react'
import './App.css'

function App() {
  const [count, setCount] = useState(0) // Hook useState para manejar el estado

  const handleIncrement = () => setCount(count + 1)
  const handleDecrement = () => setCount(count - 1)
  const handleReset = () => setCount(0)

  return (
    <div className='container-counter'>
      <div className='counter'>
        <h1>{count}</h1>
      </div>
      <div className='buttons'>
        <button onClick={handleDecrement}>Decrementar</button>
        <button onClick={handleReset}>Resetear</button>
        <button className='incButton' onClick={handleIncrement}>
          Incrementar
        </button>
      </div>
    </div>
  )
}

export default App
