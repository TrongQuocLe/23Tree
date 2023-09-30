import { useState } from "react"; 
import ReactCardFlip from "react-card-flip";

const FlipCard = (props) => {
    const [isFlipped, setIsFlipped] = useState(false);

    const handleCardClick = () => {
      setIsFlipped(!isFlipped);
    };

  return (
    <ReactCardFlip isFlipped={isFlipped} flipDirection="horizontal">
    <div className="front" onClick={handleCardClick}>
      <h2>{props.question}</h2>
    </div>
    <div className="back" onClick={handleCardClick}>
      <h2>{props.answer}</h2>
    </div>
  </ReactCardFlip>
  );
}

export default FlipCard;