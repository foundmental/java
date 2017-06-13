import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * ´ú±íµ°
 * @author Administrator
 *
 */
public class Egg {
	int row, col;
	int w = Yard.BLOCK_SIZE;
	int h = Yard.BLOCK_SIZE;
	private static Random r = new Random();
	//private Egg eg=new Egg();
	private Color color=null;
	public Egg(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public Egg() {
	this(r.nextInt(Yard.ROWS-2) + 2, r.nextInt(Yard.COLS));	
	this.rColor();
	}
	 
	public Color rColor()
	{
		//color=Color.BLACK;
		int a=r.nextInt(5);
		switch(a)
		{
		case 0:
			color=Color.BLUE;
			break;
			
		case 1:
			color=Color.cyan;
			break;
		case 2:
			color= Color.green;
			break;
		case 3:
			color=Color.red;
			break;
		case 4:
			color=Color.YELLOW;
			break;
		case 5:
			color=Color.WHITE;
			break;
		
		
		}
		return color;
		
	}
	
	
	
	public void reAppear() {
		this.row = r.nextInt(Yard.ROWS-2) + 2;
		this.col = r.nextInt(Yard.COLS);
		if(color == Color.GREEN) color = Color.RED;
		else color = Color.GREEN;
	}
	
	public Rectangle getRect() {
		return new Rectangle(Yard.BLOCK_SIZE * col, Yard.BLOCK_SIZE * row, w, h);
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(color);
		g.fillOval(Yard.BLOCK_SIZE * col, Yard.BLOCK_SIZE * row, w, h);
		g.setColor(c);
		
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
}
