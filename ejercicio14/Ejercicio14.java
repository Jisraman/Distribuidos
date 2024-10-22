import java.io.*;
import java.util.HashMap;
import java.util.Map;
public class Ejercicio14{
    Map<String, Integer> chars;   

    public Ejercicio14(){
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
    

    public static void main(String[] args)
    {
        try {
            Ejercicio14 hashmap = new Ejercicio14();
            hashmap.read();
            System.out.println("Caracteres encontrados: ");
            hashmap.printHashMap();
        } catch (Exception e) {
            System.err.println("Execution Interrupted");
        }
    }
}


