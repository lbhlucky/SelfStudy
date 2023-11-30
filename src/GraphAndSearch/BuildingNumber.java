package GraphAndSearch;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BuildingNumber {
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

    // 단지 번호 붙이기
    // 난이도 2
    // 5 <= 지도의 크기 N < 25
    // 0은 비어있는 마을, 1은 들어있는 마을
    // 인접한 단지 별로 번호 지정 해서
    // 몇 개의 단지가 있는지와 각 단지 별로 몇개의 집이 있는지 확인

    // 문제 파악하기 - 정답의 최대치
    // 집의 최대 값 : 25*25 = 600
    // 단지의 최대값 : 집의 최대 / 2 = 300
    // 집의 개수 최대값 : O(N)
    // 단지 개수 최대값 : O(N^2)


    /*
    // 접근 - 격자형 그래프 구성
    *   |0|                               (i-1, j)
    * |1|1|1|       =>>       (i, j-1)     (i, j)    (i, j+1)
    *   |1|                              (i+1, j+1)
    *
    * 상하좌우로 인접한 점을 찾을 때 다음 행렬 사용
    * int[4][2] arr = {{-1,0},{0,1},{1,0},{0,-1}}
    */

    /*
    // 접근 - 격자형 그래프 구성
    * |0|1|0|1|1|                 ●   ○   ●   ○ - ○
    *                                 |
    * |1|1|1|0|0|                 ○ - ○ - ○   ●   ●
    *                                 |
    * |0|1|0|1|1|       =>>       ●   ○   ●   ○ - ○
    *                                 |           |
    * |1|1|1|0|1|                 ○ - ○ - ○   ●   ○
    *                                 |           |
    * |0|1|0|1|1|                 ●   ○   ●   ○ - ○
    *
    * */

    // 접근 - 단지 수 세기
    /*
       ●   ○   ●   ○ - ○
           |
       ○ - ○ - ○   ●   ●
           |                  =>     새로운 단지를 찾을 때마다,
       ●   ○   ●   ○ - ○             해당 단지에 속해 있는 집들은
           |           |             처리되었다는 표시를 해주자!
       ○ - ○ - ○   ●   ○
           |           |
       ●   ○   ●   ○ - ○
    */

    // 시간, 공간 복잡도 계산하기
    // <격자형 그래프>
    // 정점 : O(N^2)
    // 간선 : O(N^2 *4)
    // => BFS 이든 DFS 이든, 시간 복잡도는 모두 O(N^2)

    static int N, group_cnt;
    static String[] a;
    static boolean[][] visit;

    // 임의의 격자에서 인접한 4개의 점(상하좌우)을 빠르게 찾기위한
    // 반복을 통해 잡기위한 미리 선언한 2차원 배열
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static ArrayList<Integer> group;

    static void input() {
        N = scan.nextInt();
        a = new String[N];
        for (int i = 0; i < N; i++)
            a[i] = scan.nextLine();
        visit = new boolean[N][N];
    }

    // x, y 를 갈 수 있다는 걸 알고 방문한 상태
    static void dfs(int x, int y) {
        // 단지에 속한 집의 개수 증가, visit 체크 하기
        group_cnt++;
        visit[x][y] = true;

        // 인접한 집으로 새로운 방문하기
        for(int k = 0; k < 4 ; k++){
            // (x, y) -> dir[k]
            int nx = x + dir[k][0];
            int ny = y + dir[k][1];

            if(nx < 0 || ny <  0 || ny >= N || nx >= N ){
                    continue;
            }else if (a[nx].charAt(ny)=='0'){
                continue;
            }else if(visit[nx][ny]){
                continue;
            }else {
                dfs(nx,ny);
            }


        }
    }

    static void pro() {
        group = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j] && a[i].charAt(j) == '1') {
                    // 갈 수 있는 칸인데, 이미 방문처리 된, 즉 새롭게 만난 단지인 경우!
                    group_cnt = 0;
                    dfs(i, j);
                    group.add(group_cnt);
                }
            }
        }

        // 찾은 단지의 정보를 출력하기
        Collections.sort(group);
        sb.append(group.size()).append('\n');
        for(int cnt : group){
            sb.append(cnt).append('\n');
        }
            System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
