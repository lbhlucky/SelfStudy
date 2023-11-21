package Sort_Basic;
import java.util.ArrayList;
import java.util.Collections;

/*
* 삽입정렬
* - 두번째 인덱스부터 시작
* - 해당 인덱스(key 값) 앞에 있는 데이터(B)부터 비교해서 key 값이 더 작으면,
*   B 값을 뒤 인덱스로 복사
* - 이를 key 값이 더 큰 데이터를 만날 떄까지 반복,
*   큰 데이터를 만난 위치 바로 뒤에 key 값 이동
*/
public class InsertionSort {
    public ArrayList<Integer> sort(ArrayList<Integer> dataList){
        for(int index = 0 ; index < dataList.size()-1 ; index++){
            for(int index2 = index+1 ; index2 > 0 ; index2--){
                if(dataList.get(index2) < dataList.get(index2-1)){
                    Collections.swap(dataList, index2, index2-1);
                }else{
                    break;
                }
            }
        }
        return dataList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> testData = new ArrayList<>();

        for(int i = 0 ; i < 10 ; i++){
            testData.add((int)(Math.random()*100));
        }

        InsertionSort insertionSort = new InsertionSort();
        System.out.println(insertionSort.sort(testData));
    }
}
