
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.awt.Font;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.Dimension;
  
public class Sombra
{
	public class Number implements Runnable
	{
		private volatile int x, y;
		private char val;
		private final Random r = new Random(); 
		private int mh, mr; 
		private final Font f = new Font("Monospaced.bold", Font.PLAIN, 24);;
		private final Color c = new Color(102, 43, 147);

		public Number(int mh, int mr)
		{
			val = '_';
			this.mh = mh;
			this.mr = mr;
			x = r.nextInt(mr);
			y = r.nextInt(mh);
			new Thread(this).start(); 
		}

		public void run()
		{
			while(true)
			{
				if(y > mh)
				{
					y = 0;
				}
				y++;
				val = (char)(r.nextInt(222) + 33);
				try
				{
					Thread.sleep(10); 
				}
				catch(Exception e)
				{
					e.printStackTrace(); 
				}
			}
		}

		public void draw(Graphics g)
		{
			g.setColor(c);
			g.setFont(f);
			g.drawString(val + "", x, y);           		
		}
    }
	
	public class SombraPanel extends JPanel implements Runnable
	{
		private ArrayList <Number> n;
		private JFrame f;
		private ImageIcon img = new ImageIcon();
	  
		public SombraPanel(JFrame f)
		{
			this.f = f;
			setBackground(Color.black); 
			n = new ArrayList <Number> ();
			for(int i = 0; i < 50; i++)
			{
				n.add(new Number(f.getHeight(), f.getWidth()));
			}
			try
			{
				img = new ImageIcon(new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/6/6f/SombraASCIISkull.svg/220px-SombraASCIISkull.svg.png"));
			}
			catch(Exception e)
			{
				System.out.println("Failure");
			}
			new Thread(this).start();
		}
	  
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			for(int i = 0; i < n.size(); i++)
			{
				n.get(i).draw(g);
			}
			g.drawImage(img.getImage(), f.getWidth() / 2 - img.getIconWidth() / 2, f.getHeight() / 2 - img.getIconHeight() / 2, null);
		}
		
		public void run()
		{
			while(true)
			{
				repaint();
				try
				{
					Thread.sleep(100); 
				}
				catch(Exception e)
				{
					e.printStackTrace(); 
				}
			}
		}
	}
	
	public static void main(String[] args)
    {
    	new Sombra().run();
    }
	
	public void run()
	{
		JFrame f = new JFrame();
      	f.setUndecorated(true);
      	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1000, 500);
		f.add(new SombraPanel(f));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation(dim.width / 2 - f.getWidth() / 2, dim.height / 2 - f.getHeight() / 2);
      	f.setVisible(true);
	}
}