/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import backend.BST.BST;
import backend.Struktury;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ondrej
 */
public class Scena extends JComponent implements Runnable {

    private BufferedImage img;
    private static float delay;
    private int prestavka;
    private TitledBorder border;
    private Struktury s;
    private Thread animate;

    public Scena(Struktury pS, int pX, int pY, int pSirka, int pVyska) {
        super();

        setLocation(pX, pY);
        setPreferredSize(new Dimension(pSirka, pVyska));

        border = BorderFactory.createTitledBorder("");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitleFont(new Font("Sans-serif", Font.ITALIC, 12));

        setBorder(border);
        prestavka = 80;
        
        s = pS;

    }

    public void start() {
        if(animate==null){
            animate=new Thread(this);
            animate.start();
        }
    }  
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        s.NakresliStrukturu(img.getGraphics());

        g.drawImage(img, 0, 0, null);
    }

    @Override
    public void run() {
        
        long t = System.currentTimeMillis();
        while (Thread.currentThread() == animate) {
            repaint();
            try {
                t += prestavka;
                Thread.sleep(Math.max(0, t - System.currentTimeMillis()));
            } catch (InterruptedException e) {
                break;
            }
            
        }
    }

    public float getDelay() {
        return delay;
    }

    public static void setDelay(float delay) {
        Scena.delay = delay;
    }

    public static void pause(int pause) {
        try {
            float a = pause * delay;

            int b = (int) a;

            Thread.sleep(b);
        } catch (InterruptedException ex) {
            Logger.getLogger(BST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
