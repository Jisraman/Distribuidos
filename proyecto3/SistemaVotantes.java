/**
 * PROYECTO 3
 * Gustavo Israel Ramos Montes
 * 4CM11
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SistemaVotantes{
    private int vps; //votantes por segundo
    private static boolean votando = true;

    public SistemaVotantes(int n){
        this.vps = n;
        try {
            File New_File = new File("VOTOS.dat");

            if (New_File.createNewFile()) {
                System.out.println("Archivo VOTOS.dat creado correctamente.");
            } else {
                System.out.println("Archivo VOTOS.dat ya existe.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    public static void iniciarGeneracion(){
        votando = true;
    }    */

    public static void detenerGeneracion() {
        votando = false;
    }

    public void generarVotos(){
        
       try (FileWriter fichero = new FileWriter("./VOTOS.dat"))
        {   
            PrintWriter pw = new PrintWriter(fichero);
            Runnable simulacionVotos = () -> {
                int k=0;
                while(votando){
                    System.out.println("Tiempo de funcionamiento del sistema: " + k + " s");
                    try {
                        for(int i=0;i<vps;i++){
                            pw.println(Generador.generarCurp() + ':' + Generador.generarOpinion());
                        }
                        pw.flush();
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    k++;
                }
            };

            Thread hilo = new Thread(simulacionVotos);
            hilo.start();
            hilo.join();

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Por favor, proporciona el número de votantes por segundo.");
            return;
        }
        SistemaVotantes INE_ciudadanos = new SistemaVotantes(Integer.valueOf(args[0]));
        INE_ciudadanos.generarVotos();
    }
}
