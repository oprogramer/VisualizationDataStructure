/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import com.sun.prism.Graphics;

/**
 *
 * @author ondrej
 */
public abstract class Uzol {
    int x,y,hod;
    boolean oznaceny;
    
    
    public Uzol(int pHod){
        this(0, 0, pHod);
    }
    
    public Uzol(int pX,int pY,int pHod){
        x=pX;
        y=pY;
        hod=pHod;
        oznaceny=false;
    }
    
    public abstract void nakresli(Graphics g);
    public abstract void posun();
    
    public void oznac(){
        oznaceny=true;
        
    }
    
    public void odznac(){
        oznaceny=false;
    }
}
