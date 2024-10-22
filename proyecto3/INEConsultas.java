/**
 * PROYECTO 3
 * Gustavo Israel Ramos Montes
 * 4CM11
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class INEConsultas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Votos por sexo");
            System.out.println("2. Votos por estado");
            System.out.println("3. Votos por edad");
            System.out.println("4. Votos por partido y total");
            System.out.println("5. Salir");

            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    mostrarVotosPorSexo();
                    break;
                case 2:
                    mostrarVotosPorEstado();
                    break;
                case 3:
                    mostrarVotosPorEdad();
                    break;
                case 4:
                    mostrarVotosPorPartido();
                    break;
                case 5:
                    ejecutando = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        sc.close();
    }

    private static void mostrarVotosPorSexo() {
        try (BufferedReader br = new BufferedReader(new FileReader("VOTOS.dat"))) {
            HashMap<String, Integer> conteoSexo = new HashMap<>();
            String linea;
            while ((linea = br.readLine()) != null) {
                String curp = linea.split(":")[0];
                String sexo = curp.substring(10, 11);
                conteoSexo.put(sexo, conteoSexo.getOrDefault(sexo, 0) + 1);
            }
            System.out.println("Votos por sexo:");
            conteoSexo.forEach((k, v) -> {
                String descripcionSexo = k.equals("H") ? "Hombres" : "Mujeres";
                System.out.println(descripcionSexo + ": " + v);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostrarVotosPorEstado() {
        try (BufferedReader br = new BufferedReader(new FileReader("VOTOS.dat"))) {
            HashMap<String, Integer> conteoEstados = new HashMap<>();
            String linea;
            while ((linea = br.readLine()) != null) {
                String curp = linea.split(":")[0];
                String estado = curp.substring(11, 13);
                conteoEstados.put(estado, conteoEstados.getOrDefault(estado, 0) + 1);
            }
            System.out.println("Votos por estado:");
            conteoEstados.forEach((k, v) -> System.out.println(k + ": " + v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostrarVotosPorEdad() {
        try (BufferedReader br = new BufferedReader(new FileReader("VOTOS.dat"))) {
            HashMap<String, Integer> conteoEdad = new HashMap<>();
            String linea;
            while ((linea = br.readLine()) != null) {
                String curp = linea.split(":")[0];
                int anioNacimiento = Integer.parseInt(curp.substring(4, 6));
                int edad = 2024- (1900 + anioNacimiento);
                conteoEdad.put(String.valueOf(edad), conteoEdad.getOrDefault(String.valueOf(edad), 0) + 1);
            }
            System.out.println("Votos por edad:");
            conteoEdad.forEach((k, v) -> System.out.println(k + " años: " + v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostrarVotosPorPartido() {
        try (BufferedReader br = new BufferedReader(new FileReader("VOTOS.dat"))) {
            HashMap<String, Integer> conteoPartidos = new HashMap<>();
            String linea;
            while ((linea = br.readLine()) != null) {
                String partido = linea.split(":")[1];
                conteoPartidos.put(partido, conteoPartidos.getOrDefault(partido, 0) + 1);
            }
            System.out.println("Votos por partido:");
            conteoPartidos.forEach((k, v) -> System.out.println(k + ": " + v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
