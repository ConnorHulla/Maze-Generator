
package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import javax.swing.JFrame;


public class DrawMaze extends Canvas {
   
    final double SIZE = 400.0;

    
    //m = rows, n = columns, just like a standard matrix!
    int m,n;
    
    DrawMaze() {
        m = 11;
        n = 7;
    }
    
    DrawMaze(int x, int y) {
        m = x;
        n = y;
    }
    
    @Override
    public void paint(Graphics graphics){
        Graphics2D g = (Graphics2D)graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON); //enables antialiasing

        //we need to generate the size of our array
        //how I derived this formula will be in the readme
        int size = 4*m*n - 2*n - 2*m; 
        //the wonky formula below is there because I wanted to round up (not down) with my integers
        
        double rowSp = SIZE/m, colSp = SIZE/n;
        
        g.setColor(Color.red);    //set the color to red
        g.draw(new Line2D.Double(0, 0, SIZE , 0)); //top border
        g.draw(new Line2D.Double(0, rowSp, 0, SIZE)); //creates the opening left edge
        g.draw(new Line2D.Double(0, SIZE, SIZE, SIZE)); //creates the bottom edge
        g.draw(new Line2D.Double(SIZE, 0, SIZE, SIZE - rowSp)); //right edge
        
        //the right and left edges both have the entrance/exis
        
        //the first row of lines:
        //runs 4 times
  
        
        
        //draws all of the vertical lines
        for(int i = 0; i < m; i++) //iterates through the y axis
        {
            for(int j = 0; j < n -1; j++) //iterates through the x axis
            {
                g.draw(new Line2D.Double((j + 1)*colSp, i * rowSp, 
                    (j + 1) * colSp, (i + 1) * rowSp));
            }
        }
        
        //think of rowSp and colSp as our units, and down a row, we move down
        //rowSp pixels. If we wanna move down 2 rows, we do 2 * rowSp
        
        //this prints all of the horizontal lines
        for(int i = 1; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                g.draw(new Line2D.Double(j*colSp, i*rowSp, (j + 1) *colSp, i *rowSp));
            }
        }
        
        
        
    }
    
    public void shrink() {
        
    }
    
    
    
}
