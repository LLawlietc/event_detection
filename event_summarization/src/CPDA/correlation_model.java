package CPDA;

import EPDA.On_Segment;

import java.util.ArrayList;

/**
 * 相关模式
 *
 * @author cheng
 * @create 2018-12-10 15:14
 */
public class correlation_model {
    public String startevent;
    public String endevent;
    public Double p;
    public int time_tolerance;
    public ArrayList<on_segment> on_segments;

    public correlation_model() {

    }

    public String getStartevent() {
        return startevent;
    }

    public void setStartevent(String startevent) {
        this.startevent = startevent;
    }

    public String getEndevent() {
        return endevent;
    }

    public void setEndevent(String endevent) {
        this.endevent = endevent;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public int getTime_tolerance() {
        return time_tolerance;
    }

    public void setTime_tolerance(int time_tolerance) {
        this.time_tolerance = time_tolerance;
    }

    public ArrayList<on_segment> getOn_segments() {
        return on_segments;
    }

    public void setOn_segments(ArrayList<on_segment> on_segments) {
        this.on_segments = on_segments;
    }

    public correlation_model(String startevent, String endevent, Double p, int time_tolerance, ArrayList<on_segment> on_segments) {
        this.startevent = startevent;
        this.endevent = endevent;
        this.p = p;
        this.time_tolerance = time_tolerance;
        this.on_segments = on_segments;
    }

    public void addSegments(on_segment segment) {
        on_segments.add(segment);
    }
}
