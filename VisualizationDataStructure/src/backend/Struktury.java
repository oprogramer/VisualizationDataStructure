/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;



import java.awt.Graphics;
import java.util.ArrayList;


/**
 * Metoda Struktury obsahuje premennú ktorá určuje meno údajovej štruktúry.
 * Obsahuje abstraktné metody vloz,najdi,zmen,clean,vypis a nakresli štruktúru.
 * @author ondrej
 */
public abstract class Struktury {
    
    protected String name;
    
    
    /**
     * Konštruktor na vytvorenie štruktúry. Ako parameter dostava reťazec, ktorý
     * predstavuje nazov štruktúry.
     * 
     * @param pMeno - názov štruktúry
     */
    public Struktury(String pMeno){
        
        name=pMeno;
        
        
    }
    
    /**
     * Vracia reťazec ktorý predstavuje nazov štruktúry
     * 
     * @return - nazov štruktúry
     */
    public String getName(){
        return name;
    }

    
    /**
     * Abstraktná metoda ktorá bude implementovať kod na vloženie uzlu do štruktúry.
     * 
     * @param pHod - cele čislo ktoré bude uložene v uzli
     */
    public abstract void vloz(int pHod);

    /**
     * Abstraktná metoda, ktorá bude implementovať kod na najdenie uzlu v štruktúre.
     * 
     * @param pHod - cele čislo ktoré obsahuje hľadaný uzol
     */
    public abstract void najdi(int pHod);

    /**
     * Abstraktná metod, ktorá bude implementovať kod na zmazanie uzlu z štruktúry
     * 
     * @param pHod - cele čislo ktoré obsahuje uzol ktorý sa ma zmazať.
     */
    public abstract void zmaz(int pHod);

    /**
     * Abstraktná metoda ktora bude implementovať kod na vykreslenie 
     * všetkých uzlov ktoré obsahuje.
     * 
     * @param g - grafický kontex na ktorý sa ma kresliť.
     */
    public abstract void NakresliStrukturu(Graphics g);

    /**
     * Abstraktná metoda ktorá bude implementovať kod na zmazanie celej štruktúry
     */
    public abstract void clean();

    /**
     * Abstraktná metoda, ktorá bude implementovať kod na vypis hodnoty všetkych
     * uylov uložených v údajovej štruktúry. Vypis bude uskutočneni na základe 
     * poradia ktore bude zadané parametrom.
     * 
     * @param pPoradie - reťazec ktorý predstavuje poradie vypisu
     */
    public abstract ArrayList<Uzol> vypis(String pPoradie);
    
    
}

