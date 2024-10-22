import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;

public class WebServer {
    private static final String TASK_ENDPOINT = "/task";
    private static final String STATUS_ENDPOINT = "/status";
    private static final String TOKEN_ENDPOINT = "/searchtoken";

    private final int port;
    private HttpServer server;

    public static void main(String[] args) {
        int serverPort = 8080;
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);
        }

        WebServer webServer = new WebServer(serverPort);
        webServer.startServer();

        System.out.println("Servidor escuchando en el puerto " + serverPort);
    }

    public WebServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        HttpContext statusContext = server.createContext(STATUS_ENDPOINT);
        HttpContext taskContext = server.createContext(TASK_ENDPOINT);
        HttpContext tokenContext = server.createContext(TOKEN_ENDPOINT);

        statusContext.setHandler(this::handleStatusCheckRequest);
        taskContext.setHandler(this::handleTaskRequest);
        tokenContext.setHandler(this::handleTokenRequest);

        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }

    private void handleTaskRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        Headers headers = exchange.getRequestHeaders();
        if (headers.containsKey("X-Test") && headers.get("X-Test").get(0).equalsIgnoreCase("true")) {
            String dummyResponse = "123\n";
            sendResponse(dummyResponse.getBytes(), exchange);
            return;
        }

        boolean isDebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            isDebugMode = true;
        }

        long startTime = System.nanoTime();

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();
        System.out.println("Longitud del cuerpo del mensaje: " + requestBytes.length);
        System.out.println("Contenido del cuerpo del mensaje: " + new String(requestBytes));
        byte[] responseBytes = calculateResponse(requestBytes);

        long finishTime = System.nanoTime();

        System.out.println("Longitud de los headers: " + headers.size());
        System.out.println("Headers:");
        headers.forEach((key, value) -> System.out.println(key + ": " + value));

        if (isDebugMode) {
            long duracion = finishTime - startTime;
            double segundos = duracion / 1000000000.0;
            double milisegundos = duracion / 1000000.0;
            String debugMessage = String.format("La operación tomó %d nanosegundos (%.0f segundos, %.0f milisegundos)", 
            duracion, segundos, milisegundos);
            exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));
        }

        sendResponse(responseBytes, exchange);
    }

    private byte[] calculateResponse(byte[] requestBytes) {
        String bodyString = new String(requestBytes);
        String[] stringNumbers = bodyString.split(",");

        BigInteger result = BigInteger.ONE;

        for (String number : stringNumbers) {
            BigInteger bigInteger = new BigInteger(number);
            result = result.multiply(bigInteger);
        }

        return String.format("El resultado de la multiplicación es %s\n", result).getBytes();
    }

    private void handleStatusCheckRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }

        String responseMessage = "El servidor está vivo\n";
        sendResponse(responseMessage.getBytes(), exchange);
    }

    private void handleTokenRequest(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }
        Headers headers = exchange.getRequestHeaders();
        if (headers.containsKey("X-Test") && headers.get("X-Test").get(0).equalsIgnoreCase("true")) {
            String dummyResponse = "123\n";
            sendResponse(dummyResponse.getBytes(), exchange);
            return;
        }

        boolean isDebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            isDebugMode = true;
        }

        long startTime = System.nanoTime();

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();
        byte[] responseBytes = calculateToken(requestBytes);

        long finishTime = System.nanoTime();    

        if (isDebugMode) {
            long duracion = finishTime - startTime;
            double segundos = duracion / 1000000000.0;
            double milisegundos = (duracion % 1000000000) / 1000000.0;
            String debugMessage = String.format("La operación tomó %d nanosegundos (%.0f segundos, %.0f milisegundos)", 
            duracion, Math.floor(segundos), milisegundos);
            exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));
        }

        sendResponse(responseBytes, exchange);
    }

    private byte[] calculateToken(byte[] requestBytes) {
        String bodyString = new String(requestBytes);
        bodyString = bodyString.replace("\n", "").replace("\r", "");
        String[] stringNumbers = bodyString.split(",");
        System.out.println("Token: " + stringNumbers[1]);

        StringBuilder cadenota = new StringBuilder(Integer.parseInt(stringNumbers[0]) * 4);
        Random random = new Random();

        for (int i = 0; i < Integer.parseInt(stringNumbers[0]); i++) {
            for (int j = 0; j < 3; j++) {
                cadenota.append((char) ('A' + random.nextInt(26)));
            }
            cadenota.append(' ');
        }

        String subcadena = stringNumbers[1];
        int count = 0;
        int index = 0;

        while ((index = cadenota.indexOf(subcadena, index)) != -1) {
            count++;
            index += subcadena.length();
        }

        return String.format("La cadena a buscar es:%s\nEl número de apariciones es:%d",stringNumbers[1], count).getBytes();
    }

    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        
            exchange.sendResponseHeaders(200, responseBytes.length);
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(responseBytes);
            outputStream.flush();
            outputStream.close();
            exchange.close();
    }
}