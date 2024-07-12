import org.junit.Test;

import java.io.CharArrayReader;
import java.lang.invoke.CallSite;

/**
 * @author YTYYYY
 * @version 1.0
 * @date 2024/7/12-14:07
 */

public class SwitchTest1 {

    enum Week{
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    @Test
    public void te1(){
        //before jdk12
        Week day = Week.FRIDAY;
        switch (day){
            case MONDAY: {
                System.out.println(1);
                break;
            }
            case TUESDAY: {
                System.out.println(2);
                break;
            }
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
            case SATURDAY:
            case SUNDAY:{
                System.out.println(7);
                break;
            }
        }

        //after jdk12 (省去了break，避免case穿透)
        switch (day){
            case MONDAY -> System.out.println(1);
            case TUESDAY -> System.out.println(2);
            case WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY -> System.out.println(7);
        }
        int res = switch (day){
            case MONDAY -> 1;
            case TUESDAY -> 2;
            case WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY -> 7;
        };

        //jdk13
        int res2 = switch (day){
            case MONDAY -> {yield 1;}
            case TUESDAY -> {yield 2;}
            case WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY -> {yield 7;}
        };
    }
}
