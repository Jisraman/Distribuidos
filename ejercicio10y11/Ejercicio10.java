import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
public class Ejercicio10{
    List<String> curps ; 
    private char s;
    private int n;
    public Ejercicio10(int n, char s){
        curps = new LinkedList<>();
        this.n = n;
        this.s = s;
    }

    public void generate(){
        for(int i = 0; i<n;i++){
            String tempCurp = new String();
        //RAMG 990130 HDFMNS 04
            tempCurp = addChars(tempCurp, 4);
            tempCurp = addNums(tempCurp, 6);
            tempCurp = addGender(tempCurp);
            tempCurp = addChars(tempCurp, 5);
            tempCurp = addNums(tempCurp, 2);
            System.out.println("curp: "+tempCurp);
            curps.add(tempCurp);
            
        }
    }

    public void filter(){
        Iterator<String> curpsIterator = curps.iterator();
        while(true){
            if(curpsIterator.hasNext()){
                char c = curpsIterator.next().charAt(10);
                if(c==this.s){
                    curpsIterator.remove();
                }
            }
            else{
                break;
            }
        }
    }

    public void sort(){
        Iterator<String> curpsIterator = curps.iterator();
        while(true){
            if(curpsIterator.hasNext()){
                String sbase = curpsIterator.next().substring(0,4);
                if(curpsIterator.hasNext()){
                    String sCompare = curpsIterator.next().substring(0,4);

                    for(int i=0;i<4;i++){
                        if(sbase.charAt(i)!=sCompare.charAt(i)){
                            
                        }
                    }
                    
                }
            }

            
            else{
                break;
            }
        }
    }


    public String addChars(String curp, int n){
        Random random = new Random();
        for(int i=0; i<n;i++){
            curp +=(char)(((random.nextInt(26) ) + 65));
        }
        return curp;
    }

    public String addNums(String curp, int n){
        Random random = new Random();
        for(int i=0; i<n;i++){
            curp +=(char)(((random.nextInt(9) ) + 48));
        }
        return curp;
    }

    public String addGender(String curp){
        Random random = new Random();
        char s = (random.nextInt() * 100)>50?'H':'M';
        curp+=s;
        return curp;
    }

    @Override
    public String toString(){
        Iterator<String> curpsIterator = curps.iterator();
        String string = "";
        while(curpsIterator.hasNext()){
            string+='\n'+curpsIterator.next();
        }
        return string;
    }
    public static void main (String[] args) {
        if (args.length >= 2) { //si hay más de 1 parámetro
            Ejercicio10 i = new Ejercicio10(Integer.valueOf(args[0]), args[1].charAt(0));
            i.generate();
            System.out.println(i.toString()); 
            System.out.println("FILTRO"); 
            i.filter();
            System.out.println(i.toString()); 

        } else { //si no hay parámetros      
            System.out.println("Faltan argumentos");                                                  
        } 
        
    }
}