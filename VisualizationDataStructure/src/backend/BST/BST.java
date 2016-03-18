/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.BST;

import backend.Struktury;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author ondrej
 */
public class BST extends Struktury{
    
    private BSTUzol koren;
    private Graphics g;
    public BST(){
        super();
        name="BST";
        koren=null;
    }
    
    public void insert(int pHod){
        insert(koren, pHod);
    }
    private void insert(BSTUzol pUzol,int pHod){
        
        if(koren==null){
            koren=new BSTUzol(600, 10, pHod, null, null, null);
        }else{
            if(pHod==pUzol.getHod()){
                return;
            }
            
            if(pHod<pUzol.getHod()){
                if(pUzol.getLavySyn()==null){
                    BSTUzol novy=new BSTUzol(pUzol.getX()-30, pUzol.getY()+30, pHod, null  ,null, pUzol);
                    
                    pUzol.setLavySyn(novy);
                    return;
                }else{
                    insert(pUzol.getLavySyn(), pHod);
                    return;
                }
            }
            
            if(pHod>pUzol.getHod()){
                if(pUzol.getPravySyn()==null){
                    BSTUzol novy=new BSTUzol(pUzol.getX()+30, pUzol.getY()+30, pHod, null  ,null, pUzol);
                    
                    pUzol.setPravySyn(novy);
                    return;
                }else{
                    insert(pUzol.getPravySyn(), pHod);
                    return;
                }
            }
        }
        
    }
    public BSTUzol getKoren() {
        return koren;
    }

    public void setKoren(BSTUzol koren) {
        this.koren = koren;
    }

    @Override
    public void NakresliStrukturu(Graphics pG) {
        g=pG;
        System.out.println("Kreslim strukturu");
        postorder(koren);
        
    }

    
    private void postorder(BSTUzol pUzol){
        if(pUzol.lavySyn!=null){
            System.out.println("Idem na lavy");
            postorder(pUzol.getLavySyn());
        }
        if(pUzol.getPravySyn()!=null){
            System.out.println("idem na pravy");
            postorder(pUzol.getPravySyn());
        }
        System.out.println("Volam metodu na kreslenie");
        pUzol.nakresli(g);
        
    }
    @Override
    public void vloz(int pHod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void najdi(int pHod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void zmaz(int pHod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
