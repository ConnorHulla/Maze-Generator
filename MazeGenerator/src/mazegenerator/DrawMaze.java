/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegenerator;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;


public class DrawMaze extends Canvas {
   
    private final double SIZE = 400.0;
    private int [][] allPairs;
    
    //m = rows, n = columns, just like a standard matrix!
    private int m,n;
    
    public DrawMaze() {
        m = 10;
        n = 10;
    }
    
    public DrawMaze(int x, int y) {
        m = x;
        n = y;
        getEdgeList();
    }
    
    @Override
    public void paint(Graphics graphics){
        Graphics2D g = (Graphics2D)graphics;

        //we need to generate the size of our array
        //how I derived this formula will be in the readme
        
        double rowSp = SIZE/m, colSp = SIZE/n;
       
        //these next 5 lines draw the borders
        g.setColor(Color.red);    //set the color to red
        g.draw(new Line2D.Double(0, 0, SIZE , 0)); //top border
        g.draw(new Line2D.Double(0, rowSp, 0, SIZE)); //creates the opening left edge
        g.draw(new Line2D.Double(0, SIZE, SIZE, SIZE)); //creates the bottom edge
        g.draw(new Line2D.Double(SIZE, 0, SIZE, SIZE - rowSp)); //right edge
        //Line2D.Double draws more accurate lines than integer values will allow
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
                g.draw(new Line2D.Double(j*colSp, i*rowSp, 
                        (j + 1) *colSp, i *rowSp));
            }
        }
        
    }
    
    private void shuffle() {
        
    }
    
    private void printList() {
        for(int i = 0; i < 2*m*n - n - m; i++)
        {
           System.out.println("(" + allPairs[i][0] + "," + allPairs[i][1] + ")");
        }
    }
    private void getEdgeList() {
        //get the number of edges in our array
        int size = 2*m*n - n - m; 
        //How this formula was derived will be provided in the readme
        allPairs  = new int [size][2];
        int pairIndex = 0, curPair = 0;
        //9 cases of edges
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {

                if(i == m-1 && j == n-1 ) //we are done, we've reachedthe corner
                {
                    return;
                }
                else if(i == m-1){
                    //connect to the right edge
                    allPairs[pairIndex][0] = curPair;
                    allPairs[pairIndex][1] = curPair + 1;
                    pairIndex++;
                }
                else if(j == n-1) 
                {
                   //conect edge to square below
                    allPairs[pairIndex][0] = curPair;
                    allPairs[pairIndex][1] = curPair + n;
                    pairIndex++;
                }
                else
                {
                    //edge to the right of the current square
                    allPairs[pairIndex][0] = curPair;
                    allPairs[pairIndex][1] = curPair + 1;
                    //edge below the current square
                    allPairs[pairIndex + 1][0] = curPair;
                    allPairs[pairIndex + 1][1] = curPair + n;
                    pairIndex += 2;
                }
                curPair++;
            }
        }
        
    }
    
    
    
}