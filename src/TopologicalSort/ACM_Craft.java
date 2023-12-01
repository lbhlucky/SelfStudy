package TopologicalSort;

import java.io.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ACM_Craft {
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

    // ACM Craft
    // 난이도 3
    // 1 <= 건물의 개수 N <= 1,000
    // 1 <= 건설 규칙 <= 100,000
    // 1 <= 건물 건설 시간 <= 100,000

    // Q. 모든 건물을 지을 수 있는 최소 시간 구하기

    // 접근 - 정답의 최대치
    // 가장 오랜 시간이 걸리려면?
    // 건물 개수 X 건설 시간
    // 10^3 * 10^5 = 10^8
    // => Integer 범위

    // 접근 - 각 건물을 완성하는 시간은?
    // 전제 조건 : T_done[X]를 계산하기 위해서는
    //            X의 선행 작업들에 대해서 T_done이 먼저 계산되어야한다.
    //            => T_done 배열을 위상 정렬 순서로 계산하면 된다.
    //T_done[X] = max(T_done[X의 선행작업]) + T[X]

    // 시간, 공간 복잡도 계산하기
    // 위상 정렬이므로 O(V+E)

    static int N, M;
    static int[] indeg, T_done, T;
    static ArrayList<Integer>[] adj;

    static void input() {
        // Testcase 가 존재하는 문제이므로 "배열 초기화"에 유의하자
       N = scan.nextInt();
       M = scan.nextInt();
       adj = new ArrayList[N+1];
       indeg = new int[N+1];
       T = new int[N+1];
       T_done = new int[N+1];

       for(int i = 1 ; i <= N ; i++){
           adj[i] = new ArrayList<>();
           T[i] = scan.nextInt();
       }
       for(int i = 0; i < M ; i++){
           int x = scan.nextInt();
           int y = scan.nextInt();
           adj[x].add(y);
           // indegree 계산하기
           indeg[y]++;
       }
    }

    static void pro() {
        Deque<Integer> queue = new LinkedList<>();
        // 제일 앞에 "정렬될 수 있는" 정점 찾기
        for(int i = 1; i <= N ; i++){
            if(indeg[i] == 0){
                queue.add(i);
                T_done[i] = T[i];
            }
        }

        // 위상 정렬 순서대로 T_done 계산을 함께 해주기
        while(!queue.isEmpty()){
            int x = queue.poll();
            sb.append(x).append(' ');
            for(int y : adj[x]){
                indeg[y]--;
                if(indeg[y] == 0){
                    queue.add(y);
                }
                T_done[y] = Math.max(T_done[y], T_done[x] + T[y]);
            }
        }
        int W = scan.nextInt();
        System.out.println(T_done[W]);
    }

    public static void main(String[] args) {
        int Q = scan.nextInt();
        while (Q > 0) {
            Q--;
            input();
            pro();
        }
    }

}
