//Connor Hulla
package adjacencymatrix;
import java.util.List;
import java.util.LinkedList;

public class AdjacencyMatrix {
    private boolean [][] matrix;
    private int rows, columns;
    
    AdjacencyMatrix(int r, int c) {
        rows = r;
        columns = c;
        
        matrix = new boolean[rows][columns];
    }
    
    public void connect(int source, int destination) {
        matrix[source][destination] = true;
    }
    
    public boolean isConnected(int source, int destination) {
        return matrix[source][destination];
    }
    
    public List getNeighbors(int n) {
        List<Integer> list = new LinkedList();
        
        //if n is connected to i, add it to the list
        for(int i = 0; i < columns; i++)
        {
            if(matrix[n][i] == true)
                list.add(i);
        }
        
        return list;
    }
    
    public void disconnect(int source, int destination) { 
        matrix[source][destination] = false;
    } 
    
    
    //print 1 for true, 0 for false
    @Override
    public String toString()
    {
        String adj = "";
        
        for(int i = 0; i < rows; i++) 
        {
            for(int j = 0; j < columns; j++)
            {
                if(matrix[i][j])
                    adj += "1 ";
                else
                    adj += "0 ";
            }
            //newline
            adj += "\r\n";
         
        }
        return adj;
    }
            
}
