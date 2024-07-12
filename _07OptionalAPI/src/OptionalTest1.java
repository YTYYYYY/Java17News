import org.junit.Test;

import java.util.Optional;

/**
 * @author YTYYYY
 * @version 1.0
 * @date 2024/7/12-14:47
 */

public class OptionalTest1 {
    @Test
    public void te1(){
        String str = null;
        //1.实例化
        //ofNullable(T value)：用来创建一个Optional实例，value可能是空，也可能是非空
        Optional<String> optional = Optional.ofNullable(str);
        //orElse(T other)
        String otherStr = "other";
        String finalStr = optional.orElse(otherStr);
        System.out.println(finalStr.toString());
    }
}
