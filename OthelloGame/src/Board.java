import java.util.ArrayList;
import java.util.Random;

public class Board {
	
	private long playerBoard;
	private long opponentBoard;
	private long fullBoard;
	private long[] maskArr;
	
	private static final long WESTMASK = 0xFEFEFEFEFEFEFEFEL; //Mask to prevent wrap around in west direction
	private static final long EASTMASK = 0x7F7F7F7F7F7F7F7FL; //Mask to prevent wrap around in east direction
	
	public Board(String player) 
	{
		//bottom right of board is LSB and the top left is the MSB
		if(player.equals("Black")) 
		{
			this.playerBoard = 0x0000000810000000L;
			this.opponentBoard = 0x0000001008000000L;
		}
		else {
			this.playerBoard = 0x0000001008000000L;
			this.opponentBoard = 0x0000000810000000L;
		}
		this.fullBoard = playerBoard & opponentBoard;
		
		//Build array to hold masks to check empty spaces for moves
		this.maskArr = new long[64];
		long mask = 0x0000000000000001L;
		for (int i=0; i<maskArr.length; i++)
		{
			maskArr[i] = mask;
			mask = mask<<1;
		}
	}
	
	public ArrayList<Move> generateMoves()
	{
		ArrayList<Move> moveList = new ArrayList<Move>();
		
		long[] directions = new long[8];
		directions[0] = fullBoard << 8; //North
		directions[1] = fullBoard << 1; //West
		directions[2] = fullBoard >>> 8; //South
		directions[3] = fullBoard >>> 1; //East
		directions[4] = (fullBoard&WESTMASK) << 9; //NorthWest
		directions[5] = (fullBoard&WESTMASK) >>> 7; //SouthWest
		directions[6] = (fullBoard&EASTMASK) >>> 9; //SouthEast
		directions[7] = (fullBoard&EASTMASK) << 7; //NorthEast
		
		for(int i=0; i<maskArr.length; i++)
		{
			for(int j=0; j<directions.length; j++)
			{
				long flipped = 0;
				long position = directions[j] & maskArr[i];
				long current = position;
				if((current & opponentBoard)!=0) 
				{
					flipped = flipped | current;
					while((current & opponentBoard)!=0)
					{
						//North
						if(j==0)
						{
							current = current<<8;	
						}
						//West
						else if(j==1)
						{
							current = current<<1;	
						}
						//South
						else if(j==2)
						{
							current = current>>>8;	
						}
						//East
						else if(j==3)
						{
							current = current>>>1;	
						}
						//NorthWest
						else if(j==4)
						{
							current = current<<9;	
						}
						//SouthWest
						else if(j==5)
						{
							current = current>>>7;	
						}
						//SouthEast
						else if(j==6)
						{
							current = current>>>9;	
						}
						//NorthEast
						else if(j==7)
						{
							current = current<<7;	
						}
						
						
						if((current & playerBoard)!=0)
						{
							moveList.add(new Move(position, i, flipped));
						}
						flipped = flipped | position;
					}
				}
			}
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
	
	public Move chooseMove(ArrayList<Move> moves)
	{
		Random rand = new Random();
		int selection = rand.nextInt(moves.size());
		return moves.get(selection);
	}
	
	public Move getOpponentMove(String str)
	{
		long flipped = 0;
		int col = 0;
		if(str.charAt(2) == 'a')
		{
			col = 1;
		}
		else if(str.charAt(2) == 'b')
		{
			col = 2;
		}
		else if(str.charAt(2) == 'c')
		{
			col = 3;
		}
		else if(str.charAt(2) == 'd')
		{
			col = 4;
		}
		else if(str.charAt(2) == 'e')
		{
			col = 5;
		}
		else if(str.charAt(2)=='f')
		{
			col = 6;
		}
		else if(str.charAt(2)=='g')
		{
			col = 7;;
		}
		else if(str.charAt(2)=='h')
		{
			col = 8;
		}
		
		int row = Character.getNumericValue(str.charAt(4));
		int position = row + ((col-1)*8);
		position--;
		long pos = maskArr[position];
		
		for(int j=0; j<8; j++)
		{
			long current = pos;
			if((current & playerBoard)!=0) 
			{
				while((current & playerBoard)!=0)
				{
					//North
					if(j==0)
					{
						current = current<<8;	
					}
					//West
					else if(j==1)
					{
						current = current<<1;	
					}
					//South
					else if(j==2)
					{
						current = current>>>8;	
					}
					//East
					else if(j==3)
					{
						current = current>>>1;	
					}
					//NorthWest
					else if(j==4)
					{
						current = current<<9;	
					}
					//SouthWest
					else if(j==5)
					{
						current = current>>>7;	
					}
					//SouthEast
					else if(j==6)
					{
						current = current>>>9;	
					}
					//NorthEast
					else if(j==7)
					{
						current = current<<7;	
					}
					
					
					if((current & opponentBoard)==0)
					{
						flipped = flipped | position;
					}
				}
			}
		}
		
		Move move = new Move(pos,position,flipped);
		return move;
	}
	
	public boolean gameOver()
	{
		return ((playerBoard | opponentBoard) == -1L) || (playerBoard == 0 || opponentBoard == 0);
	}

	public void printBoard(String color) 
	{
		int index = 1;
		StringBuilder sb = new StringBuilder();
		sb.append("  a b c d e f g h \n");
		sb.append(index+"|");
		for(int i=63; i>=0; i--) 
		{
			if(color.equals("Black"))
			{
				if(((playerBoard >> i) & 1) > 0)
				{
					sb.append("B ");
				}
				else if(((opponentBoard >> i) & 1) > 0)
				{
					sb.append("W ");
				}
				else
				{
					sb.append("- ");
				}
			}
			else
			{
				if(((playerBoard >> i) & 1) > 0)
				{
					sb.append("W ");
				}
				else if(((opponentBoard >> i) & 1) > 0)
				{
					sb.append("B ");
				}
				else
				{
					sb.append("- ");
				}
			}
			
			if(i%8 == 0)
			{
				sb.append("\n"+index+"|");
				index++;
			}
		}
		
		System.out.println(sb);
	}
}
