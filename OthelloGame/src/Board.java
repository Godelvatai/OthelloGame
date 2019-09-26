import java.util.ArrayList;

public class Board {
	
	long playerBoard;
	long opponentBoard;
	
	public Board(String player) 
	{
		//bottom right of board is LSB and the top left is the MSB
		if(player.equals("Black")) 
		{
			playerBoard = 0x0000000810000000L;
			opponentBoard = 0x0000001008000000L;
		}
		else {
			playerBoard = 0x0000001008000000L;
			opponentBoard = 0x0000000810000000L;
		}
	}
	
	public ArrayList<Move> generateMoves()
	{
		ArrayList<Move> moveList = new ArrayList<Move>();
		long[] maskArr = new long[64];
		long mask = 0x0000000000000001L;
		for (int i=0; i<maskArr.length; i++)
		{
			maskArr[i] = mask;
			mask = mask<<1;
		}
		return moveList;
	}
	
	public void applyMove(int player, Move move)
	{
		
	}
	
	public int evaluate()
	{
		return 0;
	}
	
	@Override
	public String toString() 
	{
		return"";
	}
}
