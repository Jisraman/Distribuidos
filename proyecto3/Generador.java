/**
 * PROYECTO 3
 * Gustavo Israel Ramos Montes
 * 4CM11
 */
public class Generador{
    public static final String[] Opiniones = {
            "NULO",
            "MORENA",
            "MC",
            "PAN",
            "PRI",
            "PRD",
            "PT",
            "PVEM"
        };

    public static String generarOpinion(){
        return Opiniones[(int) (Math.random() * Opiniones.length)];
    }

    public static String generarCurp() {
        String Letra = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Numero = "0123456789";
        String Sexo = "HM";
        String Entidad[] = {"AS", "BC", "BS", "CC", "CS", "CH", "CL", "CM", "DF", "DG", "GT", "GR", "HG", "JC", "MC", "MN", "MS", "NT", "NL", "OC", "PL", "QT", "QR", "SP", "SL", "SR", "TC", "TL", "TS", "VZ", "YN", "ZS"};
        int indice;
        
        StringBuilder sb = new StringBuilder(18);
        
        for (int i = 0; i < 4; i++) {
            indice = (int) (Letra.length() * Math.random());
            sb.append(Letra.charAt(indice));        
        }
        
        int anioNacimiento = (int) (Math.random() * (2024 - 1900)) + 1900;
        String anio = String.valueOf(anioNacimiento).substring(2);
        sb.append(anio);
        
        for (int i = 0; i < 4; i++) {
            indice = (int) (Numero.length() * Math.random());
            sb.append(Numero.charAt(indice));        
        }
        
        indice = (int) (Sexo.length() * Math.random());
        sb.append(Sexo.charAt(indice));        
        
        sb.append(Entidad[(int) (Math.random() * Entidad.length)]);
        
        for (int i = 0; i < 3; i++) {
            indice = (int) (Letra.length() * Math.random());
            sb.append(Letra.charAt(indice));        
        }
        for (int i = 0; i < 2; i++) {
            indice = (int) (Numero.length() * Math.random());
            sb.append(Numero.charAt(indice));        
        }
        
        return sb.toString();
    }


}