/**
 * PROYECTO 2
 * Gustavo Israel Ramos Montes
 * 4CM11
 */
public class Coordenada
{
    private int x;
    private int y;

    public Coordenada (int x, int y){
        this.x = x;
        this.y = y;
    }

    public void modificarCoordenadaX(int x){
        this.x = x;
    }
    public void modificarCoordenadaY(int y){
        this.y = y;
    }

    public int obtenerCoordenadaX(){
        return x;
    }

    public int obtenerCoordenadaY(){
        return y;
    }

}