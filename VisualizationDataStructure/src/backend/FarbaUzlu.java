/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.awt.Color;

/**
 *
 * @author ondrej
 */
public class FarbaUzlu {
    public static FarbaUzlu normalny=new FarbaUzlu(Color.BLACK, Color.WHITE);
    public static FarbaUzlu najdeny=new FarbaUzlu(Color.GREEN, Color.BLACK);
    public static FarbaUzlu zmazani=new FarbaUzlu(Color.RED, Color.BLACK);
    public static FarbaUzlu vlozenie=new FarbaUzlu(Color.darkGray, Color.WHITE);
    
    
    
    
    public Color farbaUzadia,farbaFontu;
    
    public FarbaUzlu(Color pFarbaUzadia,Color pFarbaFontu){
        farbaUzadia=pFarbaUzadia;
        farbaFontu=pFarbaFontu;
    }
}
