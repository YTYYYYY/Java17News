import java.util.*;

/**
 * @author YTYYYY
 * @version 1.0
 * @date 2024/7/6-14:01
 */

public class ListData {
    public static Map<Integer,String> getMapData(){
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"001");
        map.put(2,"002");
        map.put(3,"001");
        map.put(4,"002");
        map.put(5,"001");
        map.put(6,"002");
        return map;
    }
    public static List<String> getListData(){
        List<String> list = new ArrayList<>();
        list.add("001");
        list.add("0002");
        list.add("003");
        list.add("004");
        list.add("005");
        list.add("005");
        return list;
    }

    public static Integer[] getArrData(){
        return new Integer[] {1,2,5,4,2,1};
    }
}
