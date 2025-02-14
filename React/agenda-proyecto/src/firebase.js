import { initializeApp } from "firebase/app";
import { 
  getAuth, 
  GoogleAuthProvider, 
  signInWithPopup, 
  signInWithEmailAndPassword 
} from "firebase/auth";
import { getFirestore, doc, getDoc, setDoc } from "firebase/firestore";

// Configuración de Firebase
const firebaseConfig = {
  apiKey: "AIzaSyDnSuXdlCpB2VqfxTYvkjtixjNqSqfePlg",
  authDomain: "agendareactmbc.firebaseapp.com",
  projectId: "agendareactmbc",
  storageBucket: "agendareactmbc.appspot.com",
  messagingSenderId: "274572367467",
  appId: "1:274572367467:web:f848c1ca8dd5f51bc0c32e"
};

// Inicializar Firebase
const app = initializeApp(firebaseConfig);

// Obtener la autenticación y la base de datos
const auth = getAuth(app);
const db = getFirestore(app);

// Proveedor de Google
const googleProvider = new GoogleAuthProvider();

// Función para iniciar sesión con Google
const signInWithGoogle = () => signInWithPopup(auth, googleProvider);

// Función para iniciar sesión con email y contraseña 
const signInWithEmail = (email, password) => 
  signInWithEmailAndPassword(auth, email, password);

// Función para crear documento del usuario
const generateUserDocument = async (userAuth, additionalData) => {
  if (!userAuth) return null;

  const userRef = doc(db, "users", userAuth.uid);
  const userSnap = await getDoc(userRef);

  // Si el documento no existe, lo creamos
  if (!userSnap.exists()) {
    const { email, displayName, photoURL } = userAuth;
    try {
      await setDoc(userRef, {
        displayName,
        email,
        photoURL,
        ...additionalData
      });
    } catch (error) {
      console.error("Error creando documento de usuario", error);
    }
  }

  return userSnap.data();
};

// Exportar funciones
export { auth, db, signInWithGoogle, signInWithEmail, generateUserDocument };