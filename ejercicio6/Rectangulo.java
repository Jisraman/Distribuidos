//clase Rectangulo
public class Rectangulo extends Figura {
    private float base;
    private float altura;

    //constructor que hereda de la clase Figura
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
}