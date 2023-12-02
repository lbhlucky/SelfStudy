package DynamicProgramming;

import java.io.*;
import java.util.StringTokenizer;

public class MergeFiles {
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
    
    // 파일 합치기
    // 난이도 3
    // 1 <= 파일의 개수 K <= 500
    // 1 <= 파일의 크기 <= 10,000

    // 1. 각자의 크기가 존재하는 파일들이 순서대로 존재한다.
    // 2. 연속한 두 파일을 하나로 합치면, 합친 크기 만큼의 비용이 필요하다.
    // 3. 전체 K개의 파일들을 하나로 합치는 방법들 중에서 총 비용의 최솟값을 계산하라.

    // 완전 탐색 접근
    // 완전 탐색 접근을 통해서 모든 경우를 직접 하나 하나 찾아내 보자
    // 본 문제에서 "경우"란, 조건을 만족하게 K 개의 파일을 하나가 될 때까지 합쳐보는 과정

    // 접근 - DP
    // 1) 풀고 싶은 가짜 문제 (i <= j)
    //    Dy[i][j] := i번 ~ j번 파일을 하나로 합치는 최소비용
    //    Sum[i][j] := i번 ~ j번 파일의 총크기

    // 2) 가짜 문제를 풀면 진짜 문제를 풀 수 있는가?
    // => Dy 배열을 가득 채울 수만 있다면?
    // 진짜 문제에 대한 대답은 Dy[1][K]이다.

    // 3) 초기값은 어떻게 되는가?
    //   초기값 : 쪼개지 않아도 풀 수 있는 "작은 문제"들에 대한 정답

    // 4) 점화식 구해내기
    //   4-1) Dy[i][j] 계산에 필요한 탐색 경우를 공통점끼리 묶어내기(Partitioning)
    //   4-2) 묶어낸 부분의 정답을 Dy 배열을 이용해서 빠르게 계산해보기
    //        최소 비용 = (각 partition의 최소 비용) 들 중의 최소 비용

    // Dy[i][j] = min_k{Dy[i][k] + Dy[k+1][j] + (i ~ j 파일 총량), i <= k < j}
    // Sum[i][j] := i번 ~ j번 파일의 총크기
    //            = Sum[i][j-1] + A[j]          << 전처리(Prepocess)로 구해놓기

    // 시간, 공간 복잡도 계산하기
    // Sum 배열을 O(N^2)으로 전처리 계산 해놓자.
    // Dy 배열을 채우는 것은 O(N^3) 이라는 시간 복잡도면 충분하다.

    static int K, Q;
    static int[] num;
    static int[][] Dy, sum;

    static void input(){
        K = scan.nextInt();
        num = new int[K + 1];
        sum = new int[K + 1][K + 1];
        for (int i = 1; i <= K; i++){
            num[i] = scan.nextInt();
        }
    }

    static void preprocess(){
        for (int i = 1; i <= K; i++){
            for (int j = i; j <= K; j++){
                sum[i][j] = sum[i][j - 1] + num[j];
            }
        }
    }

    static void pro() {
        preprocess();
        Dy = new int[K + 1][K + 1];

        for (int len = 2; len <= K; len ++){
            for (int i = 1; i <= K - len + 1; i++){
                int j = i + len - 1;
                Dy[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++){
                    Dy[i][j] = Math.min(Dy[i][j], Dy[i][k] + Dy[k + 1][j] + sum[i][j]);
                }
            }
        }

        System.out.println(Dy[1][K]);
    }


    public static void main(String[] args) {
        Q = scan.nextInt();
        for (int rep = 1; rep<=Q;rep++) {
            input();
            pro();
        }
    }
    
}
