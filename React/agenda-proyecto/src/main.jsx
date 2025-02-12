import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import NavBarComponent from './components/NavBarComponent.jsx'

import 'bootstrap/dist/css/bootstrap.min.css';


createRoot(document.getElementById('root')).render(
  <StrictMode>
    <NavBarComponent />
    <App />
  </StrictMode>,
)
