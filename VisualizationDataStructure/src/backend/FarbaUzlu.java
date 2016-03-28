/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.awt.Color;

/**
 * Trieda je určena na deklarovanie statickych premien ktoré budu predstavovať
 * farby uzlov v jednotlivych stavov. K farbom bude pristup bez vytvorenia objektu.
 * Konštrutor vyvorí Farbu takže každej farbe urči jej farbu uzadia a farbu fontu.
 * Pri vykresľovaní uzlu bude mať pristup k tym dvom premenným každej farby a
 * bude vykresľovať uzol v tych farbach
 * 
 * 
 * @author ondrej
 */
public class FarbaUzlu {
    public static FarbaUzlu normalny=new FarbaUzlu(Color.BLACK, Color.WHITE);
    public static FarbaUzlu najdeny=new FarbaUzlu(Color.GREEN, Color.BLACK);
    public static FarbaUzlu zmazani=new FarbaUzlu(Color.RED, Color.BLACK);
    public static FarbaUzlu vlozenie=new FarbaUzlu(Color.lightGray, Color.WHITE);
    public static FarbaUzlu existujuci=new FarbaUzlu(Color.ORANGE, Color.RED);
    
    
    
    public Color farbaUzadia,farbaFontu;
    
    /**
     *
     * @param pFarbaUzadia - akej farbe ma byť vypnený uzol
     * @param pFarbaFontu - akej farbe ma byť vypisaná hodnota uzlu
     */
    public FarbaUzlu(Color pFarbaUzadia,Color pFarbaFontu){
        farbaUzadia=pFarbaUzadia;
        farbaFontu=pFarbaFontu;
    }
}
