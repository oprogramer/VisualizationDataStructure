/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.BST;

import backend.Struktury;
import java.awt.Graphics;


/**
 *
 * @author ondrej
 */
public class BST extends Struktury{
    
    private BSTUzol koren,novy;
    BSTPanel panel;
    private Graphics g;
    public BST(BSTPanel pPanel){
        super();
        panel=pPanel;
        name="BST";
        koren=novy=null;
        
    }
    
   
    public BSTUzol getKoren() {
        return koren;
    }

    public void setKoren(BSTUzol koren) {
        this.koren = koren;
    }

    //Implementovana abstaktna metoda Nakresli strukturu 
    //Volana  je z triede scena ktora jej posila parameter grafickeho kontextu obrazka
    //Na ten kontext ulozime do premenej typu Graphics
    //Nasledne v poradi postorder zavolame pre kazdy uzol metodu na vykreslenie
    //Ako parameter mu posleme graficky kontext
    
    @Override
    public void NakresliStrukturu(Graphics pG) {
        g=pG;
        
        if(novy!=null){
            novy.nakresli(g);
        }
        if(koren!=null)
        postorder(koren);
        
        
    }

    
    private void postorder(BSTUzol pUzol){
        if(pUzol.getLavySyn()!=null){
            
            postorder(pUzol.getLavySyn());
        }
        if(pUzol.getPravySyn()!=null){
           
            postorder(pUzol.getPravySyn());
        }
        
        pUzol.nakresli(g);
        
    }
    //*********koniec vykreslenia********
    
    //Pri zmene struktury ako je vlozenie uzla alebo zmazanie 
    //tak sa prepocita aj jeho vyska
    //V poradi postorder sa prejde cez strom
    //a zavola sa pre kazdy uzol metoda na prepocitanie vysky uzla a jeho podstromov
    
    public void prepocitajVyskuStromu(){
        if(koren!=null)
        prepocitajVyskuStromu(koren);
    }
    
    private void prepocitajVyskuStromu(BSTUzol pUzol){
        if(pUzol.getLavySyn()!=null){
            prepocitajVyskuStromu(pUzol.getLavySyn());
        }
        if(pUzol.getPravySyn()!=null){
            prepocitajVyskuStromu(pUzol.getPravySyn());
        }
        pUzol.prepocitajVysku();
    }
    
    //***** koniec prepoctu vysky *******
    //Zavolame pre koren metodu na prepocitanie suradnic
    //Medota toho uzlu prejde celim stromom a nastavi suradnice kazdeho uzlu
    //koren bude mat nastavenu suradnicu x na stred scene a y na 50px
    //a potom pre kazdy uzol nastavi take suradnice ktore prepocita na zaklade jeho podstromov
    public void prepocitanieSuradnic(){
        if(koren!=null)
        koren.prepocitaj(panel.scena.getWidth()/2);
    }
    @Override
    public void vloz(int pHod) {
        novy=new BSTUzol(panel.scena.getWidth()/2, 10, pHod, null, null, null);
        new BSTVloz(this,novy );
        
    }

    @Override
    public void najdi(int pHod) {
        new BSTNajdi(this, pHod);
    }

    @Override
    public void zmaz(int pHod) {
        new BSTDelete(this, pHod);
    }

    @Override
    public void clean() {
        koren=null;
        novy=null;
    }

    @Override
    public void vypis() {
        inorderRec(koren);
        System.out.println("");
    }
    

    // A utility function to do inorder traversal of BST
    public void inorderRec(BSTUzol root) {
        if (root != null) {
            inorderRec(root.getLavySyn());
            System.err.print(root.getHod()+" ");
            inorderRec(root.getPravySyn());
        }
    }
    
    
    public void zmazNovy(){
        novy=null;
    }
    
    
}
