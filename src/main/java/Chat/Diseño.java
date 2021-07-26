package Chat;

import java.awt.Button;
import java.awt.Color;
import java.awt.Image;
import java.awt.Scrollbar;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Diseño extends JFrame implements MouseListener, MouseMotionListener {

    private JPanel partUsers = new JPanel();
    private JPanel partMessages = new JPanel();
    private JPanel partElements = new JPanel();
    private JLabel partTop = new JLabel();
    private JPanel partGlobal = new JPanel();
    private JTextField partText = new JTextField();
    private JTextField nameText = new JTextField();

    private Scrollbar scroll = new Scrollbar();
    private JButton btSend = new JButton("SEND");
    private JButton btStart = new JButton("START");
    private Perfil fotoPerfil = new Perfil();

    private int xMovimiento = 0;
    private int yMovimiento = 0;
    private ImageIcon image = new ImageIcon("C:\\Users\\Usuario\\Desktop\\Part3.png");

    private int x = 0;
    private int y = 0;
    private int heightScroll = 0;
    private boolean flag = false;
    String message = "";

    public Diseño() {
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(6, 35, 52));
        setBackground(Color.BLACK);
        setFocusable(true);
        setUndecorated(true);
        addElements();
        setVisible(true);
        addListeners();
    }

    public void addListeners() {
        addMouseListener(this);
        addMouseMotionListener(this);
        btSend.addMouseListener(this);
        btStart.addMouseListener(this);
        scroll.addMouseListener(this);
        partTop.addMouseListener(this);
        partText.addMouseListener(this);
        partUsers.addMouseListener(this);
        scroll.addMouseMotionListener(this);
        partTop.addMouseMotionListener(this);

    }

    public void addElements() {
        addPanelDesign(partGlobal, 0, 50, 1000, 500, Color.BLACK);
        addPanelDesign(partElements, 300, 550, 700, 50, Color.BLACK);
        addPanelDesign(partUsers, 0, 0, 300, 550, new Color(6, 35, 52));
        addPanelDesign(partMessages, 300, 0, 680, 500, new Color(225, 229, 234));
        addTextFieldDesign(partText, 0, 0, 600, 50, Color.WHITE);
        addTextFieldDesign(nameText, 70, 250, 160, 40, Color.WHITE);

        addLabelDesign(partTop, 300, 0, 700, 50, new Color(225, 229, 234), image);
        addScrollDesign();

        btSend.setBounds(600, 0, 100, 50);
        btStart.setBounds(70, 300, 160, 40);

        fotoPerfil.setBounds(56, 0, 180, 218);
        partUsers.add(fotoPerfil);

        partUsers.add(btStart);
        partUsers.add(nameText);

        partGlobal.add(scroll);
        partGlobal.add(partUsers);
        partGlobal.add(partMessages);
        partElements.add(btSend);
        partElements.add(partText);

        add(partTop);
        add(partGlobal);
        add(partElements);
    }

    public void addPanelDesign(JPanel part, int x, int y, int w, int h, Color c) {
        part.setBackground(c);
        part.setBounds(x, y, w, h);
        part.setLayout(null);
    }

    public void addTextFieldDesign(JTextField part, int x, int y, int w, int h, Color c) {
        part.setBounds(x, y, w, h);
        part.setBackground(c);
        part.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    }

    public void addLabelDesign(JLabel part, int x, int y, int w, int h, Color c, ImageIcon i) {
        part.setOpaque(true);
        part.setBackground(c);
        part.setBounds(x, y, w, h);
        part.setIcon(new ImageIcon(i.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
    }

    public void addScrollDesign() {
        scroll.setBounds(980, 0, 20, 500);
        scroll.setMinimum(0);
        scroll.setMaximum(500);
        scroll.setValue(500);
        scroll.setVisibleAmount(500);
    }

    public void addMessage1(String message) throws IOException {
        Mensaje m = new Mensaje(350, y, 250, 50, message, fotoPerfil.getrSFotoSquare1().getRutaImagen());
        y += 60;
        if (partMessages.getHeight() <= y) {
            heightScroll = y - 500;
            scroll.setVisibleAmount(y - heightScroll);

            scroll.setMaximum(y);
            scroll.setValue(y);
            scroll.setBackground(Color.WHITE);

            partMessages.setSize(700, y);
            partMessages.setLocation(300, (y - 500) * -1);

        }
        partMessages.repaint();
        partMessages.add(m);
    }

    public void addMessage2(String message) throws IOException {
        Mensaje m = new Mensaje(50, y, 250, 50, message, fotoPerfil.getrSFotoSquare1().getRutaImagen());
        y += 60;
        if (partMessages.getHeight() <= y) {
            heightScroll = y - 500;
            scroll.setVisibleAmount(y - heightScroll);

            scroll.setMaximum(y);
            scroll.setValue(y);
            scroll.setBackground(Color.WHITE);

            partMessages.setSize(700, y);
            partMessages.setLocation(300, (y - 500) * -1);

        }
        partMessages.repaint();
        partMessages.add(m);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == partTop) {
            if (e.getX() > 663 && e.getX() < 679 && e.getY() > 15 && e.getY() < 29) {
                System.exit(0);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        yMovimiento = e.getY();
        xMovimiento = e.getX();
        if (e.getSource() == partTop) {
            xMovimiento += 300;
        }
        /*
        if (e.getSource() == btSend) {
            // if (!partText.getText().equals("  Type something to send...") && !partText.getText().equals("   ")) {
            try {
                addMessage();
            } catch (IOException ex) {
                //Logger.getLogger(clientDesign.class.getName()).log(Level.SEVERE, null, ex);
            }
            // }
        }
         */
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getSource() == partTop) {
            int xx = e.getXOnScreen();
            int yy = e.getYOnScreen();
            this.setLocation(xx - xMovimiento, yy - yMovimiento);
        }

        if (e.getSource() == scroll) {
            partMessages.setLocation(300, scroll.getValue() * -1);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public JButton getBtSend() {
        return btSend;
    }

    public void setBtSend(JButton btSend) {
        this.btSend = btSend;
    }

    public JButton getBtStart() {
        return btStart;
    }

    public void setBtStart(JButton btStart) {
        this.btStart = btStart;
    }

    public JTextField getPartText() {
        return partText;
    }

    public void setPartText(JTextField partText) {
        this.partText = partText;
    }

    public JTextField getNameText() {
        return nameText;
    }

    public void setNameText(JTextField nameText) {
        this.nameText = nameText;
    }

    public Perfil getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(Perfil fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public static void main(String[] args) {
        Diseño d = new Diseño();
    }
}
