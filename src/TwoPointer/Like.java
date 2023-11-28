package TwoPointer;

import DataStructure.Array;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Like {
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
    // 좋다
    // 난이도 2
    // 1 <= N <= 2,000
    // -10^9 <= 각 원소 <= 10^9
    // 원소의 개수 N

    // Q. 각 원소를 다른 두개의 원소를 골라서 만들 수 있냐

    // 문제 파악하기 - 정답의 최대치
    // N = 100,000
    // 정답이 N 이하 이므로 Integer 범위
    // 원소 두 개의 합도 최대 10^9  이므로 Integer

    // 접근 - 가장 쉬운 방법 O(N^3)
    // 1. 타겟 수 결정 => O(N)
    // 2. 다른 수 2개 결정해서 만들어지나 확인 => O(N^2)
    // 3. 총 시간 복잡도 O(N^3)

    // 접근 - 가장 쉬운 방법 O(N^2)
    // 1. step3를 위해 정렬 한번 하기 => O(N log N)
    // 2. 타겟 수 결정 => O(N)
    // 3. 다른 수 2개 결정해서 만들어지나 확인 => O(N)
    // 4. 총 시간 복잡도 O(N^2)

    static int N;
    static int[] A, cnt;

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
        cnt = new int[100000 + 1];
    }

    // target_idx 번째 원소가 서로 다른 두 수의 합으로 표현이 되는가?
    static boolean func(int target_idx) {
        int L = 1, R = N;
        int target = A[target_idx];
        while (L < R) {
           if(L == target_idx){
               L++;
           } else if (R == target_idx) {
               R--;
           } else {
               if (A[L] + A[R] == target){
                   return true;
               } else if (A[L] + A[R] > target) {
                   R--;
               } else{
                   L++;
               }
           }

        }
        return false;
    }

    static void pro() {
        // 최소, 최대를 빠르게 알기 위한 정렬
        Arrays.sort(A, 1, N+1);

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            // i 번째 원소가 서로 다른 두 수의 합으로 표현이 되는가?
            if(func(i)){
                ans++;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

}
