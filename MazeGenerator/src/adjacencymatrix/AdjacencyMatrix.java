//Connor Hulla
package adjacencymatrix;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
public class AdjacencyMatrix {
    private boolean [][] matrix;
    private int size;
    
    public AdjacencyMatrix(int m) { matrix = new boolean[m][m]; size = m; }
    
    public void connect(int source, int destination) {
        matrix[source][destination] = true;
    }
    
    public boolean isConnected(int source, int destination) {
        return matrix[source][destination];
    }
    
    //list of nodes that n will connect to
    public List getNeighbors(int n) {
        List<Integer> list = new LinkedList();
        
        //if n is connected to i, add it to the list
        for(int i = 0; i < size; i++)
        {
            if(matrix[n][i] == true)
                list.add(i);
        }
        
        return list;
    }
    
    public void disconnect(int source, int destination) { 
        matrix[source][destination] = false;
    } 
    
    public void clear() 
    { 
        matrix = null; 
    }
    

    
    //print 1 for true, 0 for false
    @Override
    public String toString()
    {
        String adj = "";
        
        for(int i = 0; i < size; i++) 
        {
            for(int j = 0; j < size; j++)
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
    //trying to find the exit with a depth first search. exit is the last node
    public ArrayList findPathD(int src, int dest) 
    {
        boolean [] visited = new boolean[size];
        ArrayList<Integer> paths = new ArrayList<>();
        int curvertex = 0;
        paths.add(src);
        
        if(src == dest)   //if we are at our destination, the path is empty
            return paths;
        
       findPathD(visited, paths, src, dest);
        
        return paths;
    }
    
    //depth first tarversal
    
    private void findPathD(boolean [] visited, ArrayList<Integer> paths, int curNode, 
            int dest)
    {
        visited[curNode] = true;
        
        if(visited[dest] == true)
        {
            return;
        }
        for(int i = 0; i < visited.length; i++)
        {
            if(matrix[curNode][i] && visited[i] == false)
            {
                paths.add(i);
                findPathD(visited, paths, i, dest);
                
                if(visited[dest] == true)
                    return;
                
                paths.remove(new Integer(i));
            }
        }
        
        visited[curNode] = false;
    }
    //finds the next integer we haven't visited yet
    
    private int nextMin(int source, int [] parent)
    {
        List<Integer> findmin = getNeighbors(source);
        int min = Integer.MAX_VALUE;
        
        //find the minimum value we haven't visited yet
        for (Integer findmin1 : findmin) 
        {
            if(parent[findmin1] != -1 && findmin1 < min)
                min = findmin1;
           
        }
        
        return min;
    } 
    
    public ArrayList<Integer> dijkstra(int src, int dest)
    {
        ArrayList<Integer> path = new ArrayList<>();
        int [] parents = new int[size];
        
        int dist [] = new int[size];
        
        //initialize our arrays
        for(int i = 0; i < size; i++)
        {
            parents[i] = -1;
            dist[i] = Integer.MAX_VALUE;
        }
        
        dist[src] = 0; //distance to itself is always 0
                        
        return path;
    }
            
}
