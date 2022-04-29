
import java.util.LinkedList;

public class GraphIsomorphism
{   
    static boolean extend(LinkedList<Integer> p, boolean [ ][ ]g, boolean [ ][ ]h )
    {
        
        if(p.size()==g.length){return true;}

        for (int i = 0; i <g.length;i++){
            if(compatible(p,i,g,h)){
                p.add(i);
                boolean success = extend(p,g,h);
                if(success){return true;}
                p.removeLast();
            }
            
        }
        return false;
    }

    static boolean compatible(LinkedList<Integer> p, int v, boolean [][]g, boolean [][]h)
    {  
        if (p.contains(v)){return false;}
    
        for(int i = 0; i < p.size();i++){
			
            if(g[p.size()][i] != h[v][p.get(i)]) {return false;}

            if(g[i][p.size()] != h[p.get(i)][v]) {return false;}
			
        }

        return true;
    }
}


















