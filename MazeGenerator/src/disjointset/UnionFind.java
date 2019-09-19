
package disjointset;

public class UnionFind {
    
    private int [] set;
    
    public UnionFind() { this(10); }
    
    public UnionFind(int n) 
    { 
        set = new int[n]; 
        //initialize to -1;
        for(int i = 0; i < n; i++)
            set[i] = -1;
    }
    //deletes set
    public void clear() {
        set = null;
    }
    
    public int find(int value) throws java.lang.IllegalArgumentException 
    {
       
        //throw exceptions for out of bounds inputs
        if(value < 0)
            throw new java.lang.IllegalArgumentException("Only positive numbers");
        
        if(value > set.length)
            throw new java.lang.IllegalArgumentException("Input goes out of bounds");
        
        int hold = value;
        
        //keep going through parents until we reach the end
        while(set[value] >= 0) 
        { 
            //set value to its parent
            value = set[value]; 
        }
        //once we've reached the end, we found the value associated with our input
        
        return value;
    }
    
    //this function will combine set1 and set2
    public boolean union(int set1, int set2)
    {
        //combine set1 and set1
     
        int parent1 = find(set1);
        int parent2 = find(set2);
        
        /*if both sets have the same parents, then they are already in the same
        set so return false */
        if(parent1 == parent2)
            return false;
        
        //otherwise, combine their sets by giving them the same parent
        set[parent2] = parent1;
        return true;
    }
    
    //print every element of the set
    public String toString() {
        String s = "";
        
        for(int i = 0; i < set.length; i++)
            s += set[i] + " ";
        
        return s;
    }
    
}
