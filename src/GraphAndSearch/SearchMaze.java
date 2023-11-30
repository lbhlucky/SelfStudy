package GraphAndSearch;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SearchMaze {
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

    /*
       <"최소 이동 횟수 " , "최단 시간" 키워드 >
       최소 "이동" 횟수와 관련된 것이기 때문에,
       가중치에 대한 개념이 없는 문제에서만 생기는 부가 효과!

       Q. 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후
          이동할 때 지나야하는 최소의 칸 수
          고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간

       => 때로는 그래프가 없는 문제에서 "정점"과 "간선"의 정의를 만들어서
          그래프 문제로 접근해야 한다.
    */

    // 미로 탐색
    // 난이도 2
    // 2 <= 지도의 크기 N, M < 100

    // 문제 파악하기 - 정답의 최대치
    // 밝게 되는 최대 개수 = 계속 해서 꼬불꼬불 S 자로 가는 경우
    // => O(N^2) : 100 * 100 = 10,000 Integer 가능

    // 접근 - 격자형 그래프 구성

    // 시간, 공간 복잡도 계산하기
    // <격자형 그래프>
    // 정점 : O(N^2)
    // 간선 : O(N^2 * 4)
    // => BFS 사용하기 때문에 시간,공간 복잡도는 O(N^2)

    static int N, M;
    static String[] a;
    static int[][] dist;
    static boolean[][] visit;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static ArrayList<Integer> group;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        a = new String[N];
        for (int i = 0; i < N; i++)
            a[i] = scan.nextLine();
        visit = new boolean[N][M];
        dist = new int[N][M];
    }

    // x, y 를 갈 수 있다는 걸 알고 방문한 상태
    static void bfs(int x, int y) {
        // dist 배열 초기화
       for(int i = 0 ; i < N ; i++){
           for(int j = 0 ; j < M ; j++){
               dist[i][j] = -1;
           }
       }

        // (x, y)를 Q에 넣어주고, visit 표시와 dist 값 초기화
        Queue<Integer> Q = new LinkedList<>();
       Q.add(x);
       Q.add(y);

       // 시작 부터 한칸을 밟기 때문
       dist[x][y] = 1;
       visit[x][y] = true;

        // BFS 과정 시작
        while(!Q.isEmpty()){
            x = Q.poll();
            y = Q.poll();
            for(int k = 0; k < 4 ; k++){
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M){
                    continue;
                }else if(a[nx].charAt(ny) == '0'){
                    continue;
                }else if (visit[nx][ny]){
                    continue;
                }else {
                    Q.add(nx);
                    Q.add(ny);
                    visit[nx][ny] = true;

                    // 시작점에서 x,y 까지 걸리는 최소 거리인데 이번에 하나 더 밟았으니까 + 1
                    dist[nx][ny] = dist[x][y] + 1;
                }

            }
        }
    }

    static void pro() {
        // 시작점이 (0, 0)인 탐색 시작
        bfs(0, 0);

        // (N-1, M-1)까지 필요한 최소 이동 횟수 출력
        System.out.println(dist[N-1][M-1]);
    }
    public static void main(String[] args) {
        input();
        pro();
    }
}
