
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Edge implements Comparable<Edge> {
    public Integer distance;
    public String vertex;

    public Edge (Integer distance, String vertex){
        this.distance = distance;
        this.vertex = vertex;
    }

    /*
     * Comparable 과 Comparator 인터페이스
     * - Comparable 와 Comparator는 둘 다 인터페이스로, 정렬 기준을 구현하기 위해 사용
     *
     * Comparable 인터페이스
     * - compareTo() 메서드를 override 해서 구현
     * - 일반적으로는 정렬할 객체에 implements 로 Comparable 인터페이스를 추가하여 구현
     *
     * Comparator 인터페이스
     * - compare() 메서드를 override 해서 구현
     * - 일반적으로는 별도 클래스를 정의해서 구현하며, 동일 객체에 다양한 정렬 기준을 가진 클래스 작성 가능
     * */
    @Override
    public int compareTo(Edge e) {
        // 빼는 순서에 따라 오름/내림차순 정의 가능
        return this.distance - e.distance;
    }

    public static void main(String[] args) {
        Edge edge1 = new Edge(12,"A");
        Edge edge2 = new Edge(10,"A");
        Edge edge3 = new Edge(13,"A");

        Edge[] edges = new Edge[]{edge1, edge2, edge3};

//        Arrays.sort(edges);
//        for(int i = 0 ; i < edges.length ; i++){
//            System.out.println(edges[i].distance);
//        }
        /*
         * Arrays.sort() 와 Comparator
         * - Arrays.sort() 메서드는 인자를 두 개 받아서, 두번째 인자에 Comparator 클래스를 넣어줄 수도 있음
         *   - Edge 객체에 Comparable 인터페이스가 정의되어 있더라도, Comparator 클래스의 정렬 기준으로 정렬이 됨
         * */
        Arrays.sort(edges, new Comparator<Edge>(){

            @Override
            public int compare(Edge o1, Edge o2) {
                // 내림차순
                return o2.distance - o1.distance;
                // 오름차순
//                return o1.distance - o2.distance;
            }
        });



        for(int i = 0 ; i < edges.length ; i++){
            System.out.println(edges[i].distance);
        }
    }


}
