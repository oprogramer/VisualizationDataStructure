/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import backend.BST.BSTPanel;
import backend.BST.BSTUzol;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ondrej
 */
public class HlavneOkno extends JFrame {

    private String currentDS = "Stromy";
    private JFrame frame;
    private JTabbedPane pane;

    private BSTPanel bstPanel;

    public HlavneOkno() {
        super(Nadpisy.nadpis);
        frame = this;
        init();

    }

    private void init() {

        this.setPreferredSize(new Dimension(1280, 768));
        this.setMinimumSize(new Dimension(800, 600));
        this.setSize(this.getPreferredSize());

        initMenu();
        initOkno();

        this.pack();

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initOkno() {
        pane = new JTabbedPane();
        switch (currentDS) {
            case "Stromy": {
                if (bstPanel == null) {
                    bstPanel = new BSTPanel(0, 0, this.getWidth(), this.getHeight());
                }

                pane.add("BST", bstPanel);
                break;
            }
            case "HashTable": {
                
                break;
            }

        }

        add(pane);

    }

    private void initMenu() {
        //Vytvorime menu bar 
        JMenuBar menuBar = new JMenuBar();
        //Vytvorime polozky do menu bar
        JMenu subor = new JMenu(Nadpisy.menuSubor);
        //JMenu struktury = new JMenu(Nadpisy.menuStruktury);
        //Vytvorime menuItems ktore pridame do polozkov menu
        //Pre menu Subor
        //Polozka novy v ponuky subor

        MojMenuItem novy = new MojMenuItem(Nadpisy.menuItemNovy);
        novy.setActionCommand("novy");
        novy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        //Polozka otovrit v ponuke subor
        MojMenuItem otvorit = new MojMenuItem(Nadpisy.menuItemOtvorit);
        otvorit.setActionCommand("otvorit");
        otvorit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        //polozka ulozit v ponuke subor
        MojMenuItem ulozit = new MojMenuItem(Nadpisy.menuItemUloziť);
        ulozit.setActionCommand("ulozit");
        ulozit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        //polozka zavriet v ponuke subor
        MojMenuItem zavriet = new MojMenuItem(Nadpisy.koniec);
        zavriet.setActionCommand("zavriet");
        zavriet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));

        //Pre menu struktury
        //polozka stromy pre zobrazenie udajovej strukturz 
        //t.j. pre zobrazenie TabbedPane s ponukou vsetkych naimplementovanych 
        //udajovvych struktur ktore su stromami
////        MojMenuItem stromy = new MojMenuItem(Nadpisy.menuItemStromy);
////        stromy.setActionCommand("stromy");
////        MojMenuItem hashtable = new MojMenuItem(Nadpisy.menuItemHash);
////        hashtable.setActionCommand("hashtable");
        //Pridame menu items do polozky v menu
        subor.add(novy);
        subor.add(otvorit);
        subor.add(ulozit);
        subor.add(zavriet);

//        struktury.add(stromy);
//        struktury.add(hashtable);

        menuBar.add(subor);
        //menuBar.add(struktury);

        this.setJMenuBar(menuBar);

    }

    private class MojMenuItem extends JMenuItem implements ActionListener {

        public MojMenuItem(String pNazov) {
            super(pNazov);
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "novy": {
                    HlavneOkno okno = new HlavneOkno();

                    okno.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    okno.setVisible(true);
                    break;
                }

                case "otvorit": {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
                    fileChooser.setAcceptAllFileFilterUsed(true);
                    int rezultat=fileChooser.showOpenDialog(this);
                    if(rezultat==JFileChooser.APPROVE_OPTION){
                        BufferedReader br = null;
                        String s="";
                        try {
                            File selectedFile = fileChooser.getSelectedFile();
                            br = new BufferedReader(new FileReader(selectedFile.getPath()));
                            String line;
                            while((line=br.readLine())!=null){
                                s+=line;
                            }
                        } catch (FileNotFoundException ex) {
                            JOptionPane.showMessageDialog(null,ex.getMessage());
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        } finally {
                            try {
                                br.close();
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, ex.getMessage());
                            }
                        }
                        String[] tokens=s.split(" ");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                switch(currentDS){
                                    case "Stromy":{
                                        
                                        switch(pane.getSelectedIndex()){
                                            case 0:{
                                                bstPanel.strom.clean();
                                                for(String hod:tokens){
                                                    bstPanel.strom.vloz(Integer.parseInt(hod));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }).start();
                    }
                    break;
                }
                case "ulozit": {

                    switch (currentDS) {
                        case "Stromy": {

                            int selecteDS = pane.getSelectedIndex();

                            switch (selecteDS) {
                                //Aktualna je datova struktura BST
                                case 0: {
                                    ArrayList<BSTUzol> zoznam = bstPanel.strom.preOrder(false);
                                    StringBuilder sb = new StringBuilder();
                                    for (BSTUzol uzol : zoznam) {
                                        sb.append(uzol.getHod() + " ");
                                    }
                                    JFileChooser fileChooser = new JFileChooser();
                                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                                    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
                                    fileChooser.setAcceptAllFileFilterUsed(true);
                                    fileChooser.setDialogTitle(Nadpisy.menuItemUloziť);
                                    int result = fileChooser.showOpenDialog(this);
                                    if (result == JFileChooser.APPROVE_OPTION) {

                                        try {
                                            
                                            File selectedFile =fileChooser.getSelectedFile();
                                            if(!selectedFile.toString().endsWith(".txt")){
                                                selectedFile = new File(selectedFile.getCanonicalPath()+".txt");
                                            }
                                            
                                            
                                            
                                            FileWriter fw = new FileWriter(selectedFile.getPath());
                                            fw.write(sb.toString());
                                            fw.flush();
                                            fw.close();
                                        } catch (IOException ex) {
                                            JOptionPane.showMessageDialog(null, ex.getMessage());
                                        }

                                    }
                                }
                                //Aktualna je datova struktura AVL
                                case 1: {

                                }
                            }
                        }
                        case "HashTable": {
                            
                        }
                    }
                    break;
                }
                case "zavriet": {
                    System.exit(0);
                    break;
                }
                case "stromy": {
                    frame.getContentPane().removeAll();
                    currentDS = "Stromy";
                    initOkno();
                    frame.validate();

                    frame.repaint();

                    break;
                }
                case "hashtable": {
                    frame.getContentPane().removeAll();
                    currentDS = "HashTable";
                    initOkno();
                    frame.validate();

                    frame.repaint();

                    break;
                }
                default: {

                }
            }
        }

    }
}
