import java.awt.Color;
import java.awt.Graphics;

public class Meteor extends GameObjects
{	
	public Meteor(int gWidth, int gHeight)
	{
		super(gWidth, gHeight);
		gWidth = 1260;
		gHeight = 960;
		y = (int) (Math.random() *gHeight) - 980;
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
			y = (int) (Math.random() *gHeight) - 980;
			
		}
	}
}
