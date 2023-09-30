import { useState } from 'react'
import './App.css'
import FlipCard from "./components/FlipCard.jsx"

const App = () => {

  const usCitizenshipTestCards = [
    {
      question: "What is the supreme law of the land?",
      answer: "The Constitution",
    },
    {
      question: "What does the Constitution do?",
      answer: "Sets up the government, defines the government, protects basic rights of Americans",
    },
    {
      question: "The idea of self-government is in the first three words of the Constitution. What are these words?",
      answer: "We the People",
    },
    {
      question: "What is an amendment?",
      answer: "A change (to the Constitution), an addition (to the Constitution)",
    },
    {
      question: "What are the first 10 amendments to the Constitution called?",
      answer: "The Bill of Rights",
    },
    {
      question: "What is one right or freedom from the First Amendment?",
      answer: "Speech, religion, assembly, press, petition the government",
    },
    {
      question: "How many amendments does the Constitution have?",
      answer: "27",
    },
    {
      question: "What did the Declaration of Independence do?",
      answer: "Announced our independence (from Great Britain), declared our independence (from Great Britain), said that the United States is free (from Great Britain)",
    },
    {
      question: "What are two rights in the Declaration of Independence?",
      answer: "Life, liberty, pursuit of happiness",
    },
    {
      question: "What is freedom of religion?",
      answer: "You can practice any religion or not practice a religion",
    },
    {
      question: "What is the economic system in the United States?",
      answer: "Capitalist economy, market economy",
    },
    {
      question: "What is the rule of law?",
      answer: "Everyone must follow the law, leaders must obey the law, government must obey the law, no one is above the law",
    },
    {
      question: "Name one branch or part of the government.",
      answer: "Congress, legislative, President, executive, the courts, judicial",
    },
    {
      question: "What stops one branch of government from becoming too powerful?",
      answer: "Checks and balances, separation of powers",
    },
    {
      question: "Who is in charge of the executive branch?",
      answer: "The President",
    },
  ];

  const [randomCard, setRandomCard] = useState(usCitizenshipTestCards[0]);

  const getRandomCard = () => {
    const randomIndex = Math.floor(Math.random() * usCitizenshipTestCards.length);
    setRandomCard(usCitizenshipTestCards[randomIndex]);
  };

  return (
    <div className='App'> 
      <div className='header'>
        <h1>The Civic Test Master!</h1>
        <h2>How good are you on the Civic Test? Test you knowledge here!</h2>
        <h3>Number of cards: {usCitizenshipTestCards.length}</h3>
      </div>
      <br></br>
      {randomCard && (
      <FlipCard question={randomCard.question} answer={randomCard.answer}/>
      )}
      <button className="nextCard" onClick={getRandomCard}>⭢</button>
    </div>
  )
}

export default App
