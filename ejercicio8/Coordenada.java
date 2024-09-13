public class Coordenada
{
    private double x;
    private double y;

    public Coordenada (double x, double y){
        this.x = x;
        this.y = y;

    }


    public void setCoordenadas(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getComponent(char c){
        return c == 'x' || c == 'X' ? x : y;
    }
}