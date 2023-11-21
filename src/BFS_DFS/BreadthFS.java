package BFS_DFS;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BreadthFS {

    // BFS 알고리즘 구현
    // 1. 자료구조 중 큐(Queue) 활용
    //    - needVisit, visited 두 개의 큐 생성
    public ArrayList<String> bfsFunc(HashMap<String, ArrayList<String>> graph, String startNode){
        // Queue 두개 생성
        ArrayList<String> visited = new ArrayList<String>();
        ArrayList<String> needVisit = new ArrayList<String>();

        // needVisit에 시작 노드 추가
        needVisit.add(startNode);

        // 방문해야할 노드가 있으면 반복
        while(needVisit.size() > 0){
            // 제일 처음 방문할 노드 지정
            String node = needVisit.remove(0);

            // 해당 노드 방문 여부
            if(!visited.contains(node)){ // 방문하지 않았으면
                // 해당 노드를 방문해라
                visited.add(node);
                // 해당 노드의 연결된 값을 추가
                needVisit.addAll(graph.get(node));
            }
        }
        // 방문한 순서 기록 리턴
        return visited;
    }
    public static void main(String[] args) {
        // 기본 선언 HashMap<키타입, 값타입> 변수 = new HashMap<키타입, 값타입>();
        HashMap<String, Integer> mapData1 = new HashMap<String, Integer>();
        // 다른 hashMap 활용해서 선언
        // HashMap<String, Integer> mapData2 = new HashMap<String, Integer>(mapData1);
        // 초기에 일정 크기로 제한해서 선언
        // HashMap<String, Integer> mapData3 = new HashMap<String, Integer>(10);
        // ArrayList 타입도 가능
        // HashMap<String, ArrayList<String>> mapData4 = new HashMap<String, ArrayList<String>>();

        // HashMap 데이터 추가 : put()
        mapData1.put("A", 1);
        mapData1.put("B", 2);

        // 전체 HashMap 읽기
        System.out.println(mapData1);
        // 특정 key 값으로 읽기
        System.out.println(mapData1.get("A"));

        // HashMap 데이터 수정
        mapData1.put("B", 3);

        // HashMap 데이터 삭제 : remove(키값)
        mapData1.remove("A");
        // 삭제 여부 확인
        System.out.println(mapData1);

        System.out.println("=====================================================");
        HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();

        graph.put("A", new ArrayList<String>(Arrays.asList("B", "C")));
        graph.put("B", new ArrayList<String>(Arrays.asList("A", "D")));
        graph.put("C", new ArrayList<String>(Arrays.asList("A", "G", "H", "I")));
        graph.put("D", new ArrayList<String>(Arrays.asList("B", "E", "F")));
        graph.put("E", new ArrayList<String>(Arrays.asList("D")));
        graph.put("F", new ArrayList<String>(Arrays.asList("D")));
        graph.put("G", new ArrayList<String>(Arrays.asList("C")));
        graph.put("H", new ArrayList<String>(Arrays.asList("C")));
        graph.put("I", new ArrayList<String>(Arrays.asList("C", "J")));
        graph.put("J", new ArrayList<String>(Arrays.asList("I")));

        System.out.println("그래프 출력 : "+graph);

        System.out.println("=====================================================");

        // ArrayList 활용해서 Queue 구현
        ArrayList<String> aList = new ArrayList<String>();
        aList.add("A");
        aList.add("B");

        String node = aList.remove(0);
        System.out.println("aList : "+aList);
        System.out.println("node : "+node);
        System.out.println("배열에 'A' 있니? : "+aList.contains("A"));
        System.out.println("=====================================================");

        ArrayList<String> list = new ArrayList<String>();
        list.add("C");
        list.addAll(graph.get("A"));
        System.out.println("list 출력 : "+list);
        System.out.println("=====================================================");

        BreadthFS bfs = new BreadthFS();
        System.out.println("BFS 결과 : "+bfs.bfsFunc(graph, "A"));

    }
}
