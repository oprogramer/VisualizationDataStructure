/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.BST;

import backend.Struktury;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import visualizationdatastructure.Nadpisy;

/**
 *
 * @author ondrej
 */
public class BSTTlacidla extends JPanel implements ActionListener {

    TitledBorder border;
    Struktury s;
    JTextField poleVkladanie;
    Dimension preferedSize;

    public BSTTlacidla(Struktury pS) {
        setLayout(new BorderLayout(5, 5));
        border = BorderFactory.createTitledBorder("");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitleFont(new Font("Sans-serif", Font.ITALIC, 12));
        border.setTitle(Nadpisy.ovladanie);
        setBorder(border);

        preferedSize = new Dimension(75, 25);
        s = pS;

        JPanel prvy = initPrvyRiadok();
        JPanel druhy = initDruhyRiadok();

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(prvy);
        add(druhy);

    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(800, 100); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(1920, 150); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1280, 150); //To change body of generated methods, choose Tools | Templates.
    }

    private JPanel initPrvyRiadok() {
        JPanel prvy = new JPanel();
        prvy.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        //Vlozime na panel pole na vnasanie udajov
        poleVkladanie = new JTextField();
        poleVkladanie.setMinimumSize(preferedSize);
        poleVkladanie.setPreferredSize(preferedSize);

        prvy.add(poleVkladanie);
        //Vlozime na panel talcidka pre vlozenie prvku, najdenie a zmazanie
        prvy.add(initBtnVloz());
        prvy.add(initBtnNajdi());
        prvy.add(initBtnZmaz());
        prvy.add(initRandom());
        prvy.add(initClear());
        return prvy;
    }

    private JPanel initDruhyRiadok() {
        JPanel druhy = new JPanel();

        druhy.add(new JButton("AAAAAAAAAAAA"));

        return druhy;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String txt = poleVkladanie.getText();
        switch (e.getActionCommand()) {
            case "vloz": {

                if (txt.equals("")) {
                    Random ran=new Random();
                    int hod=ran.nextInt(100);
                    s.vloz(hod);
                } else {

                    try {
                        int hod = Integer.parseInt(txt);
                        s.vloz(hod);
                        poleVkladanie.setText("");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Please give number");
                    }

                }
                break;
            }
            case "najdi": {
                if (txt.equals("")) {

                } else {
                    try {
                        int hod = Integer.parseInt(txt);
                        s.najdi(hod);
                        poleVkladanie.setText("");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Please give number");
                    }
                }

                break;
            }
            case "zmaz": {
                if (txt.equals("")) {

                } else {
                    try {
                        int hod = Integer.parseInt(txt);
                        s.zmaz(hod);
                        poleVkladanie.setText("");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Please give number");
                    }
                }

                break;
            }
            case "clear": {
                s.clean();
                break;
            }
            case "random": {
                Random rand = new Random();
                if (txt.equals("")) {

                    for (int i = 0; i < 10; i++) {
                        int hod = rand.nextInt(100);
                        
                        s.vloz(hod);
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(BSTTlacidla.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else {
                    try {
                        int hod = Integer.parseInt(txt);

                        poleVkladanie.setText("");
                        for (int i = 0; i < hod; i++) {
                            int hodv = rand.nextInt(100);
                            
                            s.vloz(hodv);
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(BSTTlacidla.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Please give number");
                    }
                }

                break;
            }
        }
    }

    private JButton initBtnVloz() {
        JButton vloz = new JButton(Nadpisy.btnVloz);
        vloz.addActionListener(this);
        vloz.setActionCommand("vloz");
        vloz.setMinimumSize(preferedSize);
        return vloz;

    }

    private JButton initBtnNajdi() {
        JButton najdi = new JButton(Nadpisy.btnNajdi);
        najdi.addActionListener(this);
        najdi.setActionCommand("najdi");
        najdi.setPreferredSize(preferedSize);
        return najdi;
    }

    private JButton initBtnZmaz() {
        JButton zmaz = new JButton(Nadpisy.btnZmaz);
        zmaz.addActionListener(this);
        zmaz.setActionCommand("zmaz");
        zmaz.setPreferredSize(preferedSize);
        return zmaz;
    }

    private JButton initClear() {
        JButton clear = new JButton(Nadpisy.btnClear);
        clear.addActionListener(this);
        clear.setActionCommand("clear");
        clear.setMinimumSize(preferedSize);
        return clear;

    }

    private JButton initRandom() {
        JButton random = new JButton(Nadpisy.btnRandom);
        random.addActionListener(this);
        random.setActionCommand("random");
        random.setMinimumSize(preferedSize);
        return random;

    }
}
