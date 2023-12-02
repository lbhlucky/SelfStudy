package DynamicProgramming;

import java.io.*;
import java.util.StringTokenizer;

public class TwoByNTiling {
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

    // 2 X N 타일링
    // 난이도 2
    // 1 <= N <= 1,000

    // Q. 2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.

    // 접근 - 완전 탐색 접근
    // -> N이 커질수록 탐색해야 하는 경우가 엄청 많아진다.

    // 접근 - DP
    // 1) 풀고 싶은 가짜 문제 정의
    // Hint) 진짜 문제 먼저 써보기
    // 진짜 문제 := 주어진 N에 대해서 2 X N 타일링 경우의 수
    // 가짜 문제 := Dy[i] = 2 X i 타일링 경우의 수
    // -> 레벨 1 진짜 문제랑 똑같은 가짜 문제인 경우

    // 2) 가짜 문제를 풀면 진짜 문제를 풀 수 있는가?
    // => Dy 배열을 가득 채울 수만 있다면? 진짜 문제에 대한 대답은 Dy[N]이다.

    // 3) 초기값은 어떻게 되는가?
    // -> 초기값 : 쪼개지 않아도 풀 수 있는 "작은 문제" 들에 대한 정답

    // 4) 점화식 구해내기
    //   4-1) Dy[i] 계산에 필요한 탐색 경우를 공통점끼리 묶어내기(Partitioning)
    //   4-2) 묶어낸 부분의 정답을 Dy 배열을 이용해서 빠르게 계산해보기
    //        전체 경우의 수 = (각 partition의 경우의 수) 들의 합

    // Dy[i]
    // = ( 2 X (i - 2)을 만들고 가로 두 개 붙이는 경우의 수 )
    // + ( 2 X (i - 1)을 만들고 세로 한 개 붙이는 경우의 수 )

    // 즉, Dy[i] = Dy[i - 1] + Dy[i - 2]

    static int N;
    static int[] Dy;

    static void input(){
        N = scan.nextInt();
    }

    static void pro() {
        Dy = new int[1005];
        // 초기값 구하기
        Dy[1] = 1;
        Dy[2] = 2;

        // 점화식을 토대로 Dy 배열 채우기
        for(int i = 3 ; i <= N ; i++){
            Dy[i] = (Dy[i-1]+Dy[i-2])%10007;
        }

        System.out.println(Dy[N]);
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
