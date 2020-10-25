/*
 * James Oswald
 * Period 5
 */


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
  
public class QuadSombra
{
	public class QuadRes implements Runnable
	{
		private volatile int x, y;
		private ArrayList <String> qr;
		private final Random r = new Random(); 
		private final int spa = 18, tab = 30;
		private int mh, mr; 
		private final Font f = new Font("Monospaced", Font.PLAIN, 13);;
		private final Color c = new Color(102, 43, 147);

		public QuadRes(int mh, int mr)
		{
			qr = Quadratic.printAll(r.nextInt(40), r.nextInt(40), r.nextInt(40));
			this.mh = mh;
			this.mr = mr;
			x = r.nextInt(mr) - 200;
			y = r.nextInt(mh);
			new Thread(this).start(); 
		}

		public void run()
		{
			while(true)
			{
				if(y > mh)
				{
					y = (spa * (qr.size() + 2)) * -1;
					x = r.nextInt(mr) - 200;
				}
				y++;
				try
				{
					if(y % 30 == 0)
					{
						qr = Quadratic.printAll(r.nextInt(40), r.nextInt(40), r.nextInt(40));
					}
					Thread.sleep(10); 
				}
				catch(Exception e)
				{
					e.printStackTrace(); 
					qr = Quadratic.printAll(r.nextInt(40) - 20, r.nextInt(40) - 20, r.nextInt(40) - 20);
				}
			}
		}

		public void draw(Graphics g)
		{
			g.setColor(c);
			g.setFont(f);
			g.drawString(qr.get(qr.size() - 1), x, y);
			g.drawString("{", x, y + spa);
			for(int i = 0, mul = 2; i < qr.size() - 1; i++, mul++)
			{
				g.drawString(qr.get(i), x + tab, y + mul * spa);
			}
			g.drawString("}", x, y + (qr.size() + 1) * spa);
		}
    }
	
	public class SombraPanel extends JPanel implements Runnable
	{
		private ArrayList <QuadRes> n;
		private JFrame f;
		private ImageIcon img = new ImageIcon();
	  
		public SombraPanel(JFrame f)
		{
			this.f = f;
			setBackground(Color.black); 
			n = new ArrayList <QuadRes> ();
			for(int i = 0; i < 3; i++)
			{
				n.add(new QuadRes(f.getHeight(), f.getWidth()));
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
					Thread.sleep(10); 
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
    	new QuadSombra().run();
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