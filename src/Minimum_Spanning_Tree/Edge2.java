package Minimum_Spanning_Tree;
public class Edge2 implements Comparable<Edge2> {
    public int weight;
    public String nodeV, nodeU;

    public Edge2(int weight, String nodeV, String nodeU){
        this.weight = weight;
        this.nodeV = nodeV;
        this.nodeU = nodeU;
    }

    public String toString(){
        return "(" + this.weight + ", " + this.nodeV + ", " + this.nodeU +")";
    }
    @Override
    public int compareTo(Edge2 edge) {
        return this.weight - edge.weight;
    }
}
