/**
 * PROYECTO 2
 * Gustavo Israel Ramos Montes
 * 4CM11
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Lienzo extends JPanel {
    private int numPoligonos;
    private List<PoligonoReg> poligonos;

    public Lienzo(int numPoligonos) {
        this.numPoligonos = numPoligonos;
        poligonos = new ArrayList<>();

        // Crear el JFrame
        JFrame lienzo = new JFrame("Lienzo");
        lienzo.setBounds(0, 0, 600, 600);  // Cambié las dimensiones a 600x600
        lienzo.setBackground(Color.RED);
        lienzo.setLocationRelativeTo(null);
        lienzo.setLayout(null);

        // Generar los polígonos una vez
        generarPoligonos();
        
        // Añadir el JPanel al JFrame
        lienzo.add(this);
        lienzo.setVisible(true);
        this.setSize(getParent().getSize());

        lienzo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Iniciar la animación de todos los polígonos
        iniciarAnimacion();
    }

    public void generarPoligonos() {
        Random random = new Random();
        for (int i = 0; i < numPoligonos; i++) {
           int lados = random.nextInt(12) + 3; // Lados entre 3 y 15
            double radio = random.nextDouble() * (600 / 8); // Radio máximo 1/8 de la altura de la pantalla
            
            // Generar posición aleatoria para el centro del polígono
            int posX = random.nextInt(600 - (int)(radio * 2)); // Ajustar para que no se salga de los límites
            int posY = random.nextInt(600 - (int)(radio * 2));

            PoligonoReg p = new PoligonoReg(lados);
            p.establecerRadio(radio);
            p.crearVertices(posX, posY); // Crear vértices en la nueva posición
            p.calcularArea();
            System.out.println("Área del polígono: " + p.obtenerArea());
            poligonos.add(p);
        }

        // Ordenar los polígonos de mayor a menor área
        poligonos.sort((a, b) -> Double.compare(b.obtenerArea(), a.obtenerArea()));
    }

    public void moverPoligono(PoligonoReg poligono) {
        // Desplazamiento en x e y aleatorio
        Random random = new Random();
        int deltaX = random.nextInt(20) -10; // Movimiento aleatorio en el rango [-5, 5]
        int deltaY = random.nextInt(20) -10; // Movimiento aleatorio en el rango [-5, 5]

        int nuevoCentroX = poligono.obtenerCentro().obtenerCoordenadaX()+deltaX;
        int nuevoCentroY = poligono.obtenerCentro().obtenerCoordenadaY()+deltaY;
        poligono.obtenerCentro().modificarCoordenadaX(nuevoCentroX);
        poligono.obtenerCentro().modificarCoordenadaY(nuevoCentroY);

        // Verificar que el nuevo centro y el círculo circunscrito estén dentro de los límites
        if (nuevoCentroX - poligono.obtenerRadio() > 0 || nuevoCentroX + poligono.obtenerRadio() < 600 || 
            nuevoCentroY - poligono.obtenerRadio() > 0 || nuevoCentroY + poligono.obtenerRadio() < 600) {
            // Actualizar las coordenadas de los vértices
            for (Coordenada vertice : poligono.obtenerVertices()) {

                int nuevoX = vertice.obtenerCoordenadaX() + deltaX;
                int nuevoY = vertice.obtenerCoordenadaY() + deltaY;

                vertice.modificarCoordenadaX(nuevoX);
                vertice.modificarCoordenadaY(nuevoY);
            }
        }

        
    }

    public void iniciarAnimacion() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        for (int i = 0; i < poligonos.size(); i++) {
            PoligonoReg p = poligonos.get(i);
            int delay = (int) (p.obtenerArea() / 10) + 1000; // Calcula el tiempo en base al área
            
            // Crea un nuevo Timer para cada polígono
            new Timer(delay, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    moverPoligono(p);
                    repaint(); // Volver a pintar el lienzo después de mover el polígono
                }
            }).start();

            // Esperar 1 segundo antes de comenzar el movimiento del siguiente polígono
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Limpiar el área de dibujo antes de redibujar

        g.setColor(Color.BLUE);

        // Dibujar todos los polígonos
        for (PoligonoReg poligono : poligonos) {
            List<Coordenada> vertices = poligono.obtenerVertices();
            Random random = new Random();
            // Usar streams para transformar la lista de vértices en arreglos
            int[] xPoints = vertices.stream().mapToInt(Coordenada::obtenerCoordenadaX).toArray();
            int[] yPoints = vertices.stream().mapToInt(Coordenada::obtenerCoordenadaY).toArray();
            // Dibujar el polígono
            g.drawPolygon(xPoints, yPoints, poligono.obtenerNumeroVertices());

             // Calcular el centro (kx, ky) del polígono usando el primer vértice
            int kx = poligono.obtenerCentro().obtenerCoordenadaX(); // Centro aproximado
            int ky = poligono.obtenerCentro().obtenerCoordenadaY(); // Centro aproximado

            g.setColor(Color.RED); // Cambiar el color del círculo circunscrito
            g.drawOval(kx , ky , (int) poligono.obtenerRadio() * 2, (int) poligono.obtenerRadio() * 2);
            
            g.setColor(Color.BLUE); // Restaurar color del polígono
        }
    }
}
