import org.junit.Test;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @author YTYYYY
 * @version 1.0
 * @date 2024/7/6-16:23
 */

public class VarTest {
    @Test
    public void te1(){
        //局部变量实例化
        var list = new ArrayList<String>();
        var map = new LinkedHashMap<>();

        //增强for循环的索引
        for(var i : list){
            System.out.println(i);
        }

        //传统for循环
        for(var i=0;i<100;i++){
            System.out.println(i);
        }
    }
}
