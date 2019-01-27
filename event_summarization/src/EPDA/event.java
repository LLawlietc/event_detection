package EPDA;

/**
 * detection.event
 *
 * @author cheng
 * @create 2018-12-04 10:15
 */
public class event {
     public Integer time;
   public  String type;
    public Integer getTime() {
        return time;
    }
    public void setTime(Integer time) {
        this.time = time;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString(){
        String s = "detection.event = "+type+" time = "+time;
        return s;
    }
}
