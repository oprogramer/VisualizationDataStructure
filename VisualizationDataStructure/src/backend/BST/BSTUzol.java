/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.BST;

import backend.Uzol;
import com.sun.prism.Graphics;

/**
 *
 * @author ondrej
 */
public class BSTUzol extends Uzol{

    BSTUzol lavySyn,PravySyn;

    
    public BSTUzol(int pHod) {
        super(pHod);
    }
    
    public BSTUzol(int pX,int pY,int pHod){
        super(pX,pY,pHod);
    }

    @Override
    public void nakresli(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void posun() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
