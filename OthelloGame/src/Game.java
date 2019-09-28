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
		
		if(myColor.equals("Black"))
		{
			currentPlayer = ME;
		}
		else
		{
			currentPlayer = OPPONENT;
		}
		
		Move move;
		while(!gameBoard.gameOver())
		{
			if(currentPlayer == ME)
			{
				move = gameBoard.chooseMove(gameBoard.generateMoves());
				System.out.print(myColor.charAt(0)+" "+move.toString());
			}
			else
			{
				String str = scan.next();
				move = gameBoard.getOpponentMove(str);
			}
			gameBoard.applyMove(currentPlayer, move);
			gameBoard.printBoard(myColor);
			currentPlayer = -1*currentPlayer;
		}
	}

}
