
package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;


public class DrawMaze extends Canvas {
   
    
    //m = rows, n = columns, just like a standard matrix!
    int m,n;
    
    DrawMaze() {
        m = 11;
        n = 11;
    }
    
    DrawMaze(int x, int y) {
        m = x;
        n = y;
    }
    
    @Override
    public void paint(Graphics g){
        //we need to generate the size of our array
        //how I derived this formula will be in the readme
        int size = 4*m*n - 2*n - 2*m; 
        //the wonky formula below is there because I wanted to round up (not down) with my integers
        int rowSp = 400/m, colSp = 400/n;
        
        g.setColor(Color.red);    //set the color to red
        g.drawLine(0, 0, 400 , 0); //top border
        g.drawLine(0, rowSp, 0, 400); //creates the opening left edge
        g.drawLine(0, 400, 400, 400); //creates the bottom edge
        g.drawLine(400, 0, 400, 400 - rowSp); //right edge
        
        //the right and left edges both have the entrance/exis
        
        //the first row of lines:
        //runs 4 times
  
        
        
        //draws all of the horizontal lines
        for(int i = 0; i < m; i++) //iterates through the y axis
        {
            for(int j = 0; j < n -1; j++) //iterates through the x axis
            {
                g.drawLine((j + 1)*rowSp, i * colSp, (j + 1) * rowSp, 
                        (i + 1) * colSp);
            }
        }
        
        //think of rowSp and colSp as our units, and down a row, we move down
        //rowSp pixels. If we wanna move down 2 rows, we do 2 * rowSp
        
        //this prints all of the horizontal lines
        for(int i = 1; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                g.drawLine(j*rowSp, i*colSp, (j + 1) *rowSp, i * colSp);
            }
        }
        
        
        
    }
    
    public void shrink() {
        
    }
    
    
    
}
