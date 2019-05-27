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
import disjointset.UnionFind;
import java.awt.geom.Rectangle2D;
import java.util.List;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;
import static org.jgrapht.alg.shortestpath.DijkstraShortestPath.findPathBetween;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultDirectedGraph;

public class DrawMaze extends Canvas {
   
    private final float SIZE;
    private int [][] allPairs;
    private int size;
    private UnionFind edgeSet; 
    private boolean drawShortest, drawAll;
    private Graph<Integer, DefaultEdge> maze; //graph that keeps track of my maze
    private int pathnum;
    private List<GraphPath<Integer, DefaultEdge>> listAllPaths;
    private AllDirectedPaths allpaths;
    private int numclicks; //tells us if the submit button has been clicked
    private boolean multipleSolutions; //tells us if we should allow multiple solutions in our maze
    private int numpaths; //stores the number of paths you can take to solve the maze
    //m = rows, n = columns, just like a standard matrix
    private final int m,n;
    
    public DrawMaze() {
        this(10, 10);
    }
    
    
    
    public void shortestPath() {
        drawShortest = drawShortest == false;
        
        repaint();
    }
    
    public void allPaths() {
        
        if(numclicks == 0)
        {
            AllDirectedPaths allpaths = new AllDirectedPaths(maze);
            listAllPaths = allpaths.getAllPaths(0, m*n -1, true, Integer.MAX_VALUE);
            numpaths = listAllPaths.size();
        }
        repaint();
        
        numclicks++;
    }
    public void displayAll(boolean t) { drawAll = t; }
    public boolean willDrawAll() { return drawAll; }
    public void setMultiple(boolean t) 
    {
        multipleSolutions = t;
    }
    
    public void clear() 
    {
        allPairs = null;
        maze = null;
        edgeSet.clear();
        edgeSet = null;
    }
    
    public DrawMaze(int x, int y) {
        m = x;
        n = y;
        size = 2*m*n - n - m;
        getEdgeList();
        shuffle();
        genMaze();
        drawShortest = false;
        drawAll = false;
        SIZE = 400;
        pathnum = 0;
        numclicks = 0;
    }
    
    public void genNew() {
        edgeSet = null;
        maze = null;
        pathnum = 0;
        numclicks = 0;
        listAllPaths = null;
        allpaths = null;
        drawAll = false;
        shuffle();
        if(!multipleSolutions)
            genMaze();
        else
        {
            genMazeMultiPaths();
        }
        repaint();
    }
   
    
    @Override
    public void paint(Graphics graphics){
        Graphics2D g = (Graphics2D)graphics;
        //we need to generate the size of our array
        //how I derived this formula will be in the readme
        
        float rowSp = SIZE/m, colSp = SIZE/n;
               

        

        if(drawAll == true)
        {
            //if we've gone through every path, reset
            if(pathnum == listAllPaths.size())
            {
                pathnum = 0;        
                drawAll = false;
            }
            //put our path of paths in listAllPaths
            else  //othrwise, print the next path
            {
                g.setColor(Color.GREEN);
                drawPath(listAllPaths.get(pathnum).getVertexList(), g, rowSp, colSp);
            }
            

        }
        
        //draw the shortest path
        if(drawShortest == true)
        {        
            g.setColor(Color.BLUE);
            drawPath(findPathBetween(maze, 0, m*n -1).getVertexList(), g, 
                    rowSp, colSp);
        }
        

        g.setColor(Color.red);  
        g.draw(new Line2D.Float(0, 0, SIZE , 0)); //top border
        g.draw(new Line2D.Float(0, rowSp, 0, SIZE)); //creates the opening left edge
        g.draw(new Line2D.Float(0, SIZE, SIZE, SIZE)); //creates the bottom edge
        g.draw(new Line2D.Float(SIZE, 0, SIZE, SIZE - rowSp)); //right edge
        int curnum = 0;

        //draws all of the vertical lines
        for(int i = 0; i < m; i++) //iterates through the y axis
        {
            for(int j = 0; j < n -1; j++) //iterates through the x axis
            {
                if(maze.containsEdge(curnum, curnum+1) == false)
                {
                    g.draw(new Line2D.Float((j + 1)*colSp, i * rowSp, 
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
                if(maze.containsEdge(curnum, curnum + n) == false)
                {
                    g.draw(new Line2D.Float(j*colSp, i*rowSp, 
                            (j + 1) *colSp, i *rowSp));
                }
                curnum++;
            }
        }        
        
    }
    
   
    private void drawPath(List<Integer> path, Graphics2D g, float rowSp, float colSp) {
        float curcol = 0, currow = 0;
        //returns a path using depth first search
        
        int curnum = 0, prevnum;
        g.fill(new Rectangle2D.Float(0, 0, rowSp, colSp));
        //print rectangles on every element in our path
        for(int i = 1; i < path.size(); i++)
        {
            prevnum = curnum;     //hold onto our previous number
            curnum = path.get(i); //get the next item in our path

            if(prevnum + 1 == curnum)       //move right
            {
                curcol = curcol + colSp;
            }
            else if (prevnum - 1 == curnum) //move left
            {
               curcol = curcol - colSp;
            }
            else if (prevnum - n == curnum) 
            {
                currow = currow - rowSp;
            }
            else
            {
                currow = currow + rowSp;
            }

            g.fill(new Rectangle2D.Float(curcol, currow, colSp
                    , rowSp));

        }
            
        
    }
    //generate a maze but allow for multiple solutions
    private void genMazeMultiPaths() {        
         
        //clone the array
        int [][] deletedPairs = allPairs.clone();
        for(int i = 0; i < deletedPairs.length; i++)
        {
            deletedPairs[i] = deletedPairs[i].clone();
        }

        
        maze = new DefaultDirectedGraph<>(DefaultEdge.class);
        edgeSet = new UnionFind(m *n);

        int p1 = 0, p2 = 0; //designed to hold return values for find
        /*iterate through our list of potential walls, remove them with the logic
        inside the forloop. we wont remove all edges, just enough to make it such
        that you can get from your source vertext to any other */
        for(int i = 0; i < allPairs.length; i++)
        {
            //get the next edge
           p1 = allPairs[i][0];
           p2 = allPairs[i][1];
           
           //if the edges arent in our graph, add them
           if(!maze.containsVertex(p1))
                maze.addVertex(p1);
           if(!maze.containsVertex(p2))
                maze.addVertex(p2);
           
           //if you already couldn't get from p1 to p2, remove the wall
           //if it was already possible to get form p1 to p2, dont add the wall
           if(edgeSet.union(p1, p2))
           {
               maze.addEdge(p1, p2);
               maze.addEdge(p2, p1);
               deletedPairs[i][0] = -1; //mark this as deleted
           }
        }
        
        
        //randomly delete edges to create more paths
        for(int i = 0; i < deletedPairs.length; i++) 
        {
            if(deletedPairs[i][0] == -1)
                continue;
            
            if(Math.random() < findOdds()) //probability of an edge getting removed
            {
                maze.addEdge(deletedPairs[i][0], deletedPairs[i][1]);
                maze.addEdge(deletedPairs[i][1], deletedPairs[i][0]);
            }
        }
        deletedPairs = null;
    }
    
    private void genMaze() {
        maze = new DefaultDirectedGraph<>(DefaultEdge.class);
        edgeSet = new UnionFind(m *n);

        int p1 = 0, p2 = 0; //designed to hold return values for find
        /*iterate through our list of potential walls, remove them with the logic
        inside the forloop. we wont remove all edges, just enough to make it such
        that you can get from your source vertext to any other */
        for(int i = 0; i < allPairs.length; i++)
        {
            //get the next edge
           p1 = allPairs[i][0];
           p2 = allPairs[i][1];
           
           //if the edges arent in our graph, add them
           if(!maze.containsVertex(p1))
                maze.addVertex(p1);
           if(!maze.containsVertex(p2))
                maze.addVertex(p2);
           
           //if you already couldn't get from p1 to p2, remove the wall
           //if it was already possible to get form p1 to p2, dont add the wall
           if(edgeSet.union(p1, p2))
           {
               maze.addEdge(p1, p2);
               maze.addEdge(p2, p1);
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
    
    public int getNumPaths() { return numpaths; } 
    public int getCurPath()  { return pathnum; } 
    //returns the idnex of the current path
    
    /*this will help us find the odds of an edge getting deleted.
    the value is based on the size of the array, the values are arbitrary
   but they were tested and they work well on average */
    private double findOdds()
    {
        double percent = 0.0;

        if(m*n >= 18222)            
            percent = .0006;
        else if(m*n >= 8100)
            percent = .0015;
        else if(m*n >= 6400)
            percent = .002;
        else if(m*n >= 4900)
            percent = .0035;
        else if(m*n >= 3600)
            percent = .0038;
        else if(m*n >= 2500)
            percent = .0045;
        else if(m*n >= 1600)
            percent = .0055;
        else if(m*n >= 900)
            percent = .0065;
        else if(m*n >= 676)
            percent = .016;
        else if(m*n >= 225)
            percent = .025;
        else if(m*n >= 100)
            percent = .045;
        else
            percent = .1;
        return percent;
    }
    
    public void setPath(int n)  { pathnum = n; } 
   
    public int getPathSize() {
        if(listAllPaths == null)
            return 0;
        else
            return listAllPaths.size();
    } 
    
    public void incPath() {
        if(listAllPaths == null)
            pathnum = 0;
        if(pathnum + 1 != listAllPaths.size())
            pathnum++;
        else
            pathnum = 0;
    }
    
    public void decPath() {
        if(listAllPaths == null)
            pathnum = 0;
        else if(pathnum == 0)
            pathnum = listAllPaths.size() - 1;
        else
            pathnum--;
    }

}