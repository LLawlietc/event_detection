package CPDA;

/**
 * A的时间，A的顺序索引
 *
 * @author cheng
 * @create 2018-12-06 17:05
 */
public class A_location {
    public Integer A_time;
    public Integer A_index;

    public Integer getA_time() {
        return A_time;
    }

    public void setA_time(Integer a_time) {
        A_time = a_time;
    }

    public Integer getA_index() {
        return A_index;
    }

    public void setA_index(Integer a_index) {
        A_index = a_index;
    }


    @Override
    public String toString(){
        return "A_index = " + A_index + " A_time = "+ A_time;
    }
}
