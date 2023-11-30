package Tree;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Tree1068 {
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

    // 트리 : 단말 노드의 개수 개념 잡는 문제
    // 난이도 2
    // 1 <= 정점 개수 N <= 50

    // 접근 - 정답의 최대치
    // 단말 노드의 개수 <= 전체 정점의 개수 <= N
    // => Integer 범위

    // 접근 - 1. 정점 제거 방법
    // 1. 정점 x 가 지워진다
    //    => 그래프에서 정점이 사라진다?
    //    => 정점과 이어진 간선들이 모두 사라진다.
    //    => 정점 x의 부모에서 x로 가는 간선을 삭제 or 무시 하자!

    // 접근 -  2. 트리의 단말 노드 개수를 세는 방법
    // 2. 트리의 단말 노드(leaf)의 개수는 ?
    //    => 트리 := Root 노드와 연결된 정점들의 그래프
    //    => 트리의 단말 노드 := Root 노드에서 탐색할 수 있는 단말 노드의 개수
    //    => Root를 시작점으로 하는 그래프 탐색 알고리즘! BFS or DFS
    //    => 이렇게 문제를 풀어도 된다.

    // 접근 - 트리에서의 큰 문제와 작은 문제
    // Subtree : x와 그의 모든 자손을 포함하는 트리! -> x가 새로운 Root가 된다

    // 큰 문제 := 트리 안에 있는 단말 노드의 개수
    // 작은 문제 := Root의 자식 노드들의 subtree 안에 있는 단말 노드의 개수

    // <포인트>
    // 큰 문제의 정답을 작은 문제의 정답을 이용해서 구하자

    // 접근 - 3. 단말 노드의 개수를 세는 법
    // < leaf[x]를 계산하는 방법 >
    // Root 에서 DFS를 한다면?
    // 어떤 노드 X에서 자식 노드 Y에 대한 탐색을 끝내고 돌아오면
    // leaf[Y}ㅏ값이 계산되어 왔을테니, leaf[X]에 leaf[Y]를 누적해주면 된다.

    static int n, root, erased;
    static ArrayList<Integer>[] child;
    static int[] leaf;

    static void input() {
        n = scan.nextInt();
        child = new ArrayList[n];
        leaf = new int[n];
        for(int i = 0; i < n ; i++){
            child[i] = new ArrayList<>();
        }
        for(int i =0; i< n ; i++){
            int par = scan.nextInt();
            if(par == -1){
                root = i;
                continue;
            }
            child[par].add(i);
        }
        erased = scan.nextInt();
    }

    // dfs(x, par) := 정점 x 의 부모가 par 였고, Subtree(x) 의 leaf 개수를 세주는 함수
    static void dfs(int x) {
        if(child[x].isEmpty()){
            leaf[x] = 1;
        }
        for (int y : child[x]){
            dfs(y);
            // leaf[y]가 계산됨
            leaf[x] += leaf[y];
        }
    }

    static void pro() {
        // erased와 그의 부모 사이의 연결을 끊어주기
        for(int i = 0; i< n ; i++){
            if(child[i].contains(erased)){
                child[i].remove(child[i].indexOf(erased));
            }
        }

        // erased 가 root 인 예외 처리하기
        if(root != erased){
            dfs(root);
        }

        // 정답 출력하기
        System.out.println(leaf[root]);
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
