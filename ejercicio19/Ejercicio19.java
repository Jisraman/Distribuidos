import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Ejercicio19 implements Comparator{
     Map<String, Integer> chars;   
     List<Entry<String, Integer>> list;

    public Ejercicio19(){
        chars = new HashMap<>(); 
    } 

    public void read() {
        try (FileReader fr = new FileReader("./BIBLIA_COMPLETA.txt")) {
            BufferedReader br = new BufferedReader(fr);
            int c;
            while ((c = br.read()) != -1) {
                String charStr = Character.toString((char) c);
                chars.put(charStr, chars.getOrDefault(charStr, 0) + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Ordena el hashmap de caracteres
    @Override
    public void sort() {
        
        this.list = new ArrayList<>(chars.entrySet());

        this.list.sort(Entry.<String, Integer>comparingByValue().reversed());

        
    }


    public void printHashMap(){

        for(Entry<String, Integer> l : list){
            System.out.print("\n[" + l.getKey() + ":" + l.getValue() + "]\n");
        }
    }
    

    public static void main(String[] args)
    {
        try {
            Ejercicio19 hashmap = new Ejercicio19();

            long startTime = System.nanoTime();    

            System.nanoTime();
            hashmap.read();
            //
            hashmap.sort();
            // ... the code being measured ...    
            long estimatedTime = System.nanoTime() - startTime;
            System.out.println("Caracteres Ordenados en orden de Frecuencia en : " + estimatedTime + " Nano segundos");
            hashmap.printHashMap();

        } catch (Exception e) {
            System.err.println("Execution Interrupted");
        }
    }
    
}