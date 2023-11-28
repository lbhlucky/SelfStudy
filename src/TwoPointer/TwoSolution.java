package TwoPointer;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwoSolution {
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

    // 두 용액
    // 난이도 3
    // 2 <= N <= 100,000
    // -10억 <= 원소 <= 10억

    // Q. 서로 다른 두 용액을 더해서 합이 최대한 0에 가깝게 만들기

    // 접근 - 기존 이분 탐색 방법 O(N log N)
    // 1. 정렬 O(N log N)
    // 2. 이분 탐색 O(N log N)

    // 접근 - 투 포인터 활용 빠른 방법 O(N log N)
    // L : "남아 있는 것들 중" 제일 작은 원소
    // R : "남아 있는 것들 중" 제일 큰 원소

    // 접근 - 색다른 접근
    // 1. 최소 + 최대 < 0
    //    => 최소 입장에서는 최선책을 만난 상태!
    //       짝을 찾았으니 최소 삭제 (더 이상 고려하지 않음)
    // 2. 최소 + 최대 > 0
    //    => 최대 입장에서는 최선책을 만난 상태!
    //       짝을 찾았으니 최대 삭제 (더 이상 고려하지 않음)
    // 3. L = R
    //    => 서로 다른 두 용액을 고를 수 없는 상태
    //       종료!!

    // 시간, 공간 복잡도 계산하기
    // 1. 배열 정렬 한번 => O(N log N)
    // 2. 매 순간 L, R로 계산한 후 이동 => O(N)
    // 3. 총 시간복잡도 O(N log N)

    static int N;
    static int[] A;
    static void input(){
        N = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
    }

    static void pro() {
        // 최소, 최대 원소를 빠르게 찾기 위해서 정렬을 미리 해주자.
        Arrays.sort(A, 1, N + 1);

        int best_sum = Integer.MAX_VALUE;
        int v1 = 0, v2 = 0, L = 1, R = N;

        while (L < R){  // L == R 인 상황이면 용액이 한 개 뿐인 것이므로, L < R 일 때까지만 반복한다.
            int sum = A[L] + A[R];
            if(Math.abs(sum) < best_sum){
                best_sum = Math.abs(sum);
                v1 = A[L];
                v2 = A[R];
            }

            if(sum > 0){
                R--;
            }else {
                L++;
            }
        }
        sb.append(v1).append(' ').append(v2);
        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
