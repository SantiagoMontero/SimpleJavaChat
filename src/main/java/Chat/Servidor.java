package Chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.HashMap;

public class Servidor implements ActionListener {

    //Relacionado con el socket.    
    private static HashMap<String, PrintWriter> connectedClients = new HashMap<>();
    private static final int MAX_CONNECTED = 50;
    private static int PORT;
    private static ServerSocket server;
    private static volatile boolean exit = false;
    private static DiseñoServer servidor;

    public Servidor() {
        servidor = new DiseñoServer();
        servidor.getBtStart().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == servidor.getBtStart()) {
            if (servidor.getBtStart().getText().equals("START")) {
                exit = false;
                getRandomPort();
                start();
                servidor.getBtStart().setText("STOP");
            } else {
                exit = true;
                servidor.getBtStart().setText("START");
            }
        }

    }

    public static void start() {
        new Thread(new ServerHandler()).start();
    }

    private static int getRandomPort() {
        int port = 1234;
        PORT = port;
        return port;
    }

    private static class ServerHandler implements Runnable {

        @Override
        public void run() {
            servidor.getjTextArea1().setText("El servidor tiene el puerto: " + PORT);
            try {
                server = new ServerSocket(PORT);
                while (!exit) {
                    if (connectedClients.size() <= MAX_CONNECTED) {
                        new Thread(new ClientHandler(server.accept())).start();
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    public static String mensaje(String m) {
        String m1 = "";
        for (int i = 0; i < m.length(); i++) {
            if (m.substring(i, i + 1).equals("|")) {
                m1 = m.substring(0, i);
                return m1;
            }
        }
        return m1;

    }

    public String url(String u) {
        String u1 = "";
        for (int i = 0; i < u.length(); i++) {
            if (u.substring(i, i + 1).equals("|")) {
                u1 = u.substring(i, u.length());
                return u1;
            }
        }
        return u1;
    }

    private static void difusionMensaje(String message) {
        for (PrintWriter p : connectedClients.values()) {
            p.println(message);
        }
    }

    private static class ClientHandler implements Runnable {

        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String name;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                while (true) {

                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    if (!name.isEmpty() && !connectedClients.keySet().contains(name)) {
                        break;
                    } else {
                        out.println("Invalido");
                    }
                    
                }
                servidor.getjTextArea1().setText(servidor.getjTextArea1().getText() + "\n" + "Se unió al chat.- " + name);
                out.println("Bienvenido al grupo, " + name.toUpperCase() + "!");

                difusionMensaje("[Sistema] " + name.toUpperCase() + " se unió.");
                connectedClients.put(name, out);
                String message;
                out.println("Acabas de entrar al chat");
                while ((message = in.readLine()) != null && !exit) {
                    if (!message.isEmpty()) {
                        if (message.toLowerCase().equals("/salir")) {
                            break;
                        }
                        difusionMensaje(String.format("[%s] %s", name, message));
                    }
                }
            } catch (Exception e) {

            } finally {
                if (name != null) {
                    connectedClients.remove(name);
                    difusionMensaje(name + " salió del chat");
                }
            }
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();

    }

}
