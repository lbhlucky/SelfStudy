package Dijkstra;
import java.util.*;
public class DijkstarAlgorithm {
        // 그래프 정보와, 시작점을 변수로 가지고 최단 경로 기록을 가진 해쉬맵을 리턴하는 메서드 선언
            // 최단 경로 기록을 저장할 해쉬맵 객체 생성
            // 그래프의 키값을 반복하며 첫 노드 거리는 0, 나머지는 무한대(inf)를 객체에 저장
            // 시작 노드 업데이트
            // 우선 순위 큐 객체 생성
            // 우선순위 큐에 시작노드 거리, 시작 노드 저장
            // 우선순위 큐에 검토할 노드가 있는 동안 반복
                // 우선순위 큐에 맨 앞에 데이터를 꺼내서 객체에 저장
                // 맨 앞 데이터의 거리를 저장한 변수 선언
                // 맨 앞 데이터의 노드를 저장한 변수 선언
                // 저장된 그래프 데이터의 거리가 현재 맨 앞 데이터의 거리보다 작으면 스킵
                // 저장된 그래프 데이터의 거리가 현재 맨 앞 데이터의 거리보다 클 때
                // => 현재 맨 앞 데이터의 거리가 더 짧음
                // => 더 짧은 노드를 저장할 배열 선언 후 저장
                // 저장된 배열 크기 만큼 반복
                    // 더 짧은 노드 배열에서 현재 노드를 저장하는 객체 선언
                    // 해당 객체의 노드를 저장하는 변수 선언
                    // 해당 객체의 거리를 저장하는 변수 선언
                    // 현재 맨앞에 데이터(지금 제일 짧은 거리) + 해당 객체의 거리를 저장할 변수 선언
                    // 두 거리를 합친 거리가 현재 저장된 거리보다 짧으면
                        // 현재 저장된 객체에( 해당 객체의 노드와, 거리) 저장
                        // 우선 순위 큐에 추가
            // 최단경로를 저장한 해쉬맵 리턴
    public HashMap<String, Integer> func(HashMap<String, ArrayList<Edge>> graph, String start){
        HashMap<String, Integer> distances = new HashMap<String, Integer>();

        for(String key : graph.keySet()){
            distances.put(key, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>();
        priorityQueue.add(new Edge(distances.get(start),start));

        while(priorityQueue.size() > 0){
            Edge edge = priorityQueue.poll();
            int edgeDistance = edge.distance;
            String edgeVertext = edge.vertex;

            if(distances.get(edgeVertext) < edgeDistance){
                continue;
            }

            ArrayList<Edge> nodeList = graph.get(edgeVertext);
            for(int i = 0 ; i < nodeList.size() ; i++){
                Edge currEdge = nodeList.get(i);
                String node = currEdge.vertex;
                int weight = currEdge.distance;
                int minWeight = edgeDistance+weight;

                if(distances.get(node) > minWeight){
                    distances.put(node, minWeight);
                    priorityQueue.add(new Edge(minWeight, node));
                }

            }
        }
        return distances;
    }



    public static void main(String[] args) {
        DijkstarAlgorithm da = new DijkstarAlgorithm();
        HashMap<String, ArrayList<Edge>> graph = new HashMap<String, ArrayList<Edge>>();
        graph.put("A", new ArrayList<Edge>(Arrays.asList(new Edge(8, "B"), new Edge(1, "C"), new Edge(2, "D"))));
        graph.put("B", new ArrayList<Edge>());
        graph.put("C", new ArrayList<Edge>(Arrays.asList(new Edge(5, "B"), new Edge(2, "D"))));
        graph.put("D", new ArrayList<Edge>(Arrays.asList(new Edge(3, "E"), new Edge(5, "F"))));
        graph.put("E", new ArrayList<Edge>(Arrays.asList(new Edge(1, "F"))));
        graph.put("F", new ArrayList<Edge>(Arrays.asList(new Edge(5, "A"))));
        System.out.println(da.func(graph, "A"));
    }
}
