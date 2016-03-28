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
 * Trieda je algoritmus na vloženie nového prvku do údajovej štruktúry binarny 
 * vyhľadávaci strom. Algoritmus sa uskutoční v samostatnom vlakne, ked sa uzol
 * vloží zavolá metody na prepočet vyšky stromu a suradnic všetkych uzlov.
 * Algoritmus pamätá údajovú štruktúru fo ktorej vkladá a uzol ktorý vkladá, 
 * tieto dve veci dostava cez konštruktor.
 * @author ondrej
 */
public class BSTVloz implements Runnable {

    private Thread vlakno;
    private final BST T;
    private final BSTUzol u;
    private boolean uspesne;

    /**
     * Konľtruktor vytvára objekt typu BSTVloz, nastaví hodonty premien pre 
     * údajovú štruktúru a uzol ktorý sa vkladá. Potom nsatartuje vklano na 
     * vykonanie algoritmu.
     * 
     * @param pT - údajová štruktúra do ktorej sa vkladá
     * @param pU - uzol ktorý sa vklada
     */
    public BSTVloz(BST pT, BSTUzol pU) {
        T = pT;
        u = pU;

        start();
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

    /**
     * Prepisana metoda z rozhrania Runneble volá metodu na vkladanie uzla.
     * Ked je údajová štruktúra prazna tak iba nastavi suradnice nového uzla
     * na hore stred scene na ktorej sa vykresľuje. Ked nie je prazna ta potom
     * vola metodu na vlozenie, ktorá rekurzívne vloží prvok na správne miesto.
     * Ked je prvok vlození uspešne tak zavolá metody na prepočet vyšký stromu 
     * a suradnic prvku.
     */
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

    /**
     * Metoda ktorá implementuje algoritmus na vloženie nového prvku do údajovéj
     * štruktúry. Prvo skontroluje či sa hodnota uzla ktorý kontrolujeme 
     * rovná s hodnotou nového uzla, ak ano tak novému zmeni farbu na existujúci, 
     * potom ho zmaze z scene a vrati že vloženie bolo neuspešné. Ked sa hodnoty
     * nerovnajú posunie nový uzol nad kontrolovaný uzol a skontroluje či je 
     * hodnota nového uzla menšia ako hodnota kontrolovaného uzla tak 
     * skontrolujeme či kontrolovany uzol ma lavého syna ak nie tak nastavime 
     * novemu uzlu nové suradnice, a kontrolovanému uzlu nastavime laveho syna
     * nový uzol a vratime že je vloženie uspešné a ak kontrolovaný uzol 
     * obsahuje laveho syna tak rekurzivne zavola metoda samu seba pre uzol 
     * laveho syna. Ak je hodnota nového uzla väčšsia ako hodnota kontrolovaného
     * uzla tak skontrolujeme či obsahuje pravého syna ked nie tak mu nastavime
     * nový uzol ako pravý syn s novími suradnicami a vratime že je vloženie 
     * uspešné, ak pravého syna obsahuje tak rekurzívne zavolá samu seba pre 
     * prevého syna
     *  
     * 
     * @param pU - uzol ktorý sa ma kontrolovať t.j porovnávať s novím uzlo
     * @return - pravdivostna hodnota či je vloženie uspešné
     */
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
        T.pause(1000);
    }
}
