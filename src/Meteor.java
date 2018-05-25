import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Meteor extends GameObjects implements Intersection
{	
	public Meteor(int gWidth, int gHeight)
	{
		super(gWidth, gHeight);
		gWidth = 1260;
		gHeight = 960;
		y = (int) (Math.random() *gHeight) - gHeight + height;
		x= (int) (Math.random() *gWidth);
		width = 20;
		height = 20;
		speed = 3;
		c = Color.DARK_GRAY;
	}
	public void draw(Graphics g)
	{
		g.setColor(c);
		g.fillRect(x, y, width, height);
		fall();
	}
	public void fall()
	{
		y += speed;
		if(y >= gHeight)
		{
			y = (int) (Math.random() *gHeight) - gHeight + height;
			
		}
	}
	@Override
	public Rectangle getRect() 
	{
		return new Rectangle(x, y, width, height);
	}
}
