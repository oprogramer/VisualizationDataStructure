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
 * Trieda obsahuje konponenty ako su scena, tlačidla a komentáre. Prvo vytvorí
 * údajovú štruktúru BST a potom inicializuje komponenty ktoré pridá na panel
 * ktorý sa zobrazí na forme.
 * 
 * @author ondrej
 */
public class BSTPanel extends JPanel{
    
    
    TitledBorder border;
    int sirka,vyska;
    public BST strom;
    Scena scena;
    
    /**
     * Konštruktor ktorý nastaví všetky potrebne parametre pre zobrazenie
     * panelu ako su lokcia, velkosť a grafické zobrazenie. Potom vytvorí 
     * údajovú štruktúru. Potom si vytvorí komponenty scena,komentare a 
     * BSTTlačidla a pridá ich na panel.
     * 
     * @param pX - x-ova suradnica horného praveého rohu,odkiaľ sa ma zobraziť
     * @param pY - y-ova suradnica horného praveého rohu,odkiaľ sa ma zobraziť
     * @param pSirka - cele čislo ktoré predsavuje širku komponentu
     * @param pVyska - cele čislo ktoré predsavuje vyšku komponentu 
     */
    public BSTPanel(int pX,int pY,int pSirka,int pVyska) {
        super();
        sirka=pSirka;
        vyska=pVyska;
        
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
    /**
     * Metoda vytvori objekt typu Scena, aj naštartuje časovač ktory 
     * zabezpeči vykreslovanie údajovej štruktúry. Nakoniec prida scenu na panel.
     */
    private void initScenu(){
        scena=new Scena(strom, 2, 2, sirka , (int)(vyska*0.75));
        
        scena.start();
        add(scena,BorderLayout.CENTER);
        
    }
    /**
     * Metoda ktora vytvori údajovú štruktúru BST
     */
    private void initBST(){
        strom=new BST(this);
        
        
        
    }
}
