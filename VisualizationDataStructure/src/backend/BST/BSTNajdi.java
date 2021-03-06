/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.BST;

import backend.FarbaUzlu;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.Scena;


/**
 * Trieda je algoritmus na hľadanie prvku z údajovej štruktúry binarny 
 * vyhľadávaci strom. Algoritmus sa uskutoční v samostatnom vlakne. 
 * Algoritmus prejde udajovou štrukturou a bude vyznačovať uzly v ktorych sa 
 * sa nachadza a ked najde hladanu hodnotu tak zmení farbu uzlu na najdeny.
 * 
 * @author ondrej
 */
public class BSTNajdi implements Runnable{
    private Thread vlakno;
    private BST DS;
    private int hod;
    
    /**
     * Konštruktor ktorý vytvorý nový objekt triedy. Nastavy hodnoty pre
     * štruktúru vktoréj sa má hľadať uzol a hodnotu ktorú hľadá.
     * Nakoniec naštartuje vlakno ktoré ma naimplementovaný algoritmus na 
     * zmazanie uzlu.
     * 
     * @param pDS -  údajová štruktúra z ktorej sa má zmazať uzol
     * @param pHod - cele čislo, hodnota uzlu ktorý sa má zmazať
     */
    public BSTNajdi(BST pDS,int pHod){
        this.DS=pDS;
        this.hod=pHod;
        
        start();
        try {
            vlakno.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(BSTNajdi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Metoda naštartue vlakno. Ked neexistuje vlakno tak vytvori nove a 
     * naštartuje ho.
     */
    private void start(){
        if(vlakno==null){
            vlakno=new Thread(this);
            vlakno.start();
        }
    }

    @Override
    public void run() {
        DS.panel.kom.zmazKomentare();
        pridajKomentar("Hľadanie uzla "+hod);
        pridajKomentar("Začneme hľadať v koreni stromu.");
        Najdi(DS.getKoren(),hod);
        pridajKomentar("Koniec hľadania.");
    }
    
    private void Najdi(BSTUzol pUzol,int pHod){
        pUzol.oznac();
        pridajKomentar("Kontrolujeme uzol "+pUzol.getHod());
        pause();
        
        if(pUzol.getHod()==pHod){
            pridajKomentar("Nájdený.");
            pUzol.setFarbu(FarbaUzlu.najdeny);
            pause();
            pUzol.setFarbu(FarbaUzlu.normalny);
            pUzol.odznac();
            
        }
        if(pHod<pUzol.getHod()){
            
            if(pUzol.getLavySyn()==null){
                pridajKomentar("Uzol s hodnotou "+ hod+" v strome neexistuje.");
                pUzol.odznac();
            }else{
                pridajKomentar("Hodnota "+hod+" je menšia ako "+pUzol.getHod()+", budeme hľadať ďalej v ľavom podstrome.");
                pUzol.odznac();
                Najdi(pUzol.getLavySyn(), pHod);
            }
        }
        if(pHod>pUzol.getHod()){
            if(pUzol.getPravySyn()==null){
                pridajKomentar("Uzol s hodnotou "+ hod+" v strome neexistuje.");
                pUzol.odznac();
            }else{
                pridajKomentar("Hodnota "+hod+" je väčšia ako "+pUzol.getHod()+",  budeme hľadať ďalej v pravom podstrome.");
                pUzol.odznac();
                Najdi(pUzol.getPravySyn(), pHod);
            }
        }
    }
    
     private void pause() {
        Scena.pause(1500);
    }
     private void pridajKomentar(String pKomentar){
         DS.panel.kom.pridajKomentar(pKomentar);
     }
}
