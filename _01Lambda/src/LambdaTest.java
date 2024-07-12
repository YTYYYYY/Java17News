import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author YTYYYY
 * @version 1.0
 * @date 2024/7/6-9:39
 */

public class LambdaTest {
    @Test
    public void te1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("r1 is running");
            }
        };
        r1.run();

        //Lambda表达式写法
        Runnable r2 = () -> {
            System.out.println("r2 is running");
        };

        r2.run();
    }

    @Test
    public void te2(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };

        System.out.println(com1.compare(21,22));

        //Lambda写法
        Comparator<Integer> com2 = (o1,o2) -> Integer.compare(o1,o2);

        System.out.println(com2.compare(21,22));

        //方法引用
        Comparator<Integer> com3 = Integer::compare;
        System.out.println(com3.compare(21,22));
    }

    @Test
    public void te3(){
        Consumer<String> com1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        com1.accept("ok");

        //Lambda写法
        Consumer<String> com2 = (s) -> {
            System.out.println(s);
        };
        com2.accept("ok");
    }
}
