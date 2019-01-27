package EPDA;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

/**
 * generatedata
 *
 * @author cheng
 * @create 2018-12-04 10:15
 */
public class Generatedata {
    public static void main(String[] args) throws FileNotFoundException {
        //String s = "CDEFGHIJ";
        char[] s = {'A','B'};
        int minMod = 50;
        int maxMod = 5000;
        int n = 500;
        int lag = 0;
        Scanner cin = new Scanner(System.in);
        lag = cin.nextInt();
        long t = 100;
        FileOutputStream out=new FileOutputStream("D:\\code_house\\java\\1.txt");
        PrintStream p=new PrintStream(out);
        Random rand = new Random();
        for (int i = 0; i < n; ++i) {
            if (Math.abs(rand.nextInt()) % 10 <= 3) {
                p.println(t + " " + "A");
                t += lag + (Math.abs(rand.nextInt())) % (lag / 100);
                p.println(t + " " + "B");
            } else {
                p.println(t + " " + s[Math.abs(rand.nextInt()) % 8]);
                if (Math.abs(rand.nextInt()) % 5 <= 2) {
                    t += Math.abs(rand.nextInt()) % maxMod + 1;
                } else {
                    t += Math.abs(rand.nextInt()) % minMod + 1;
                }
                p.println(t + " " + s[Math.abs(rand.nextInt()) % 8]);
            }
            if (Math.abs(rand.nextInt()) % 5 <= 2) {
                t += Math.abs(rand.nextInt()) % maxMod + 1;
            } else {
                t += Math.abs(rand.nextInt()) % minMod + 1;
            }
        }
    }
}
