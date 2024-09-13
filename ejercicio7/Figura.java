//clase Figura
public abstract class Figura {
    private int numeroLados;
    public Figura(){
        this.numeroLados = 4;
    }
    public abstract float area();
    
    public int getNumeroLados(){
        return this.numeroLados;
    }
    public void setNumeroLados(int numeroLados){
        this.numeroLados = numeroLados;
    }
}

