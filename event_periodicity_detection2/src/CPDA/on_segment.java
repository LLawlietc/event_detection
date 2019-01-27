package CPDA;

/**
 * 相关模式段
 *
 * @author cheng
 * @create 2018-12-10 15:15
 */
public class on_segment {
    private int starttime;
    private int endtime;

    public on_segment(Integer starttime, Integer endtime){
        this.starttime = starttime;
        this.endtime = endtime;
    }
    public int getStarttime() {
        return starttime;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }

    public int getEndtime() {
        return endtime;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
    }
}
