package CPDA;

import EPDA.event;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 算法2
 *
 * @author cheng
 * @create 2018-12-06 15:32
 */
public class CPDA_detection {
     ArrayList<event> events;
     ArrayList<event> initialData;
     ArrayList<A_location> a_locations;
     Double time_tolerance;
     Graph finallyGraph;
     public CPDA_detection(){

     }
    public ArrayList<event> read(String location) throws IOException {
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

    public  ArrayList<event> getInitialData(String a, String b){
        ArrayList<event> arrayList = new ArrayList<event>();
        for(int i=0;i<events.size();i++){
            event eve = events.get(i);
            if(eve.getType().equals(a) || eve.getType().equals(b)){
                arrayList.add(eve);
            }
        }
        int j = 0;
        while(arrayList.get(j).getType().equals(b) ){
            arrayList.remove(j);
            j++;
        }
        int k = arrayList.size()-1;
        while(arrayList.get(k).getType().equals(a)){
            arrayList.remove(k);
            k--;
        }
        this.initialData = arrayList;
        return arrayList;
    }

    public ArrayList<A_location> getA_locations(){
        ArrayList<A_location> a_locations = new ArrayList<A_location>();
        ArrayList<A_location> finallya_locations = new ArrayList<A_location>();
        for(int i=0;i<initialData.size()-1;i++){
            if(initialData.get(i).getType().equals("A")){
                A_location mid = new A_location();
                mid.A_index = i;
                mid.A_time = initialData.get(i).getTime();
                a_locations.add(mid);
            }
        }
        //合并aaaaaa
        int fin_index = 0;
        int current = a_locations.get(fin_index).getA_index();
        for(int i=1;i<a_locations.size();i++){
            if(a_locations.get(i).getA_index() == current+1){
                current =a_locations.get(i).getA_index();
            }else{
                finallya_locations.add(a_locations.get(fin_index));
                fin_index =i;
                current = a_locations.get(fin_index).getA_index();
            }
        }
        this.a_locations = finallya_locations;
        return finallya_locations;
    }
    public Graph getInitialGraph() {
        int nodenum = a_locations.size();
        Graph initialGraph = new Graph(nodenum, false);
        this.finallyGraph = initialGraph;
        return initialGraph;
    }
    public void start(String location ,String a,String b) throws IOException{
        read(location);
        getInitialData(a,b);
        getA_locations();
        getInitialGraph();
    }

}

