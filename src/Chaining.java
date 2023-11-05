/*
 * 1. Chaining 기법
 * - 개방 해슁 또는 open Hashing 기법 중 하나
 *   해쉬테이블 저장공간 외의 공간을 활용하는 기법
 * - 충돌이 일어나면 링크드리스트를 사용해 데이터를 추가로 뒤에 연결시켜 저장하는 기법
* */
public class Chaining {
    public Slot[] hashTable;

    public Chaining(Integer size){
        this.hashTable = new Slot[size];
    }

    public class Slot {
        String key;
        String value;
        // 링크드리스트의 포인터값을 위한 변수 선언
        Slot next;
        
        // 생성자 생성
        Slot(String key, String value){
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
    public int hashFunc(String key) {
        return (int)(key.charAt(0)) % this.hashTable.length;
    }

    public boolean saveData(String key, String value){
        Integer address =this.hashFunc(key);
        if(this.hashTable[address] != null){
            // 링크드리스트일 수도 있으니 순회할 수 있도록 두가지 슬롯 선언
            // 링크드리스트의 head 선언 개념
            Slot findSlot = this.hashTable[address];
            Slot prevSlot = this.hashTable[address];
            while(findSlot != null){
                if(findSlot.key == key){    // findSlot의 key 가 내가 찾는 key  라면
                    // findSlot의 value를 내가 저장할 value로 변경
                    findSlot.value = value;
                    return true;
                } else {
                    prevSlot = findSlot;
                    findSlot = findSlot.next;
                }
            }
           prevSlot.next = new Slot(key, value);
        } else {
            this.hashTable[address] = new Slot(key, value);
        }
        return true;
    }

    public String getData(String key) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
            Slot findSlot = this.hashTable[address];
            while (findSlot != null) {
                if (findSlot.key == key) {
                    return findSlot.value;
                } else {
                    findSlot = findSlot.next;
                }
            }
            return null;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Chaining mainObject = new Chaining(20);
        mainObject.saveData("DaveLee", "01022223333");
        mainObject.saveData("fun-coding", "01033334444");
        mainObject.saveData("David", "01044445555");
        mainObject.saveData("Dave", "01055556666");
        System.out.println(mainObject.getData("fun-coding"));
        System.out.println(mainObject.getData("DaveLee"));
        System.out.println(mainObject.getData("David"));
        System.out.println(mainObject.getData("Dave"));
    }
}
