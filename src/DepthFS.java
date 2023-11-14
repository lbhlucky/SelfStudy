import java.util.*;

public class DepthFS {
    public ArrayList<String> dfsFunc(HashMap<String, ArrayList<String>> graph, String startNode){
        // Queue 구조를 가진 visited
        // Stack  구조를 가진 needVisit 선언
        ArrayList<String> visited = new ArrayList<String>();
        ArrayList<String> needVisit = new ArrayList<String>();

        needVisit.add(startNode);

        while(needVisit.size() > 0){
            // 스택은 Last-In, First-Out 이므로
            // 제일 마지막 인덱스를 꺼낸 후 삭제
            String node = needVisit.remove(needVisit.size()-1);
            if(!visited.contains(node)){
                visited.add(node);
                needVisit.addAll(graph.get(node));
            }
        }
        return visited;
    }
    public static void main(String[] args) {
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

        System.out.println("graph : \n"+graph);
        System.out.println("---------------------------------------------");

        // 스택 구현
        ArrayList<String> stack = new ArrayList<String>();
        stack.add("A");
        stack.add("B");

        // 제일 마지막 인덱스를 꺼내오기 위함
        String node = stack.remove(stack.size()-1);

        System.out.println(stack);
        System.out.println(node);
        System.out.println("---------------------------------------------");

        DepthFS dfs = new DepthFS();
        System.out.println(dfs.dfsFunc(graph, "A"));

    }
}
