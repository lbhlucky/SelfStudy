package GraphAndSearch;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HideAndSeek {
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

    // 숨바꼭질
    // 난이도 3
    // O <= 수빈이의 위치 N < 100,000
    // 0 <= 동생의 위치 K < 100,000

    // 내 위치의 왼쪽(-1), 오른쪽(+1), 두배(*2) 위치로 이동 가능

    // 문제 파악하기 - 정답의 최대치
    // 문제에 대한 관찰 연습 ! 언제 가장 오래 걸릴까?
    // N > K 이라면, 갈 수 있는 방법이 1 씩 감소하는 것 뿐인다.
    // 즉, N=10만, K=0인 경우가 10만초로 제일 오래 걸린다.
    // Integer 범위

    // 접근 - "최소 연산 횟수" 키워드
    // Q. 수빈이가 동생을 찾을 수 있는 "가장 빠른 시간"이 몇 초 후 인지
    // But!!!!
    // BFS를 하려고 해도, Graph가 주어지지 않아서 불가능하다!
    // 혹시 주어진 정보들을 가지고 정점과 간선을 정의할 수 있을까?

    // 접근 - Graph 창조해보기
    // < 정점 >
    // - 보통 문제에서 표현하는 "하나의 상태" 가 "하나의 정점"
    // - 이 문제는 "점의 번호"가 곧 "정점의 번호"
    // < 간선 >
    // - 이동을 의미하는 것을 간선으로 표현하기
    // - 이 문제는 각 점마다 -1, +1, *2 인 점을 향하는 "방향성 간선"
    //
    // 간선 하나를 타고 이동하는 행위 => 1초 동안 이동하는 행위
    // 가장 빨리 동생을 찾는 방법 => 최소 개수로 간선 이동하는 방법

    // 시간, 공간 복잡도 계산하기
    // <새로 만든 그래프>
    // 정점 : O(10^5)
    // 간선 : O(10^5 *3)
    // => BFS를 사용하므로 모두 O(10^6)

    static int N, K;
    static int[] dist;
    static boolean[] visit;

    static void input() {
        N = scan.nextInt();
        K = scan.nextInt();
        visit = new boolean[100005];
        dist = new int[100005];
    }

    // 숨바꼭질 시작~
    static void bfs() {
        Queue<Integer> Q = new LinkedList<>();
        Q.add(N);
        visit[N] = true;
        dist[N] = 0;

        // BFS 과정 시작
        while(!Q.isEmpty()){
            int x = Q.poll();

            if(0 <= x - 1 && !visit[x - 1]){
                visit[x - 1] = true;
                dist[x - 1] = dist[x]+1;
                Q.add(x - 1);
            }

            if(x + 1 <= 100000 && !visit[x + 1]){
                visit[x + 1] = true;
                dist[x + 1] = dist[x]+1;
                Q.add(x + 1);
            }

            if(x*2 <= 100000 && !visit[x*2]){
                visit[x*2] = true;
                dist[x*2] = dist[x]+1;
                Q.add(x*2);
            }
        }

    }

    static void pro() {
        bfs();
        System.out.println(dist[K]);
    }
    public static void main(String[] args) {
        input();
        pro();
    }
}
