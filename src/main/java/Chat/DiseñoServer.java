package Chat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DiseñoServer extends JFrame implements MouseListener, MouseMotionListener {

    private JPanel panelData = new JPanel();
    private JLabel lbRight = new JLabel();
    private JLabel lbLeft = new JLabel();
    private JButton btStart = new JButton("START");

    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextArea jTextArea1 = new JTextArea();

    private ImageIcon image1 = new ImageIcon("C:\\Users\\Usuario\\Desktop\\Parte1.png");
    private ImageIcon image2 = new ImageIcon("C:\\Users\\Usuario\\Desktop\\Parte2.png");

    private int x = 131;
    private int y = 160;
    private int xMove = 0;
    private int yMove = 0;

    public DiseñoServer() {
        setSize(700, 400);
        setLayout(null);
        setFocusable(true);
        setLocationRelativeTo(null);
        addElements();
        addMouseListener(this);
        addMouseMotionListener(this);
        setUndecorated(true);
        setVisible(true);
    }

    public void addElements() {
        addLabelDesign(lbLeft, 450, 0, 250, 400, new Color(6, 35, 52), image1);
        addLabelDesign(lbRight, 0, 0, 450, 400, Color.WHITE, image2);

        addButtonDesign(btStart, x, y - 40, 185, 46, Color.BLACK);

        addJtextAreaDesign();

        panelData.setBounds(0, 0, 800, 400);
        panelData.setLayout(null);

        add(panelData);
    }

    public void addLabelDesign(JLabel part, int x, int y, int w, int h, Color c, ImageIcon i) {
        part.setOpaque(true);
        part.setBackground(c);
        part.setBounds(x, y, w, h);
        part.setIcon(new ImageIcon(i.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
        panelData.add(part);
    }

    public void addTextFieldDesign(JTextField part, int x, int y, int w, int h, Color c) {
        part.setBounds(x, y, w, h);
        part.setFont(new Font("Arial", Font.BOLD, 15));
        part.setHorizontalAlignment(SwingConstants.CENTER);
        part.setBackground(c);
        part.addMouseListener(this);
        part.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    }

    public void addButtonDesign(JButton part, int x, int y, int w, int h, Color c) {
        part.setBounds(x, y, w, h);
        part.setFont(new Font("Arial", Font.BOLD, 15));
        part.setHorizontalAlignment(SwingConstants.CENTER);
        part.setBackground(c);
        part.setForeground(Color.WHITE);
        part.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(btStart);
    }

    public void addJtextAreaDesign() {

        jScrollPane1.setBounds(50, 200, 350, 150);
        jTextArea1.setLineWrap(true);
        jTextArea1.setEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);
        
        add(jScrollPane1); 
    }
    public void editText(){
         jTextArea1.setText("Hola");
         jTextArea1.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() > 668 && e.getX() < 681 && e.getY() > 24 && e.getY() < 38) {
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        yMove = e.getY();
        xMove = e.getX();
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
        int newX = e.getXOnScreen();
        int newY = e.getYOnScreen();
        this.setLocation(newX - xMove, newY - yMove);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public JButton getBtStart() {
        return btStart;
    }

    public void setBtStart(JButton btStart) {
        this.btStart = btStart;
    }

    public JTextArea getjTextArea1() {
        return jTextArea1;
    }

    public void setjTextArea1(JTextArea jTextArea1) {
        this.jTextArea1 = jTextArea1;
    }

}
