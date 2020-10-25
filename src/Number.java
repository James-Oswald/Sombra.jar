
//I have no idea what this file is for, or what i was doing with it


import java.awt.IconImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import javax.swing.SwingUtilities;
import java.util.ArrayList;
  
public class Number implements Runnable
    {
      private volatile int x, y;
      private char val;
      private final Random r = new Random(); 

      public Number()
      {
        val = '_';
        x = r.nextInt(((JFrame) SwingUtilities.getWindowAncestor(this)).getWidth());
        y = r.nextInt(((JFrame)SwingUtilities.getWindowAncestor(this)).getHeight());
        new Thread(this).start((JFrame) SwingUtilities.getWindowAncestor(this).getHeight()); 
      }

      public void run()
      {
        while(true)
        {
          if(y > ((JFrame)SwingUtilities.getWindowAncestor(this)).getHeight())
          {
            y = 0;
          }
          y++;
          val = (char)(r.nextInt(27) + 97);
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

      public void draw(Graphics g)
      {
        g.setColor(Color.white);
        g.drawString(val + "", x, y);           		
      }

    }
  	
  	