/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.BST;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
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
        setBounds(pX, pY, sirka, vyska);
        border=BorderFactory.createTitledBorder("");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitleFont(new Font("Sans-serif", Font.ITALIC, 12));
        border.setTitle("Na picu");
        setBorder(border);
        setLayout(new BorderLayout());
        initBST();
        //initScenu();
        scena=new Scena(strom, 2, 2, 80 , 80);
        scena.setBackground(Color.red);
        add(scena,BorderLayout.CENTER);
    }
    
    private void initScenu(){
        scena=new Scena(strom,0, 0, sirka, vyska);
        //scena.setObrazok(img);
        scena.start();
        add(scena);
    }
    private void initBST(){
        strom=new BST();
        strom.insert(40);
        strom.insert(20);
        strom.insert(10);
        strom.insert(5);
        strom.insert(50);
        strom.insert(60);
        strom.insert(70);
        strom.insert(15);
        
        
    }
}
