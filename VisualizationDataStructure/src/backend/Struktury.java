/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author ondrej
 */
public abstract class Struktury {
    //protected BufferedImage scena;
    protected String name;
    
    public Struktury(){
        
        name="";
    }
    
    public String getName(){
        return name;
    }
    public abstract void vloz(int pHod);
    public abstract void najdi(int pHod);
    public abstract void zmaz(int pHod);
    public abstract void NakresliStrukturu(Graphics g);
    
}

