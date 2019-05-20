
package mazegenerator;

import java.awt.BorderLayout;
import java.awt.Canvas;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

import javax.swing.SwingUtilities;

public class MazeHolder extends JFrame {
    private JButton backButton, genMaze, idk; //north buttons
    private JButton DFS, BFS, ShortestPath; //south buttons
    private Canvas canvas;
    //dfs = depth first search, bfs = breadth first search, 
    //shortest path will probably use dijkstra
    
    private Graphics maze;
    //private Canvas mazeHolder;
    private int m, n;
    //m = rows, n = cols, m x n maze
    MazeHolder() {
        
        /*plan, put a flow layout north and south for added buttons
        south might contain a button for dfs, bfs, and shortest path
        north might contain a back button for the main menu, or a button
        that generates a new maze
        no east or west. You can add panels to each container */
        m = 4; n = 4; //default size 4x4
        
        buildGUI();
        setTitle("Maze");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    MazeHolder(int r, int c) {

        m = r; n = c;
        
        buildGUI();
        setTitle("Maze");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        
    }

    private void buildGUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        //north
        JPanel north = new JPanel();
        
        backButton = new JButton("Back Button");
        genMaze = new JButton("Generate Maze");
        idk = new JButton("idk");
        
        
        north.add(backButton);
        north.add(genMaze);
        north.add(idk);
       
        north.setVisible(true);
        panel.add(north, BorderLayout.NORTH); //adds our north panel to north
        
        //set up our south panel
        JPanel south = new JPanel();
        
        DFS = new JButton("Depth First Search");
        BFS = new JButton("Breadth First Search");
        ShortestPath = new JButton("Shortest Path");
        
        
        //add the buttons to the south frame
        south.add(DFS);
        south.add(BFS);
        south.add(ShortestPath);
        south.setVisible(true);
        
     
        panel.add(south, BorderLayout.SOUTH);
       
        JPanel center = new JPanel();
        
        /* DrawMaze has a function that paints an oval, so we set our canvas
        equal to it, set the size of our canvas, add the canvas to our center,
        and we done*/
        //if we want to access our methods, set thte canvas equal like htis
        DrawMaze maze = new DrawMaze(m,n);
        canvas = maze;
        canvas.setSize(410, 410);
        center.add(canvas);
        center.setVisible(true);

        
        panel.add(center, BorderLayout.CENTER);
        
        
        
        panel.setVisible(true);
        
        
        getContentPane().add(panel);
    }
    

    
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            new MazeHolder().setVisible(true);
        });
        
    }
    
    
}
