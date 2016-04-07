/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.BST;


import backend.Uzol;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import ui.Scena;

/**
 * Trieda predstavuje uzol pre údajovú štruktúru binárny vyhľadávaci strom. 
 * Dedi všetkyvlastnosti triedy Uzol ktorú rozširuje o ukazovatele na uzly ktoré
 * sa nachadzajú na pravo a na ľavo o neho a taktiež ukazovateľ na uzol ktorý je 
 * uroven vyššie. Taktiež obsahuje informáciu na akej vyške sa nachadza dany uzol
 * t.j najdlkšiu cestu do konca večšieho podstromu. Obsahuje aj informaciu o tom
 * kolko potomkov obsahuje dany uzol, tuto informaciu využíva rodič pri vypočte 
 * svjich súradnic
 * 
 * @author ondrej
 */
public class BSTUzol extends Uzol {

    private Font f = new Font("TimesRoman", Font.BOLD, 14);
    private BSTUzol lavySyn, pravySyn, rodic;
    
    private int vyska; //je vyska stromu, vypocita sa tak ze kolko maju uzlov pravy a lavy podstrom
    //a vecsia hodnota z tych dvoch je vyska stromu, na zaciatku je 1
    private int vyskaPraveho, vyskaLaveho,pocetDeti;

    /**
     * Tento konstruktor nastavi uzol suradnice na 0,0, Zavola konštruktor svojej
     * nadtriedy ktora nastaví suradnice na 0,0 a hodnotu uzlu. Potom nastavi
     * ukazovatele na rodica a synov uzla. Vysku stromu a podstromvov ako aj 
     * pocet potomkov nastavi na 0.
     * 
     * @param pHod - cele čislo, hodnota ktorú obsahuje uzol
     * 
     */
    public BSTUzol(int pHod) {
        super(pHod);
        rodic = null;
        lavySyn = null;
        pravySyn = null;
        vyska = vyskaLaveho = vyskaPraveho = pocetDeti=0;
    }

    /**
     * Tento konstruktor nastavi konkretne hodnoty suradnic noveho uzla.
     * Najprv zavola konštruktor nadtriedy ktory nastavi hodnoty suradnic z 
     * parametrov a potom nastavi ukazovatele na potomkov a rodiča tatiež z 
     * parametrov a nakoniec prepočita vyšku.
     * 
     * 
     * @param pX
     * @param pY
     * @param pHod
     * @param pLavySyn
     * @param pPravySyn
     * @param pRodic 
     */
    public BSTUzol(int pX, int pY, int pHod, BSTUzol pLavySyn, BSTUzol pPravySyn, BSTUzol pRodic) {
        super(pX, pY, pHod);
        lavySyn = pLavySyn;
        pravySyn = pPravySyn;
        rodic = pRodic;
        prepocitajVysku();
    }

    //Prepocitame vysky pre
    /**
     * Metoda skontroluje či uzol obsahuje potomkov. Ked obsahuje laveho syna
     * tak nastaví premennú vyskaLaveho na hodnotu ktorú ma jeho lavy syn ako 
     * vyšku a počet potomkov zviši o jeden viac ako čo je počet deti
     * lavého syna. Rovnako spravi aj s pravým synom. Ked nebude obsahovať jedného 
     * alebo oboch synov tak prišlušná hodnota zostane 0. Nakoniec vypočita 
     * vyšku uzlu takze na väčšiu višku z praveho a laveho podstromu pripočita
     * jedničku.
     */
    public void prepocitajVysku() {
        pocetDeti=0;
        if (getLavySyn() != null) {
            vyskaLaveho = getLavySyn().getVyska();
            pocetDeti+=getLavySyn().getPocetDeti()+1;
        }else{
            vyskaLaveho=0;
        }

        if (getPravySyn() != null) {
            vyskaPraveho = getPravySyn().vyska;
            pocetDeti+=getPravySyn().getPocetDeti()+1;
            
        }else{
            vyskaPraveho=0;
        }
        
        vyska = (Math.max(vyskaLaveho, vyskaPraveho) + 1);

    }

    /**
     * Implementacia abstraktnej metode na vykreslenie uzlu .
     * Volana je z triede BST ktora posila parameter grafickeho kontextu.
     * S tym grafickym kontextom sa vykreslia dane uzly na obrazok.
     * Ked obsahuje rodiča tak vykresli aj čiaru k rodičovi, a ked premenná
     * označený obsahuje hodnotu true tak vykresli kruh cervenej farby okolo 
     * uzlu. Uzol vykresluje vo farbe kera je nastavená na premennej farba.
     * 
     * @param g - graficky kontext na ktory kresli
     */
    @Override
    public void nakresli(Graphics g) {

        if (!isRoot()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(1));
            g.setColor(Color.red);
            g.drawLine(x, y, getRodic().getX(), getRodic().getY());
        }
        g.setColor(farba.farbaUzadia);
        g.fillOval(x - velkost / 2, y - velkost / 2, velkost, velkost);
        if (oznaceny) {
//            g.setColor(Color.red);
//            g.drawOval(x - (velkost / 2), y - (velkost / 2) , velkost , velkost );
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.red);
            g2.drawOval(x - (velkost / 2), y - (velkost / 2) , velkost , velkost );
        }

        g.setColor(farba.farbaFontu);
        g.setFont(f);
        String s = getStringHod();
        FontMetrics fm = g.getFontMetrics();
        g.drawString(s, x - fm.stringWidth(s) / 2, (y - fm.getHeight() / 2) + fm.getAscent());
        
        
    }
    //**********koniec vykreslenia*****

    //
    /**
     * Metoda na vypocet kordinatov uzla na zaklade velkosti podstromov.
     * Ked je uzol koren stromu tak nastavy premennu tox na stred scene, čo je 
     * hodnota poslata parametrom a zavolametodu na posunutie uzlu.
     * Potom nastavi hodnoty premennej tox pre svojho laveho syna takže ich vypočita na 
     * základe parametru, vekosti uzla a počtu deti praveho podstromu laveho 
     * uzla a premennej toy iba hodnotu svojej suradnice y zvečšenej o velksoť
     * uzla. Ked vypočita premenne tox a toy ak nie su rovnaké ako staré 
     * suradnice tak zavola metodu posun pre laveho syna. Nasledne zavola metodu
     * prepocitaj uzlu ktorý je na lavo od neho s parametrom svojej x-ove 
     * suradnice. Podobne prepocita aj pre uzol na pravo od neho.
     * 
     * @param pStred - x-ova suranica uzlu  
     */
    public void prepocitaj(int pStred) {

        if (isRoot()) {

            if (getX() != pStred) {
                setTox(pStred);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        posun();
                    }
                }).start();

               
            }
        }
        if (getLavySyn() != null) {
            //Nastavime pre lavy premenu tox a zavolame metodu posun pre laveho
            if (getLavySyn().getPravySyn() != null) {
                getLavySyn().setTox(pStred - velkost * ((getLavySyn().getPravySyn().getPocetDeti()+1)+1));

            } else {
                getLavySyn().setTox(pStred - velkost * (getLavySyn().vyskaPraveho + 1));
            }
            getLavySyn().setToy(getToy()+30);
            if (getLavySyn().getX() != getLavySyn().getTox() || getLavySyn().getY() != getLavySyn().getToy()) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getLavySyn().posun();
                    }
                }).start();
            }

            //Potom zavolame metodu prepocitaj pre laveho syna
            getLavySyn().prepocitaj(getLavySyn().getTox());
        }

        if (getPravySyn() != null) {
            //Nastavime premenu pre pravy tox a  zavolame metodu posun pre praveho 
            //getPravySyn().setTox(pStred + velkost * (getPravySyn().vyskaLaveho + 1));
            if (getPravySyn().getLavySyn() != null) {
                getPravySyn().setTox(pStred + velkost * ((getPravySyn().getLavySyn().getPocetDeti()+1)+1));
            } else {
                getPravySyn().setTox(pStred + velkost * (getPravySyn().vyskaLaveho + 1));
            }

            getPravySyn().setToy(getToy()+30);
            if (getPravySyn().getX() != getPravySyn().getTox() || getPravySyn().getY() != getPravySyn().getToy()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getPravySyn().posun();
                    }
                }).start();
            }

            //potom zavolame metodu prepocitaj pre praveho syna
            getPravySyn().prepocitaj(getPravySyn().getTox());
        }

    }

    /**
     * Metoda pripočitava na suradnice hodnotu rozdielu medzi tox a x a toy a y
     * a tak opakuje predurčeny počet krokov.
     */
    @Override
    public void posun() {

        int kroky = 10;

        while (getX() != getTox() || getY() != getToy() || kroky>0) {
            
            if (getX() != getTox()) {
                x += (getTox() - getX()) / kroky;
            }
            if (getY() != getToy()) {
                y += (getToy() - getY()) / kroky;
            }
            kroky--;
            Scena.pause(100);
        }

    }

    /**
     *
     * @return
     */
    public int getVyska() {
        return vyska;
    }

    /**
     *
     * @param pVyska
     */
    public void setVyska(int pVyska) {
        this.vyska = pVyska;
    }

    /**
     *
     * @return
     */
    public int getVyskaPraveho() {
        return vyskaPraveho;
    }

    /**
     *
     * @param vyskaPraveho
     */
    public void setVyskaPraveho(int vyskaPraveho) {
        this.vyskaPraveho = vyskaPraveho;
    }

    /**
     *
     * @return
     */
    public int getVyskaLaveho() {
        return vyskaLaveho;
    }

    /**
     *
     * @param vyskaLaveho - 
     */
    public void setVyskaLaveho(int vyskaLaveho) {
        this.vyskaLaveho = vyskaLaveho;
    }

    /**
     *
     * @return - vracia pravdivostnu hodnotu či je uzol koren stromu alebo nie
     */
    public boolean isRoot() {
        return getRodic() == null;
    }

    /**
     *
     * @return - uzol na ktorý ukazuje ukazovateľ lavySyn
     */
    public BSTUzol getLavySyn() {
        return lavySyn;
    }

    /**
     *
     * @param pLavySyn - uzol na ktorý bude ukazovať ukazovateľ lavySyn
     */
    public void setLavySyn(BSTUzol pLavySyn) {
        this.lavySyn = pLavySyn;
    }

    /**
     *
     * @return - uzol na ktorý ukazuje ukazovateľ pravySyn
     */
    public BSTUzol getPravySyn() {
        return pravySyn;
    }

    /**
     *
     * @param pPravySyn - uzol na ktorý bude ukazovať ukazovateľ pravySyn
     */
    public void setPravySyn(BSTUzol pPravySyn) {
        this.pravySyn = pPravySyn;
    }

    /**
     *
     * @return - vrati uzol na ktorý ukazuje premenná rodic
     */
    public BSTUzol getRodic() {
        return rodic;
    }

    /**
     *
     * @param pRodic - nastavi novu hodnotu na ukazovateľ rodic
     */
    public void setRodic(BSTUzol pRodic) {
        this.rodic = pRodic;
    }

    /**
     *
     * @return - vracia hodnotu premennej pocetDeti
     */
    public int getPocetDeti(){
        return pocetDeti;
    }

}
