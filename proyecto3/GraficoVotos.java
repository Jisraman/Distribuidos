/**
 * PROYECTO 3
 * Gustavo Israel Ramos Montes
 * 4CM11
 */
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import javax.swing.*;

    public class GraficoVotos extends JFrame {
        private HashMap<String, Integer> conteoPartidos;
        private HashMap<String, Color> coloresPartidos;
        private JPanel panelPartidos;

        public GraficoVotos() {
        conteoPartidos = new HashMap<>();
        coloresPartidos = new HashMap<>();

        for (String partido : Generador.Opiniones) {
            conteoPartidos.put(partido, 0);
        }

        coloresPartidos.put("MORENA", new Color(0x622432));
        coloresPartidos.put("PT", new Color(0xDF0E1A));
        coloresPartidos.put("PVEM", new Color(0x08B308));
        coloresPartidos.put("PRI", new Color(0x2B2A2A));
        coloresPartidos.put("PRD", new Color(0xFFCC01));
        coloresPartidos.put("PAN", new Color(0x004AAD));
        coloresPartidos.put("MC", new Color(0xFE8201));

        JTabbedPane pestañas = new JTabbedPane();
        panelPartidos = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarGraficoBarrasHorizontal(g, conteoPartidos, getWidth(), getHeight());
            }
        };

        pestañas.addTab("Votos por Partido", panelPartidos);
        add(pestañas);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        new Timer(1000, e -> panelPartidos.repaint()).start();
        new Thread(this::lecturaContinuaArchivo).start();
    }


    private void lecturaContinuaArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("VOTOS.dat"))) {
            String linea;
            while (true) {
                while ((linea = br.readLine()) != null) {
                    procesarVoto(linea);
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void procesarVoto(String linea) {
        System.out.println("LOG: Voto Recibido - " + linea);
        String[] datos = linea.split(":");
        String partido = datos[1].trim();

        conteoPartidos.put(partido, conteoPartidos.getOrDefault(partido, 0) + 1);

        SwingUtilities.invokeLater(panelPartidos::repaint);
    }

    private void dibujarGraficoBarrasHorizontal(Graphics g, HashMap<String, Integer> datos, int width, int height) {
        int y = 10;
        int barHeight = 30;
        int totalVotos = datos.values().stream().mapToInt(Integer::intValue).sum();

        int maxVotos = datos.values().stream().max(Integer::compareTo).orElse(1);
        int scaleFactor = Math.max((int) Math.ceil((double) maxVotos / 100), 1);

        for (String partido : datos.keySet()) {
            int votos = datos.get(partido);
            int barWidth = votos / scaleFactor;
            
            barWidth = Math.max(barWidth, 1);

            Color color = coloresPartidos.getOrDefault(partido, Color.GRAY);
            
            g.setColor(Color.BLACK);
            g.drawString(partido, 10, y + barHeight / 2 + 5);
            
            g.drawString(String.valueOf(votos), 100, y + barHeight / 2 + 5);

            g.setColor(color);
            g.fillRect(150, y, barWidth, barHeight - 5);

            y += barHeight + 10;
        }

        g.setColor(Color.BLUE);
        g.drawString("Total de Votos: " + totalVotos, 10, y);
    }

    public static void main(String[] args) {
        new GraficoVotos();
    }
}
