/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizationdatastructure;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author ondrej
 */
public class HlavneOkno extends JFrame{
    
    public HlavneOkno(){
        super(Nadpisy.nadpis);
        init();
    }
    
    private void init(){
        
    initMenu();
    
    this.setPreferredSize(new Dimension(1280, 768));
    
    this.pack();
    this.setSize(this.getPreferredSize());
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
   
    
    }
    
    private void initMenu(){
        //Vytvorime menu bar 
        JMenuBar menuBar=new JMenuBar();
        //Vytvorime polozky do menu bar
        JMenu subor=new JMenu(Nadpisy.menuSubor);
        JMenu struktury=new JMenu(Nadpisy.menuStruktury);
        //Vytvorime menuItems ktore pridame do polozkov menu
        //Pre menu Subor
        //Polozka novy v ponuky subor
        
        MojMenuItem novy=new MojMenuItem(Nadpisy.menuItemNovy);
        novy.setActionCommand("novy");
        novy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        //Polozka otovrit v ponuke subor
        MojMenuItem otvorit=new MojMenuItem(Nadpisy.menuItemOtvorit);
        otvorit.setActionCommand("otvorit");
        otvorit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        //polozka ulozit v ponuke subor
        MojMenuItem ulozit=new MojMenuItem(Nadpisy.menuItemUloziť);
        ulozit.setActionCommand("ulozit");
        ulozit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        //polozka zavriet v ponuke subor
        MojMenuItem zavriet=new MojMenuItem(Nadpisy.koniec);
        zavriet.setActionCommand("zavriet");
        zavriet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
        
        //Pre menu struktury
        //polozka stromy pre zobrazenie udajovej strukturz 
        //t.j. pre zobrazenie TabbedPane s ponukou vsetkych naimplementovanych 
        //udajovvych struktur ktore su stromami
        
        MojMenuItem stromy=new MojMenuItem(Nadpisy.menuItemStromy);
        stromy.setActionCommand("stromy");
        
        //Pridame menu items do polozky v menu
        subor.add(novy);
        subor.add(otvorit);
        subor.add(ulozit);
        subor.add(zavriet);
        
        struktury.add(stromy);
        
        menuBar.add(subor);
        menuBar.add(struktury);
        
        this.setJMenuBar(menuBar);
    }
    
    
    private class MojMenuItem extends JMenuItem implements ActionListener{

        public MojMenuItem(String pNazov){
            super(pNazov);
            addActionListener(this);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()){
                case "novy": {
                    HlavneOkno okno= new HlavneOkno();
               
                    okno.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    okno.setVisible(true);
                    break;
                }
                
                case "otvorit":{
                     break;
                }
                case "ulozit":{
                    break;
                }
                case "zavriet":{
                    System.exit(0);
                    break;
                }
                case "stromy":{
                    break;
                }
                default:{
                    
                }
            }
        }
        
    }
}
