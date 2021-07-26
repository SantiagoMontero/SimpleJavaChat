package Chat;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mensaje extends JPanel {

    private int x, y, w, h;
    private String text;

    private JLabel etiqueta = new JLabel();
    private ImageIcon imagen;

    public Mensaje(int x, int y, int w, int h, String text, String url) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.text = text;
        imagen = new ImageIcon(url);
        etiqueta.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        etiqueta.setBounds(0, 0, 50, 50);
        add(etiqueta);
        setBounds(x, y, w, h);
        setBackground(new Color(81, 196, 211));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString(text, 70, getHeight() / 2);
    }

}
