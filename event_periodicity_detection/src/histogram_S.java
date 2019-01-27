import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 每个边对应的直方图
 *
 * @author cheng
 * @create 2018-12-06 18:18
 */
public class histogram_S {
    Map<Integer,Integer> L_C;

    public Integer findMaxL(){
        Integer maxIndex = 0;

        return maxIndex;
    }
    public Integer getApproxmated(){
        Integer all= 0;

        return all;
    }

    public histogram_S(Map<Integer, Integer> l_C) {
        L_C = l_C;
    }

    public static Map<Integer, Integer> getneedmap(ArrayList<event> list){

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        ArrayList<Integer> chalist = new ArrayList<Integer>();

        if(list.size()>1){

            for(int i=0;i<list.size()-1;i++){

                if(list.get(i).getType().equals("A")&&list.get(i+1).getType().equals("E")){

                    chalist.add(list.get(i+1).getTime()-list.get(i).getTime());

                    //System.out.println("777:"+(list.get(i+1).getTime()-list.get(i).getTime()));

                }
            }
        }
        //处理chalist
        ArrayList<jieguo> mylist = new ArrayList<jieguo>();

        jieguo aaa = new jieguo(chalist.get(0),1);
        mylist.add(aaa);

        for(int i=1;i<chalist.size();i++){

            for(int j=0;j<mylist.size();j++){

                if(chalist.get(i)==mylist.get(j).key){

                    mylist.get(j).number++;
                    break;

                }else if(j==mylist.size()-1){

                    jieguo aa = new jieguo(chalist.get(i),1);
                    mylist.add(aa);
                    break;
                }
            }
        }

        for(jieguo j:mylist){

            map.put(j.key, j.number);

        }
        return map;
    }
}
