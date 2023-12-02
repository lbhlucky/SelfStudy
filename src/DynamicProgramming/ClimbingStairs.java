package DynamicProgramming;

import java.io.*;
import java.util.StringTokenizer;

public class ClimbingStairs {
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
    
    // 계단 오르기
    // 난이도 2
    // 1 <= 계단의 개수 N <= 300

    // 1. 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다.
    //    즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
    // 2. 연속된 세 개의 꼐단을 모두 밟아서는 안 된다.
    //    단, 시작점은 계단에 포함되지 않는다.
    // 3. 마지막 도착 계단은 반드시 밟아야 한다.

    // 정답의 최대치
    // N 개의 계단을 모두 밟는다고 해도
    // N * 최대 점수 = 300 * 10,000 = 300만
    // => Integer 범위

    // 완전 탐색 접근
    // 완전 탐색 접근을 통해서 모든 경우를 직접 하나 하나 찾아 보자.
    // 이 문제에서 "경우"란,
    // 조건을 만족하게 계단을 올라 도착점까지 가는 방법

    // 접근 - DP

    // 1) 풀고 싶은 가짜 문제 정의 - 일단 도전해보기
    // 진짜 문제 := N번째 계단에 도착하며 얻는 최대 점수
    // 가짜 문제 := Dy[i] = i번째 계단에 도착하며 얻는 최대 점수
    // -> 레벨 1 진짜 문제랑 똑같은 가짜 문제인 경우

    // 2) 가짜 문제를 풀면 진짜 문제를 풀 수 있는가?
    // => Dy 배열을 가득 채울 수만 있다면? 진짜 문제에 대한 대답은 Dy[N]이다.

    // 3) 초기값은 어떻게 되는가?
    // -> 초기값 : 쪼개지 않아도 풀 수 있는 "작은 문제" 들에 대한 정답

    // 4) 점화식 구해내기
    //   4-1) Dy[i] 계산에 필요한 탐색 경우를 공통점끼리 묶어내기(Partitioning)
    //   4-2) 묶어낸 부분의 정답을 Dy 배열을 이용해서 빠르게 계산해보기
    //        최대 점수 = (각 partition의 최대 점수) 들 중의 최대 점수

    // 마지막에 한 칸 오르는 경우 Dy[i] = (i -1) 번째 계단 최대 점수 + A[i]
    //                                 =           ????           + A[i]
    // -----------------------------------------------------------------------------------------------------
    // 마지막에 두 칸 오르는 경우 Dy[i] = (i -2) 번째 계단 최대 점수 + A[i]

    // 연속된 세 개의 꼐단을 모두 밟아서는 안 된다. 라는 조건을 확인해야함.
    // 모자란 정보 ???? := (i-1)번째 계단 직전에 (i-2)번쨰 계단도 밟았는가?

    // 1) 풀고 싶은 가짜 문제 정의 - 필요한 정보를 문제에 추가하기
    // 진짜 문제 := N번째 계단에 도착하며 얻는 최대 점수
    // 가짜 문제 =>
    // Dy[i][0] = i-1번째 계단은 밟지 않고, i번째 계단에 도착하며 얻는 최대 점수
    // Dy[i][1] = i-1번째 계단은 밟고, i번째 계단에 도착하며 얻는 최대 점수
    // -> 레벨 2 문제 조건에 맞게 상태를 추가한 경우

    // 2) 가짜 문제를 풀면 진짜 문제를 풀 수 있는가?
    // => Dy 배열을 가득 채울 수만 있다면? 진짜 문제에 대한 대답은 max(Dy[N][0], Dy[N][1])이다.

    // 3) 초기값은 어떻게 되는가?
    // -> 초기값 : 쪼개지 않아도 풀 수 있는 "작은 문제" 들에 대한 정답

    // 4) 점화식 구해내기
    //   4-1) Dy[i][0],Dy[i][1] 계산에 필요한 탐색 경우를 공통점끼리 묶어내기(Partitioning)
    //   4-2) 묶어낸 부분의 정답을 Dy 배열을 이용해서 빠르게 계산해보기
    //        최대 점수 = (각 partition의 최대 점수) 들 중의 최대 점수

    // Dy[i][0] = max(Dy[i-2][1] + A[i] || Dy[i-2][0] + A[i] )
    // Dy[i][1] = Dy[i-1][0] + A[i])

    // 시간, 공간 복잡도 계산하기
    // 완전 탐색을 통해 모든 경우를 세면 정답의 개수만큼의 시간이 걸리지만,
    // Dy 배열을 1 번지부터 N 번지까지 채우는 것은 O(N) 이라는 시간 복잡도면 충분하다.

    static int N;
    static int[][] Dy;
    static int[] A;

    static void input(){
        N = scan.nextInt();
        A = new int[N + 1];
        Dy = new int[N + 1][2];
        for (int i = 1; i <= N; i++){
            A[i] = scan.nextInt();
        }
    }

    static void pro() {
        // 초기값 구하기
        Dy[1][0] = 0;
        Dy[1][1] = A[1];

        if(N >= 2){
            Dy[2][0] = A[2];
            Dy[2][1] = A[1]+A[2];
        }

        // 점화식을 토대로 Dy 배열 채우기
        for(int i = 3 ; i <= N ; i++){
            Dy[i][0] = Math.max(Dy[i -2][0] + A[i],Dy[i -2][1] + A[i]);
            Dy[i][1] = Dy[i-1][0] + A[i];
        }

        // Dy배열로 정답 계산하기
        int ans = Math.max(Dy[N][0], Dy[N][1]);

        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

}
