package DynamicProgramming;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TreeAndQuery {
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
    
    // 트리와 쿼리
    // 난이도 2
    // 1 <= 정점 개수 N <= 100,000

    // Rooted Tree에서 정점이 주어질 때마다, 정점에 대한 subtree에 속한 정점의 개수를 계산하라.

    // 작은 문제 -> 큰 문제
    // DP로 문제를 해결하기 가장 좋은 예시 : Rooted Tree 문제

    // 접근 - DP
    // 1) 풀고 싶은 문제 정의
    // Dy[i] := i를 root로 하는 subtree의 정점 개수

    // 3) 초기값은 어떻게 되는가?
    // 초기값 : "단말노드"가 제일 작은 문제이다.(정점이 한 개뿐이라서)

    // 4) 점화식 구해내기
    // Rooted Tree 이므로 부모, 자식 관계가 존재한다.
    // 자식노드에 대해 문제를  해결한다면, 부모 노드는 그걸 이요해서 문제를 풀면 된다.

    // Dy[부모노드] = ∑Dy[자식노드들] + 1

    // 시간, 공간 복잡도 계산하기
    // Rooted Tree 문제를 DP로 풀 경우 대부분  DFS 한 번에 해결한다.
    // 따라서, DFS 시간 복잠도인 O(V+E) = O(N) 만에 문제를 풀 수 있다.

    static int N, R, Q;
    static ArrayList<Integer>[] con;
    static int[] Dy;

    static void input(){
        N = scan.nextInt();
        R = scan.nextInt();
        Q = scan.nextInt();
        con = new ArrayList[N + 1];
        for(int i = 1 ; i <= N; i++){
            con[i] = new ArrayList<>();
        }
        for(int i = 1 ; i < N; i++){
            int x = scan.nextInt();
            int y = scan.nextInt();
            con[x].add(y);
            con[y].add(x);
        }
    }

    // Dy[x] 를 계산하는 함수
    static void dfs(int x, int prev){
        Dy[x] = 1;
        for (int y : con[x]){
            if(y == prev){
                continue;
            }else {
                dfs(y, x);
                Dy[x] += Dy[y];
            }
        }
    }

    static void pro() {
       Dy = new int[N + 1];

       dfs(R, -1);

       for(int i = 1 ; i <= Q; i++){
           int q = scan.nextInt();
           sb.append(Dy[q]).append('\n');
       }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
