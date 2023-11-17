/*
 * 프림 알고리즘
 * - 대표적인 최소 신장 트리 알고리즘
 * - 시작 정점을 선택한 후, 정점에 인접한 간선 중 최소 간선으로 연결된 정점 선택,
 *    해당 정점에서 다시 최소 간선으로 연결된 정점을 선택하는 방식으로
 *    최소 신장트리를 확장해 가는 방식
 * */
/*
 * 프림 알고리즘 순서
 * 1. 정점 하나 선택(정점만들고)
 * 2. 해당 정점에서 연결된 간선 중에 작은 weight 부터 선정
 * 3. 연결된 정점들이 가진 엣지 중에서 가장 웨이트 적은 녀석을 추출 나가면서 mst 선정
 * 4. 엣지 선택할 때 사이클이 성립되면 안됨
 * */

import java.util.*;

public class Prim {
    /*
     * 프림 알고리즘 코드
     * 1. 모든 간선 정보를 저장(adjacentEdges)
     * 2. 임의의 정점을 선택, 연결된 노드 집합(connectedNodes)에 삽입
     * 3. 선택된 정점에 연결된 간선들을 간선 리스트(candidateEdgeList)에 삽입
     * 4. 간선 리스트에서 최소 가중치를 가지는 간선부터 추출
     *    if 해당 간선에 연결된 인접 정점이 있으면 스킵 : 사이클 발생을 막기 위해
     *   else 해당 간선 선택하고, 해당 간선 정보를 최소신장트리(mst)에 삽입
     *   4-1) 선택받지 못한 정점들의 간선들만 간선리스트에 삽입
     *        => connectedNodes 에 없는 노드의 간선들만 candidateEdgeList애 삽입
     *   because)
     *   - connectedNodes에 있는 노드의 간선들을 삽입해도 skip 되기 때문
     *   - skip 될 간선들을 넣지 않으므로 effot 줄일 수 있음
     * 5. 4에서 추출된 간선은 candidateEdgeList에서 삭제
     * 6. candidateEdgeList에 간선이 없을 때 까지 반복
     * */

    // 시작노드(startNode)와, 전체간선리스트(edges)를 매개변수로 가지고, 최소신장트리(mst)배열 을 리턴하는 메서드 구현
    public ArrayList<Edge3> primFunc(String startNode, ArrayList<Edge3> edges) {
        // 연결된 노드의 집합
        ArrayList<String> connectedNodes = new ArrayList<String>();
        // 최종 최소신장트리
        ArrayList<Edge3> mst = new ArrayList<Edge3>();
        // 간선에 연결된 노드들
        // HashMap<노드, 노드에 연결된 간선 리스트>
        HashMap<String, ArrayList<Edge3>> adjacentEdges = new HashMap<String, ArrayList<Edge3>>();
        // 각각의 간선정보를 담을 currentEdge
        Edge3 currentEdge;
        // currentEdge의 key값에 연될된 간선 데이터를 추가하기위한 currentEdgeList
        ArrayList<Edge3> currentEdgeList;
        // 선택된 노드에 연결된 모든 간선을 저장할 배열 candidateEdgeList
        ArrayList<Edge3> candidateEdgeList;
        // 간선 리스트에서 최소 가중치를 가지는 간선부터 추출하기 위한 우선순위 큐 PriorityQueue
        PriorityQueue<Edge3> priorityQueue;
        // 우선순위 큐에서 하나씩 꺼내쓰기 위한 변수
        Edge3 poppedEdge;
        // 꺼낸 간선에 연결된 간선 정보를 추가하기 위한 배열 adjacentEdgeNodes
        ArrayList<Edge3> adjacentEdgeNodes;
        // 꺼낸 간선에 연결된 간선 정보을 저장하기 위한 adjacentEdgeNode
        Edge3 adjacentEdgeNode;

        // 초기화
        for (int index = 0; index < edges.size(); index++) {
            currentEdge = edges.get(index);
            // 전체 간선리스트에 해당 간선이 있으면
            if (!adjacentEdges.containsKey(currentEdge.node1)) {
                // 해당 간선의 노드를 키로 쓴다.
                adjacentEdges.put(currentEdge.node1, new ArrayList<Edge3>());
            }
            if (!adjacentEdges.containsKey(currentEdge.node2)) {
                // 해당 간선의 노드를 키로 쓴다.
                adjacentEdges.put(currentEdge.node2, new ArrayList<Edge3>());
            }
        }

        // 각각의 노드 마다 연결된 간선들 추가
        for (int index = 0; index < edges.size(); index++) {
            currentEdge = edges.get(index);
            currentEdgeList = adjacentEdges.get(currentEdge.node1);
            // node1에 연결된 간선들 추가
            currentEdgeList.add(new Edge3(currentEdge.weight, currentEdge.node1, currentEdge.node2));
            currentEdgeList = adjacentEdges.get(currentEdge.node2);
            // node2에 연결된 간선들 추가
            currentEdgeList.add(new Edge3(currentEdge.weight, currentEdge.node2, currentEdge.node1));
        }

        // 선택된 노드를 연결
        connectedNodes.add(startNode);
        // 선택된 노드에 연결된 모든 간선 추가
        candidateEdgeList = adjacentEdges.getOrDefault(startNode, new ArrayList<Edge3>());

        priorityQueue = new PriorityQueue<Edge3>();
        // 간선 리스트에서 최소 가중치를 가지는 간선부터 추출
        for (int index = 0; index < candidateEdgeList.size(); index++) {
            priorityQueue.add(candidateEdgeList.get(index));
        }

        while (priorityQueue.size() > 0) {
            poppedEdge = priorityQueue.poll();

            // 이미 연결된 노드인지 확인
            if (!connectedNodes.contains(poppedEdge.node2)) {
                // 해당 edge를 mst에 추가
                connectedNodes.add(poppedEdge.node2);
                mst.add(new Edge3(poppedEdge.weight, poppedEdge.node1, poppedEdge.node2));

                adjacentEdgeNodes = adjacentEdges.getOrDefault(poppedEdge.node2, new ArrayList<Edge3>());

                for (int index = 0; index < adjacentEdgeNodes.size(); index++) {
                    adjacentEdgeNode = adjacentEdgeNodes.get(index);
                    // 시간 복잡도를 줄이기 위해 의미없는 데이터를 미리 빼기 위함
                    // 의미없는 것 : 사이클이 생기는 것들
                    if (!connectedNodes.contains(adjacentEdgeNode.node2)) {
                        priorityQueue.add(adjacentEdgeNode);
                    }
                }
            }
        }
        // 최종 최소신장트리 리턴
        return mst;

    }

    // 개선된 프림 알고리즘
    // edge 기준이 나닌 vertex 기준
    public ArrayList<Path> enhancePrimFunc(HashMap<String, HashMap<String, Integer>> graph, String startNode){
        ArrayList<Path> mst = new ArrayList<Path>();
        PriorityQueue<Edge4> keys = new PriorityQueue<Edge4>();
        Edge4 edgeObject;
        HashMap<String, String> mstPath = new HashMap<String, String>();
        HashMap<String, Edge4> keysObjects = new HashMap<String, Edge4>();
        Integer totalWeight = 0;
        HashMap<String, Integer>    linkedEdges;
        Edge4 poppedEdge;
        Edge4 linkedEdge;

        for(String key : graph.keySet()){
            if(key == startNode){
                edgeObject = new Edge4(key, 0);
                mstPath.put(key, key);
            }else {
                edgeObject = new Edge4(key, Integer.MAX_VALUE);
                mstPath.put(key, null);
            }
            keys.add(edgeObject);
            keysObjects.put(key, edgeObject);
        }

        while(keys.size() > 0){
            poppedEdge = keys.poll();
            keysObjects.remove(poppedEdge.node);

            mst.add(new Path(mstPath.get(poppedEdge.node), poppedEdge.node, poppedEdge.weight));
            totalWeight += poppedEdge.weight;

            linkedEdges = graph.get(poppedEdge.node);
            for(String adjacent : linkedEdges.keySet()){
                if(keysObjects.containsKey(adjacent)){
                    linkedEdge = keysObjects.get(adjacent);
                    if(linkedEdges.get(adjacent) < linkedEdge.weight){
                        linkedEdge.weight = linkedEdges.get(adjacent);

                        keys.remove(linkedEdge);
                        keys.add(linkedEdge);
                    }
                }
            }
        }
        System.out.println(totalWeight);
        return mst;
    }

    public static void main(String[] args) {
        PriorityQueue<Edge3> priorityQueue = new PriorityQueue<Edge3>();
        priorityQueue.add(new Edge3(2, "A", "B"));
        priorityQueue.add(new Edge3(5, "B", "D"));
        priorityQueue.add(new Edge3(3, "C", "A"));

        while (priorityQueue.size() > 0) {
            System.out.println(priorityQueue.poll());
        }
        System.out.println("------------------------------------------------------------");

        // HashMap에 특정 키 존재 여부 확인
        // containKey() 메서드
        HashMap<String, ArrayList<Edge3>> graph = new HashMap<String, ArrayList<Edge3>>();
        graph.put("A", new ArrayList<Edge3>());
        graph.put("B", new ArrayList<Edge3>());

        System.out.println(graph.containsKey("B"));

        // 찾는 키(key)에 대한 값(value)가 없을 때 타입에 맞는 특정 디폴트 값 반환하기
        // getOrDefault() 메서드
        System.out.println(graph.getOrDefault("C", new ArrayList<Edge3>()));

        System.out.println("------------------------------------------------------------");
        ArrayList<Edge3> myedges = new ArrayList<Edge3>();
        myedges.add(new Edge3(7, "A", "B"));
        myedges.add(new Edge3(5, "A", "D"));
        myedges.add(new Edge3(8, "B", "C"));
        myedges.add(new Edge3(9, "B", "D"));
        myedges.add(new Edge3(7, "D", "E"));
        myedges.add(new Edge3(5, "C", "E"));
        myedges.add(new Edge3(7, "B", "E"));
        myedges.add(new Edge3(6, "D", "F"));
        myedges.add(new Edge3(8, "E", "F"));
        myedges.add(new Edge3(9, "E", "G"));
        myedges.add(new Edge3(11, "F", "G"));
        System.out.println("그래프의 간선들 : " + myedges);

        Prim prim = new Prim();
        System.out.println(prim.primFunc("A", myedges));

        System.out.println("------------------------------------------------------------개선된 프림 알고리즘");
        HashMap<String, HashMap<String, Integer>> mygraph = new  HashMap<String, HashMap<String, Integer>>();
        HashMap<String, Integer> edges;
        edges = new HashMap<String, Integer>();
        edges.put("B", 7);
        edges.put("D", 5);
        mygraph.put("A", edges);
        edges = new HashMap<String, Integer>();
        edges.put("A", 7);
        edges.put("D", 9);
        edges.put("C", 8);
        edges.put("E", 7);
        mygraph.put("B", edges);
        edges = new HashMap<String, Integer>();
        edges.put("B", 8);
        edges.put("E", 5);
        mygraph.put("C", edges);
        edges = new HashMap<String, Integer>();
        edges.put("A", 5);
        edges.put("B", 9);
        edges.put("E", 7);
        edges.put("F", 6);
        mygraph.put("D", edges);
        edges = new HashMap<String, Integer>();
        edges.put("B", 7);
        edges.put("C", 5);
        edges.put("D", 7);
        edges.put("F", 8);
        edges.put("G", 9);
        mygraph.put("E", edges);
        edges = new HashMap<String, Integer>();
        edges.put("D", 6);
        edges.put("E", 8);
        edges.put("G", 11);
        mygraph.put("F", edges);
        edges = new HashMap<String, Integer>();
        edges.put("E", 9);
        edges.put("F", 11);
        mygraph.put("G", edges);

        System.out.println("그래프의 간선들 : " + mygraph);
        System.out.println(prim.enhancePrimFunc(mygraph, "A"));
    }
}
