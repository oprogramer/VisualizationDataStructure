/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.BST;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import visualizationdatastructure.Scena;

/**
 *
 * @author ondrej
 */
public class BSTPanel extends JPanel{
    
    
    TitledBorder border;
    int sirka,vyska;
    public BST strom;
    Scena scena;
    
    public BSTPanel(int pX,int pY,int pSirka,int pVyska) {
        super();
        sirka=pSirka;
        vyska=pVyska;
        //setBounds(pX, pY, sirka, vyska);
        setLocation(pX, pY);
        setPreferredSize(new Dimension(sirka, vyska));
        setLayout(new BorderLayout(5, 5));
        
        border=BorderFactory.createTitledBorder("");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitleFont(new Font("Sans-serif", Font.ITALIC, 12)); 
        setBorder(border);
        
        initBST();
        initScenu();
        
        
        
        JButton b=new JButton("aaaaaaaaaaa");
        b.setPreferredSize(new Dimension((int)(sirka*0.33), vyska));
        add(b,BorderLayout.LINE_END);
        BSTTlacidla ovladanie=new BSTTlacidla(strom);
        add(ovladanie,BorderLayout.PAGE_END);
    }
    
    private void initScenu(){
        scena=new Scena(strom, 2, 2, sirka , (int)(vyska*0.75));
        scena.setBackground(Color.red);
        scena.start();
        add(scena,BorderLayout.CENTER);
        
    }
    private void initBST(){
        strom=new BST(this);
        
        
        
    }
}
