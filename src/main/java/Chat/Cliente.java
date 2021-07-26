package Chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cliente implements ActionListener {

    //Relacionado con el socket.
    private static Socket clientSocket;
    private static int PORT;
    private PrintWriter out;
    private static String clientName = "";

    //Diseño de prueba
    private static Diseño diseño;
    private final static Logger logger = LogManager.getRootLogger();

    public Cliente() {
        diseño = new Diseño();
        diseño.getBtSend().addActionListener(this);
        diseño.getBtStart().addActionListener(this);
    }

    public void comenzar() {
        try {
            PORT = 1234;
            clientName = diseño.getNameText().getText();
            clientSocket = new Socket("localhost", PORT);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            new Thread(new Listener()).start();
            out.println(clientName);

        } catch (Exception e) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == diseño.getBtStart()) {
            if (diseño.getBtStart().getText().equals("START")) {
                diseño.getBtStart().setText("STOP");
                comenzar();
            } else {
                diseño.getBtStart().setText("START");
                //stop();
            }
        }
        if (e.getSource() == diseño.getBtSend()) {
            String message = diseño.getPartText().getText().trim();
            if (!message.isEmpty()) {
                out.println(message);
                logger.info(message);
                diseño.getPartText().setText("");
            }
        }
    }

    public static boolean flagName(String read) {
        for (int i = 0; i < read.length(); i++) {
            if (read.substring(i, i + 1).equals("]")) {
                String person = read.substring(1, i);
                if (person.equals(clientName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static class Listener implements Runnable {

        private BufferedReader in;

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String read;
                while (true) {
                    read = in.readLine();
                    if (read != null && !(read.isEmpty())) {
                        if (flagName(read) == true) {
                            diseño.addMessage1(read);
                        } else {
                            diseño.addMessage2(read);
                        }

                    }
                }
            } catch (Exception e) {
                return;
            }

        }

    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        try {

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

}
