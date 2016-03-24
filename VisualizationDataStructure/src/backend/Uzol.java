/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;


import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author ondrej
 */
public abstract class Uzol {
    protected int x,y,hod,velkost;
    protected boolean oznaceny;
    protected FarbaUzlu farba;
    
    
    public Uzol(int pHod){
        this(0, 0, pHod);
    }
    
    public Uzol(int pX,int pY,int pHod){
        x=pX;
        y=pY;
        hod=pHod;
        oznaceny=false;
        velkost=30;
        farba=FarbaUzlu.vlozenie;
    }
    
    public abstract void nakresli(Graphics g);
    public abstract void posun();
    
    public void oznac(){
        oznaceny=true;
        
    }
    
    public void odznac(){
        oznaceny=false;
    }
    
    public Color getFarbuUzadia(){
        return farba.farbaUzadia;
    }
    
    public void setFarbu(FarbaUzlu pC){
        farba=pC;
    }
    public String getStringHod(){
        return String.valueOf(hod);
    }
    public void setVelksot(int pVelksot){
        velkost=pVelksot;
    }
    public int getVelkost(){
        return velkost;
    }
    public int getHod(){
        return hod;
    }

    public int getX() {
        return x;
    }

    public void setX(int pX) {
        this.x = pX;
    }

    public int getY() {
        return y;
    }

    public void setY(int pY) {
        this.y = pY;
    }
    
    
}
