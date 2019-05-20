
package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;


public class DrawMaze extends Canvas {
   
    int m,n;
    
    DrawMaze() {
        m = 6;
        n = 6;
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
        int rowSp = 400/n, colSp = 400/m; //rowSpacing, columnSpacing
        
        g.setColor(Color.red);    //set the color to red
        g.drawLine(0, 0, 400 , 0); //top border
        g.drawLine(0, 400/m, 0, 400); //creates the opening left edge
        g.drawLine(0, 400, 400, 400); //creates the bottom edge
        g.drawLine(400, 0, 400, 400 - 400/m); //right edge
        
        //the right and left edges both have the entrance/exit
        
        
        //the first row of lines:
        //runs 4 times
        /*
        g.drawLine(400/n, 0, 400/n, 400/m);
        g.drawLine(2 *400/n, 0, 2* 400/n, 400/m);
        g.drawLine(3 * 400/n, 0, 3 * 400/n, 400/m);
        g.drawLine(4* 400/n, 0, 4 * 400/n, 400/m);
        
        g.drawLine(400/n, 400/m, 400/n, 2* 400/m); */
        
        
        //draws all of the horizontal lines
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n -1; j++)
            {
                g.drawLine((j + 1)*rowSp, i * colSp, (j + 1) * rowSp, 
                        (i + 1) * colSp);
            }
        }
        //the first column of lines
        /*
        g.drawLine(0, 400/m, 400/n, 400/m);
        g.drawLine(400/n, 400/m, 2 * 400/n, 400/m);
        g.drawLine(2*400/n, 400/m, 3 * 400/n, 400/m);
        g.drawLine(3*400/n, 400/m, 4 * 400/n, 400/m);
        g.drawLine(4 * 400/n, 400/m, 5 * 400/n, 400/m); */
        //attempting to draw all horizontal lines
        
        
        //this prints all of the horizontal lines
        /*we start off from 0 to 100, then we draw one form 100 to 200,
        and so on */
        //the outer for loop brings us down the y axis
        //the inner for loop brings us through the x axis
        //we go down row by row, drawing the lines
        for(int i = 1; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                g.drawLine(j*rowSp, i*colSp, (j + 1) *rowSp, i * colSp);
            }
        }
        
       
        boolean [][] hasLine = new boolean[m][n];
        
        
        
    }
    
    public void shrink() {
        
    }
    
    
    
}
