import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameRunner
{
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		int gHeight = 960;
		int gWidth = 1280;
		frame.setTitle("Meteors");
		JPanel panel = new GamePanel(gWidth, gHeight);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(gWidth, gHeight);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
	}

}
