public class potato{

	public static void main(String[]args)
	{
		Hand hand1 = new Hand();
		hand1.addCard(new Card(2,1));
		hand1.addCard(new Card(3,2));
		hand1.addCard(new Card(4,3));
		hand1.addCard(new Card(5,1));
		hand1.addCard(new Card(6,2));
		
		Hand hand2 = new Hand();
		hand2.addCard(new Card(1,1));
		hand2.addCard(new Card(2,2));
		hand2.addCard(new Card(3,3));
		hand2.addCard(new Card(4,1));
		hand2.addCard(new Card(5,2));
		
		System.out.println(hand1.compareTo(hand2));
		
		
	}
	

}