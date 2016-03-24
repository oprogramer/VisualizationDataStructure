/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.BST;

import backend.FarbaUzlu;
import com.sun.javafx.font.t2k.T2KFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ondrej
 */
public class BSTVloz implements Runnable {

    private Thread vlakno;
    private BST T;
    private BSTUzol u;
    private boolean uspesne;

    public BSTVloz(BST pT, BSTUzol pU) {
        T = pT;
        u = pU;

        start();
    }

    private void start() {
        if (vlakno == null) {
            vlakno = new Thread(this);
            vlakno.start();
        }
    }

    @Override
    public void run() {

        if (T.getKoren() == null) {

            u.setSuradnice(T.panel.scena.getWidth() / 2, 50);
            T.setKoren(u);
            pause();
            uspesne = true;
        } else {
            uspesne = insert(T.getKoren());
        }
        if (uspesne) {
            u.setFarbu(FarbaUzlu.normalny);
            T.prepocitajVyskuStromu();
            T.prepocitanieSuradnic();

        }
        
    }

    private boolean insert(BSTUzol pU) {

        //Ked vlozime hodnotu ktora uz je vlozena v strome
        //Zahodime ho lebo v strome sa nemozu nachadzat rovnake hodnoty
        if (pU.getHod() == u.getHod()) {

            u.setFarbu(FarbaUzlu.existujuci);
            pause();
            T.zmazNovy();
            return false;
        } else {
            u.setSuradnice(pU.getX(), pU.getY()-35);
            pause();
            if (u.getHod() < pU.getHod()) {
                if (pU.getLavySyn() == null) {
                    u.setSuradnice(pU.getX() - u.getVelkost(), pU.getY() + u.getVelkost());
                    pU.setLavySyn(u);
                    u.setRodic(pU);
                    return true;
                } else {
                    
                    insert(pU.getLavySyn());
                }
            }
            if (u.getHod() > pU.getHod()) {
                if (pU.getPravySyn() == null) {
                    u.setSuradnice(pU.getX() + u.getVelkost(), pU.getY() + u.getVelkost());
                    pU.setPravySyn(u);
                    u.setRodic(pU);
                    return true;
                } else {
                    
                    insert(pU.getPravySyn());
                }
            }
            return true;
        }

    }

    private void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BSTVloz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
