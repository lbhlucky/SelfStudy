package Dijkstra;
import java.util.*;

public class Dijkstra {
    /*
     * 최단 경로 문제
     * - 가중치 그래프(weight graph)에서 Edge 의 가중치 합이 최소가 되도록하는 경로
     *   => 두 노드를 잇는 가장 짧은 경로를 찾는 것
     *
     * 최단 경로 문제 종류
     * 1. 단일 출발(Single-source) 최단 경로 문제
     *    - 그래프 내의 특정 노드에서 출발하여, 그래프 내의 모든 다른 노드에 도착하는 가장 빠른 경로를 찾는 문제
     * 2. 단일 도착(Single-destination) 최단 경로 문제
     *    - 모든 노드들로부터 출발해서, 그래프 내의 특정 노드로 도착하는 가장 짧은 경로를 찾는 문제
     * 3. 단일 쌍(Single-pair) 최단 경로 문제
     *    - 주어진 두 개의 노드간의 최단 경로를 찾는 문제 ( 최단 경로 하나만 나옴 )
     * 4. 전체 쌍(all-pair) 최단 경로 문제
     *    - 그래프 내의 모든 노드 쌍 사이에 댛란 최단 경로를 찾는 문제
     *
     * 다익스트라 알고리즘
     * - 단일 출발(Single-source)에 해당
     * - 하나의 노드에서 다른 모든 노드에 도착하는 최단 경로를 구하는 문제
     *
     * 다익스트라 알고리즘 로직
     * - BFS와 유사함
     * */
    public static class Edge implements Comparable<Edge> {
        public int distance;
        public String vertex;

        // 생성자
        public Edge(int distance, String vertex){
            this.distance = distance;
            this.vertex = vertex;
        }

        // System.out.println() 으로 객체 자체 출력시,
        public String toString(){
            return "vertex : "+this.vertex +", distance : "+this.distance;
        }

        // 정렬 기준 정의
        @Override
        public int compareTo(Edge edge){
            return this.distance - edge.distance;
        }
    }

    // 1단계 구현 초기화
    // - 첫 정점을 기준으로 배열을 선언하여, 첫 정점에서 각 정점까지의 거리를 저장
    //   초기에 첫 정점의 거리는 0, 나머지는 무한대(inf)로 저장함
    public HashMap<String, Integer> dijikstarFunc(HashMap<String, ArrayList<Edge>> graph, String start){
//        Edge edgeNode, adjacentNode;
//        ArrayList<Edge> nodeList;
//        int currDistance, weight, distance;
//        String currNode, adjacent;

        HashMap<String, Integer> distances = new HashMap<String, Integer>();
        for(String key : graph.keySet()){
            // Integer.MAX_VALUE : 최댓값
            distances.put(key, Integer.MAX_VALUE);
        }
        // 시작 노드 업데이트
        distances.put(start, 0);

        // 우선순위 큐 생성
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>();
        // 첫 시작 노드 추가
        priorityQueue.add(new Edge(distances.get(start), start));

        // 알고리즘 작성
        while(priorityQueue.size() > 0){    // 우선순위 큐에 더이상 검토할 노드가 없으면 종료
           Edge edgeNode = priorityQueue.poll();
           int currDistance =edgeNode.distance;
           String currNode = edgeNode.vertex;

            // 최소 거리를 가진 노드를 뽑아서
            if(distances.get(currNode) < currDistance){// 현재 가중치가 현재 노드가 가지고 있는 가중치보다 크면 스킵
                // 계산할 필요가 없으면 무시
                continue;
            }

            // 계산할 필요가 있으면
            ArrayList<Edge> nodeList = graph.get(currNode);
            for(int i = 0 ; i < nodeList.size() ; i++){
                // 연결된 노드들의 weight 정보
                Edge adjacentNode = nodeList.get(i);
                String adjacent = adjacentNode.vertex;
                int weight = adjacentNode.distance;
                int distance = currDistance + weight;

                // 지금 최단 거리를 가지고 있는 배열 값과 비교
                // 더 짧은 최단거리를 가지고 있으면
                if(distance < distances.get(adjacent)){
                    // 해당 배열 업데이트
                    distances.put(adjacent, distance);
                    // 해당 정보 큐에 추가
                    priorityQueue.add(new Edge(distance, adjacent));
                }
            }

        }
        // 각각의 노드에 접근하는 최단 거리 값을 가진 값들이 들어있는 배열 리턴
        return distances;

    }

    public static void main(String[] args) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>();
        priorityQueue.add(new Edge(2, "A"));
        priorityQueue.add(new Edge(5, "B"));
        priorityQueue.offer(new Edge(1, "C"));
        priorityQueue.offer(new Edge(7, "D"));

        // priorityQueue 전체 출력
        System.out.println("전체 데이터 : "+priorityQueue);
        // peek() : 맨 앞에 있는 데이터 확인
        System.out.println("맨 앞에 있는 데이터 peek() : "+priorityQueue.peek());
        // poll() : 맨 앞에 있는 데이터 꺼내면서 삭제
        Edge edge1 = priorityQueue.poll();
        System.out.println("맨 앞에 있는 데이터 poll() : "+edge1);
        Edge edge2 = priorityQueue.remove();
        System.out.println("맨 앞에 있는 데이터 remove() : "+edge2);
        System.out.println("맨 앞에 있는 데이터 삭제 후 전체 데이터 : "+priorityQueue);
        System.out.println("전체 데이터  총 몇개 : "+priorityQueue.size());

        System.out.println("===================================================================================");

        // 그래프 생성
        HashMap<String, ArrayList<Edge>> graph = new HashMap<String, ArrayList<Edge>>();
        graph.put("A", new ArrayList<Edge>(Arrays.asList(new Edge(8, "B"), new Edge(1, "C"), new Edge(2, "D"))));
        graph.put("B", new ArrayList<Edge>());
        graph.put("C", new ArrayList<Edge>(Arrays.asList(new Edge(5, "B"), new Edge(2, "D"))));
        graph.put("D", new ArrayList<Edge>(Arrays.asList(new Edge(3, "E"), new Edge(5, "F"))));
        graph.put("E", new ArrayList<Edge>(Arrays.asList(new Edge(1, "F"))));
        graph.put("F", new ArrayList<Edge>(Arrays.asList(new Edge(5, "A"))));

        // HashMap에 들어 있는 모든 Key 가져오기
        for(String key : graph.keySet()){
            System.out.println(key);
            System.out.println(graph.get(key));
        }

        // 각 key 에 해당하는 값 추출
        // => ArrayList의 모든 Edge 객체 추출학
        ArrayList<Edge> nodeList = graph.get("A");
        for (int index = 0; index < nodeList.size(); index++) {
            System.out.println(nodeList.get(index));
        }

        System.out.println("===================================================================================");
        System.out.println("Dijkstra Algorithm 구현");

        Dijkstra dijkstra = new Dijkstra();
        System.out.println(dijkstra.dijikstarFunc(graph, "A"));


    }
}
