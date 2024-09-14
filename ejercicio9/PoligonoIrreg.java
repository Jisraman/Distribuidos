
import java.util.ArrayList;
import java.util.List;
public class PoligonoIrreg implements Comparator{
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
    
    public void sortVertices(){
        for(int i = 0; i<Coordenadas.size();i++){
            for(int j = 0; j<Coordenadas.size();j++){
                if (Coordenadas.get(i).getMagnitd() > Coordenadas.get(j).getMagnitd()){
                    double auxCoordx = Coordenadas.get(i).getComponent('x');
                    double auxCoordy = Coordenadas.get(i).getComponent('y');
                    Coordenadas.get(i).setCoordenadas(Coordenadas.get(j).getComponent('x'), Coordenadas.get(j).getComponent('y'));
                    Coordenadas.get(j).setCoordenadas(auxCoordx, auxCoordy);
                }
            } 
        }
    }
    
    //Sobreescritura del método de la superclase objeto para imprimir con System.out.println( )
    @Override
    public String toString( ) {
        String cad = "";
        if(Coordenadas.size()==0){
            cad = "\nPoligono sin vertices\n";
        }
        for(Coordenada coordenada:Coordenadas){
            cad += "\n["+coordenada.getComponent('x')+","+coordenada.getComponent('y')+"]  m: "+coordenada.getMagnitd()+"\n";
        }
        return cad;
    }
}