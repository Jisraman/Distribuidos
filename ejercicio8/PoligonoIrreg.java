
import java.util.ArrayList;
import java.util.List;
public class PoligonoIrreg {
    private List <Coordenada> Coordenadas;
    //private Coordenada superiorIzq, inferiorDer;
    
    public PoligonoIrreg(){
        Coordenadas = new ArrayList<Coordenada>();
        System.out.println("Vertices Totales: "+Coordenadas.size());
    }
    
    public void anadirVertice(double x, double y){
       this.Coordenadas.add(new Coordenada(x,y));
       System.out.println("Se ha añadido la coordenada x: " + x + "\t y: " + y + "\n");
    }
    
    
    //Sobreescritura del método de la superclase objeto para imprimir con System.out.println( )
    @Override
    public String toString( ) {
        String cad = "";
        if(Coordenadas.size()==0){
            cad = "\nPoligono sin vertices\n";
        }
        for(Coordenada coordenada:Coordenadas){
            cad += "\n["+coordenada.getComponent('x')+","+coordenada.getComponent('y')+"]\n";
        }
        return cad;
    }
}