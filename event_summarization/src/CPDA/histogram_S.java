package CPDA;

import EPDA.event;

import java.util.*;


/**
 * 每个边对应的直方图
 *
 * @author cheng
 * @create 2018-12-06 18:18
 */
public class histogram_S {
     public Map<Integer,Integer> L_C;
     public Map<Integer,Integer> apprL_C1;
     public Map<Integer,Integer> apprL_C2;
     public Double encoding_length;
     public Integer Sw;
     public Double p;

    public Map<Integer, Integer> getApprL_C2() {
        return apprL_C2;
    }

    public void setApprL_C2(Map<Integer, Integer> apprL_C2) {
        this.apprL_C2 = apprL_C2;
    }

    public Map<Integer, Integer> getApprL_C1() {
        return apprL_C1;
    }

    public void setApprL_C1(Map<Integer, Integer> apprL_C) {
        this.apprL_C1 = apprL_C;
    }

    public histogram_S(){

    }
    public Integer findMaxL(){
        List<Map.Entry<Integer,Integer>> list = new ArrayList(L_C.entrySet());
        Collections.sort(list, (o1, o2) -> (o1.getValue() - o2.getValue()));
        Integer maxL = list.get(0).getKey();
        return maxL;
    }
    public Map<Integer,Integer> getApproxmatedL_C(){
        Map<Integer,Integer> appr = new HashMap<Integer, Integer>();
        Integer all = 0;
        for(Integer key: L_C.keySet()){
            all+=L_C.get(key);
        }
        Integer appr_L = findMaxL();
        appr.put(appr_L,all);
        this.apprL_C1 = appr;
        return appr;
    }

    public Map<Integer,Integer> getApproxmatedL_C2(){
        Map<Integer,Integer> dddd = new HashMap<Integer, Integer>();
        Integer all = 0;
        for(Integer key: L_C.keySet()){
            all+=L_C.get(key);
        }
        Integer midall1 = all/2;
        Integer midall2 = all-midall1;
        jieguo  max1 = new jieguo(0,0);
        jieguo  max2 = new jieguo(0,0);

        for (Map.Entry<Integer, Integer> entry : L_C.entrySet()) {

           // System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            if(L_C.entrySet().size() == 1){
                dddd.put(0,0);
            }else {
                if (entry.getValue() > max1.number) {
                    max2.key = max1.key;
                    max2.number = max1.number;
                    max1.key = entry.getKey();
                    max1.number = entry.getValue();
                } else if (entry.getValue() == max1.number) {
                    max2.key = entry.getKey();
                    max2.number = entry.getValue();
                } else if (entry.getValue() > max2.number) {
                    max2.key = entry.getKey();
                    max2.number = entry.getValue();
                }
            }

        }
        dddd.put(max1.key,midall1);
        dddd.put(max2.key,midall2);
        this.apprL_C2 = dddd;
        return dddd;

    }

    public Double getEncoding_length(){
        Double length;
        Integer bestd = 0;
        Integer D1 = 0;
        Integer D1_key = 0;
        Collection<Integer> keycollection1 = apprL_C1.keySet();
        List<Integer> keylist1 = new ArrayList<Integer>(keycollection1);
        D1_key = keylist1.get(0);
        D1 = apprL_C1.get(D1_key)-L_C.get(D1_key);
        Integer D2 = 0;
        Integer D2_key1 = 0;
        Integer D2_key2 = 0;
        Collection<Integer> keycollection2 = apprL_C2.keySet();
        List<Integer> keylist2 = new ArrayList<Integer>(keycollection2);
        if(keylist2.size() == 1){
            length = Math.log(Sw)/Math.log(10) + Math.log(1) + (Double)(Math.log(apprL_C1.get(D1_key))/Math.log(10));
            this.p = (apprL_C1.get(D1_key))+0.0;
        }else{
            D2_key1 = keylist2.get(0);
            D2_key2 = keylist2.get(1);
            D2 = (apprL_C2.get(D2_key1)-L_C.get(D2_key1)) + (apprL_C2.get(D2_key2)-L_C.get(D2_key2));
            if(D1>=D2){
                bestd = D1;
            }else{
                bestd = D2;
            }
            Integer Sw = this.Sw;
            Integer Nnon;
            if(bestd == D1){
                Nnon = 1;
                this.p = (apprL_C1.get(D1_key))+0.0;
            }else{
                Nnon = 2;
                this.p = apprL_C2.get(D2_key1)+apprL_C2.get(D2_key2)+0.0;
            }
            Double lgCi;
            if(Nnon ==1){
                lgCi = (Double)(Math.log(apprL_C1.get(D1_key))/Math.log(10));
            }else{
                lgCi = (Double)((Math.log(apprL_C2.get(D2_key1))/Math.log(10))+(Math.log(apprL_C2.get(D2_key2))/Math.log(10)));
            }
            length =(Double)( Math.log(Sw)/Math.log(10) + Math.log(Nnon)/Math.log(10) + lgCi + bestd);
        }

        this.encoding_length = length;
        return length;
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
            if(j!= null) {

                map.put(j.key, j.number);
            }
        }
        return map;
    }
    public void start(){
        getApproxmatedL_C();
        getApproxmatedL_C2();
        getEncoding_length();
    }
}
