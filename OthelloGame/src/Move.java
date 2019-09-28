
public class Move {
	
	long move;
	int position;
	long flip;
	
	public Move(long move, int position, long flip)
	{
		this.move = move;
		this.position = position;
		this.flip = flip;
	}
	
	@Override
	public String toString()
	{
		String column = "";
		int x = position+1;
		int col = x%8;
		int row = position/8;
		if(col==1)
		{
			column="a";
		}
		else if(col==2)
		{
			column="b";
		}
		else if(col==3)
		{
			column="c";
		}
		else if(col==4)
		{
			column="d";
		}
		else if(col==5)
		{
			column="e";
		}
		else if(col==6)
		{
			column="f";
		}
		else if(col==7)
		{
			column="g";
		}
		else if(col==0)
		{
			column="h";
		}
		row++;
		String output = column+" "+row+"\n";
		return output;
	}
}
