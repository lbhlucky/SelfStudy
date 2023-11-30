package Tree;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FindTreeParent {
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

    // 트리의 부모 찾기
    // 난이도 2
    // 1 <= 정점 개수 N <= 100,000

    // Q. 루트 없는 트리가 주어진다. 이떄, 트리의 루트를 1이라고 정했을 떄,
    //    각 노드의 부모를 구하는 프로그램을 작성하시오.

    // 접근 - Non-Rooted Tree -> Rooted Tree
    // 1. 인접 리스트로 저장하기
    // 2. Root 말고는 아무것도 정답을 구하지 못한 상태로 시작
    // 3. 정점 X가 Parent를 안다면, 자신의 자식 Child를 찾을 수 있다.
    //    - 어떻게? 연결된 것들 중 Parent 를 제외한 모든 것들
    // 4. Root부터 차례대로 문제를 해결 해 보자

    // 시간, 공간 복잡도 계산하기
    // "Root"를 시작으로 하는 그래프 탐색 문제
    // 탐색 알고리즘 : BFS or DFS
    // => 인접리스트를 쓴다면 O(V+E)
    // DFS가 매우 쉽게 구현할 수 있다!




    static int n;
    static ArrayList<Integer>[] adj;
    static int[] parent;

    static void input() {
        n = scan.nextInt();

        // 인접 리스트 구성하기
        adj = new ArrayList[n+1];
        parent = new int[n+1];
        for(int i = 1 ; i <= n ; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 1 ; i < n ; i++){
            int x = scan.nextInt();
            int y = scan.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }
    }

    // dfs(x, par) := 정점 x 의 부모가 par 였고, x의 children들을 찾아주는 함수
    static void dfs(int x, int par) {
        for(int y : adj[x]){
            if(y == par){
                continue;
            } else {
                parent[y] = x;
                dfs(y, x);
            }
        }
    }

    static void pro() {
        // 1 번 정점이 ROOT 이므로, 여기서 시작해서 Tree의 구조를 파악하자.
        // -1 : 부모가 없다
        dfs(1, -1);

        // 정답 출력하기
        for(int i = 2 ; i <= n ; i++){
            sb.append(parent[i]).append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

}
