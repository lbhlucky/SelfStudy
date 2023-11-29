package GraphAndSearch;

import java.io.*;
import java.util.*;

public class DfsAndBfsByList {
    static DfsAndBfsByMatrix.FastReader scan = new DfsAndBfsByMatrix.FastReader();
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

    // DFS 와 BFS
    // 난이도 2
    // 1 <= 정점 개수 N < 1,000
    // 1 <= 간선 개수 M <= 10,000

    // Q. 그래프를 DFS로 탐색한 결과와 BFS로 탐색한결과를 출력하는 프로그램을 작성하시오.
    //    단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고,
    //    더 이상 방문할 수 있는 점이 없는 경우 종료한다.
    //    정점 번호는 1번부터 N번까지이다.

    // 접근 - 유일한 차이
    // => 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고,
    // 인접행렬 : 차례대로 가다가 1이 나오는 것 부터
    // - 시간 : O(V^2)
    // - 공간 : O(V^2)
    // 인접리스트 : 입력순서대로 저장하면 작은번호부터 보기 위해 많은 시간이 필요하지만,
    // 만약 초기에 정렬 해놓는다면? O(dex(x)log(deg(x)))
    // - 시간 : O(E log E)
    // - 공간 : O(E)

    static int N, M, V;
    static ArrayList<Integer>[] adj;
    static boolean[] visit;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        V = scan.nextInt();
        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N ;i++){
            adj[i] = new ArrayList<Integer>();
        }
        for(int i = 1 ; i <= M ; i++){
            int x = scan.nextInt(), y = scan.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }
        for (int i = 1 ; i <= N ; i++){
            Collections.sort(adj[i]);
        }
        visit = new boolean[N + 1];

    }

    // x 를 갈 수 있다는 걸 알고 방문한 상태
    static void dfs(int x) {
        visit[x] = true;
        sb.append(x).append(' ');
        for(int y : adj[x]){
            if(visit[y]){
                continue;
            }else{
                dfs(y);
            }
        }
    }

    // start 에서 시작해서 갈 수 있는 정점들을 모두 탐색하기
    static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();

        que.add(start);
        visit[start] = true;

        while(!que.isEmpty()){
            start = que.poll();
            sb.append(start).append(' ');
            for(int y : adj[start]){
                if (visit[y]) {
                    continue;
                } else {
                    que.add(y);
                    visit[y]=true;
                }
            }
        }
    }

    static void pro() {
        // 모든 x에 대해서 adj[x] 정렬하기
        visit = new boolean[N+1];

        // DFS, BFS 결과 구하기
        dfs(V);
        sb.append("\n");
        for(int i = 1 ; i <= N;i++){
            visit[i] = false;
        }
        bfs(V);

        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
