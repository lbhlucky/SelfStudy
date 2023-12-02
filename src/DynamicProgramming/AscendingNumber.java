package DynamicProgramming;

import java.io.*;
import java.util.StringTokenizer;

public class AscendingNumber {
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
    
    // 오르막 수
    // 난이도 3
    // 1 <= 수의 길이 N <= 1,000

    // 오르막 수란, 각 자리가 올므차순을 이루는 수를 말한다.
    // 길이가 N인  수 중에서 오르막 수의 개수를 10,007로 나눈 나머지를 구하자.
    // 단, 수는 0으로 시작할 수 있다.
    // 예)
    // 길이 1 := 0, 1, 2, 3, ..., 9 => 총 10개
    // 길이 2 := 00, 01, 02, 3, ..., 09, 11, 12, ..., 99 => 총 55개
    // 길이 N := 총 몇 개?

    // 정답의 최대치
    // 정답을 10,007로 나눈 나머지로 출력해야한다.
    // 즉, 문제를 푸는 과정에서 계속 나눈 나머지만 가지고 있다면
    // Integer 범위로도 충분할 것이다.

    // 완전 탐색 접근
    // 완전 탐색 접근을 통해서 모든 경우를 직접 하나 하나 찾아 보자.
    // 이 문제에서 "경우"란,
    // 길이에 맞는 오르막 수를 전부 하나 하나 찾는다는 것이다.

    // 1) 풀고 싶은 가짜 문제 정의
    // 진짜 문제 := 길이가 N인 오르막 수의 개수
    // 가짜 문제 := Dy[i][last] = 길이가 i 이 며, last로 끝나는 오르막 수의 개수
    // -> 레벨 3 새로운 정의가 필요한 경우

    // 2) 가짜 문제를 풀면 진짜 문제를 풀 수 있는가?
    // => Dy 배열을 가득 채울 수만 있다면? 진짜 문제에 대한 대답은
    //    Dy[N][0] + Dy[N][1] + ... + Dy[N][9]이다.

    // 3) 초기값은 어떻게 되는가?
    // -> 초기값 : 쪼개지 않아도 풀 수 있는 "작은 문제" 들에 대한 정답

    // 4) 점화식 구해내기
    //   4-1) Dy[i][k] 계산에 필요한 탐색 경우를 공통점끼리 묶어내기(Partitioning)
    //   4-2) 묶어낸 부분의 정답을 Dy 배열을 이용해서 빠르게 계산해보기
    //        최대 점수 = (각 partition의 최대 점수) 들 중의 최대 점수

    // Dy[i][j] = ∑k=j_k=0 Dy[i-1][k]

    // 시간, 공간 복잡도 계산하기
    // 완전 탐색을 통해 모든 경우를 세면 정답의 개수만큼의 시간이 걸리지만,
    // Dy 배열을 채우는 것은 O(N*10^2) 이라는 시간 복잡도면 충분하다.

    static int N;
    static int[][] Dy;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];
        Dy = new int[N + 1][10];
    }

    static void pro() {
        // 초기값 구하기
        for(int num = 0; num <= 9 ; num++){
            Dy[1][num] = 1;
        }

        // 점화식을 토대로 Dy 배열 채우기
        for(int len = 2 ; len <= N; len++){
            for(int num = 0; num <= 9; num++){
                // Dy[len][num] := ?
                for(int prev = 0; prev <= num; prev++){
                    Dy[len][num] += Dy[len-1][prev];
                    Dy[len][num] %= 10007;
                }
            }
        }

        // Dy배열로 정답 계산하기
        int ans = 0;
        for(int num = 0; num <=9 ; num++){
            ans += Dy[N][num];
            ans %= 10007;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        pro();
    }
    
}
