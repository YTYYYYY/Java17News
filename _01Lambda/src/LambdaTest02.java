import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author YTYYYY
 * @version 1.0
 * @date 2024/7/6-10:33
 */

public class LambdaTest02 {
    @Test
    public void te1(){
        Consumer<String> con1 = System.out::println;
        con1.accept("ok");
    }

    @Test
    public void te2(){
        Comparator<Integer> com1 = Integer::compare;
        System.out.println(com1.compare(12,21));

        Function<Double, Long> fun1 = Math::round;

    }
    @Test
    public void te3(){
        Comparator<String> com1 = String::compareTo;

        BiPredicate<String,String> bip = String::equals;
        System.out.println(bip.test("ok","ok"));
    }
}
