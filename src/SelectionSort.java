import java.util.ArrayList;
import java.util.Collections;

/*
* 선택정렬(Selection Sort)
* 다음과 같은 순서를 반복하며 정렬하는 알고리즘
* 1. 주어진 데이터 중 최소값을 찾음
* 2. 해당 최소값을 데이터 맨 앞에 위치한 값과 교체함
* 3. 맨 앞의 위치를 뺸 나머지 데이터를 동일한 방법으로 반복함
* */
public class SelectionSort {
    public ArrayList<Integer> sort(ArrayList<Integer> dataList){
        int lowest;
        for(int i = 0 ; i < dataList.size() -1 ; i++){
            lowest = i;
            for(int j = i+1; j < dataList.size() ; j++){
                if(dataList.get(lowest) > dataList.get(j)){
                    lowest = j;
                }
            }
            Collections.swap(dataList, lowest, i);
        }
        return dataList;
    }



    public static void main(String[] args) {
        ArrayList<Integer> testData = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            testData.add((int)(Math.random()*100));
        }
        SelectionSort selectionSort = new SelectionSort();
        System.out.println(selectionSort.sort(testData));
    }
}
