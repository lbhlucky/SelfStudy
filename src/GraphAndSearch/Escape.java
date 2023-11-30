package GraphAndSearch;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Escape {
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

    // 탈출
    // 난이도 4
    // 1 <= 지도의 크기 N, M <= 50
    // .(빈칸 위치) *(물의 위치) X(벽의 위치) D(탈출구 위치) S(고슴도치 위치) 로 이루어짐
    // 물과 인접해 있는 비어있는 칸(적어도 한변을 공유) 물이 찬다
    // 물고 고슴도치는 돌을 통과할 수 없다
    // 고슴도치는 물로 못간다
    // 물은 탈출구로 못넘어온다.

    // 고슴도치가 물을 피해 탈출 할 수 있는 최소 시간
    // 탈출 못한다면 -1 출력

    // 문제 파악하기 - 정답의 최대치
    // 밟게 되는 최대 개수 = O(N^2)

    // 접근 - 고슴도치의 행동 방법
    // 1. T_h + 1 >= T_w 이라면, 물에 잠긴 뒤라서 못가는 곳!
    // 2. 벽도 못 가는 곳

    // 접근 - 각 칸이 물에 잠기는 시간 계산
    // dist_water[i][j] := (i, j)가 물에 잠기는 시간
    //                  := 물이 해당하는 칸에 도달하기까지 걸리는 시간
    // 즉. 물에서 시작하는 BFS를 하자
    // 문제점! 시작점이 여러개다!
    // 모든 물마다 BFS를 각자 하면 => O(N^2 * N^2)
    // 모든 물을 동시에 Queue에 넣고 시작하면? = Multisource BFS
    // 단 한번의 BFS, O(N^2)

    // dist_hedgehog[i][j] := 고슴도치가 물을 피해서 (i, j)에 도달하는 최소 시간
    // => dist_water를 이미 구했으니 어떤 칸에 고슴도치가 갈 수 있는지 알 수 있다.
    // 단 한번의 BFS, O(N^2)

    // 시간 공간 복잡도 계산하기
    // 1. dist_water를 O(N^2)에 계산
    // 2. dist_hedgehog를 O(N^2)에 계산
    // 총 2번의 BFS 이용

    static int N, M;
    static String[] a;
    static int[][] dist_water, dist_hedgehog;
    static boolean[][] visit;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        a = new String[N];
        for (int i = 0; i < N; i++)
            a[i] = scan.nextLine();
        visit = new boolean[N][M];
        dist_water = new int[N][M];
        dist_hedgehog = new int[N][M];
    }

    // 모든 물들을 시작으로 동시에 BFS 시작!
    static void bfs_water() {
        Queue<Integer> Q = new LinkedList<>();
        // 모든 물의 위치를 Q에 전부 넣어주기!
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // dist_water 와 visit 배열 초기화
                dist_water[i][j] = -1;
                visit[i][j] = false;
                if (a[i].charAt(j) == '*') {
                    Q.add(i);
                    Q.add(j);
                    dist_water[i][j] = 0;
                    visit[i][j] = true;
                }
            }
        }

        // BFS 과정 시작
        while(!Q.isEmpty()){
            int x = Q.poll();
            int y = Q.poll();

            for(int k = 0 ; k < 4 ; k++){
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M){  // 지도를 벗어나는 곳으로 가는가?
                    continue;
                }else if(a[nx].charAt(ny) != '.'){  // 갈 수 있는 칸인지 확인해야 한다.
                    continue;
                }else if (visit[nx][ny]){   // 이미 방문한 적이 있는 곳인가?
                    continue;
                }else {
                    visit[nx][ny] = true;
                    dist_water[nx][ny] = dist_water[x][y] + 1;
                    Q.add(nx);
                    Q.add(ny);
                }
            }
        }
    }

    // 고슴도치를 시작으로 동시에 BFS 시작!
    static void bfs_hedgehog() {
        Queue<Integer> Q = new LinkedList<>();
        // 고슴도치 위치를 Q에 넣어주기!
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // dist_hedgehog 와 visit 배열 초기화
                dist_hedgehog[i][j] = -1;
                visit[i][j] = false;
                if (a[i].charAt(j) == 'S') {
                    Q.add(i);
                    Q.add(j);
                    dist_hedgehog[i][j] = 0;
                    visit[i][j] = true;
                }
            }
        }

        // BFS 과정 시작
        while(!Q.isEmpty()){
            int x = Q.poll();
            int y = Q.poll();

            for(int k = 0 ; k < 4 ; k++){
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M){  // 지도를 벗어나는 곳으로 가는가
                    continue;
                }else if(a[nx].charAt(ny) != '.' && a[nx].charAt(ny) != 'D' ){  // 갈 수 있는 칸인지 확인해야 한다.
                    continue;
                }else if (dist_water[nx][ny] != -1 && dist_water[nx][ny] <= dist_hedgehog[x][y] + 1){  // 물에 잠기지는 않는가?
                    continue;
                }else if (visit[nx][ny]){  // 이미 방문한 적이 있는 곳인가?
                    continue;
                }else {
                    visit[nx][ny] = true;
                    dist_hedgehog[nx][ny] = dist_hedgehog[x][y] + 1;
                    Q.add(nx);
                    Q.add(ny);
                }
            }
        }
    }

    static void pro() {
        // 각 칸마다 물에 닿는 시간 계산하기
        bfs_water();

        // 고슴도치가 물을 피해 탐색할 수 있는 공간 찾기
        bfs_hedgehog();

        // 탈출구 'D' 에 대한 결과를 통해 정답 출력하기
        for (int i=0;i<N;i++){
            for (int j=0;j<M;j++){
                if (a[i].charAt(j) == 'D'){
                    if(dist_hedgehog[i][j] == -1){  // 고슴도치가 여기 못왔다.
                        System.out.println("KAKTUS");
                    }else{
                        System.out.println(dist_hedgehog[i][j]);
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        input();
        pro();
    }
}
