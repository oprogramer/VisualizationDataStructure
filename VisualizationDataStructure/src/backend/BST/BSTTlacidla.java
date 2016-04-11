/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.BST;

import backend.Struktury;
import backend.Uzol;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import ui.Nadpisy;
import ui.Scena;

/**
 * Trieda ktorá vytvorí všetky potrebné ovladacie prvky pre údajovú štruktúru
 * binárny vyhľadavaci strom. Je to panel na ktorom budu všetky prvky.uthor
 * ondrej
 */
public class BSTTlacidla extends JPanel implements ActionListener, ChangeListener {

    private TitledBorder border;
    private Struktury s;
    private JTextField poleVkladanie;
    private Dimension preferedSize;
    private BSTPanel panel;
    //Tlacidla na ovladanie
    private JButton vloz, najdi, zmaz, random, clear, vypis;
    private JRadioButton rbPreOrder;
    private JRadioButton rbInOrder;
    private JRadioButton rbPostOrder;

    /**
     * Konštruktor ktorý vytvorí objekt tejto triedy. Najprv nastavi graficke
     * prvky panelu, nasledne inicializuje ovladcie prvky.
     *
     * @param pS - údajová štruktúra ktorú maju prvky ovladat.
     */
    public BSTTlacidla(Struktury pS, BSTPanel pPanel) {
        setLayout(new BorderLayout(5, 5));
        border = BorderFactory.createTitledBorder("");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitleFont(new Font("Sans-serif", Font.ITALIC, 12));
        border.setTitle(Nadpisy.ovladanie);
        setBorder(border);

        preferedSize = new Dimension(75, 25);
        s = pS;
        panel = pPanel;
        JPanel prvy = initPrvyRiadok();
        JPanel druhy = initDruhyRiadok();
        JPanel slider = initSlider();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(prvy);
        add(druhy);
        add(slider);
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

    /**
     * Metoda ktorá vytvori panel s ovladacimi prvkami v prvom rade
     *
     * @return - panel ktorý obsahuje pole na vkladanie udaov, a tlačidla
     * vlož,najdi,zmaz,nahodne a uvolni
     */
    private JPanel initPrvyRiadok() {
        JPanel prvy = new JPanel();
        prvy.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        //Vlozime na panel pole na vnasanie udajov
        poleVkladanie = new JTextField();
        poleVkladanie.setMinimumSize(preferedSize);
        poleVkladanie.setPreferredSize(preferedSize);
        poleVkladanie.setToolTipText(Nadpisy.tooltiptxtVloz);

        prvy.add(poleVkladanie);
        //Vlozime na panel talcidka pre vlozenie prvku, najdenie a zmazanie
        prvy.add(initBtnVloz());
        prvy.add(initBtnNajdi());
        prvy.add(initBtnZmaz());
        prvy.add(initRandom());
        prvy.add(initClear());
        return prvy;
    }

    /**
     * Metoda ktorá vytvori panel s ovladacimi prvkami v druhom rade na vypis
     * štruktúry.
     *
     * @return - panel ktorý obsahuje radion button pre vyber poradia vypisu a
     * tlačidlo ktoré spusti vypis
     */
    private JPanel initDruhyRiadok() {
        JPanel druhy = new JPanel();
        druhy.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        rbPreOrder = new JRadioButton(Nadpisy.rbPreOrder);
        rbInOrder = new JRadioButton(Nadpisy.rbInOrder);
        rbPostOrder = new JRadioButton(Nadpisy.rbPostOrder);
        rbPreOrder.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(rbPreOrder);
        group.add(rbInOrder);
        group.add(rbPostOrder);

        druhy.add(rbPreOrder);

        druhy.add(rbInOrder);
        druhy.add(rbPostOrder);

        JButton vypis = initVypis();
        druhy.add(vypis);
        return druhy;
    }

    /**
     * Metoda ktorá vytvorí panel na ktorom bude slider na ovladanie rychlosti
     * animácie.
     *
     * @return - panel s sliderom
     */
    private JPanel initSlider() {
        JPanel slider = new JPanel();
        int FPS_MIN = 0;
        int FPS_MAX = 390;
        int FPS_INIT = 200;

        //Create the slider.
        JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL,
                FPS_MIN, FPS_MAX, FPS_INIT);
        framesPerSecond.setToolTipText(Nadpisy.tooltipsliderRychost);
        framesPerSecond.addChangeListener(this);

        //Turn on labels at major tick marks.
        framesPerSecond.setBorder(
                BorderFactory.createEmptyBorder(0, 0, 10, 0));
        framesPerSecond.setPreferredSize(new Dimension(800, 15));
        Scena.setDelay(framesPerSecond.getValue() / FPS_INIT);

        slider.add(new JLabel(Nadpisy.pomalsie));
        slider.add(framesPerSecond);
        slider.add(new JLabel(Nadpisy.rychlejsie));

        return slider;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String txt = poleVkladanie.getText();
        switch (e.getActionCommand()) {
            case "vloz": {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        zpristupni(false);
                        if (txt.equals("")) {
                            Random ran = new Random();
                            int hod = ran.nextInt(100);
                            s.vloz(hod);
                        } else {

                            try {
                                int hod = Integer.parseInt(txt);
                                s.vloz(hod);

                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Musí byť vložené celé čislo!");
                            }
                            poleVkladanie.setText("");
                        }

                        zpristupni(true);
                    }
                }).start();

                break;
            }
            case "najdi": {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        zpristupni(false);
                        if (txt.equals("")) {

                        } else {
                            try {
                                int hod = Integer.parseInt(txt);
                                s.najdi(hod);

                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Musí byť vložené celé čislo!");
                            }
                            poleVkladanie.setText("");
                        }

                        zpristupni(true);
                    }
                }).start();

                break;
            }
            case "zmaz": {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        zpristupni(false);
                        if (txt.equals("")) {

                        } else {
                            try {
                                int hod = Integer.parseInt(txt);
                                s.zmaz(hod);

                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Musí byť vložené celé čislo!");
                            }
                            poleVkladanie.setText("");
                        }

                        zpristupni(true);
                    }
                }).start();

                break;
            }
            case "clear": {

                panel.kom.zmazKomentare();
                s.clean();
                break;
            }
            case "random": {
                Random rand = new Random();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        zpristupni(false);
                        if (txt.equals("")) {

                            for (int i = 0; i < 10; i++) {
                                int hod = rand.nextInt(100);

                                s.vloz(hod);

                            }

                        } else {
                            try {
                                int hod = Integer.parseInt(txt);

                                for (int i = 0; i < hod; i++) {
                                    int hodv = rand.nextInt(100);

                                    s.vloz(hodv);

                                }
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Musí byť vložené celé čislo!");
                            }
                            poleVkladanie.setText("");
                        }
                        zpristupni(true);

                    }
                }, "Random").start();

                break;
            }
            case "vypis": {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        StringBuilder br = new StringBuilder();
                        ArrayList<Uzol> zoznam;
                        if (rbInOrder.isSelected()) {
                            zoznam = s.vypis("inorder");
                            if (zoznam.isEmpty()) {
                                br.append("Strom je prázdny, nie je čo prehľadavať.");
                            } else {
                                for (Uzol uzol : zoznam) {
                                    br.append(uzol.getHod() + " ");
                                }
                            }

                        }
                        if (rbPostOrder.isSelected()) {
                            zoznam = s.vypis("postorder");
                            if (zoznam.isEmpty()) {
                                br.append("Strom je prázdny, nie je čo prehľadavať.");
                            } else {
                                for (Uzol uzol : zoznam) {
                                    br.append(uzol.getHod() + " ");
                                }
                            }
                        }
                        if (rbPreOrder.isSelected()) {
                            zoznam = s.vypis("preorder");
                            if (zoznam.isEmpty()) {
                                br.append("Strom je prázdny, nie je čo prehľadavať.");
                            } else {
                                for (Uzol uzol : zoznam) {
                                    br.append(uzol.getHod() + " ");
                                }
                            }

                        }

                        panel.kom.pridajKomentar(br.toString());
                    }
                }).start();

            }
        }
    }

    /**
     * Metoda vytvori tlačidlo na spušťanie algoritmu na vkladanie uzlov. Najprv
     * vytvori nove tlačidlo ktrému nastaví listener v tejto triede, potom
     * komadnu na akciu aby listener ktoré je to tlačidlo, taktiež nastavime aj
     * menimalnu velkosť a vratime tlačidlo.
     *
     * @return - tlačidlo ktoré spusti algoritmus na vkladanie uzlov
     */
    private JButton initBtnVloz() {
        vloz = new JButton(Nadpisy.btnVloz);
        vloz.addActionListener(this);
        vloz.setToolTipText(Nadpisy.tooltipbtnVloz);
        vloz.setActionCommand("vloz");
        vloz.setMinimumSize(preferedSize);
        return vloz;

    }

    /**
     * Metoda vytvori tlačidlo na spušťanie algoritmu na hľadanie uzlov. Najprv
     * vytvori nove tlačidlo ktrému nastaví listener v tejto triede, potom
     * rozkaz na akciu aby listener ktoré je to tlačidlo, taktiež nastavime aj
     * menimalnu velkosť a vratime tlačidlo.
     *
     * @return - tlačidlo ktoré spusti algoritmus na hľadanie uzlov
     */
    private JButton initBtnNajdi() {
        najdi = new JButton(Nadpisy.btnNajdi);
        najdi.addActionListener(this);
        najdi.setToolTipText(Nadpisy.tooltipbtnNajdi);
        najdi.setActionCommand("najdi");
        najdi.setPreferredSize(preferedSize);
        return najdi;
    }

    /**
     * Metoda vytvori tlačidlo na spušťanie algoritmu na zmazanie uzlov. Najprv
     * vytvori nove tlačidlo ktrému nastaví listener v tejto triede, potom
     * rozkaz na akciu aby listener ktoré je to tlačidlo, taktiež nastavime aj
     * menimalnu velkosť a vratime tlačidlo.
     *
     * @return - tlačidlo ktoré spusti algoritmus na zmazanie uzlov
     */
    private JButton initBtnZmaz() {
        zmaz = new JButton(Nadpisy.btnZmaz);
        zmaz.addActionListener(this);
        zmaz.setToolTipText(Nadpisy.tooltipbtnZmaz);
        zmaz.setActionCommand("zmaz");
        //zmaz.setPreferredSize(preferedSize);
        return zmaz;
    }

    /**
     * Metoda vytvori tlačidlo na spušťanie algoritmu na zmazanie celej
     * štruktúry. Najprv vytvori nove tlačidlo ktrému nastaví listener v tejto
     * triede, potom rozkaz na akciu aby listener ktoré je to tlačidlo, taktiež
     * nastavime aj menimalnu velkosť a vratime tlačidlo.
     *
     * @return - tlačidlo ktoré spusti algoritmus na zmazanie celej štruktúry
     */
    private JButton initClear() {
        clear = new JButton(Nadpisy.btnClear);
        clear.addActionListener(this);
        clear.setToolTipText(Nadpisy.tooltipbtnUvolni);
        clear.setActionCommand("clear");
        clear.setMinimumSize(preferedSize);
        return clear;

    }

    /**
     * Metoda vytvori tlačidlo na spušťanie algoritmu na vytvorenie uzlov s
     * nahodnými hodnotami. Najprv vytvori nove tlačidlo ktrému nastaví listener
     * v tejto triede, potom rozkaz na akciu aby listener ktoré je to tlačidlo,
     * taktiež nastavime aj menimalnu velkosť a vratime tlačidlo.
     *
     * @return - tlačidlo ktoré spusti algoritmus na vytvorenie uzlov s nahodnou
     * hodnotou
     */
    private JButton initRandom() {
        random = new JButton(Nadpisy.btnRandom);
        random.addActionListener(this);
        random.setToolTipText(Nadpisy.tooltipbtnRandom);
        random.setActionCommand("random");
        random.setMinimumSize(preferedSize);
        return random;

    }

    /**
     * Metoda vytvori tlačidlo na spušťanie algoritmu na vypis uzlov. Najprv
     * vytvori nove tlačidlo ktrému nastaví listener v tejto triede, potom
     * rozkaz na akciu aby listener ktoré je to tlačidlo, taktiež nastavime aj
     * menimalnu velkosť a vratime tlačidlo.
     *
     * @return - tlačidlo ktoré spusti algoritmus na vypis údajovej štruktúry
     */
    private JButton initVypis() {
        vypis = new JButton(Nadpisy.btnVypis);
        vypis.addActionListener(this);
        vypis.setToolTipText(Nadpisy.tooltipbtnVypis);
        vypis.setActionCommand("vypis");
        vypis.setPreferredSize(preferedSize);
        return vypis;
    }

    private void zpristupni(boolean pZ) {
        poleVkladanie.setEnabled(pZ);
        vloz.setEnabled(pZ);
        najdi.setEnabled(pZ);
        zmaz.setEnabled(pZ);
        random.setEnabled(pZ);
        clear.setEnabled(pZ);
        vypis.setEnabled(pZ);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            int fps = (int) source.getValue();
            fps = 400 - fps;
            float a = ((float) fps) / ((float) 200);

            Scena.setDelay(a);
        }

    }
}
