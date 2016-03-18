/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.BST;

import backend.Uzol;
import java.awt.Color;
import java.awt.Graphics;


/**
 *
 * @author ondrej
 */
public class BSTUzol extends Uzol{

    BSTUzol lavySyn,pravySyn,rodic;

    
    public BSTUzol(int pHod,BSTUzol pRodic) {
        super(pHod);
        rodic=pRodic;
        lavySyn=null;
        pravySyn=null;
    }
    
    public BSTUzol(int pX,int pY,int pHod,BSTUzol pLavySyn, BSTUzol pPravySyn,BSTUzol pRodic){
        super(pX,pY,pHod);
        lavySyn=pLavySyn;
        pravySyn=pPravySyn;
        rodic=pRodic;
    }

    @Override
    public void nakresli(Graphics g) {
        System.out.println("Vkreslujem uzol s hodnotou "+getHod());
        g.setColor(farba.farbaUzadia);
        g.fillOval(x, y, velkost, velkost);
        if(oznaceny){
            g.setColor(Color.red);
            g.drawOval(x-2, y-2, velkost+4, velkost+4);
        }
        g.setColor(farba.farbaFontu);
        g.drawString(getStringHod(), x+(velkost/2)-5, y+(velkost/2)+5);
    }

    public BSTUzol getLavySyn() {
        return lavySyn;
    }
    
    @Override
    public void posun() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setLavySyn(BSTUzol pLavySyn) {
        this.lavySyn = pLavySyn;
    }

    public BSTUzol getPravySyn() {
        return pravySyn;
    }

    public void setPravySyn(BSTUzol pPravySyn) {
        this.pravySyn = pPravySyn;
    }

    public BSTUzol getRodic() {
        return rodic;
    }

    public void setRodic(BSTUzol pRodic) {
        this.rodic = pRodic;
    }
    
    

    
    
}
