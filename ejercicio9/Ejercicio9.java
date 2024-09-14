import java.util.Random;
public class Ejercicio9{
    public static void main(String[] args) {
        PoligonoIrreg pi= new PoligonoIrreg();
        System.out.print(pi.toString());
        Random random = new Random();
        double x = Math.round((random.nextDouble() * 200 - 100) * 1000.0) / 1000.0;
        double y = Math.round((random.nextDouble() * 200 - 100) * 1000.0) / 1000.0;
        pi.anadirVertice(x,y);

        x = Math.round((random.nextDouble() * 200 - 100) * 1000.0) / 1000.0;
        y = Math.round((random.nextDouble() * 200 - 100) * 1000.0) / 1000.0;
        pi.anadirVertice(x,y);

        x = Math.round((random.nextDouble() * 200 - 100) * 1000.0) / 1000.0;
        y = Math.round((random.nextDouble() * 200 - 100) * 1000.0) / 1000.0;

        x = Math.round((random.nextDouble() * 200 - 100) * 1000.0) / 1000.0;
        y = Math.round((random.nextDouble() * 200 - 100) * 1000.0) / 1000.0;
        pi.anadirVertice(x,y);

        x = Math.round((random.nextDouble() * 200 - 100) * 1000.0) / 1000.0;
        y = Math.round((random.nextDouble() * 200 - 100) * 1000.0) / 1000.0;

        x = Math.round((random.nextDouble() * 200 - 100) * 1000.0) / 1000.0;
        y = Math.round((random.nextDouble() * 200 - 100) * 1000.0) / 1000.0;
        pi.anadirVertice(x,y);

        x = Math.round((random.nextDouble() * 200 - 100) * 1000.0) / 1000.0;
        y = Math.round((random.nextDouble() * 200 - 100) * 1000.0) / 1000.0;
        pi.anadirVertice(x,y);

        System.out.print(pi.toString());

        System.out.print("Sorted");
        pi.sortVertices();

        System.out.print(pi.toString());

    }
}