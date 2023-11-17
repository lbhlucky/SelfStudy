// Prim 클래스에서 사용할 Edge3클래스 구현
public class Edge3 implements Comparable<Edge3>{
    public int weight;
    public String node1;
    public String node2;

    public Edge3(int weight, String node1, String node2){
        this.weight = weight;
        this.node1 = node1;
        this.node2 = node2;
    }

    public String toString(){
        return "(" + this.weight +", "+ this.node1 + ", " + this.node2+")";
    }

    @Override
    public int compareTo(Edge3 edge){
        return this.weight - edge.weight;
    }
}
