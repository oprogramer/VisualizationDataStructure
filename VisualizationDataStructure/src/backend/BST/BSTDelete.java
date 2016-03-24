/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.BST;

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
        //DS.prepocitajVyskuStromu();
        DS.prepocitanieSuradnic();
    }

    private BSTUzol Zmaz(BSTUzol pUzol, int pHod) {

        if (DS.getKoren() == null) {
            return DS.getKoren();
        }

        if (pHod < pUzol.getHod()) {
            pUzol.setLavySyn(Zmaz(pUzol.getLavySyn(), pHod));
        } else {
            if (pHod > pUzol.getHod()) {
                pUzol.setPravySyn(Zmaz(pUzol.getPravySyn(), pHod));
            } else {
                if (pHod == pUzol.getHod()) {
                    if (pUzol.getLavySyn() == null) {
                        pUzol.getPravySyn().setRodic(pUzol.getRodic());
                        return pUzol.getPravySyn();
                    } else {
                        if (pUzol.getPravySyn() == null) {
                            pUzol.getLavySyn().setRodic(pUzol.getRodic());
                            return pUzol.getLavySyn();
                        }else{
                            
                            uzol=najmesiVpravomPodstrome(pUzol.getPravySyn());
                            pUzolx=pUzol.getX();
                            pUzoly=pUzol.getY();
                            uzolx=uzol.getX();
                            uzoly=uzol.getY();
                            pUzolRodic=pUzol.getRodic();
                            uzolRodic=uzol.getRodic();
                            //Odpojime tie dva uzly ktore ideme vymenit
                            uzol.setRodic(null);
                            pUzol.setRodic(null);
                            pUzol.getPravySyn().setRodic(null);
                            pUzol.getLavySyn().setRodic(null);
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
                            uzol.setLavySyn(pUzol.getLavySyn());
                            uzol.setPravySyn(pUzol.getPravySyn());
                            uzol.setRodic(pUzolRodic);
                            pUzolRodic.setLavySyn(uzol);
                            pUzol.getPravySyn().setRodic(uzol);
                            pUzol.getLavySyn().setRodic(uzol);
                            pUzol.setLavySyn(uzol.getLavySyn());
                            pUzol.setPravySyn(uzol.getPravySyn());
                            pUzol.setRodic(uzolRodic);
                            uzolRodic.setLavySyn(pUzol);
                        }
                    }
                    
                }
            }
        }

        return pUzol;
    }
    
    private BSTUzol najmesiVpravomPodstrome(BSTUzol pUzol){
        while(pUzol.getLavySyn()!=null){
            pUzol=pUzol.getLavySyn();
        }
        return pUzol;
    }
}
