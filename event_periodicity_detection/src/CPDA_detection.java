import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
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
     Graph finallyGraph;
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

    public  ArrayList<event> getInitialData(){
        ArrayList<event> arrayList = new ArrayList<event>();
        for(int i=0;i<events.size();i++){
            event eve = events.get(i);
            if(eve.getType().equals("A") || eve.getType().equals("E")){
                arrayList.add(eve);
            }
        }
        int j = 0;
        while(arrayList.get(j).getType().equals("E") ){
            arrayList.remove(j);
            j++;
        }
        int k = arrayList.size()-1;
        while(arrayList.get(k).getType().equals("A")){
            arrayList.remove(k);
            k--;
        }
        this.initialData = arrayList;
        return arrayList;
    }

    public ArrayList<A_location> getA_locations(){
        ArrayList<A_location> a_locations = new ArrayList<A_location>();
        for(int i=0;i<initialData.size();i++){
            if(initialData.get(i).getType().equals("A")){
                A_location mid = new A_location();
                mid.A_index = i;
                mid.A_time = initialData.get(i).getTime();
                a_locations.add(mid);
            }
        }
        this.a_locations = a_locations;
        return a_locations;
    }
    public Graph getInitialGraph() {
        int nodenum = a_locations.size();
        Graph initialGraph = new Graph(nodenum, false);
        this.finallyGraph = initialGraph;
        return initialGraph;
    }
    public void start(String location) throws IOException{
        read(location);
        getInitialData();
        getA_locations();
        getInitialGraph();
    }

}

