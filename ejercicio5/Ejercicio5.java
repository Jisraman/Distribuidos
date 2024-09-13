public class Ejercicio5{
    public static void main (String[] args) {
        //Rectangulo rect1 = new Rectangulo(2,3,5,1);
        double ancho, alto;
        try{
            //Rectangulo rect1 = new Rectangulo(new Coordenada(5,1), new Coordenada(2,3)); // Para que mande el mensaje de error
            Rectangulo rect1 = new Rectangulo(new Coordenada(2,3), new Coordenada(5,1)); //Construccion Correcta
            System.out.println("Calculando el area de un rectangulo dadas sus coordenadas en un plano cartesiano:");
            System.out.println(rect1);
            alto = rect1.superiorIzquierda().ordenada() - rect1.inferiorDerecha().ordenada();
            ancho = rect1.inferiorDerecha().abcisa() - rect1.superiorIzquierda().abcisa();
            System.out.println("El area del rectangulo es = " + ancho*alto);
        }
        catch(IllegalArgumentException e){
            System.out.println("Error al crear el rectangulo"+e.getMessage());
        }
            //Creando Poligono Irregular
        PoligonoIrreg poligono = new PoligonoIrreg(7);
        System.out.println("Calculando el perimetro de un poligono irregular dadas sus coordenadas en un plano cartesiano:");
        System.out.println(poligono.toString());
        //modifica vertice  
        System.out.println("Modificando el vertice 2 del poligono");
        poligono.modificaVertice(2, new Coordenada(3.5,4.5));
        System.out.println(poligono.toString());
    }
}