package backend.BST;

import backend.FarbaUzlu;
import java.util.logging.Level;
import java.util.logging.Logger;

import ui.Scena;

/**
 * Trieda je algoritmus na zmazanie prvku z údajovej štruktúry binarny 
 * vyhľadávaci strom. Algoritmus sa uskutoční v samostatnom vlakne, ked sa uzol
 * zmaže zavolá metody na prepočet vyšky stromu a suradnic všetkych uzlov.
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

    /**
     * Konštruktor ktorý vytvorý nový objekt triedy. Nastavy hodnoty pre
     * štruktúru z ktoréj sa ma zmazať uzol a hodnotu uzlu ktorý sa má zmazať.
     * Nakoniec naštartuje vlakno ktoré ma naimplementovaný algoritmus na 
     * zmazanie uzlu.
     * 
     * @param pDS -  údajová štruktúra z ktorej sa má zmazať uzol
     * @param pHod - cele čislo, hodnota uzlu ktorý sa má zmazať
     */
    public BSTDelete(BST pDS, int pHod) {
        this.DS = pDS;
        this.hod = pHod;
        start();
        try {
            vlakno.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(BSTDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metoda naštartue vlakno. Ked neexistuje vlakno tak vytvori nove a 
     * naštartuje ho.
     */
    private void start() {
        if (vlakno == null) {
            vlakno = new Thread(this);
            vlakno.start();
        }
    }
    
    @Override
    public void run() {
        DS.panel.kom.zmazKomentare();
        pridajKomentar("Mazanie uzla "+hod);
        pridajKomentar("Začneme v koreni stromu.");
        DS.setKoren(Zmaz(DS.getKoren(), hod));

        DS.prepocitajVyskuStromu();
        DS.prepocitanieSuradnic();
        pridajKomentar("Koniec mazania");
    }
    /**
     * Metoda ktorá implementuje algoritmus na zmazanie uzlu z údajovej štruktúry 
     * binarny vyhľadávaci strom. Metoda vyžaduje uzol ktorý skúma a hodnotu ktorú
     * ma zmazať. Ked bude strom prazdny vrati prazdnu hodnotu. Ked bude hodnota
     * menšia ako hodnota skúmaného uzla tak rekurzivne zavola samu seba pre 
     * levho syna toho uzla, a vratenu hodnotu nastaví ako lavého syna.
     * Ak nie je menšia, skontorluje či nie je väčšia, ak ano tak rekurzivne 
     * zavola samu seba pre pravého syna a vratenu hodnotu nastaví ako prevého 
     * syna. Ak nie je ani väčšie tak skontroluje či sa rovnajú hodnota pre 
     * mazanie a hodnota uzlu, ak ano skontroluje ktorú z troch možnosti má 
     * využiť. Prvá je ked neobsahuje laveho syna potom ako ma praveho syna tak
     * jemu nastavi rodica, uzol ktorý je rodicom skumaného uzla. Nakoniec vrati
     * uzol ktorý je pravím synom aj ked bude null nevadi lebo uzol neobsahuje
     * potomkov tak jeho rodic nebude mat viacej laveho syna. Ked uzol obsahuje 
     * laveho syna tak skontroluje či obsahuje pravého ak nie tak nastavime 
     * levemu rodiča, uzol ktorý je rodičom skumaného uzla, a vratime laveho syna.
     * Tretia možnosť je ked skúmaný uzol obshuje oboch synov, potom najdeme 
     * uzol v pravom podstrome s najmenšou hodnotou a vymeníme tie dva uzly. 
     * Prvo oba odpojime od svojich rodicou a potomkou ale tie hodnoty si uložíme
     * potom ked si vymenia polohy tak im nastavime hodnoty uzlov toho druheho.
     * Potom metoda vola samu seba pre pravého syna a vratenu hodnotu nastavi ako 
     * pravý syn. Tymto dotiahneme že zase najdeme uzol ktorý sme chcely zmazať 
     * a opakujeme až kym nebude jeden z prvých dvoch prípadov.
     * 
     *  
     * @param pUzol - uzol ktorý skúmame
     * @param pHod - cele čislo, hodnota uzlu ktorého chceme zmazať
     * @return uzol ktorý nastavime ako pravý alebo lavý syn skúmaného uzla
     */
    private BSTUzol Zmaz(BSTUzol pUzol, int pHod) {

        if (DS.getKoren() == null) {
            pridajKomentar("Strom je prázdny, nie je čo mazať.");
            return DS.getKoren();
        }
        
        
        pUzol.oznac();
        pause();
        
        if (pHod < pUzol.getHod()) {
            if (pUzol.getLavySyn()==null) {
                pridajKomentar("Uzol s hodnotou "+ hod+" v strome neexistuje.");
            }else{
                pUzol.odznac();
                pridajKomentar("Kedže je "+hod+" menšia ako "+pUzol.getHod()+", tak budeme mazať uzol v ľavom podstrome.");
                pUzol.setLavySyn(Zmaz(pUzol.getLavySyn(), pHod));
            }
            

        } else if (pHod > pUzol.getHod()) {
            if(pUzol.getPravySyn()==null){
                pridajKomentar("Uzol s hodnotou "+ hod+" v strome neexistuje.");
            }else{
                pUzol.odznac();
                pridajKomentar("Kedže je "+hod+" večšia ako "+pUzol.getHod()+", tak budeme mazať uzol v pravom podstrome. ");
                pUzol.setPravySyn(Zmaz(pUzol.getPravySyn(), pHod));
            }
            
        } else if (pHod == pUzol.getHod()) {
            
            pUzol.setFarbu(FarbaUzlu.zmazani);
            pause();
            if (pUzol.getLavySyn() == null) {
               
                if (pUzol.getPravySyn() != null) {
                    pridajKomentar("Nájdený. \nPripad II: Uzol "+pUzol.getHod()+" nemá ľavého"
                        + "potomka. Uzol "+pUzol.getHod()+" zmažeme tak, že jeho pravého potomka "
                            + "pripojíme na jeho rodiča");
                    pUzol.getPravySyn().setRodic(pUzol.getRodic());
                    pUzol.getPravySyn().oznac();
                    pUzol.setRodic(null);
                    pause();
                    pUzol.getPravySyn().odznac();
                }else{
                     pridajKomentar("Nájdený. \nPripad I: Uzol "+pUzol.getHod()+" nemá ani jedného "
                        + "potomka. Može sa jednoducho zmazať.");
                }
                pUzol.setRodic(null);
                pUzol.setSuradnice(pUzol.getX(), 1000);
                
                return pUzol.getPravySyn();
            } else if (pUzol.getPravySyn() == null) {
                pridajKomentar("Nájdený. \nPripad II: Uzol "+pUzol.getHod()+" nemá pravého"
                        + "potomka. Uzol "+pUzol.getHod()+" zmažeme tak, že jeho ľavého potomka "
                            + "pripojíme na jeho rodiča");
                pUzol.getLavySyn().setRodic(pUzol.getRodic());
                pUzol.getLavySyn().oznac();
                pUzol.setRodic(null);
                pause();
                pUzol.getLavySyn().odznac();
                
                
                pUzol.setSuradnice(pUzol.getX(), 1000);
                return pUzol.getLavySyn();
            } else {
                
                pridajKomentar("Nájdený. \nPripad III: Uzol "+pUzol.getHod()+" na oboch"
                        + "potomkov. Uzol "+pUzol.getHod()+" zmažeme tak, že nájdeme najmenší "
                            + "prvok v jeho pravom podstrome, s ktorým ho vymeníme. Ten prvok"
                        + "bude mať najviac jedného syna a bude sa dať ľahko zmazať.");
                
                
                //Zistime uzol v pravom podstorme s najmenšou hodnotou
                uzol = najmesiVpravomPodstrome(pUzol.getPravySyn());
                pridajKomentar("Najmenší prvok v pravom podstrome je "+uzol.getHod()+"."
                        + "Odpojíme prvok "+uzol.getHod() +" a "+pUzol.getHod()+" a vymeníme ich.");
                //Uložime hodnoty uzlu ktorý chceme zmazat
                //aby sme ich potom vedeli nastaviť uzlu z pravého podstomu
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

                //Ked je uzol ktorý chceme zmazať koren stromu, tak nema rodiča
                //potom jeho rodičovi nemožme nastavovať praveho a laveho syna.
                //Ked nie je koren tak potom potrebujeme nastaviť hodnoty
                //praveho alebo laveho syna, rodičovi uzla ktorý mažeme a to
                //ak je uzol pravy syn tak nastavime prevého syna na uzol z 
                //pravého podstromu a ak je lavy tak nastavime lavého syna
                if (pUzolRodic != null) {
                    if (pUzolRodic.getPravySyn() == pUzol) {
                        pUzolRodic.setPravySyn(uzol);
                    }
                    if (pUzolRodic.getLavySyn() == pUzol) {
                        pUzolRodic.setLavySyn(uzol);
                    }
                } else {
                    DS.setKoren(uzol);
                }
                //Nastavime hodnoty pre uzol ktorý mažeme, hodnoty ktoré boli
                //pre uzol z pravého podstromu. A ak mal potomkov tak im nastavime
                //hodnotu rodiča na uzol ktorý chceme zmazať
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
                
                //Nastavime praveho syna na uzol ktorý vrati metoda zmaz pre 
                //preveho syna uzla z praveho podstromu
//                pridajKomentar("Pokračujeme tak, že zase nájdeme uzol ktorý chceme zmazať. Teraz by "
//                        + "mal obsahovať najviac jedného syna tak ho vieme zmazať.");
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
    /**
     * Metoda ktorá vrati uzol s najmenšou hodnotou v pravom podstrome 
     * 
     * @param pUzol - uzol v ktorom ma začať hladať
     * @return - uzol sa najmenšou hodnotou
     */
    private BSTUzol najmesiVpravomPodstrome(BSTUzol pUzol) {
        pridajKomentar("Začneme v koreni pravého postromu.");
        while (pUzol.getLavySyn() != null) {
            
            pUzol.oznac();
            pause();
            pUzol.odznac();
            pUzol = pUzol.getLavySyn();
            pridajKomentar("Získame ľavého potomka.");
        }
        pUzol.setFarbu(FarbaUzlu.najdeny);
        
        return pUzol;
    }
    
    private void pause(){
        Scena.pause(1500);
    }
    
    private void pridajKomentar(String pKomentar){
        DS.panel.kom.pridajKomentar(pKomentar);
    }
}
