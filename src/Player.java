import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObjects implements Intersection
{
	private Direction dir;
	private JumpDirection jumpDir;
	private double a;
	public Player(int x, int y, Color c, int gWidth, int gHeight)
	{
		super(gWidth, gHeight);
		this.x = x;
		this.y = y;
		this.c = c;
		width = 20;
		height = 20;
		speed = 4;
		a = -2;
	}
	
	
	public void draw(Graphics g)
	{
		g.setColor(c);
		g.fillRect(x, y, width, height);
		move();
	}

	public void move()
	{
		if(dir == Direction.LEFT)
		{
			x -= speed;
		}
		else if(dir == Direction.RIGHT)
		{
			x += speed;
		}
		if(x + width > gWidth)
		{
			x = gWidth - height;
		}
		else if(x < 0)
		{
			x = 0;
		}
		
		
		if(jumpDir == JumpDirection.JUMP)
		{
			//Make this a graph that increases, then decreases to infinity unless altered
			jumpArc();
		}
		else if(jumpDir == JumpDirection.NONE)
		{
			
		}
		
	}
	
	public void jumpArc()
	{
		y -= 9 + a;
	}
	public void setDir(Direction dir) 
	{
		this.dir = dir;
	}
	
	public void setJumpDir(JumpDirection jumpDir) 
	{
		this.jumpDir = jumpDir;
	}
	
	public void setA(double a)
	{
		this.a = a;
	}
	
	public double getA()
	{
		return a;
	}
	
}
