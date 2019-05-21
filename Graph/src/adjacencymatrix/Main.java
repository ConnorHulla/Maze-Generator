
package adjacencymatrix;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AdjacencyMatrix test = new AdjacencyMatrix(10, 10);
        
        test.connect(2, 3);
        test.connect(5, 3);
        test.connect(8, 9);
        test.connect(5, 4);
        test.connect(3, 1);
        test.connect(1, 1);
        test.connect(0, 2);
        
        
        
        System.out.println(test.toString());
        
    }
    
}
