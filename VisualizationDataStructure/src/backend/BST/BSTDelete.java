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
        DS.prepocitajVyskuStromu();
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
                            BSTUzol uPS=uzol.getPravySyn();
                            BSTUzol uLS=uzol.getLavySyn();
//                            //Odpojime tie dva uzly ktore ideme vymenit
                            uzol.setRodic(null);
                            pUzol.setRodic(null);
                            pUzol.getPravySyn().setRodic(null);
                            pUzol.getLavySyn().setRodic(null);
                            
                            if(uPS!=null){
                                uPS.setRodic(null);
                            }
                            //Nepotrebne nastavovat kedze hladame najlavesi v pravom strome 
                            //takze toto bude vydy null
                            if(uLS!=null){
                                uLS.setRodic(null);
                            }
//                            //Koniec odpojovanie 
//                            //Posuvame uzly kim si nevimenia polohy
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    uzol.setSuradnice(pUzolx, pUzoly);
                                    
                                }
                            }).start();
                            pUzol.setSuradnice(uzolx, uzoly);
                            //Koniec vymen poloh
                            
                            uzol.setRodic(pUzolRodic);
                            uzol.setPravySyn(pUzol.getPravySyn());
                            uzol.setLavySyn(pUzol.getLavySyn());
                            pUzol.getLavySyn().setRodic(uzol);
                            pUzol.getPravySyn().setRodic(uzol);
                            
                            
                            if(pUzolRodic.getPravySyn()==pUzol){
                                System.out.println("Pravy syn");
                                pUzolRodic.setPravySyn(uzol);
                                System.out.println(""+pUzolRodic.getPravySyn().getHod());
                            }
                            if(pUzolRodic.getLavySyn()==pUzol){
                                System.out.println("Lavy syn");
                                pUzolRodic.setLavySyn(uzol);
                                System.out.println(""+pUzolRodic.getLavySyn().getHod());
                            }
                            uzolRodic.setLavySyn(pUzol);
                            pUzol.setRodic(uzolRodic);
                            pUzol.setPravySyn(uPS);
                            pUzol.setLavySyn(uLS);
                            if(uPS!=null){
                                uPS.setRodic(pUzol);
                            }
                            //Nepotrebne nastavovat kedze hladame najlavesi v pravom strome 
                            //takze toto bude vydy null
                            if(uLS!=null){
                                uLS.setRodic(pUzol);
                            }
                            uzol.setPravySyn(Zmaz(uzol.getPravySyn(), pUzol.getHod()));
                            return uzol;
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
