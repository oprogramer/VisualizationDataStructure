/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.BST;

import backend.FarbaUzlu;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ondrej
 */
public class BSTDelete implements Runnable {

    private Thread vlakno;
    private BST DS;
    private int hod;

    //premene ktore potrebujem ked uzol ktory mazeme ma oby dvoch synov
    public BSTUzol uzol;
    public int pUzolx;
    public int pUzoly;
    public int uzolx;
    public int uzoly;
    public BSTUzol pUzolRodic;
    public BSTUzol uzolRodic;

    public BSTDelete(BST pDS, int pHod) {
        this.DS = pDS;
        this.hod = pHod;
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
        DS.setKoren(Zmaz(DS.getKoren(), hod));

        DS.prepocitajVyskuStromu();
        DS.prepocitanieSuradnic();
        DS.vypis();
    }

    private BSTUzol Zmaz(BSTUzol pUzol, int pHod) {

        if (DS.getKoren() == null) {
            return DS.getKoren();
        }

        pUzol.oznac();
        pause();
        
        if (pHod < pUzol.getHod()) {
            pUzol.odznac();
            pUzol.setLavySyn(Zmaz(pUzol.getLavySyn(), pHod));

        } else if (pHod > pUzol.getHod()) {
            pUzol.odznac();
            pUzol.setPravySyn(Zmaz(pUzol.getPravySyn(), pHod));
        } else if (pHod == pUzol.getHod()) {

            pUzol.setFarbu(FarbaUzlu.zmazani);
            pause();
            if (pUzol.getLavySyn() == null) {

                if (pUzol.getPravySyn() != null) {

                    pUzol.getPravySyn().setRodic(pUzol.getRodic());
                    pUzol.getPravySyn().oznac();
                    pUzol.setRodic(null);
                    pause();
                    pUzol.getPravySyn().odznac();
                }
                
                pUzol.setSuradnice(pUzol.getX(), 1000);
                return pUzol.getPravySyn();
            } else if (pUzol.getPravySyn() == null) {

                pUzol.getLavySyn().setRodic(pUzol.getRodic());
                pUzol.getLavySyn().oznac();
                pUzol.setRodic(null);
                pause();
                pUzol.getLavySyn().odznac();
                
                
                pUzol.setSuradnice(pUzol.getX(), 1000);
                return pUzol.getLavySyn();
            } else {
                
                uzol = najmesiVpravomPodstrome(pUzol.getPravySyn());
                pUzolx = pUzol.getX();
                pUzoly = pUzol.getY();
                uzolx = uzol.getX();
                uzoly = uzol.getY();
                pUzolRodic = pUzol.getRodic();
                uzolRodic = uzol.getRodic();
                BSTUzol uPS = uzol.getPravySyn();
                BSTUzol uLS = uzol.getLavySyn();
                //Odpojime tie dva uzly ktore ideme vymenit
                uzol.setRodic(null);
                pUzol.setRodic(null);
                pUzol.getPravySyn().setRodic(null);
                pUzol.getLavySyn().setRodic(null);

                if (uPS != null) {
                    uPS.setRodic(null);
                }
                //Nepotrebne nastavovat kedze hladame najlavesi v pravom strome 
                //takze toto bude vydy null
                if (uLS != null) {
                    uLS.setRodic(null);
                }
                 //Koniec odpojovanie 
                //Posuvame uzly kim si nevimenia polohy
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        uzol.setSuradnice(pUzolx, pUzoly);

                    }
                }).start();
                pUzol.setSuradnice(uzolx, uzoly);
                //Koniec vymen poloh

                uzol.setRodic(pUzolRodic);

                //Problem ked je najlavejsi pravy potomok aj priami pravy potomok
                //takze potrebujeme vyriesit takze skontrolujeme 
                //ci sa nevimienaju syn s rodicom
                if (pUzol.getPravySyn() == uzol) {
                    uzol.setPravySyn(pUzol);
                    pUzol.setRodic(uzol);
                } else {
                    uzol.setPravySyn(pUzol.getPravySyn());
                    uzolRodic.setLavySyn(pUzol);
                    pUzol.getPravySyn().setRodic(uzol);
                    pUzol.setRodic(uzolRodic);
                }
                uzol.setLavySyn(pUzol.getLavySyn());

                pUzol.getLavySyn().setRodic(uzol);

                if (pUzolRodic != null) {
                    if (pUzolRodic.getPravySyn() == pUzol) {
                        System.out.println("Pravy syn");
                        pUzolRodic.setPravySyn(uzol);
                        System.out.println("" + pUzolRodic.getPravySyn().getHod());
                    }
                    if (pUzolRodic.getLavySyn() == pUzol) {
                        System.out.println("Lavy syn");
                        pUzolRodic.setLavySyn(uzol);
                        System.out.println("" + pUzolRodic.getLavySyn().getHod());
                    }
                } else {
                    DS.setKoren(uzol);
                }

                pUzol.setPravySyn(uPS);
                pUzol.setLavySyn(uLS);
                if (uPS != null) {
                    uPS.setRodic(pUzol);
                }
                //Nepotrebne nastavovat kedze hladame najlavesi v pravom strome 
                //takze toto bude vydy null
                if (uLS != null) {
                    uLS.setRodic(pUzol);
                }
                uzol.setPravySyn(Zmaz(uzol.getPravySyn(), pUzol.getHod()));
                uzol.odznac();
                uzol.setFarbu(FarbaUzlu.normalny);
                uzol.getPravySyn().odznac();
                return uzol;
            }
            
        }
        pUzol.odznac();
        pUzol.setFarbu(FarbaUzlu.normalny);
        return pUzol;
    }

    private BSTUzol najmesiVpravomPodstrome(BSTUzol pUzol) {
        while (pUzol.getLavySyn() != null) {
            pUzol.oznac();
            pause();
            pUzol.odznac();
            pUzol = pUzol.getLavySyn();
            
        }
        pUzol.setFarbu(FarbaUzlu.najdeny);
        
        return pUzol;
    }
    
    private void pause(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BSTDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
