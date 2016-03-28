/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;


import java.awt.Color;
import java.awt.Graphics;

/**
 * Trieda ktorá predstavuje uzly hoci ktoréj údajovéj štruktúry.
 * Obsahuje udaje o súradnicach kde sa ma uzol vykresliť, akej veľkosti, akú 
 * hodnotu, tox a toy sú premenné ktoré sa používajú pri zmene súradnic. Taktiež 
 * obsahuje aj údaj o tom či e uzol označený alebo nie a farbu uzlu.
 * 
 * @author ondrej
 */
public abstract class Uzol {
    protected int x,y,tox,toy,hod,velkost;
    protected boolean oznaceny;
    protected FarbaUzlu farba;
    
    /**
     * Konštruktor ktorý vytvára novy uzol na suradnicach 0,0 a nejkou hodnotou
     * @param pHod - hodnota ktorú obsahuje nový uzol
     */
    public Uzol(int pHod){
        this(0, 0, pHod);
    }
    
    /**
     * Konštruktor ktorý vytvára nový uzol s suradnicami ktoré posielame ako 
     * parametre a nejkou hodnotou
     * @param pX - x-ová súradnica
     * @param pY - y-ová súradnica
     * @param pHod - hodnota ktorú obsahuje uzol
     */
    public Uzol(int pX,int pY,int pHod){
        x=pX;
        y=pY;
        hod=pHod;
        oznaceny=false;
        velkost=30;
        farba=FarbaUzlu.vlozenie;
    }
    
    /**
     *
     * @param g - graficky kontext t.j. prestavuje obrazok na ktorý sa ma kresliť
     */
    public abstract void nakresli(Graphics g);

    /**
     * Metoda posunie uzol na hodnoty toX a toY
     */
    public abstract void posun();
    
    /**
     * Metoda ktora nastavi nove hodnoty toX a toY 
     * a zavola metodu posun ktora posunie uzol na nove suradnice
     * 
     * @param pX
     * @param pY
     */
    public void setSuradnice(int pX, int pY) {
        tox = pX;
        toy = pY;
        posun();
    }
    
    /**
     *Metoda ktorá nastavi hodnotu premennej oznaceny na true, čo znamena že 
     * uzol je označeny t.j pri vykresľovani sa bude vykresľovať s 
     * červenou kružnicou.
     */
    public void oznac(){
        oznaceny=true;
        
    }
    
    /**
     * Metoda ktorá nastavi hodnotu premennej oznaceny na false, čo znamena že 
     * uzol nie je označeny t.j pri vykresľovani sa nebude vykresľovať s 
     * červenou kružnicou.
     */
    public void odznac(){
        oznaceny=false;
    }
    
    /**
     * Metoda vracia hodnotu typu FarbaUzlu ktorá je v premennej farba.
     * 
     * @return - farba ktorou sa ma vykresliť uzol
     */
    public Color getFarbuUzadia(){
        return farba.farbaUzadia;
    }
    
    /**
     * Metoda ktorá nastavi farbu uzlu.
     * 
     * @param pC - hodnota typu FarbaUzlu 
     */
    public void setFarbu(FarbaUzlu pC){
        farba=pC;
    }

    /**
     * Metoda ktorá vracia premennú hod prekonverotvanú na reťazec
     * 
     * @return - reťazec ktorý zobrazuje hodnotu premennej hod
     */
    public String getStringHod(){
        return String.valueOf(hod);
    }

    /**
     * Metoda ktorá nastaví hodnotu premennej velksoť. Premenná velksoť
     * určuje aky veľký uzol bude t.j koľko na koľko pikselov sa ma uzol
     * nakresliť.
     * 
     * @param pVelksot - cele čislo, ktoré určuje na koľko pikselov sa ma uzol 
     *                  vykresliť
     */
    public void setVelksot(int pVelksot){
        velkost=pVelksot;
    }

    /**
     * Metoda ktorá vracia hodnotu premennej velksoť. Premenná velksoť
     * určuje aky veľký uzol bude t.j koľko na koľko pikselov sa ma uzol
     * nakresliť.
     * 
     * @return - hodnotu premennej velksot
     */
    public int getVelkost(){
        return velkost;
    }

    /**
     * Metoda ktorá vracia hodnotu premennej hod.  Premenná hod určuje hodnotu 
     * ktorú obsahuje uzol.
     * 
     * @return - hodnotu premennej hod
     */
    public int getHod(){
        return hod;
    }

    /**
     * Metoda nasatavuje hodnotu premennej x. Premenná x určuje y-ovú súradnicu
     * na ktorej sa ma uzol vykresliť.
     * 
     * @param pHod - cele cislo ktore bude nastavene na premennú hod
     */
    public void setHod(int pHod) {
        this.hod = pHod;
    }

    /**
     * Metoda ktorá vracia hodnotu premennej x.  Premenná x určuje x-ovú súradnicu
     * na ktorej sa ma uzol vykresliť
     * 
     * @return - hodnotu premennej x
     */
    public int getX() {
        return x;
    }

    /**
     * Metoda nasatavuje hodnotu premennej x. Premenná x určuje y-ovú súradnicu
     * na ktorej sa ma uzol vykresliť
     * 
     * @param pX - hodnota ktorú nastavi na premennú x
     */
    public void setX(int pX) {
        this.x = pX;
    }

    /**
     * Metoda ktorá vracia hodnotu premennej y.  Premenná y určuje y-ovú súradnicu
     * na ktorej sa ma uzol vykresliť
     * 
     * @return - hodnotu premennej y
     */
    public int getY() {
        return y;
    }

    /**
     * Metoda nasatavuje hodnotu premennej y. Premenná y určuje y-ovú súradnicu
     * na ktorej sa ma uzol vykresliť
     * 
     * @param pY - hodnota ktorú nastavi na premennú y
     */
    public void setY(int pY) {
        this.y = pY;
    }

    /**
     * Metoda ktorá vracia premennú tox. Premenná určuje x-ovú suradnicu na 
     * ktorú sa ma posunuť uzol ked bude zavolana metoda na psuvanie.
     * 
     * @return - hodnotu premennej tox
     */
    public int getTox() {
        return tox;
    }

    /**
     * Metoda ktorá nastavuje premennú tox. Premenná určuje x-ovú suradnicu na 
     * ktorú sa ma posunuť uzol ked bude zavolana metoda na psuvanie.
     * 
     * @param pTox - x-ová suradnica na ktorú sa ma posunut uzol
     */
    public void setTox(int pTox) {
        this.tox = pTox;
    }

    /**
     * Metoda ktorá vracia premennú toy. Premenná určuje y-ovú suradnicu na 
     * ktorú sa ma posunuť uzol ked bude zavolana metoda na psuvanie.
     * 
     * @return - hodnotu premennej toy
     */
    public int getToy() {
        return toy;
    }

    /**
     * Metoda ktorá nastavuje premennú toy. Premenná určuje y-ovú suradnicu na 
     * ktorú sa ma posunuť uzol ked bude zavolana metoda na psuvanie.
     * 
     * @param pToy - y-ová suradnica na ktorú sa ma posunut uzol
     */
    public void setToy(int pToy) {
        this.toy = pToy;
    }

    
}
