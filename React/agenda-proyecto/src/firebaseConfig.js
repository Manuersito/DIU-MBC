import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";

// Configuración de Firebase
const firebaseConfig = {
  apiKey: "AIzaSyDnSuXdlCpB2VqfxTYvkjtixjNqSqfePlg",
  authDomain: "agendareactmbc.firebaseapp.com",
  projectId: "agendareactmbc",
  storageBucket: "agendareactmbc.appspot.com", // Corregido
  messagingSenderId: "274572367467",
  appId: "1:274572367467:web:f848c1ca8dd5f51bc0c32e"
};

// Inicializa Firebase
const app = initializeApp(firebaseConfig);
export const auth = getAuth(app); // Exporta autenticación
export const db = getFirestore(app); // Exporta Firestore
export default app;
