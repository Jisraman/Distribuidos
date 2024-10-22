

public class Ejercicio12 implements Runnable {
 
    public static int variable_compartida = 0;
    public static int n;

    public void run()
    {
        for(int i=0;i<n;i++){
            modifica();
        }
    }

    public synchronized void modifica(){
        if(Thread.currentThread().getName().equals("hilo1")){
            //System.out.println("Hilo 1 i = " + variable_compartida);
            variable_compartida++;
        }
        if(Thread.currentThread().getName().equals("hilo2")){
            //System.out.println("Hilo 2 i = " + variable_compartida);
            variable_compartida--;
        }
                 
    }
    
    public static void main(String[] args)
    {
        try {
            if (args.length >= 1) {
                Ejercicio12.n = Integer.valueOf(args[0]);

                Ejercicio12 tarea = new Ejercicio12(); // Crear UNA instancia compartida
                
                Thread t1 = new Thread(tarea,"hilo1");
                Thread t2 = new Thread(tarea,"hilo2");
                t1.start();
                t2.start();

                t1.join(); 
                t2.join(); 
                
                System.out.println("Variable compartida: " + Ejercicio12.variable_compartida);
            }
        } catch (InterruptedException e) {
            System.err.println("Thread Interrupted");
        }
        
    }    

}