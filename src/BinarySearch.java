import java.util.ArrayList;
import java.util.Collections;

/*
* 이진탐색
* - 탐색할 자료를 둘로 나누어 해당 데이터가 있을만한 곳을 탐 색하는 방법
* */
public class BinarySearch {
    // 데이터가 있으면 true
    // 없으면 false
    public boolean search(ArrayList<Integer> dataList , int data){
        if(dataList.size() == 1 && data == dataList.get(0)){
            return true;
        }
        if(dataList.size() == 1 && data != dataList.get(0)){
            return false;
        }
        if(dataList.size() < 1){
            return false;
        }

        int center = dataList.size() / 2;
        if(data == dataList.get(center)){
            return true;
        }else if(data < dataList.get(center)){
            return this.search(new ArrayList<Integer>(dataList.subList(0, center)), data);
        }else{
            return this.search(new ArrayList<Integer>(dataList.subList(center, dataList.size())), data);
        }
    }
    public static void main(String[] args){
        ArrayList<Integer> dataSet = new ArrayList<Integer>();
        for(int i = 0 ; i < 25 ; i++){
            dataSet.add((int)(Math.random()*100));
        }

        // 이진탐색은 정렬된 상태에서 진행되므로 Collections.sort()를 활용해 정렬해준다.
        // 정렬하지않으면 찾는 데이터가 배열에 있어도 못찾을 수 있음
        Collections.sort(dataSet);
        System.out.println("< dataSet > \n"+dataSet);
        BinarySearch binarySearch = new BinarySearch();
        System.out.println("배열에 28있나요 ? "+binarySearch.search(dataSet, 28));
    }
}
