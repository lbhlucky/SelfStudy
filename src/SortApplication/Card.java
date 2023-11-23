package SortApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Card{
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

    // 카드
    // 난이도 1.5
    // 해쉬맵 사용 시 더욱 쉽게 풀이 가능
    
    // N <= 100,000
    // -2^62 <= 카드 숫자 <= 2^62

    // 문제 파악하기 - 정답의 최대치
    // 입출력 : -2^62 <= 카드 숫자 <= 2^62
    // int 범위로는 감당이 안된다.
    // => long 타입 쓰자

    // 접근 - 가장 쉬운 방법 O(N^2) : 시간 초과
    // 1. 첫번쨰 숫자 선택
    // 2. 전체를 순회하면 몇번 등장하는지 카운트 후 저장
    // 3. 두번째 숫자 선택
    // 4. 위 과정 반복
    // 5. 가장 많이 카운트된 수 출력

    // 특성 : 같은 정보들은 인접해 있다.
    // 접근 - 같은 숫자를 빨리 보는 방법 O(N log N)
    // 1. 배열 정렬
    // 2. 내 앞에 숫자와 같으면 계속 등장, 아니면 처음 등장
    //    currentCount : 지금 보고 있는 숫자가 등장한 횟수
    //    modeCount : 지금까지의 최대빈도값의 등장 횟수
    //    mode : 지금까지의 최대빈도값

    // 시간, 공간 복잡도 계산
    // 배열 정렬 : O(N log N)
    // 카운팅 : 순서대로 읽으면 O(N)
    // 복잡도 : 시간 - O(N log N)
    //          공간 - O(N)
    
    static int N;
    static long[] a;

    static void input(){
        N = scan.nextInt();
        a = new long[N+1];
        for(int i = 1; i <= N ; i++){
            a[i] = scan.nextLong();
        }
    }

    static void pro(){
        // Sort 정렬하기
        Arrays.sort(a, 1, N+1);
        
        // mode : 최대빈도값
        // modeCnt : 최대빈도값의 등장 횟수
        // currCnt : 현재 값(a[1])의 등장 횟수
        long mode = a[1];
        int modeCnt = 1;
        int currCnt = 1;

        // 2번 원소부터 차례대로 보면서, 같은 숫자가 이어서 나오고 있는지,
        // 새로운 숫자가 나왔는지를 판단하여,
        // currCnt를 갱신해주고, 최대빈도값을 갱신하는 작업
        for(int i=2 ; i <= N ; i++){
            if(a[i] == a[i-1]){
                currCnt++;
            }else{
                currCnt = 1;
            }

            if(modeCnt > currCnt){
                mode = a[i];
            }
        }
        
        // 정답 출력 (mode)
        System.out.println("mode : "+mode);
    }
    public static void main(String[] args) {
        input();
        pro();
    }
}
