import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Graph
{
    boolean directed = true;
    int nVertices;
    int nEdges;
    boolean [ ][ ] adj; // Adjacency matrix
    Map<String, Integer> translationMap;
    Map<String, List<String>> outputMap;
    String [ ] vertexToString;
    
    String vertexName(int v) { return vertexToString[v];}
    
    public Graph(File file) throws IOException
    {
        FileReader fr= new FileReader(file);
        Scanner sc = new Scanner(fr);
        this.outputMap = new TreeMap<>();
        String graphType = sc.next();
        directed = "directed".compareToIgnoreCase(graphType) == 0;
        
        nVertices = sc.nextInt();
        nEdges = sc.nextInt();
        
        adj = new boolean[nVertices][nVertices];
        vertexToString = new String[nVertices];
        translationMap = new HashMap<>();
        
        // Read all the edge pairs and assign numbers to each vertex
        int vertexCounter = 0;
        for (int k = 0; k < nEdges; k++)
        {
            String uName = sc.next();
            String vName = sc.next();
            Integer u, v;
            u = translationMap.get(uName);
            if (u == null) 
            { 
                u = vertexCounter++; 
                vertexToString[u] = uName;
                translationMap.put(uName, u);
            }
            v = translationMap.get(vName);
            if (v == null) 
            { 
                v = vertexCounter ++; 
                vertexToString[v] = vName;
                translationMap.put(vName, v);
            }            
            adj[u][v] = true;     
            if (!directed) { adj[v][u] = true; }
        } 
        // Initialize output map
        for (int u = 0; u < adj.length; u++)
        {
            for (int v = 0; v < adj.length; v++)
            {
                if (adj[u][v]) 
                {
                    if (!outputMap.containsKey(vertexName(u))) 
                    { 
                        outputMap.put(vertexName(u), new LinkedList<>());                        
                    }                        
                    outputMap.get(vertexName(u)).add(vertexName(v));                   
                }
            }
        }
    } 
    
    @Override
    public String toString()
    {        
        StringBuilder builder = new StringBuilder();
        for (String vert : outputMap.keySet())
        {
            builder.append(String.format("%s : %s\n", vert, outputMap.get(vert)));
        }
        return builder.toString();
    }
}
