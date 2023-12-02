package DynamicProgramming;

import java.io.*;
import java.util.StringTokenizer;

public class SumOneTwoThree {
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
    
    // 1, 2, 3 더하기
    // 난이도 2
    // 1 <= N <= 11

    // Q. 정수 n 이 주어졌을 때,
    //    n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.

    // 접근 - 완전 탐색 접근
    // 완전 탐색 접근을 통해서 모든 경우를 직접 하나하나 찾아내 보자.
    // => 백트래킹활용해서 풀 수 있지만
    // =>> N이 커질수록 탐색해야하는 경우가 엄청 많아진다.

    // 접근 - DP
    // 1) 풀고 싶은 가짜 문제 정의
    // Hint) 진짜 문제 먼저 써보기
    // 진짜 문제 := 주어진 N에 대해서 N을 1, 2, 3의 합으로 표현하는 경우의 수
    // 가짜 문제 := Dy[i] = i 를 1, 2, 3의 합으로 표현하는 경우의 수
    // -> 레벨1 : 진짜 문제랑 똑같은 가짜 문제인 경우

    // 2) 가짜 문제를 풀면 진짜 문제를 풀 수 있는가?
    // -> Dy 배열만 가득 채울 수만 있다면? 진짜 문제에 대한 대답은 Dy[N]이다.

    // 3) 초기값은 어떻게 되는가?
    // -> 초기값 : 쪼개지 않아도 풀 수 있는 "작은 문제" 들에 대한 정답

    // 4) 점화식 구해내기
    //   4-1) Dy[i] 계산에 필요한 탐색 경우를 공통점끼리 묶어내기(Partitioning)
    //   4-2) 묶어낸 부분의 정답을 Dy 배열을 이용해서 빠르게 계산해보기
    //        전체 경우의 수 = (각 partition의 경우의 수) 들의 합

    // Dy[i]
    // = ( (i - 1)을 만들고 1 더하는 경우의 수 ) + ( ( i - 2)을 만들고 2 더하는 경우의 수 )
    // + ( ( i - 3)을 만들고 3 더하는 경우의 수 )

    // 즉, Dy[i] = Dy[i - 1] + Dy[i - 2] + Dy[i - 3]

    // 시간, 공간 복잡도 계산하기
    // 완전 탐색을 통해 모든 경우를 세면 정답의 개수만큼의 시간이 걸리지만,
    // Dy 배열을 1 번지부터 N 번지까지 채우는 것은 O(N) 이라는 시간 복잡도면 충분하다.
    // => 다수의 테스트 케이스를 처리하기 전에 모든 N에 대해 정답을 구해놓자.

    static int[] Dy;

    static void preprocess() {
        Dy = new int[15];
        // 초기값 구하기
        Dy[1] = 1;
        Dy[2] = 2;
        Dy[3] = 4;

        // 점화식을 토대로 Dy 배열 채우기
        for(int i = 4 ; i <= 11 ; i++){
            Dy[i] = Dy[i-1] + Dy[i-2] + Dy[i-3];
        }
    }

    static void pro() {
        int T = scan.nextInt();
        for (int tt = 1; tt <= T; tt++) {
            int N = scan.nextInt();
            sb.append(Dy[N]).append('\n');
        }
        System.out.print(sb);
    }

    public static void main(String[] args) {
        preprocess();
        pro();
    }

}
