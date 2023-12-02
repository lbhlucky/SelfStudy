package DynamicProgramming;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GreatTown {
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
    
    // 우수 마을 - 카카오 블라인드 문제에서 어렵게 바꿔서 낸 적 있음
    // 난이도 4
    // 1 <= 마을 개수 N <= 10,000
    // 1 <= 각 마을의 주민 수 <= 10,000

    // 인접하지 않은 마을들을 골라서 주민 수의 총합을 최대화

    // 정답의 최대치
    // 일단 모든 마을에 최대 인원이 존재해야 할 것이므로 모든 마을에 1만 명의 주민 존재할 것
    // 마을을 최대한 많이 고르기 위해서 최대 마을 개수(1만 개)의 마을이 일렬로 존재해서
    // 약 9,999 개의 마을을 선택할 경우
    // 10,000 * 9,999
    // 즉, 약 1억 명 고르는 것이 최대
    // => Integer 범위

    // 접근 - DP
    // 1) 풀고 싶은 문제 정의
    // Dy[i][0] := i를 root로 하는 subtree에서 root를 선택하지 않고서 가능한 최대 주민 수
    // Dy[i][1] := i를 root로 하는 subtree에서 root를 선택하고서 가능한 최대 주민 수

    // 2) 가짜 문제를 풀면 진짜 문제를 풀 수 있는가?
    // Dy 배열을 가득 채울 수만 있다면?
    // 진짜 문제에 대한 대답은 실제 root를 고르거나 안고르는 것 중 최대이므로
    // => max(Dy[1][0], Dy[1][1]) 이다.

    // 3) 초기값은 어떻게 되는가?
    // 초기값 : "단말 노드"가 제일 작은 문제이다.(정점이 한 개 뿐이라서)

    // 4) 점화식 구해내기
    // Rooted Tree 이므로 부모, 자식 관계가 존재한다.
    // 자식노드에 대해 문제를  해결한다면, 부모 노드는 그걸 이요해서 문제를 풀면 된다.

    //   4-1) Root를 고르는 경우, Dy[R][1]
    //        자식 노드들 각각의 최대 점수를 가져와서 누적하면 되는데, Root가 골라진 상태이기 때문에
    //        Dy[Root, R][1] = num[R] + ∑Dy[Child][0]

    //   4-2) Root를 고르지 않는 경우, Dy[R][0]
    //       자식 노드들 각각의 최대 점수를 가져와서 누적하면 되는데, Root가 골라지지 않은 상태이기 때문에
    //        Dy[Root, R][0] = ∑max(Dy[Child][0], Dy[Child][1])

    // 점화식
    // Dy[Root, R][0] = ∑max(Dy[Child][0], Dy[Child][1])
    // Dy[Root, R][1] = num[R] + ∑Dy[Child][0]

    // 시간, 공간 복잡도 계산하기
    // Rooted Tree 문제를 DP로 풀 경우 대부분  DFS 한 번에 해결한다.
    // 따라서, DFS 시간 복잠도인 O(V+E) = O(N) 만에 문제를 풀 수 있다.

    static int N;
    static int[] num;
    static ArrayList<Integer>[] con;
    static int[][] Dy;

    static void input(){
        N = scan.nextInt();
        num = new int[N + 1];
        con = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++){
            num[i] = scan.nextInt();
            con[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++){
            int x = scan.nextInt(), y = scan.nextInt();
            con[x].add(y);
            con[y].add(x);
        }
    }

    static void dfs(int x, int prev){
        Dy[x][1] = num[x];
        for (int y: con[x]){
            if (y == prev) continue;
            dfs(y, x);
            Dy[x][0] += Math.max(Dy[y][0], Dy[y][1]);
            Dy[x][1] += Dy[y][0];
        }
    }

    static void pro() {
        Dy = new int[N + 1][2];

        dfs(1, -1);

        System.out.println(Math.max(Dy[1][0], Dy[1][1]));
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
