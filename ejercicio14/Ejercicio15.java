import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
public class Ejercicio15 implements Comparator{
    Map<String, Integer> chars;   

    public Ejercicio15(){
        chars = new HashMap<>(); 
    }
    

    public void read() {
        try (FileReader fr = new FileReader("./El_viejo_y_el_mar.txt")) {
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


    public void printHashMap(){

        chars.forEach((key, value) -> {
            System.out.print( "\n \t " + key + " : " + value + "\n");
        });
    }

    //Ordena el hashmap de caracteres
    @Override
    public void sort() {
        
        List<Entry<String, Integer>> list = new ArrayList<>(chars.entrySet());

        list.sort(Entry.<String, Integer>comparingByValue().reversed());

        for(Entry<String, Integer> l : list){
            System.out.print("\n\t" + l.getKey() + " : " + l.getValue() + "\n");
        }
    }
    

    public static void main(String[] args)
    {
        try {
            Ejercicio15 hashmap = new Ejercicio15();
            hashmap.read();
            System.out.println("Caracteres Ordenados en orden de Frecuencia: ");
            hashmap.sort();
        } catch (Exception e) {
            System.err.println("Execution Interrupted");
        }
    }
}


