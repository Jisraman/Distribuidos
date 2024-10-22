/**
 * PROYECTO 2
 * Gustavo Israel Ramos Montes
 * 4CM11
 */
import java.util.ArrayList;
import java.util.List;

public class PoligonoReg{

    private int numVertices;
    private double r;
    private double area;
    private double angulo;
    private List <Coordenada> vertices;
    private Coordenada centro;

    public PoligonoReg(int n){
        this.numVertices = n>=3? n:3;
        this.angulo  = 360 /n;
        this.vertices = new ArrayList<Coordenada>();

        System.out.println("VERTICES : " + +numVertices);
        System.out.println("ÁNGULO : " + angulo);
        
    }

    public void crearVertices(int kx, int ky){
        int radio = (int) r;

        double anguloInicial = Math.toRadians(-90-angulo); // Comenzamos en la parte superior del círculo
        this.centro = new Coordenada(kx,ky);
        
         // Calcular las coordenadas de los vértices
        for (int i = 0; i < numVertices; i++) {
            double anguloRad = anguloInicial + i * Math.toRadians(angulo);
            
            vertices.add(new Coordenada((int) (kx+radio + radio * Math.cos(anguloRad)),(int) (ky+radio + radio * Math.sin(anguloRad))));
            
        }
    }

    public void calcularArea(){
        this.area = (numVertices * 0.5 * r * r * Math.sin(Math.toRadians(angulo)));
    }

    public double obtenerArea(){
        return this.area;
    }

    public int obtenerNumeroVertices(){
        return numVertices;
    }

    public List <Coordenada> obtenerVertices(){
        return this.vertices;
    }

    public double obtenerRadio(){
        return this.r;
    }

    public Coordenada obtenerCentro(){
        return this.centro;
    }
    public void establecerRadio(double r){
        this.r = r;
    }
}