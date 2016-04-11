/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import de.javasoft.plaf.synthetica.SyntheticaLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;


import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author ondrej
 */
public class HlavneOknoMain {

    private static void initLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new SyntheticaSkyMetallicLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(HlavneOknoMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(HlavneOknoMain.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        initLookAndFeel();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HlavneOkno().setVisible(true);
            }
        });

    }

}
