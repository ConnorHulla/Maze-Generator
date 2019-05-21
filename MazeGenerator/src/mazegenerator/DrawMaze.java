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
import java.awt.geom.Line2D;
import adjacencymatrix.AdjacencyMatrix;
import disjointset.UnionFind;

public class DrawMaze extends Canvas {
   
    private final double SIZE = 400.0;
    private int [][] allPairs;
    private int size;
    private AdjacencyMatrix maze; //graph that keeps track of removed edges
    private UnionFind edgeSet; 
    
    //m = rows, n = columns, just like a standard matrix!
    private final int m,n;
    
    public DrawMaze() {
        this(10, 10);
    }
    
    public DrawMaze(int x, int y) {
        m = x;
        n = y;
        size = 2*m*n - n - m;
        edgeSet = new UnionFind(m *n);
        maze = new AdjacencyMatrix(m*n);
        
        getEdgeList();
        shuffle();
        generateMaze();
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
  
        
        int curnum = 0;
        //draws all of the vertical lines
        for(int i = 0; i < m; i++) //iterates through the y axis
        {
            for(int j = 0; j < n -1; j++) //iterates through the x axis
            {
                if(maze.isConnected(curnum, curnum+1) == false)
                {
                    g.draw(new Line2D.Double((j + 1)*colSp, i * rowSp, 
                        (j + 1) * colSp, (i + 1) * rowSp));
                }
                curnum++;
            }
            curnum++;
        }
        
        //think of rowSp and colSp as our units, and down a row, we move down
        //rowSp pixels. If we wanna move down 2 rows, we do 2 * rowSp
        
        //this prints all of the horizontal lines
        
        curnum = 0;
        for(int i = 1; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(maze.isConnected(curnum, curnum + n) == false)
                {
                    g.draw(new Line2D.Double(j*colSp, i*rowSp, 
                            (j + 1) *colSp, i *rowSp));
                }
                curnum++;
            }
        }
        
    }
    
    private void generateMaze() {
        int p1 = 0, p2 = 0; //designed to hold return values for find
        for(int i = 0; i < allPairs.length; i++)
        {
           p1 = allPairs[i][0];
           p2 = allPairs[i][1];
           
           if(edgeSet.union(p1, p2))
           {
               maze.connect(p1, p2);
               maze.connect(p2, p1);
           }
        }
    }
    
    //this function will shuffle our array of edges (allPairs)
    private void shuffle() {
        
        int rand1, rand2, hold1, hold2;
        java.util.Random generator = new java.util.Random();
        
        
        //randomly swaps elements of the array
        for(int i = 0; i < size; i++)
        {
            //generates 2 random numbers
            rand1 = generator.nextInt(size);
            rand2 = generator.nextInt(size);
            
            //swap the 2 elements
            hold1 = allPairs[rand1][0];
            hold2 = allPairs[rand1][1];
            
            allPairs[rand1][0] = allPairs[rand2][0];
            allPairs[rand1][1] = allPairs[rand2][1];
            
            allPairs[rand2][0] = hold1;
            allPairs[rand2][1] = hold2;
        }
    }
    
    private void printList() 
    {
        for(int i = 0; i < allPairs.length; i++)
        {
           System.out.println("(" + allPairs[i][0] + "," + allPairs[i][1] + ")");
        }
    }
    private void getEdgeList() {
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