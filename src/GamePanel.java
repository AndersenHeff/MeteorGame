import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GamePanel extends JPanel implements Runnable
{
	private static final int m = 150;
	private Meteor[] meteor = new Meteor[m];
	private Player player;
	private Thread thread;
	private boolean jumped;
	
	
	public GamePanel(int gWidth, int gHeight)
	{
		jumped = false;
		player = new Player(gWidth/2 - 10, gHeight - 45, Color.BLUE, gWidth, gHeight);
		thread = new Thread(this);
		thread.start();
		
        for (int i = 0; i < m; i++) 
        {
            meteor[i] = new Meteor(gWidth, gHeight);
        }
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		player.draw(g);
		for(int i = 0; i < m; i++)
		{
			meteor[i].draw(g);
			//fix collision statement
			
			/*if(true)
			{
				player.setY(meteor[i].getY() - player.getHeight());
			}*/
		}
        Toolkit.getDefaultToolkit().sync();
	}
	
	
	
	@Override
	public void run() 
	{
		
		registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "left", left);
		registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "stop", stop);
		registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "right", right);
		registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "stop", stop);
		registerKeyBinding(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "jump", jump);

		
		
		while(true)
		{
			try {
				//FPS
				Thread.sleep(20);
				if(jumped)
				{
					player.setA(player.getA() * 1.04);
				}
				else if(!jumped)
				{
					player.setA(-2);
				}
			} catch (InterruptedException e) { 
				System.out.println("Thread stopped");
				e.printStackTrace();
				thread.interrupt();
				return;
			}
			repaint();
		}
	}
	
	
	
	private Action left = new AbstractAction("left") {
		@Override
		public void actionPerformed(ActionEvent ae) {
			player.setDir(Direction.LEFT);
		}
	};
	private Action right = new AbstractAction("right") {
		@Override
		public void actionPerformed(ActionEvent ae) {
			player.setDir(Direction.RIGHT);
		}
	};
	private Action stop = new AbstractAction("stop") {
		@Override
		public void actionPerformed(ActionEvent ae) {
			player.setDir(Direction.NONE);
		}
	};
	private Action jump = new AbstractAction("jump") {
		@Override
		public void actionPerformed(ActionEvent ae) {
			player.setJumpDir(JumpDirection.JUMP);
			jumped = true;
			if(intersect())
			{
				player.setJumpDir(JumpDirection.NONE);
				jumped = false;
			}
		}
	};
	
	private void registerKeyBinding(KeyStroke keyStroke, String name, Action action)
	{
		InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = getActionMap();
		im.put(keyStroke, name);
		am.put(name, action);
	}

	public boolean intersect()
	{
		for(int i = 0; i < m; i++)
		{
			if(player.intersects(meteor[i]))
			{
				System.out.println("intersect");
				return true;
			}
		}
		return false;
	}
		
		
		
		
		
		
		
		
		
		
		
}
