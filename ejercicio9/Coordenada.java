public class Coordenada
{
    private double x;
    private double y;
    private double m;

    public Coordenada (double x, double y){
        this.x = x;
        this.y = y;
        this.m = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
    }


    public void setCoordenadas(double x, double y){
        this.x = x;
        this.y = y;
        this.m = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
    }

    public double getComponent(char c){
        return c == 'x' || c == 'X' ? x : y;
    }

    public double getMagnitd(){
        return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
    }
}