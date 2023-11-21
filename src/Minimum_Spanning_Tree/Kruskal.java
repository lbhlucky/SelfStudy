package Minimum_Spanning_Tree;
/*
 * 최소 신장 트리 : 가능한 신장 트리 중에서 간선의 가중치 합이 최소인 신장 트리
 * 신장 트리 : 원래의 그래프의 모든 노드가 연결되어 있으면서 트리의 속성을 만족하는 그래프
 *
 * 신장 트리의 조건
 * 1. 본래의 그래프의 모든 노드를 포함해야 함
 * 2. 모든 노드가 서로 연결
 * 3. 트리의 속성을 만족시킴(사이클 존재 X)
 * */
import java.util.*;

import Minimum_Spanning_Tree.Edge2;
public class Kruskal {
    /*
     * 크루스칼 알고리즘(Kruskal algorithm)
     * 1. 모든 정점을 독립적인 집합으로 만든다.
     * 2. 모든 간선을 가중치를 기준으로 정렬하고, 가중치가 작은 간선부터 양 끝의
     *    두 정점을 비교한다.
     * 3. 두 정점의 최사우이 정점을 확인하고, 서로 다를 경우 두 정점을 연결한다.
     *    (최소 신장 트리는 사이클이 없으므로, 사이클이 생기지 않도록 하는 것)
     * */

    // Union-Find 알고리즘 메서드
    // 각각의 부모 노드를 의미하는 HashMap 선언
    HashMap<String, String> parent = new HashMap<String, String>();
    // 각 노드별 높이를 의미하는 HashMap 선언
    HashMap<String, Integer> rank = new HashMap<String, Integer>();

    public String find(String node){
        // 루트 노드 체크
        // 부모노듸의 노드가 현재 노드가 아니면
        // path compression 기법
        // -> 내 부모 노드가 루트 노드가 아니면 루트 노드를 찾아서
        //    부모노드와 맞바로 이어줘서 랭크를 낮춰주는 기능
        if(this.parent.get(node) != node){
            this.parent.put(node, this.find(this.parent.get(node)));
        }

        return this.parent.get(node);
    }

    public void union(String nodeV, String nodeU){
        String root1 = this.find(nodeV);
        String root2 = this.find(nodeU);

        // union-rank 기법
        // 1. 높이가 다르면 높이가 높은 쪽에 붙여라
        // 2. 높이가 같으면 한쪽에 +1 한 후 붙여라
        if(this.rank.get(root1) > this.rank.get(root2)){
            this.parent.put(root2, root1);
        } else {
            this.parent.put(root1, root2);
            if(this.rank.get(root1) == this.rank.get(root2)) {
                this.rank.put(root2, this.rank.get(root2) + 1);
            }
        }
    }

    public void makeSet(String node){
        this.parent.put(node, node);
        this.rank.put(node, 0);
    }

    public ArrayList<Edge2> kruskalFunc(ArrayList<String> vertices, ArrayList<Edge2> edges){
        ArrayList<Edge2> mst = new ArrayList<Edge2>();
        Edge2 currentEdge;

        // 1. 초기화
        for(int index = 0 ; index < vertices.size() ; index++){
            this.makeSet(vertices.get(index));
        }

        // 2. 간선 weight 기준으로 정렬
        Collections.sort(edges);

        for(int i = 0 ; i < edges.size() ; i++){
            currentEdge = edges.get(i);
            if(this.find(currentEdge.nodeV) != this.find(currentEdge.nodeU)) {
                this.union(currentEdge.nodeV, currentEdge.nodeU);
                mst.add(currentEdge);
            }
        }
        return mst;
    }

    public static void main(String[] args) {
        ArrayList<String> vetices = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G"));
        ArrayList<Edge2> edges = new ArrayList<Edge2>();
        edges.add(new Edge2(7, "A", "B"));
        edges.add(new Edge2(8, "B", "C"));
        edges.add(new Edge2(5, "A", "D"));
        edges.add(new Edge2(7, "B", "A"));
        edges.add(new Edge2(9, "B", "D"));
        edges.add(new Edge2(7, "B", "E"));
        edges.add(new Edge2(8, "C", "B"));
        edges.add(new Edge2(5, "C", "E"));
        edges.add(new Edge2(5, "D", "A"));
        edges.add(new Edge2(9, "D", "B"));
        edges.add(new Edge2(7, "D", "E"));
        edges.add(new Edge2(6, "D", "F"));
        edges.add(new Edge2(7, "E", "B"));
        edges.add(new Edge2(5, "E", "C"));
        edges.add(new Edge2(7, "E", "D"));
        edges.add(new Edge2(8, "E", "F"));
        edges.add(new Edge2(9, "E", "G"));
        edges.add(new Edge2(6, "F", "D"));
        edges.add(new Edge2(8, "F", "E"));
        edges.add(new Edge2(11, "F", "G"));
        edges.add(new Edge2(9, "G", "E"));
        edges.add(new Edge2(11, "G", "F"));

        System.out.println(vetices);
        System.out.println(edges);

        System.out.println("=====================================================================================================================");
        Kruskal kruskal = new Kruskal();
        System.out.println(kruskal.kruskalFunc(vetices, edges));
    }
}
