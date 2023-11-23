// 국영수
// 난이도 1
// N <= 100,000
// 이름 국어점수 영어 점수 수학점수
// 정렬 기준
// 1. 국어 점수가 감소하는 순서(= 국어 점수 내림차순)
// 2. 아니면 영어 점수가 증가하는 순서(= 영어 점수 오름차순)
// 3. 아니면 수학 점수가 감소하는 순서(= 수학 점수 내림차순)
// 4. 아니면 이름이 사전순으로 증가하는 순서(= 이름 오름차순)
// 위의 기준으로 정렬해서, 이름 출력하기

// 문제 파악하기 - 정답의 최대치
// 1 <= 모든 점수 <= 100
// => Integer 면 충분

// 시간, 공간 복잡도 계산
// 정렬만 하면 되니까 O(N log N)
// ※ N log N <= 100,000 * log 100,000 = 1,600,000
// => 1초 안에 충분히 가능

package SortApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Kor_Eng_Math implements Comparable<Kor_Eng_Math> {
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
    static int N;
    static Kor_Eng_Math[] a;

    static void input(){
        N = scan.nextInt();
        a = new Kor_Eng_Math[N];
        for(int i = 0 ; i < N ; i++){
            a[i] = new Kor_Eng_Math();
            a[i].name = scan.next();
            a[i].korean = scan.nextInt();
            a[i].english = scan.nextInt();
            a[i].math = scan.nextInt();
        }
    }
    
    // 변수 선언 
    public String name; // 이름
    public int korean, english, math;   // 국어점수, 영어점수, 수학점수

     @Override
    public int compareTo(Kor_Eng_Math o) {
      // 국어 점수 내림 차순
      if(korean != o.korean){
        return o.korean - korean;
      }
      // 영어 점수 오름 차순
      if(english != o.english){
        return english - o.english;
      }
      // 수학 점수 내림 차순
      if(math != o.math){
        return o.math - math;
      }
      // 이름 오름 차순
     return name.compareTo(o.name);

    }

    static void pro(){
        // 기준을 통해 정렬
        Arrays.sort(a);

        // 정답 출력하기
        for(int i = 0 ; i < a.length ; i++){
            sb.append(a[i].name).append('\n');
        }
        System.out.println(sb.toString());
    }
    

    public static void main(String[] args) {
        input();
        pro();
    }


    
}
