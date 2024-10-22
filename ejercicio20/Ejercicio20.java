import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import  java.util.concurrent.ExecutorService;
import  java.util.concurrent.Executors;

public class Ejercicio20 implements Comparator, Runnable{
    Map<String, Integer> chars;   
    List<Entry<String, Integer>> list;
    String name;
    List<String> lines;

    public Ejercicio20(String name, List<String> lines) {
        this.name = name;
        this.chars = new HashMap<>();
        this.lines = lines;
    }


    public void run(){
        try {
            long startTime = System.nanoTime(); 
            read();
            sort();
            printHashMap();
            long estimatedTime = System.nanoTime() - startTime;
            System.out.println("\n"+this.name +" tiempo : " + estimatedTime + " Nano segundos");
        } catch (Exception e) {

        }
    }

    public void read() {
        try (FileReader fr = new FileReader("./BIBLIA_COMPLETA.txt")) {
            for (String line : lines) {
                for (char c : line.toCharArray()) {
                    String charStr = Character.toString(c);
                    chars.put(charStr, chars.getOrDefault(charStr, 0) + 1);
                }
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
            // Leer el archivo
            List<String> allLines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("./BIBLIA_COMPLETA.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    allLines.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // cada quinta parte tiene aprox 7037 lineas
            int linesPerTask  = 7037;

            List<List<String>> partitions = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                int start = i * linesPerTask;
                int end = (i == 4) ? linesPerTask*5 : (i + 1) * linesPerTask; // Última partición ajustada
                partitions.add(allLines.subList(start, end));
            }

                System.out.print("POOL DE " + args[0] + " HILOS");

                ExecutorService pool= Executors.newFixedThreadPool(Integer.valueOf(args[0]));
                
                Runnable  r1 = new Ejercicio20("Task 1", partitions.get(0));
                Runnable  r2 = new Ejercicio20("Task 2", partitions.get(1));
                Runnable  r3 = new Ejercicio20("Task 3", partitions.get(2));
                Runnable  r4 = new Ejercicio20("Task 4", partitions.get(3));
                Runnable  r5 = new Ejercicio20("Task 5", partitions.get(4));
                pool.execute(r1);
                pool.execute(r2);
                pool.execute(r3);
                pool.execute(r4);
                pool.execute(r5);
                pool.shutdown();
            
            // ... the code being measured ...    
            

        } catch (Exception e) {
            System.err.println("Execution Interrupted");
        }
    }
    
}