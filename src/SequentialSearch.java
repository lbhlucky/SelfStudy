import java.util.ArrayList;
public class SequentialSearch {
    /*
    임의 배열이 있을 때, 원하는 데이터의 위치를 리턴하는 순차탐색 알고리즘 작성해보기
    가장 기본적인 방법이므로, 직접 작성해보겠습니다.
    - 원하는 데이터가 리스트에 없을 경우 -1을 리턴
    - Math.random() 메서드를 사용해서, 0 과 100 사이의 랜덤 값을 10 개 가진 testData 만들기
    */
    public int search(ArrayList<Integer> dataList, int data){
        for(int i = 0 ; i < dataList.size() ; i++){
            if(dataList.get(i).equals(data)){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        SequentialSearch sequentialSearch = new SequentialSearch();
        ArrayList<Integer> dataSet = new ArrayList<Integer>();
        for(int i = 0 ; i < 10 ; i++){
            dataSet.add((int)(Math.random()*100));
        }
        // 해당 데이터가 있으면 해당인덱스번호 출력
        // 없으면 -1 출력
        System.out.println("dataSet 배열에 98 있나요? "+sequentialSearch.search(dataSet, 98));
    }
}
