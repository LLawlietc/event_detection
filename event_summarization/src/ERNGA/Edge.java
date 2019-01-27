package ERNGA;

import CPDA.correlation_model;
import CPDA.on_segment;
import EPDA.On_Segment;
import EPDA.event;
import EPDA.periods_model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * bian
 *
 * @author cheng
 * @create 2018-12-10 16:00
 */
public class Edge {
    public ArrayList<String> VerticesOfEdge;
    public String edge;
    public int totallength;
//    public int getTotalLength(){
//        int i = events.get(events.size()-1).getTime();
//        int j = events.get(0).getTime();
//        return i-j;
//    }
    public Edge(int length){
        this.totallength = length;
    }
    public Edge(ArrayList<String> verticesOfEdge, String edge) {
        VerticesOfEdge = verticesOfEdge;
        this.edge = edge;
    }

    public ArrayList<String> getVerticesOfEdge() {
        return VerticesOfEdge;
    }

    public void setVerticesOfEdge(ArrayList<String> verticesOfEdge) {
        VerticesOfEdge = verticesOfEdge;
    }

    public String getEdge() {
        return edge;
    }

    public void setEdge(String edge) {
        this.edge = edge;
    }
    public void addEdge(periods_model pmodel){
        ArrayList<String> mid2 = new ArrayList<String>();
        String start = pmodel.getEvent();
        String end = pmodel.getEvent();
        int segment_length = 0;
        for(On_Segment segment:pmodel.getSegments()){
            int mid = segment.getEndtime()-segment.getStarttime();
            segment_length+=mid;
        }
        NumberFormat numberFormat = NumberFormat.getInstance();

        // 设置精确到小数点后2位

        numberFormat.setMaximumFractionDigits(2);

        String result = numberFormat.format((float) segment_length / (float) totallength * 100);
        //double prod = segment_length/totallength+0.0;
        int p = pmodel.getP();
        String allsegment = null;
        for(On_Segment segment:pmodel.getSegments()){
            String mid = "[" + segment.getStarttime()+","+segment.getEndtime()+"] ";
            allsegment = allsegment+mid;
        }
        String finallystr = "<"+p+","+result+"%,"+allsegment+">";
        mid2.add(start);
        mid2.add(end);
        this.VerticesOfEdge = mid2;
        this.edge = finallystr;
    }
    public void addEdge(correlation_model cmodel){
        ArrayList<String> mid2 = new ArrayList<String>();
        String start = cmodel.getStartevent();
        String end = cmodel.getEndevent();
        int segment_length = 0;
        for(on_segment segment:cmodel.getOn_segments()){
            int mid = segment.getEndtime()-segment.getStarttime();
            segment_length+=mid;
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位

        numberFormat.setMaximumFractionDigits(2);

        String result = numberFormat.format((float) segment_length / (float) totallength * 100);
        //double prod = segment_length/totallength+0.0;
        Double p = cmodel.getP();
        String allsegment = null;
        for(on_segment segment:cmodel.getOn_segments()){
            String mid = "[" + segment.getStarttime()+","+segment.getEndtime()+"] ";
            allsegment = allsegment+mid;
        }
        String finallystr = "<"+p+","+result+"%,"+allsegment+">";
        mid2.add(start);
        mid2.add(end);
        this.VerticesOfEdge = mid2;
        this.edge = finallystr;
    }
}
