//clase Rectangulo
public class Rectangulo extends Figura implements Perimetro{
    private float base;
    private float altura;
    public Rectangulo(float base, float altura){
        super();
        this.base = base;
        this.altura = altura;
    }
    public float area(){
        System.out.println("El numero de lados del rectangulo es: " + super.getNumeroLados());
        System.out.println("El area del rectangulo es: ");
        return this.base * this.altura;
    }
    public float imprimePerimetro(){
        System.out.println("El perimetro del rectangulo es: ");
        return 2 * (this.base + this.altura);
    }
}