package Minimum_Spanning_Tree;
public class Edge4 implements Comparable<Edge4> {
    public String node;
    public int weight;

    public Edge4(String node, int weight){
        this.node = node;
        this.weight = weight;
    }

    public String toString(){
        return "("+this.node+", "+this.weight+")";
    }
    @Override
    public int compareTo(Edge4 edge) {
        return this.weight - edge.weight;
    }
}
