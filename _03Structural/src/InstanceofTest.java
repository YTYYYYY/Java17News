import org.junit.Test;

/**
 * @author YTYYYY
 * @version 1.0
 * @date 2024/7/6-16:29
 */

public class InstanceofTest {
    @Test
    public void te1(){
        Object str = new String("Hello");
        if (str instanceof String s){
            System.out.println(s);
        }
    }
}
