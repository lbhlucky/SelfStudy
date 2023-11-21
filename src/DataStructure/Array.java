package DataStructure;
import java.util.ArrayList;
import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Array {
    public static void main(String[] args) {
        /* 배열(Array)
         - 데이터를 나열하고, 각 데이터를 인덱스에 대응하도록 구성한 데이터 구조
         - 파이썬에서는 리스트 타입이 배열 기능을 제공함

         장점
         - 빠른 접근 가능
           : 첫 데이터의 위치에서 상대적인 위치로 데이터 접근(인덱스 번호로 접근)
         단점
         - 데이터 추가/삭제의 어려움
           : 미리 최대 길이를 지정해야 함

         */
        /*
        참고: Primitive 자료형과 Wrapper 클래스
          - JAVA 에서는 int 와 Integer 같이, Primitive 자료형과 Wrapper 클래스가 있음
          - 본 강의 내에서는 Integer 와 같은 Wrapper 클래스가 다음과 같은 이유로, 사용되며,
            가급적 복잡도를 낮추기 위해, Primitive 와 마구 혼용하기 보다는 주로 Wrapper 클래스를 사용하기로 함
            (필요 시에만 Primitive 자료형을 사용하기로 함)
          - null 을 용이하게 처리할 수 있고,
          - ArrayList 등 객체만을 핸들링 하는 기능을 사용하기 위해
        */

        // new 키워드를 사용해서, 배열을 미리 선언하고, 데이터를 넣을 수도 있음
        Integer[] data_list = new Integer[10];
        data_list[0] = 1;

        System.out.println("data_list : "+data_list[0]);

        // 직접 배열 데이터 선언 시 넣을 수도 있음
        Integer data_list1[] = {5, 4, 3, 2, 1};
        Integer[] data_list2 = {2, 4, 1, 3, 5};

        System.out.println("data_list1 : "+data_list1[0]);
        System.out.println("data_list2 : "+data_list2[0]);

        // Arrays 클래스를 활용하여, 전체 데이터 출력하기
        // 배열의 내용을 출력하려면, Arrays.toString(배열변수) 메서드 사용하면 됨
        System.out.println("Arrays 클래스 활용 전체 데이터 출력 :"+ Arrays.toString(data_list2));


        // ArrayList : 가변 길이의 배열 자료구조를 다룰 수 있는 기능을 제공함

        /*
         * List 와 Array List
         * 선언의 차이점
         * - List<Integer> list1 = new ArrayList<Integer>();
         * - ArrayList<Integer> list1 = new ArrayList<Integer>();
         *
         * List는 인터페이스이고, ArrayList는 클래스임
         * - 클래스는 크게 일반 클래스와 클래스 내에 '추상 메서드'가 하나 이상 있거나, abstract로
         *   정의된 추상클래스로 나뉨
         * - 인터페이스는 모든 메서드가 추상 메서드인 경우를 의미하며, 인터페이스를 상속받는 클래스는
         *   인터페이스에서 정의된 추상 메서드를 모두 구현 해야 함
         *   (따라서, 다양한 클래스를 상속받는 특정 인터페이스는 결국 동일한 메서드를 제공함)
         * - ArrayList 가 아니라, List로 선언된  변수는 다음과 같이 필요에 따라 다른 리스트 클레스를
         *   쓸 수 있는 '구현상의 유연성'을 제공함
         *   List<Integer> list1 = new ArrayList<Integer>();
         *   list1 = new LinkedList<Integer>();
         * - 이외에 JDK 1.7 이상부터는 인스턴스 생성 시 타입을 추정할 수 있는 경우에는 타입을 샹략할 수 있으므로,
         *   다음과 같이 작성 가능하지만, 가능한 JAVA 버전 제한하지 않기 위해, 타입을 생략하지 않기도 함
         *   ArrayList<Integer> list1 = new ArrayList<>();
         * */

        // int형 데이터를 담을 수 있는 가변 길이의 배열 선언
        ArrayList<Integer> list1 = new ArrayList<Integer>();

        // 배열에 아이템 추가 시 add(아이템) 메서드 사용
        list1.add(1);
        list1.add(2);

        // 배열의 특정 아이템을 읽을 시 get(인덱스번호) 메서드 사용
        list1.get(0);
        System.out.println(list1.get(0));

        // 특정 인덱스에 해당하는 아이템 변경 시, set(인덱스번호, 변경할 값) 메서드 사용
        list1.set(0,5);
        System.out.println(list1.get(0));

        // 특정 인덱스에 해당하는 아이템 삭제시, remove(인덱스번호) 메서드 사용
        list1.remove(0);
        System.out.println(list1.get(0));

        // 배열 길이 확인하기 size() 메서드 사용
        System.out.println("배열의 길이 : "+list1.size());


        /* 2차원 배열 */
        Integer ex_list[][] = { {1, 2, 3}, {4, 5, 6} };
        // 데이터 2 인덱스로 지정해서 출력
        System.out.println("데이터 2 인덱스로 지정해서 출력 : "+ex_list[0][1]);
        // 데이터 5 인덱스로 지정해서 출력
        System.out.println("데이터 5 인덱스로 지정해서 출력 : " + ex_list[1][1]);

        /* 3차원 배열 */
        Integer[][][] ex_list1 = {
                {
                        {1, 2, 3},
                        {4, 5, 6}
                },
                {
                        {7, 8, 9},
                        {10, 11, 12}
                }
        };
        // 데이터 5 인덱스로 지정해서 출력
        System.out.println("데이터 5 인덱스로 지정해서 출력 : "+ex_list1[0][1][1]);
        // 데이터 12 인덱스로 지정해서 출력
        System.out.println("데이터 12 인덱스로 지정해서 출력 : "+ex_list1[1][1][2]);

        /*
         * Example1
         * 위 3차원 배열에서 8, 10, 2,를 순서대로 각각의 라인에 출력해보기
         * sout 사용
         */
        System.out.println("데이터 8 인덱스로 지정해서 출력 : " + ex_list1[1][0][1]);
        System.out.println("데이터 10 인덱스로 지정해서 출력 : " + ex_list1[1][1][0]);
        System.out.println("데이터 2 인덱스로 지정해서 출력 : " + ex_list1[0][0][1]);

        /*
         * Example2
         * 아래 1차원 배열에서 문자 'M'을 가지고 있는 아이템의 수를 츌력
         * - 배열.length : 배열에 들어 있는 아이템 갯수
         * - 문자열.indexof(String key)
         *   : 문자 key 가 해당 문자열에 있으면 해당 문자의 위치(index 값)을 리턴, 없으면 -1 리턴
         */
        String dataset[] = {
                "Braund, Mr. Owen Harris",
                "Cumings, Mrs. John Bradley (Florence Briggs Thayer)",
                "Heikkinen, Miss. Laina",
                "Futrelle, Mrs. Jacques Heath (Lily May Peel)",
                "Allen, Mr. William Henry",
                "Moran, Mr. James",
                "McCarthy, Mr. Timothy J",
                "Palsson, Master. Gosta Leonard",
                "Johnson, Mrs. Oscar W (Elisabeth Vilhelmina Berg)",
                "Nasser, Mrs. Nicholas (Adele Achem)",
                "Sandstrom, Miss. Marguerite Rut",
                "Bonnell, Miss. Elizabeth",
                "Saundercock, Mr. William Henry",
                "Andersson, Mr. Anders Johan",
                "Vestrom, Miss. Hulda Amanda Adolfina",
                "Hewlett, Mrs. (Mary D Kingcome) ",
                "Rice, Master. Eugene",
                "Williams, Mr. Charles Eugene",
                "Vander Planke, Mrs. Julius (Emelia Maria Vandemoortele)",
                "Masselmani, Mrs. Fatima",
                "Fynney, Mr. Joseph J",
                "Beesley, Mr. Lawrence",
                "McGowan, Miss. Anna",
                "Sloper, Mr. William Thompson",
                "Palsson, Miss. Torborg Danira",
                "Asplund, Mrs. Carl Oscar (Selma Augusta Emilia Johansson)",
                "Emir, Mr. Farred Chehab",
                "Fortune, Mr. Charles Alexander",
                "Dwyer, Miss. Ellen",
                "Todoroff, Mr. Lalio"
        };
        // 문자 M을 가지고 있는 아이템의 수 출력
        Integer cnt = 0;
        System.out.println("dataset의 길이 : "+dataset.length);
        for(int i = 0 ; i < dataset.length; i++){
            if(dataset[i].indexOf('M') >= 0){
                cnt++;
            }
        }
        System.out.println("dataset 배열 내의 문자 'M'의 갯수 : "+cnt);
    }
}
