import java.awt.Color;
import java.awt.Rectangle;

public class GameObjects 
{
	protected int x,y;
	protected int width,height;
	protected int gWidth, gHeight;
	protected Color c;
	protected int speed;
	public GameObjects(int gWidth, int gHeight)
	{
		this.gWidth = gWidth;
		this.gHeight = gHeight;
	}
	
	
	public Rectangle getRect() 
	{
		return new Rectangle(x, y, width, height);
	}
	public boolean intersects(Meteor meteor) 
	{
		return meteor.getRect().intersects(this.getRect());
	}
	
	
	
	public int getWidth() 
	{
		return width;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight() 
	{
		return height;
	}
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	public int getX() 
	{
		return x;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getY() 
	{
		return y;
	}
	public void setY(int y)
	{
		this.y = y;
	}
}
