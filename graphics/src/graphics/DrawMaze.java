
package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;


public class DrawMaze extends Canvas {
   
    int m,n;
    
    DrawMaze() {
        m = 4;
        n = 4;
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
        g.setColor(Color.red);    //set the color to red
        g.drawLine(0, 0, 400 , 0); //top border
        g.drawLine(0, 400/m, 0, 400); //creates the opening left edge
        g.drawLine(0, 400, 400, 400); //creates the bottom edge
        g.drawLine(400, 0, 400, 400 - 400/m); //right edge
        
        //the right and left edges both have the entrance/exit
        
        boolean [][] hasLine = new boolean[m][n];
        
        
        
    }
    
    public void shrink() {
        
    }
    
    
    
}
