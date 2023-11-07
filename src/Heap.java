/*
* 힙(Heap)
* 데이터에서 최대값과 최소값을 빠르게 찾기 위해 고안된 완전이진트리(Complete Binary Tree)
*   `완전이진트리 : 노드를 삽입할 때 최하단 왼쪽 노드부터 차례대로 삽입하는 트리
*
* - 배열에 데이터를 넣고, 최대/최소값을 찾으려면 O(n)이 걸림
* - 힙에 데이터를 넣고, 최대/최소값을 찾으면 O(logn)이 걸림
* - 우선순위 큐와 같이 최대/최소 값을 빠르게 찾아야하는 자료구조 및 알고리즘 구현에 활용
*
* 힙과 이진탐색트리의 차이점
* - 힙은 각 노드의 값이 자식 노드보다 크거나 같음(Max Heap의 경우)
* - 이진탐색트리는 왼쪽 자식 노드의 값이 가장 작고, 그 다음 부모 노드, 그 다음 오른쪽 자식 노드 값이 가장 큼
* - 힘은 이진 탐색 트리의 조건인 자식 노드에서 작은 값은 왼쪽, 큰 값은 오른쪽이라는 조건은 없음
*   > 힙의 왼쪽 및 오른쪽 자식 노드의  값은 오른쪽이 클 수 도 있고, 왼쪽이 클 수도 있음
* - 이진 탐색 트리는 탐색을 위한 구조, 힙은 최대/최소값 검색을 위한 구조 중 하나로 이해하면 됨
*
* 특정 노드의 관련 노드 위치 알아내기
* - 부모 노드 인덱스 번호 (parent node's index) = 자식 노드 인덱스 번호 (child node's index) / 2
* - 왼쪽 자식 노드 인덱스 번호 (left child node's index) = 부모 노드 인덱스 번호 (parent node's index) * 2
* - 오른쪽 자식 노드 인덱스 번호 (right child node's index) = 부모 노드 인덱스 번호 (parent node's index) * 2 + 1

* */
import java.util.ArrayList;
import  java.util.Collections;
public class Heap {
    // 보통 내부 attribute 는 private 으로 선언하기도 하지만, 외부에서도 간단히 데이터를 확인할 수 있도록 public 으로 선언
    public ArrayList<Integer> heapArray = null;

    public Heap (Integer data){
        heapArray = new ArrayList<Integer>();

        heapArray.add(null);
        heapArray.add(data);
    }

    public boolean move_up(Integer inserted_idx){
        if(inserted_idx <= 1){
            return false;
        }else {
            Integer parent_idx = inserted_idx / 2;
            if(this.heapArray.get(inserted_idx) > this.heapArray.get(parent_idx)){
                return true;
            } else {
                return false;
            }
        }
    }
    public boolean insert(Integer data){
        Integer inserted_idx, parent_idx;
        if(heapArray == null){
            heapArray = new ArrayList<Integer>();

            heapArray.add(null);
            heapArray.add(data);
            return true;
        }
        this.heapArray.add(data);
        inserted_idx = this.heapArray.size() - 1;

        while(this.move_up(inserted_idx)){
            parent_idx = inserted_idx / 2;
            Collections.swap(this.heapArray, inserted_idx, parent_idx);
            inserted_idx = parent_idx;
        }
        return true;
    }

    // 힙에서는 최상단 노드(root 노드)를 삭제하는 것이 일반적임
    public Integer pop(){
        Integer returned_data, popped_idx, left_child_popped_idx, right_child_popped_idx;

        if(this.heapArray == null){
            return null;
        } else {
            returned_data = this.heapArray.get(1);
            this.heapArray.set(1,this.heapArray.get(this.heapArray.size() - 1));
            this.heapArray.remove(this.heapArray.size() - 1);
            popped_idx = 1;

            while(this.move_down(popped_idx)){
                left_child_popped_idx = popped_idx * 2;
                right_child_popped_idx = popped_idx * 2 + 1;

                // Case 2 : 오른쪼 자식 노드만 없을 때
                if(right_child_popped_idx >= this.heapArray.size()){
                    if(this.heapArray.get(popped_idx) < this.heapArray.get(left_child_popped_idx)){
                        Collections.swap(heapArray, popped_idx, left_child_popped_idx);
                        popped_idx = left_child_popped_idx;
                    }
                // Case 3 : 왼/오른쪽 자식 노드가 모두 있을 때
                } else {
                    if(this.heapArray.get(left_child_popped_idx) > this.heapArray.get(right_child_popped_idx)){
                        if(this.heapArray.get(popped_idx)<this.heapArray.get(left_child_popped_idx)){
                           Collections.swap(heapArray, popped_idx, left_child_popped_idx);
                           popped_idx = left_child_popped_idx;
                        }
                    } else {
                        if(this.heapArray.get(popped_idx)<this.heapArray.get(right_child_popped_idx)){
                            Collections.swap(heapArray, popped_idx, right_child_popped_idx);
                            popped_idx = right_child_popped_idx;
                        }
                    }
                }
            }
            return returned_data;
        }
    }

    public boolean move_down(Integer popped_idx){
        Integer left_child_popped_idx, right_child_popped_idx;

        left_child_popped_idx = popped_idx * 2;
        right_child_popped_idx = popped_idx* 2 + 1;

        // Case 1 : 왼쪽 자식 노드도 없을 때 (자식 노드가 하나도 없을 때)
        if(left_child_popped_idx >= this.heapArray.size()){
            return false;
        // Case 2 : 오른쪽 자식 노드만 없을 때
        } else if(right_child_popped_idx >= this.heapArray.size()){
            if(this.heapArray.get(popped_idx) < this.heapArray.get(left_child_popped_idx)){
                return true;
            } else {
                return false;
            }
        // Case 3 : 왼/오른쪽 자식 노드가 모두 있을 때
        }else {
            if(this.heapArray.get(left_child_popped_idx) > this.heapArray.get(right_child_popped_idx)){
                if(this.heapArray.get(popped_idx)<this.heapArray.get(left_child_popped_idx)){
                    return true;
                } else{
                    return false;
                }
            } else {
                if(this.heapArray.get(popped_idx)<this.heapArray.get(right_child_popped_idx)){
                    return true;
                } else{
                    return false;
                }
            }
        }
    }


    public static void main(String[] args) {
      Heap heapTest = new Heap(1);
        System.out.println(heapTest.heapArray);

        System.out.println("---------------------------------------------");
        Heap heapTest1 = new Heap(1);
        heapTest1.insert(2);
        heapTest1.insert(3);
        heapTest1.insert(4);
        heapTest1.insert(5);

        System.out.println(heapTest1.heapArray);
        System.out.println("---------------------------------------------");

        Heap heapTest2 = new Heap(15);
        heapTest2.insert(10);
        heapTest2.insert(8);
        heapTest2.insert(5);
        heapTest2.insert(4);
        heapTest2.insert(20);
        System.out.println(heapTest2.heapArray);
        System.out.println("---------------------------------------------");

        Heap heapTest3 = new Heap(15);
        heapTest3.insert(10);
        heapTest3.insert(8);
        heapTest3.insert(5);
        heapTest3.insert(4);
        heapTest3.insert(20);
        System.out.println(heapTest3.heapArray);

        heapTest3.pop();
        System.out.println(heapTest3.heapArray);
    }
}
