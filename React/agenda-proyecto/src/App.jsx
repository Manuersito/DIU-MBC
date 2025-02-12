import { useState } from 'react'
import ContactList from './components/ContactList.jsx';
import ContactDetails from './components/ContactDetails.jsx';

export default function App() {
  const [selectedContact, setSelectedContact] = useState(null);

  return (
    <>
      <ContactList onSelectContact={setSelectedContact} />
      {selectedContact && <ContactDetails contact={selectedContact} />}
    </>
  );
}
