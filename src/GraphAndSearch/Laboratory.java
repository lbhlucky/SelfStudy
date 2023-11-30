package GraphAndSearch;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Laboratory {
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

    // 연구소 : 삼성 역량 테스트에 출제됨
    // 난이도 4
    // 3 <= 지도의크기 N, M <= 8
    // 2 <= 바이러스의 개수 <= 10

    // 문제 파악하기 - 정답의 최대치
    // 단지 개수 최댓값 : O(N^2)

    // 접근 - 1. 바이러스에 노출된 지역 확인
    // 먼저, 바이러스가 얼마나 퍼질 수 있는 지 확인하는 방법은 ?
    // "바이러스"에서 "갈 수 있는" 칸들을 찾는다 : 탐색!!!

    // 접근 - 2. 완전 탐색을 제일 먼저 확인해보기!
    // 세 칸을 막을 수 있는 모든 경우의 수는 얼마나 될까 ?
    // 빈 공간 B <= 64
    // 막을 수 있는 방법 = 41k
    // 중복 | 순서 |    시간 복잡도  | 공간복잡도
    // Yes  | Yes |       O(N^M)    |   O(M)
    // No   | Yes |   O(N!/(N-M)!)  |   O(M)
    // Yes  | No  | O(N^M) 보단 작음 |   O(M)
    // No   | No  |  O(N!/M!(N-M)!  |   O(M)

    // 접근 - 정리
    // 1. (B_3)가지 경우 만큼 직접 벽을 세운다. (완전 탐색) -> 약 41K 번
    // 2. 매번 직접 "탐색"을 통해서 바이러스로 부터 안전한 구역 확인하기 -> O(T)
    // 3. 탐색 방법에 따라서 O(41K * T)만큼의 시간이 걸릴 것이다.

    // 접근 - 시작점이 여러 개인 탐색(Multisource BFS)
    // 2. 매번 직접 "탐색"을 통해서 바이러스로 부터 안전한 구역 확인하기 -> O(T)
    // => 모든 시작점을 전부 Queue에 넣은 상ㅌ채로 BFS 시작하면된다.
    //    =>> 시간 복잡도는, O(V+E)가 유지됨

    // 시간, 공간 복잡도 계산하기
    // 1. (B_3)가지 경우 만큼 직접 벽을 세운다. (완전 탐색) -> 약 41K 번
    // 2. 매번 직접 "탐색"을 통해서 바이러스로 부터 안전한 구역 확인하기 -> O(N^2)
    // 3. 총 시간 복잡도는 O(41K * N^2 ~= 41K * 64 = 260만) 이다.


    static int N, M, B, ans;
    static int[][] A, blank;
    static boolean[][] visit;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        A = new int[N + 1][M + 1];
        blank = new int[N * M + 1][2];
        visit = new boolean[N + 1][M + 1];
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= M; j++)
                A[i][j] = scan.nextInt();
    }

    // 바이러스 퍼뜨리기!!
    static void bfs() {
        Queue<Integer> Q = new LinkedList<>();

        // 모든 바이러스가 시작점으로 가능하니까, 전부 큐에 넣어준다.
        for(int i = 1; i<=N;i++){
            for(int j = 1 ; j <= M ; j++){
                visit[i][j] = false;
                if(A[i][j] == 2){
                    // 먼저 넣는 것이 행
                    Q.add(i);
                    // 뒤에 넣는 것이 열
                    Q.add(j);

                    visit[i][j] = true;
                }
            }
        }

        // BFS 과정
        while(!Q.isEmpty()){
            int x = Q.poll(), y = Q.poll();
            for(int k = 0 ; k < 4 ; k++){
                int nx = x + dir[k][0], ny = y + dir[k][1];
                if(nx <  1 || ny < 1 || nx > N || ny > M){
                    continue;
                } else if(A[nx][ny] != 0){
                    continue;
                } else if(visit[nx][ny]){
                    continue;
                } else{
                    visit[nx][ny] = true;
                    Q.add(nx);
                    Q.add(ny);
                }
            }
        }

        // 탐색이 종료된 시점이니, 안전 영역의 넓이를 계산하고, 정답을 갱신한다.
        int cnt = 0;
        for(int i = 1 ; i <= N ; i++){
            for(int j = 1 ; j <= M ; j++){
                if(A[i][j] == 0 && !visit[i][j]){
                    cnt++;
                }
            }
        }
        ans = Math.max(ans, cnt);
    }

    // 완전탐색 하는 부분
    // idx 번째 빈 칸에 벽을 세울 지 말 지 결정해야 하고, 이 전까지 selected_cnt 개의 벽을 세웠다.
    static void dfs(int idx, int selected_cnt) {
        if (selected_cnt == 3) {  // 3 개의 벽을 모두 세운 상태
            bfs();
            return;
        }
        if (idx > B) {
            return;  // 더 이상 세울 수 있는 벽이 없는 상태
        }

       A[blank[idx][0]][blank[idx][1]] = 1;
       dfs(idx+1, selected_cnt + 1);

        A[blank[idx][0]][blank[idx][1]] = 0;
        dfs(idx+1, selected_cnt);
    }

    static void pro() {
        // 모든 벽의 위치를 먼저 모아놓자.
        for(int i = 1 ; i <= N ; i++){
            for (int j = 1 ; j <= M; j++){
                if(A[i][j] == 0){
                    B++;
                    blank[B][0] = i;
                    blank[B][1] = j;
                }
            }
        }

        // 벽을 3개 세우는 모든 방법을 확인해보자!
        dfs(1,0);

        System.out.println(ans);
    }
    public static void main(String[] args) {
        input();
        pro();
    }
}
