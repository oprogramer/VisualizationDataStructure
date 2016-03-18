/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.BST;

import com.sun.javafx.font.t2k.T2KFactory;

/**
 *
 * @author ondrej
 */
public class BSTVloz implements Runnable{
    private Thread vlakno;
    private BST T;
    private BSTUzol u;
    private int hod;
    
    public BSTVloz(BST pT,BSTUzol pU){
        T=pT;
        u=pU;
        hod=u.getHod();
    }
    
    public void start(){
        if(vlakno==null){
            vlakno=new Thread(this);
            vlakno.start();
        }
    }
    public void stop(){
        if(vlakno!=null){
            vlakno.stop();
            vlakno=null;
        }
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
