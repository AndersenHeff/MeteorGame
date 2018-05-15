import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObjects
{
	private Direction dir;
	private JumpDirection jumpDir;
	private int countTime;
	public Player(int x, int y, Color c, int gWidth, int gHeight)
	{
		super(gWidth, gHeight);
		this.x = x;
		this.y = y;
		this.c = c;
		width = 20;
		height = 20;
		speed = 2;
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
		
		
		else if(jumpDir == JumpDirection.JUMP)
		{
			//Make this a graph that increases, then decreases to infinity unless altered
			y -= 8 - (countTime/20);
		}
	}


	public void setDir(Direction dir) 
	{
		this.dir = dir;
	}
	
	public void setJumpDir(JumpDirection jumpDir) 
	{
		this.jumpDir = jumpDir;
	}
	
	
	public void setCountTime(int countTime)
	{
		this.countTime= countTime;
	}
	
	public int getCountTime()
	{
		return countTime;
	}
	
}
