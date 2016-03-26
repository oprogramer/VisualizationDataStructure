/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.BST;

import backend.FarbaUzlu;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ondrej
 */
public class BSTNajdi implements Runnable{
    Thread vlakno;
    BST DS;
    int hod;
    
    
    public BSTNajdi(BST pDS,int pHod){
        this.DS=pDS;
        this.hod=pHod;
        
        start();
        
    }
    
    private void start(){
        if(vlakno==null){
            vlakno=new Thread(this);
            vlakno.start();
        }
    }

    @Override
    public void run() {
        Najdi(DS.getKoren(),hod);
    }
    
    private void Najdi(BSTUzol pUzol,int pHod){
        pUzol.oznac();
        pause();
        
        if(pUzol.getHod()==pHod){
            
            pUzol.setFarbu(FarbaUzlu.najdeny);
            pause();
            pUzol.setFarbu(FarbaUzlu.normalny);
            pUzol.odznac();
            
        }
        if(pHod<pUzol.getHod()){
            
            if(pUzol.getLavySyn()==null){
                
                pUzol.odznac();
            }else{
                pUzol.odznac();
                Najdi(pUzol.getLavySyn(), pHod);
            }
        }
        if(pHod>pUzol.getHod()){
            if(pUzol.getPravySyn()==null){
                pUzol.odznac();
            }else{
                pUzol.odznac();
                Najdi(pUzol.getPravySyn(), pHod);
            }
        }
    }
    
     private void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BSTVloz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
