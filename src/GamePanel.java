import java.awt.Color;
import java.awt.Graphics;
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
	private boolean jTime;
	
	
	public GamePanel(int gWidth, int gHeight)
	{
		jTime = false;
		player = new Player(630, 900, Color.BLUE, gWidth, gHeight);
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
			if(player.getY() + player.getHeight() == meteor[i].getY())
			{
				player.setY(meteor[i].getY() - player.getHeight());
			}
		}

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
				if(jTime)
				{
					player.setCountTime(player.getCountTime() + 3);
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
			jTime = true;
			player.setJumpDir(JumpDirection.JUMP);
		}
	};
	
	private void registerKeyBinding(KeyStroke keyStroke, String name, Action action)
	{
		InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = getActionMap();
		im.put(keyStroke, name);
		am.put(name, action);
	}
	

}
