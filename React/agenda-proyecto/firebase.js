import firebase from "firebase/app";
import "firebase/auth";
import "firebase/firestore";

// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyDnSuXdlCpB2VqfxTYvkjtixjNqSqfePlg",
  authDomain: "agendareactmbc.firebaseapp.com",
  projectId: "agendareactmbc",
  storageBucket: "agendareactmbc.firebasestorage.app",
  messagingSenderId: "274572367467",
  appId: "1:274572367467:web:f848c1ca8dd5f51bc0c32e"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);