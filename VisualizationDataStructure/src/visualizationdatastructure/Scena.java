/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizationdatastructure;

import backend.Struktury;
import com.sun.javafx.animation.TickCalculation;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

/**
 *
 * @author ondrej
 */
public class Scena extends JComponent implements ActionListener{

    BufferedImage img;
    Timer casovac;
    int prestavka;
    TitledBorder border;
    Struktury s;
    
    public Scena(Struktury pS,int pX,int pY,int pSirka,int pVyska){
        super();
        setBounds(pX, pY, pSirka, pVyska);
        setBackground(Color.red);
        border=BorderFactory.createTitledBorder("");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitleFont(new Font("Sans-serif", Font.ITALIC, 12));
        border.setTitle("Scena");
        setBorder(border);
        prestavka=1000;
        casovac=new Timer(prestavka, this);
        s=pS;
        
        img=new BufferedImage(1280, 780, BufferedImage.TYPE_INT_ARGB);
    }
    
   

    public void start(){
        casovac.start();
    }
    
    public void setObrazok(BufferedImage pImg){
        img=pImg;
    }

    

    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        repaint();
        
    }

    @Override
    public void paint(Graphics g) {
       // super.paint(g);
        
        
        s.NakresliStrukturu(img.getGraphics());
        g.drawImage(img, 0, 0, null);
    }
    
    
    
    
    
}
