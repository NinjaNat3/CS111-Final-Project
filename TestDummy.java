//import java.util.ArrayList;
import java.util.Random;

public class TestDummy
{
	public static void main(String[] args) 
	{
		Player player1 = new Player(100);
		player1.getBalance();
		Random value = new Random();
		Random suit = new Random();

		for (int i = 0; i < 5; i++)
		{
			int randomValue = value.nextInt(13)+1;
			int randomSuit = suit.nextInt(4);
			Card random = new Card(randomValue,randomSuit);
			player1.deal(random);
		}
		player1.showHand().printHand();;
	}
	
}