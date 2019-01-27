
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;

/**
 * detection
 *
 * @author cheng
 * @create 2018-12-04 9:19
 */
public class detection {
    ArrayList<event> events;
    ArrayList<Integer> timeData;
    ArrayList<Integer> arrivalTime;
    ArrayList<Double> probabilities;
    ArrayList<Integer> threshold;
    ArrayList<Integer> totalArrivals;
    ArrayList<Double> chiSquared;
    ArrayList<Boolean> isConfidence;
    double mean_arrival;
    int time_tolerance;
    public detection(int time_tolerance){
        this.time_tolerance = time_tolerance;
    }
    public  ArrayList<event> read(String location) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(location));
        //创建集合对象
        ArrayList<event> array = new ArrayList<event>();
        String line;
        while((line = br.readLine()) != null) {
            //分割字符串,分割后的元素存储到数组中,以","为分界点
            String[] strArray = line.split(" ");
            event eve = new event();
            eve.setTime(Integer.parseInt(strArray[0]));
            eve.setType(strArray[1]);
            array.add(eve);
        }
        br.close();
        this.events = array;
        return array;
    }
    public  void first_show(){
        for(int x=0;x<events.size();x++) {
            event eve = events.get(x);
            System.out.println(eve.getTime()+"\t"+eve.getType());
        }
    }
    public  ArrayList<Integer> getTimeData(){
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        for(int i = 0;i< events.size();i++){
            event eve = events.get(i);
            if(eve.getType().equals("A")){
                integerArrayList.add(eve.getTime());
            }
        }
        this.timeData = integerArrayList;
        return integerArrayList;
    }
    public  void next_show(){
        for(int i = 0;i<timeData.size();i++){
            Integer integer = timeData.get(i);
            System.out.println(integer);
        }
    }
    public  ArrayList<Integer> getArrivalTime(){
        ArrayList<Integer> arrivaltime = new ArrayList<Integer>();
        for(int i=0;i<=timeData.size()-2;i++){
            Integer time = timeData.get(i+1)-timeData.get(i);
            arrivaltime.add(time);
        }
        this.arrivalTime = arrivaltime;
        return arrivaltime;
    }
    public  ArrayList<Double> getprobabilities(){
        ArrayList<Double> probabilities = new ArrayList<Double>();
        double mean_arrival = get_mean_arrival();
        for(int i = 0;i<arrivalTime.size();i++){
            double p = 2*time_tolerance*mean_arrival*Math.exp(-mean_arrival*arrivalTime.get(i));
            probabilities.add(p);
        }
        this.probabilities = probabilities;
        return probabilities;
    }
    public   double get_mean_arrival(){
        double n = arrivalTime.size();
        double t = timeData.get(timeData.size()-1) - timeData.get(0);
        this.mean_arrival = n/t;
        return n/t;
    }
    public  ArrayList<Integer> getThreshold() {
        ArrayList<Integer> threshold = new ArrayList<Integer>();
        for(int i=0;i<probabilities.size();i++){
            int c = 0;
            if(isConfidence.get(i) == true){
               c = (int)(Math.sqrt(3.84*arrivalTime.size()*probabilities.get(i)*(1-probabilities.get(i)))
                       + arrivalTime.size()*probabilities.get(i));
            }else{
                c=-1;
            }
            threshold.add(c);
        }
        this.threshold = threshold;
        return threshold;
    }
    public  ArrayList<Double> getChiSquared(){
        ArrayList<Double> chiSquared = new ArrayList<Double>();
        for(int i=0;i<arrivalTime.size();i++){
            double a = Math.pow(totalArrivals.get(i) - arrivalTime.size()*probabilities.get(i),2);
            double b = arrivalTime.size()*probabilities.get(i)*(1 - probabilities.get(i));
            double X = a/b;
            chiSquared.add(X);
        }
        this.chiSquared = chiSquared;
        return chiSquared;
    }
    public  ArrayList<Integer> getTotalArrivals(){
        ArrayList<Integer> totalArrivals = new ArrayList<Integer>();
        for(int i=0;i<arrivalTime.size();i++){
            int number = 0;
            int p = arrivalTime.get(i);
            for(int j=0;j<arrivalTime.size();j++){
                if(arrivalTime.get(j)<=(p+time_tolerance)&&arrivalTime.get(j)>=(p-time_tolerance)){
                    number++;
                }
            }
           // number = number-1;
            totalArrivals.add(number);
        }
        this.totalArrivals = totalArrivals;
        return totalArrivals;
    }
    public ArrayList<Boolean> getIsConfidence(){
        ArrayList<Boolean> isconfidence = new ArrayList<Boolean>();
        for(int i=0;i<chiSquared.size();i++){
            boolean b = false;
            if(chiSquared.get(i) > 3.841){
                b = true;
            }else{
                b = false;
            }
            isconfidence.add(b);
        }
        this.isConfidence = isconfidence;
        return isconfidence;
    }
    public LinkedHashMap<Integer,ArrayList<Integer>> start(String location) throws IOException{
        read(location);
        getTimeData();
        getArrivalTime();
        getprobabilities();
        getTotalArrivals();
        getChiSquared();
        getIsConfidence();
        getThreshold();
        //System.out.println(timeData);
        LinkedHashMap<Integer,ArrayList<Integer>> mapofsegment = new LinkedHashMap<>();
        for(int i=0;i<arrivalTime.size();i++){
            int p = arrivalTime.get(i);
            ArrayList<Integer> mid = new ArrayList<Integer>();
            if(isConfidence.get(i) == true){
                if(totalArrivals.get(i) >= threshold.get(i)){
                    for(int j=0;j < arrivalTime.size();j++){
                        if(arrivalTime.get(j)<=(p+time_tolerance)&&arrivalTime.get(j)>=(p-time_tolerance)){
                            mid.add(j);
                        }
                    }
                }
            }
            Collections.sort(mid);
            if(!mid.isEmpty()){
                mapofsegment.put(i,mid);
            }
        }
        //System.out.println(mapofsegment);
        return mapofsegment;
    }
}
