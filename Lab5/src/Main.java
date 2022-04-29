import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class Main
{
    public static void main(String[] args) throws IOException, CloneNotSupportedException
    {
        System.out.print("Перший граф:\n");

        Graph graph1 = new Graph(new File("src/graph1.txt"));

        System.out.println(graph1);
        
        System.out.print("Другий граф:\n");

        Graph graph2 = new Graph(new File("src/graph2.txt"));

        System.out.println(graph2);
        
        if (graph1.adj.length != graph2.adj.length)
        {
             System.out.println("Ці графи не є ізоморфні!");
            return;
        }
        
        // Look for an isomorphism
        LinkedList<Integer> p = new LinkedList<>();
        boolean success = GraphIsomorphism.extend(p, graph1.adj, graph2.adj);
        if (!success)
        {
            System.out.println("Ці графи не є ізоморфні!");
            return;
        }
        // Found an isomorphism
        System.out.println("Ось ізоморфізм:\n");
        for (int v = 0; v < p.size(); v++)
        {
            System.out.printf("%s -> %s\n", graph1.vertexName(v), graph2.vertexName(p.get(v)));
        }
        
    }
}
