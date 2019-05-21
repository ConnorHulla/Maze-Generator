
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
    
    public void clear() {
        set = null;
    }
    
    public int find(int n) throws java.lang.IllegalArgumentException 
    {
       
        if(n < 0)
            throw new java.lang.IllegalArgumentException("Only positive numbers");
        
        if(n > set.length)
            throw new java.lang.IllegalArgumentException("Input goes out of bounds");
        
        int hold = n;
        //keep going through parents until we reach the end
        while(set[n] >= 0) 
        { 
            n = set[n];
        }

        
        return n;
    }
    
    public boolean union(int set1, int set2)
    {
        int p1 = find(set1);
        int p2 = find(set2);
        
        if(p1 == p2)
            return false;
        
        set[p2] = p1;
        return true;
    }
    
    public String toString() {
        String s = "";
        
        for(int i = 0; i < set.length; i++)
            s += set[i] + " ";
        
        return s;
    }
    
}
