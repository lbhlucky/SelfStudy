package TopologicalSort;

import java.io.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class LineUp {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    // 줄세우기
    // 난이도 3
    // 1 <= 학생 수 N <= 32,000
    // 1 <= 키 관계 <= 100,000

    // 접근 - Graph 만들어 보기
    // 학생들 간에 위상 관계가 주어지고, 이에 맞게 줄을 세운다.
    // Graph를 정의해보고 위상 정렬을 통해 문제를 해결해보자!
    // 정점(V) := i번 학생이 곧 i번 정점
    // 간선(E) := x번 학생이 y번 학생보다 먼저 서야한다 :: x -> y

    // 시간, 공간 복잡도 계산하기
    // Graph 를 만들고나면 위상 정렬
    // => 인접 리스트를 쓰면다면 O(V+E)

    static int N, M;
    static int[] indeg;
    static ArrayList<Integer>[] adj;

    static void input() {
        // Adjacent List 생성 및 indegree 계산하기
        N = scan.nextInt();
        M = scan.nextInt();
        adj = new ArrayList[N+1];
        indeg = new int[N+1];

        for(int i = 1 ; i <= N ; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 1 ; i <= M ; i++){
            int x = scan.nextInt();
            int y = scan.nextInt();
            adj[x].add(y);
            indeg[y]++;
        }
    }

    static void pro() {
        Deque<Integer> queue = new LinkedList<>();
        // 제일 앞에 "정렬될 수 있는" 정점 찾기
        for(int i = 1 ; i <= N ; i++){
            if(indeg[i] == 0){
                queue.add(i);
            }
        }

        // 정렬될 수 있는 정점이 있다면?
        // 1. 정렬 결과에 추가하기
        // 2. 정점과 연결된 간선 제거하기
        // 3. 새롭에 "정렬 될 수 있는" 정점
        while(!queue.isEmpty()){
            int x = queue.poll();
            sb.append(x).append(' ');
            for(int y : adj[x]){
                indeg[y]--;
                if(indeg[y] == 0){
                    queue.add(y);
                }
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
