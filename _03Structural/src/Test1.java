import org.junit.Test;

import java.io.*;

/**
 * @author YTYYYY
 * @version 1.0
 * @date 2024/7/6-16:05
 */

public class Test1 {
    @Test
    public void te1(){
        InputStreamReader isr = new InputStreamReader(System.in);
        try(isr) {
            char[] chs = new char[1024];
            int len;
            while((len = isr.read(chs)) != -1){
                System.out.print(String.valueOf(chs,0,len));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
