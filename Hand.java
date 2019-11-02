
/**
 * An object of type Hand represents a hand of cards.  The
 * cards belong to the class Card.  A hand is empty when it
 * is created, and any number of cards can be added to it.
 */

//import java.util.ArrayList;

public class Hand {

	private Card[] hand;   // The cards in the hand.
	private int count; 
     
	public Hand() {
		/**
		 * Create a hand that is initially empty.
		 */
		hand = new Card[5];
		count = 0;		
	}
   
   	public void clear() {
		/**
		* Remove all cards from the hand, leaving it empty.
		*/
		for(int i=0 ; i<hand.length; i++){ hand[i] = null; }
		count = 0;
   	}
   
	public void addCard(Card c) {
		/**
	    * Add a card to the hand.  It is added at the end of the current hand.
	    * @param c the non-null card to be added.
	    * @throws NullPointerException if the parameter c is null.
	    */
		for(int i=0 ; i<hand.length; i++){ 
			if (hand[i] == null){
				hand[i] = c;
				count = count + 1;
				break;
			}
		}
	}
   
	public void removeCard(Card c) {
		/**
		 * Remove a card from the hand, if present.
		 * @param c the card to be removed.  If c is null or if the card is not in 
		 * the hand, then nothing is done.
		 */
		for(int i=0 ; i<hand.length; i++){ 
			if (hand[i].equals(c)){
				hand[i] = null;
				count = count-1;
			}
		}
	}
      
	public void removeCard(int position) {
		/**
		 * Remove the card in a specified position from the hand.
		 * @param position the position of the card that is to be removed, where
		 * positions are starting from zero.
		 * @throws IllegalArgumentException if the position does not exist in
		 * the hand, that is if the position is less than 0 or greater than
		 * or equal to the number of cards in the hand.
		 */
		if (position < 0 || position >= hand.length)
			throw new IllegalArgumentException("Position does not exist in hand: " + position);
		hand[position] = null;
		count = count - 1;
	}	

	public int getCardCount() {
		/*
		 * Returns the number of cards in the hand.
		 */
		return count;
	}

	public Card getCard(int position) {
		/**
		 * Gets the card in a specified position in the hand.  (Note that this card
		 * is not removed from the hand!)
		 * @param position the position of the card that is to be returned
		 * @throws IllegalArgumentException if position does not exist in the hand
		 */
		if (position < 0 || position >= hand.length)
			throw new IllegalArgumentException("Position does not exist in hand: "
					+ position);
		return hand[position];
	}
   
	public void sortBySuit() {
		/**
		 * Sorts the cards in the hand so that cards of the same suit are
		 * grouped together, and within a suit the cards are sorted by value.
		 * Note that aces are considered to have the lowest value, 1.
		 */		
		int size = count;
		int nonnull = 0;
		int index = 0;
	  
		Card[] newHand = new Card[5];
		while (size > 0) {
			if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
			int pos = nonnull;  // Position of minimal card.
			Card c = hand[nonnull];  // Minimal card.
		 
			for (int i = nonnull+1; i < hand.length; i++) {
				Card c1 = hand[i];
				if (c1 != null){
					if ( c1.getSuit() < c.getSuit() ||
							(c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
						pos = i;
						c = c1;
					}
				}
			}
			hand[pos] = null;
			size = size - 1;
			newHand[index++] = c;
			nonnull = 0;
		}
		hand = newHand;
	}
    
	public void sortByValue() {
		/**
		 * Sorts the cards in the hand so that cards of the same value are
		 * grouped together.  Cards with the same value are sorted by suit.
		 * Note that aces are considered to have the lowest value, 1.
		 */
		int size = count;
		int nonnull = 0;
		int index = 0;
	  
		Card[] newHand = new Card[5];
		while (size > 0) {
			if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
			int pos = nonnull;  // Position of minimal card.
			Card c = hand[nonnull];  // Minimal card.
		 
			for (int i = nonnull+1; i < hand.length; i++) {
            
				Card c1 = hand[i];
				if (c1 != null){
					if ( c1.getValue() < c.getValue() ||
							(c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
						pos = i;
						c = c1;
					}
				}
			}
			hand[pos] = null;
			size = size - 1;
			newHand[index++] = c;
			nonnull = 0;
		}
		hand = newHand;
	}
   
	public void printHand(){
	   
		for(int i=0; i<hand.length; i++){
			if (hand[i] != null){
				System.out.println(hand[i]);
			}
		}
		System.out.println();
	}
   
/******************************** Implement your methods here ****************************************/

	public int numPairs() 
	{
		/*Returns the number of pairs this hand contains
		 */

		int numPairs = 0;
		int pos1,pos2,first,second;
		Hand newHand = new Hand();
		for (int i = 0; i < 5; i++)
		{
			newHand.addCard(this.hand[i]);
		}
		newHand.sortByValue();
		
		if(newHand.hasFourOfAKind()==true)
		{
			numPairs = 2;
			return numPairs;
		}
		else if(newHand.hasTriplet()==true) 
		{
			if(newHand.hand[2].getValue() == newHand.hand[1].getValue()) 
			{
				if(newHand.hand[3].getValue() == newHand.hand[4].getValue())
				{
					numPairs = 2;
					return numPairs;
				}
			}
			else if(newHand.hand[1].getValue() == newHand.hand[0].getValue())
			{
				numPairs = 2;
				return numPairs;
			}
			else
			{
				numPairs = 1;
				return numPairs;
			}
		}
		else 
		{
			for(int i = 0; i < 4; i++)
			{
				pos1 = i;
				first = newHand.hand[pos1].getValue();
				
				for(int j = i + 1; j < 5; j++)
				{
					pos2 = j;
					second = newHand.hand[pos2].getValue();
					if (first == second) {
						numPairs++;
					}
				}
			}
		} 	
		return numPairs;
	}

	public boolean hasTriplet() 
	{
		/*Returns true if this hand has 3 cards that are of the same value
		 */
		boolean hasTriplet = false;
		Hand newHand = new Hand();
		for (int i = 0; i < 5; i++)
		{
			newHand.addCard(this.hand[i]);
		}
		newHand.sortByValue();
		
		if(newHand.hasFourOfAKind()==true)
		{
			hasTriplet = true;
			return hasTriplet;
		}
		
		if(newHand.hand[2].getValue() == newHand.hand[0].getValue())
		{
			hasTriplet = true;
			return hasTriplet;
		}
		else if(newHand.hand[3].getValue() == newHand.hand[1].getValue())
		{
			hasTriplet = true;
			return hasTriplet;
		}
		else if(newHand.hand[4].getValue() == newHand.hand[2].getValue())
		{
			hasTriplet = true;
			return hasTriplet;
		}
			
		return hasTriplet;
	}

	public boolean hasFlush() 
	{
		/*Returns true if this hand has all cards that are of the same suit
		 */
		boolean hasFlush = false;
		Hand newHand = new Hand();
		for (int i = 0; i < 5; i++)
		{
			newHand.addCard(this.hand[i]);
		}
		newHand.sortBySuit();
		
		for (int i = 1; i < 5; i++)
		{
			if(newHand.hand[0].getSuit()==newHand.hand[i].getSuit())
			{
				hasFlush = true;
			}
			else 
			{
				hasFlush = false;
				return hasFlush;
			}
		}
		return hasFlush;
	}
	
	public boolean hasStraight() 
	{
		/*Returns true if this hand has 5 consecutive cards of any suit
		 */
		boolean hasStraight = false;
		Hand newHand = new Hand();
		for (int i = 0; i < 5; i++)
		{
			newHand.addCard(this.hand[i]);
		}
		newHand.sortByValue();
		
		if (newHand.hand[1].getValue() == newHand.hand[0].getValue() + 1)
		{
			if(newHand.hand[2].getValue() == newHand.hand[1].getValue() + 1)
			{
				if(newHand.hand[3].getValue() == newHand.hand[2].getValue() + 1)
				{
					if(newHand.hand[4].getValue() == newHand.hand[3].getValue() + 1)
					{
						hasStraight = true;
						return hasStraight;
					}
				}
			}
		}
		else if(newHand.hand[0].getValue() == 1)
		{
			if(newHand.hand[1].getValue() == 10)
			{
				if(newHand.hand[2].getValue() == 11)
				{
					if(newHand.hand[3].getValue() == 12)
					{
						if(newHand.hand[4].getValue() == 13)
						{
							hasStraight = true;
							return hasStraight;
						}

					}

				}
	
			}
		}
		return hasStraight;
	}
	
	public boolean hasFullHouse() 
	{
		/*Returns true if this hand has a triplet and a pair of different //values
		 */
		boolean hasFullHouse = false;
		Hand newHand = new Hand();
		for (int i = 0; i < 5; i++)
		{
			newHand.addCard(this.hand[i]);
		}
		newHand.sortByValue();
		if (newHand.hasFourOfAKind())
		{
			hasFullHouse = false;
			return hasFullHouse;
		}
		else if (newHand.hasTriplet() == true && newHand.numPairs() == 2) 
		{
			hasFullHouse = true;
		}
		return hasFullHouse;
	}
	
	public boolean hasFourOfAKind() 
	{
		/*Returns true if this hand has 4 cards that are of the same value
		 */
		boolean hasFourOfAKind = false;
		Hand newHand = new Hand();
		for (int i = 0; i < 5; i++)
		{
			newHand.addCard(this.hand[i]);
		}
		newHand.sortByValue();
		
		if(newHand.hand[0].getValue() == newHand.hand[3].getValue())
		{
			hasFourOfAKind = true;
			return hasFourOfAKind;
		}
		else if(newHand.hand[1].getValue() == newHand.hand[4].getValue())
		{
			hasFourOfAKind = true;
			return hasFourOfAKind;
		}
		
		return hasFourOfAKind;
	}
	
	public boolean hasStraightFlush() {
		/*Returns true if this hand has a straight and a flush
		 */
		Hand newHand = new Hand();
		for (int i = 0; i < 5; i++)
		{
			newHand.addCard(this.hand[i]);
		}
		
		boolean hasStraightFlush = (newHand.hasStraight()) && (newHand.hasFlush());
		
		return hasStraightFlush;
	}
	
	public Card highestValue() 
	{
		/*Returns the card with the highest value in the hand. When there is
		 * more than one highest value card, you may return any one of them
		 */
		Card highestValue;
		Hand newHand = new Hand();
		for (int i = 0; i < 5; i++)
		{
			newHand.addCard(this.hand[i]);
		}
		newHand.sortByValue();
		
		if (newHand.hand[0].getValue() == 1)
		{
			highestValue = newHand.hand[0];
			return highestValue;
		}
		highestValue = newHand.hand[4];
		
		return highestValue;
	}
	
	public Card highestDuplicate() 
	{
		/*Returns the highest valued Card of any pair or triplet found, -1 if 
		 * none. When values are equal, you may return either
		 */
		Card highestDuplicate = null;
		Hand newHand = new Hand();
		for (int i = 0; i < 5; i++)
		{
			newHand.addCard(this.hand[i]);
		}
		newHand.sortByValue();
		
		if (newHand.hand[0].getValue() == newHand.hand[1].getValue() &&
				newHand.hand[0].getValue() == 1)
		{
			highestDuplicate = newHand.hand[0];
			return highestDuplicate;
		}
		for(int i = 4; i >= 1; i--) 
		{
		
			if (newHand.hand[i].getValue() == newHand.hand[i-1].getValue())
			{
				highestDuplicate = newHand.hand[i];
				return highestDuplicate;
			}
		}
		return highestDuplicate;
	}
	
	public Card primaryValue()
	{
		Card valueCard = new Card();
		Hand newHand = new Hand();
		for (int i = 0; i < 5; i++)
		{
			newHand.addCard(this.hand[i]);
		}
		
		if (newHand.hasStraight())
		{
			newHand.sortByValue();
			if (newHand.hand[4].getValue() == 5)
			{
				valueCard = newHand.hand[4];
				return valueCard;
			}
			else
			{
				valueCard = newHand.highestValue();
				return valueCard;
			}
		}
		else if (newHand.hasTriplet())
		{
			newHand.sortByValue();
			valueCard = newHand.hand[2];
			return valueCard;
		}
		else if (newHand.hasFlush())
		{
			valueCard = newHand.highestValue();
			return valueCard;
		}
		else if (newHand.numPairs() > 0)
		{
			valueCard = newHand.highestDuplicate();
			return valueCard;
		}
		else
		{
			return null;
		}
	}
	
	public Card secondaryValue()
	{
		Card valueCard = new Card();
		Hand newHand = new Hand();
		for (int i = 0; i < 5; i++)
		{
			newHand.addCard(this.hand[i]);
		}
		
		if(newHand.hasFourOfAKind() || newHand.hasStraight() || newHand.hasFlush())
		{
			return null;
		}
		else if(newHand.hasFullHouse())
		{
			newHand.sortByValue();
			if (newHand.hand[2].getValue() == newHand.hand[0].getValue())
			{
				valueCard = newHand.hand[4];
				return valueCard;
			}
			else
			{
				valueCard = newHand.hand[0];
				return valueCard;
			}
		}
		else if(newHand.hasTriplet() && !(newHand.hasFullHouse()) )
		{
			return null;
		}
		else if(newHand.numPairs() == 2)
		{	
			newHand.sortByValue();
			if (newHand.hand[0].getValue() == newHand.hand[1].getValue() && newHand.hand[0].getValue() == 1)
			{
				valueCard = newHand.hand[3];
				return valueCard;
			}
			valueCard = newHand.hand[1];
			return valueCard;
		}
		else
		{
			return null;
		}
	}
	
	public Card kicker()
	{
		Card kicker = new Card();
		Hand newHand = new Hand();
		for (int i = 0; i < 5; i++)
		{
			newHand.addCard(this.hand[i]);
		}
		
		if (newHand.hasStraight())
		{
			return null;
		}
		else if(newHand.hasFourOfAKind())
		{
			newHand.sortByValue();
			if (newHand.hand[0].getValue() == newHand.hand[2].getValue())
			{
				kicker = newHand.hand[4];
				return kicker;
			}
			else
			{
				kicker = newHand.hand[0];
				return kicker;
			}
		}
		else if(newHand.hasFullHouse())
		{
			return null;
		}
		else if(newHand.hasFlush())
		{
			kicker = newHand.highestValue();
			return kicker;
		}
		else if(newHand.hasTriplet() && !(newHand.hasFourOfAKind()))
		{
			newHand.sortByValue();
			if (newHand.hand[2].getValue() == newHand.hand[0].getValue())
			{
				if (newHand.hand[3].getValue() == 1)
				{
					kicker = newHand.hand[3];
					return kicker;
				}
				else if (newHand.hand[4].getValue() == 1)
				{
					kicker = newHand.hand[4];
					return kicker;
				}	
				else if (newHand.hand[3].getValue() > newHand.hand[4].getValue())
				{
					kicker = newHand.hand[3];
					return kicker;
				}
				else
				{
					kicker = newHand.hand[4];
					return kicker;
				}
			}
			else if (newHand.hand[2].getValue() == newHand.hand[4].getValue())
			{
				if (newHand.hand[0].getValue() == 1)
				{
					kicker = newHand.hand[0];
					return kicker;
				}
				else if (newHand.hand[1].getValue() == 1)
				{
					kicker = newHand.hand[1];
					return kicker;
				}	
				else if (newHand.hand[0].getValue() > newHand.hand[1].getValue())
				{
					kicker = newHand.hand[0];
					return kicker;
				}
				else
				{
					kicker = newHand.hand[1];
					return kicker;
				}
			}
			else
			{
				if (newHand.hand[0].getValue() == 1)
				{
					kicker = newHand.hand[0];
					return kicker;
				}
				else if (newHand.hand[4].getValue() == 1)
				{
					kicker = newHand.hand[4];
					return kicker;
				}	
				else if (newHand.hand[0].getValue() > newHand.hand[4].getValue())
				{
					kicker = newHand.hand[0];
					return kicker;
				}
				else
				{
					kicker = newHand.hand[4];
					return kicker;
				}
			}
		}
		else if(newHand.numPairs() == 2 && !(newHand.hasFourOfAKind()))
		{
			newHand.sortByValue();
			if (newHand.hand[1].getValue() == newHand.hand[2].getValue())
			{
				kicker = newHand.hand[0];
				return kicker;
			}
			else if (newHand.hand[2].getValue() == newHand.hand[3].getValue())
			{
				kicker = newHand.hand[4];
				return kicker;
			}
			else 
			{
				kicker = newHand.hand[2];
				return kicker;
			}
		}
		else if(newHand.numPairs() == 1 && !(newHand.hasTriplet()))
		{
			newHand.sortByValue();
			if (newHand.highestDuplicate().getValue() != newHand.highestValue().getValue())
			{
				kicker = newHand.highestValue();
				return kicker;
			}
			else
			{
				for(int i = 4; i >=0; i--)
				{
					int pos = i;
					kicker = newHand.hand[pos];
					if (kicker.getValue() != newHand.highestDuplicate().getValue())
					{
						return kicker;
					}
				}
			}
		}

		kicker = newHand.highestValue();
		return kicker;
		
	}
	
	public int compareTo(Hand h) 
	{
		/*Returns -1 if the instance hand loses to the parameter hand, 0 if 
		 * the hands are equal, and +1 if the instance hand wins over the 
		 * parameter hand. Hint: you might want to use some of the methods above
		 */
		int isWinning = 0;
		int hand1Value = 0;
		int hand2Value = 0;
		
		Hand newHand1 = new Hand();
		for (int i = 0; i < 5; i++)
		{
			newHand1.addCard(this.hand[i]);
		}
		Hand newHand2 = new Hand();
		for (int i = 0; i < 5; i++)
		{
			newHand2.addCard(h.hand[i]);
		}
		newHand1.sortByValue();
		newHand2.sortByValue();
		
		//Straight-Flush
		if(newHand1.hasStraightFlush())
		{
			hand1Value += 100000000;
		}
		if(newHand2.hasStraightFlush())
		{
			hand2Value += 100000000;
		}
		
		//4 of a kind
		if(newHand1.hasFourOfAKind())
		{
			hand1Value += 10000000;
		}
		if(newHand2.hasFourOfAKind())
		{
			hand2Value += 10000000;
		}
		
		//Full house
		if(newHand1.hasFullHouse())
		{
			hand1Value += 1000000;
		}
		if(newHand2.hasFullHouse())
		{
			hand2Value += 1000000;
		}
		
		//Flush & Pairs
		if(newHand1.hasFlush())
		{
			hand1Value += 100000;
		}
		else
		{
			hand1Value += newHand1.numPairs() * 100;
		}
		if(newHand2.hasFlush())
		{
			hand2Value += 100000;
		}
		else
		{
			hand2Value += newHand2.numPairs() * 100;
		}
		
		//Straight
		if(newHand1.hasStraight())
		{
			hand1Value += 10000;
		}
		if(newHand2.hasStraight())
		{
			hand2Value += 10000;
		}
		
		//Triplet
		if(newHand1.hasTriplet())
		{
			hand1Value += 1000;
		}
		if(newHand2.hasTriplet())
		{
			hand2Value += 1000;
		}
		
		//Initial Compare
		if(hand1Value > hand2Value)
		{
			isWinning = 1;
			return isWinning;
		}
		else if(hand1Value < hand2Value)
		{
			isWinning = -1;
			return isWinning;
		}
		else
		{
			//Compare no junk
			if (newHand1.primaryValue() != null)
			{
				if (newHand1.primaryValue().getValue() == 1)
				{
					hand1Value += 14;
				}
				else
				{
					hand1Value += newHand1.primaryValue().getValue();		
				}
				
				if (newHand2.primaryValue().getValue() == 1)
				{
					hand2Value += 14;
				}
				else
				{
					hand2Value += newHand2.primaryValue().getValue();		
				}
				
				if(hand1Value > hand2Value)
				{
					isWinning = 1;
					return isWinning;
				}
				else if(hand1Value < hand2Value)
				{
					isWinning = -1;
					return isWinning;
				}
			}
			if(newHand1.secondaryValue() != null)
			{
				if (newHand1.secondaryValue().getValue() == 1)
				{
					hand1Value += 14;
				}
				else
				{
					hand1Value += newHand1.secondaryValue().getValue();		
				}
				
				if (newHand2.secondaryValue().getValue() == 1)
				{
					hand2Value += 14;
				}
				else
				{
					hand2Value += newHand2.secondaryValue().getValue();		
				}
				
				if(hand1Value > hand2Value)
				{
					isWinning = 1;
					return isWinning;
				}
				else if(hand1Value < hand2Value)
				{
					isWinning = -1;
					return isWinning;
				}
			}
			if(newHand1.kicker() != null)
			{
				if (newHand1.kicker().getValue() == 1)
				{
					hand1Value += 14;
				}
				else
				{
					hand1Value += newHand1.kicker().getValue();		
				}
				
				if (newHand2.kicker().getValue() == 1)
				{
					hand2Value += 14;
				}
				else
				{
					hand2Value += newHand2.kicker().getValue();		
				}
				
				if(hand1Value > hand2Value)
				{
					isWinning = 1;
					return isWinning;
				}
				else if(hand1Value < hand2Value)
				{
					isWinning = -1;
					return isWinning;
				}
			}
			for (int i = 4; i >= 0; i--)
			{
				if(newHand1.hand[i].getValue() > newHand2.hand[i].getValue())
				{
					isWinning = 1;
					return isWinning;
				}
				else if (newHand2.hand[i].getValue() > newHand1.hand[i].getValue())
				{
					isWinning = -1;
					return isWinning;
				}
			}
			isWinning = 0;
			return isWinning;		
		}
	}
	
}