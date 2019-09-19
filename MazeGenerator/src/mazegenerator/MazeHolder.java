
package mazegenerator;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

/*MazeHolder is a graphical user interface that displays the maze to the user
while also providing some useful buttons*/

public class MazeHolder extends JFrame {
    private JButton backButton, genMaze; //north buttons
    private JButton AllPaths, ShortestPath, prevpath, nextpath; //south buttons
    private JToggleButton toggleMult; //will allow the user to allow the algorithm to produce mazes with multiple solutions
    private Canvas canvas; //canvas displays graphics objects
    private Menu prev; //used to go back to the first screen
    private JLabel curpath;
    private DrawMaze maze;
    private Graphics d;
    private boolean showPaths = false;
    private int rows, cols;
    public MazeHolder() {
        
        /*plan, put a flow layout north and south for added buttons
        south might contain a button for dfs, bfs, and shortest path
        north might contain a back button for the main menu, or a button
        that generates a new maze
        no east or west. You can add panels to each container */
        rows = 4; cols = 4; //default size 4x4
        
        buildGUI();
        setTitle("Maze");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        
        
    }
    
    //this function will set up the north panel, south panel, and center panel
    
    public MazeHolder(int r, int c, Menu hold) {

        rows = r; cols = c;
        
        buildGUI();
        setTitle("Maze");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        prev = hold;
        
    }

    private void buildGUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(500, 480));
        int index = 0; //index for path
        //north
        JPanel north = new JPanel();
        
        backButton = new JButton("Back");
        genMaze = new JButton("Generate Maze");
        toggleMult = new JToggleButton("Allow Multiple Solutions");
        
        /*pressing the "Allow Multiple Solutions" button sets the maze to generate
        with multiple solutions instead of just one */
        toggleMult.addActionListener((ActionEvent e) -> {
            maze.setMultiple(toggleMult.isSelected());
        });
        
        genMaze.addActionListener(new MazeListener());
        
        
        /*add the back button, generate maze button, and multiple solutions 
        toggle to the north part of the GUI */
        north.add(backButton);
        north.add(genMaze);
        north.add(toggleMult);
       
        north.setVisible(true);
        panel.add(north, BorderLayout.NORTH); //adds our north panel to north
        
        //set up our south panel
        JPanel south = new JPanel();
        
        //These are the buttons and their respective labels
        curpath = new JLabel("");
        AllPaths = new JButton("All Paths");
        ShortestPath = new JButton("Shortest Path");
        prevpath = new JButton("<--- Prev");
        nextpath = new JButton("Next --->");
        prevpath.setEnabled(false);
        nextpath.setEnabled(false);
        
        //When the button is pressed, go to the previous path
        prevpath.addActionListener((ActionEvent e) -> { 
            maze.decPath();
            curpath.setText("Path # " + (maze.getCurPath() + 1)
                    + " out of " + maze.getNumPaths());
            maze.allPaths();
        });
        
        //when the button is pressed, go to the next path
        nextpath.addActionListener((ActionEvent e) -> { 
            maze.incPath(); //goes to the next path in maze
            curpath.setText("Path # " + (maze.getCurPath() + 1)
                    + " out of " + maze.getNumPaths());
            maze.allPaths();
        });
        backButton.addActionListener(new BackListener());
        
        
        /*calls upon the function that allows for the shortest path in our maze
        to be displayed */
        ShortestPath.addActionListener((ActionEvent e)->{maze.shortestPath();});
        
        
        AllPaths.addActionListener((ActionEvent e) -> {
            showPaths = maze.willDrawAll() == false;
            maze.displayAll(showPaths);
            maze.allPaths();
            curpath.setText("Path # " + (maze.getCurPath() + 1)
                    + " out of " + maze.getNumPaths());
         
            prevpath.setEnabled(showPaths);
            nextpath.setEnabled(showPaths);
        });
        
        //adds the path-related buttons to the south part of our GUI
        south.add(ShortestPath);
        south.add(AllPaths);
        south.add(prevpath);
        south.add(nextpath);
        south.add(curpath);
        south.setVisible(true);
        
     
        panel.add(south, BorderLayout.SOUTH);
       
        JPanel center = new JPanel();
    
        //Create a new maze, put it on our canvas
        maze = new DrawMaze(rows,cols);
        canvas = maze;
        
        //add the canvas to the center of our GUI then make it visible
        canvas.setSize(410, 410);
        center.add(canvas);
        center.setVisible(true); //this line makes the maze visible

        
        panel.add(center, BorderLayout.CENTER);
        panel.setVisible(true);
        getContentPane().add(panel);
    }
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            new MazeHolder().setVisible(true);
        });
        
    }

    //press the back button to go to the menu, and hides the mazeHolder
    private class BackListener implements ActionListener {
        //this function deletes some objects
        //this needs to be done because really large mazes use lots of memory
        @Override
        public void actionPerformed(ActionEvent e) {
            maze.clear(); //delete the maze
            setVisible(false); 
            prev.setVisible(true);
            maze = null;
            System.gc(); //calls the garbage collector
        }
    }

    
    //generates a new
    private class MazeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            curpath.setText("");
            maze.genNew();
        }
    }
    
    
}
