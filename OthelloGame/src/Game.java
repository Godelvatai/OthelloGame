/*
 * author: Dustin Craven
 * date: 9/27/2019
 * class: CSCI 312 Algorithms and Data Structures
 */
import java.util.Scanner;

public class Game 
{	
	public static int ME = 1;
	public static int OPPONENT = -1;
	
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		int currentPlayer;
		String myColor;
		
		String initialInput = scan.next();
		if(initialInput.equals("I B")) 
		{
			myColor = "Black";
		}
		else {
			myColor = "White";
		}
		
		Board gameBoard = new Board(myColor);
		gameBoard.generateMoves();
		
	}

}
