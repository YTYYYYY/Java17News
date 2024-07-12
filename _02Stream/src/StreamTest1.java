import org.junit.Test;

import java.lang.annotation.Retention;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author YTYYYY
 * @version 1.0
 * @date 2024/7/6-14:00
 */

public class StreamTest1 {
    @Test
    public void te1(){
        ListData.getListData().stream()
                .filter(data -> data.length()==3)
                .limit(5)
                .distinct()
                .forEach(System.out::println);

        ListData.getListData().stream()
                .map(str -> "#"+str)
                .forEach(System.out::println);

        ListData.getListData().parallelStream();

        Arrays.stream(ListData.getArrData());

        Comparator<Integer> com1 = (i1,i2) -> i2-i1 ;
        Arrays.stream(ListData.getArrData())
                .sorted(com1)
                .forEach(System.out::println);

        Stream.of("a", "b", "c", "d");
    }
    @Test
    public void te2(){
        System.out.println(ListData.getListData().stream()
                .anyMatch(data -> data.length() == 3));
        System.out.println(ListData.getListData().stream()
                .findFirst().get());

        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream()
                .reduce(0, Integer::sum));
    }
    @Test
    public void te3(){
        List<Integer> collect = Arrays.stream(ListData.getArrData())
                .sorted()
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
