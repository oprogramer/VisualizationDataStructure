/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.BST;

import backend.Uzol;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ondrej
 */
public class BSTUzol extends Uzol {

    private Font f = new Font("TimesRoman", Font.BOLD, 14);
    private BSTUzol lavySyn, pravySyn, rodic;
    private int tox, toy;
    private int vyska; //je vyska stromu, vypocita sa tak ze kolko maju uzlov pravy a lavy podstrom
    //a vecsia hodnota z tych dvoch je vyska stromu, na zaciatku je 1
    private int vyskaPraveho, vyskaLaveho;

    //Tento konstruktor nastavi uzol suradnice na 0,0
    public BSTUzol(int pHod, BSTUzol pRodic) {
        super(pHod);
        rodic = pRodic;
        lavySyn = null;
        pravySyn = null;
        vyska = vyskaLaveho = vyskaPraveho = 0;
    }

    //tento konstruktor nastavi konkretne hodnoty suradnic noveho uzla
    public BSTUzol(int pX, int pY, int pHod, BSTUzol pLavySyn, BSTUzol pPravySyn, BSTUzol pRodic) {
        super(pX, pY, pHod);
        lavySyn = pLavySyn;
        pravySyn = pPravySyn;
        rodic = pRodic;
        prepocitajVysku();
    }

    //Prepocitame vysky pre
    public void prepocitajVysku() {

        if (getLavySyn() != null) {
            vyskaLaveho = getLavySyn().getVyska();
        }

        if (getPravySyn() != null) {
            vyskaPraveho = getPravySyn().vyska;
        }

        vyska = (Math.max(vyskaLaveho, vyskaPraveho) + 1);

    }

    //Implementacia abstraktnej metode na vykreslenie uzlu 
    //Volana je z triede BST ktora posila parameter grafickeho kontextu
    //S tym grafickym kontextom sa vykreslia dane uzly na obrazok
    @Override
    public void nakresli(Graphics g) {

        if (!isRoot()) {
            g.setColor(Color.red);
            g.drawLine(x, y, getRodic().getX(), getRodic().getY());
        }
        g.setColor(farba.farbaUzadia);
        g.fillOval(x - velkost / 2, y - velkost / 2, velkost, velkost);
        if (oznaceny) {
            g.setColor(Color.red);
            g.drawOval(x - (velkost / 2) - 2, y - (velkost / 2) - 2, velkost + 4, velkost + 4);
        }

        g.setColor(farba.farbaFontu);
        g.setFont(f);
        String s = getStringHod();
        FontMetrics fm = g.getFontMetrics();
        g.drawString(s, x - fm.stringWidth(s) / 2, (y - fm.getHeight() / 2) + fm.getAscent());
    }
    //**********koniec vykreslenia*****

    //Metoda na vypocet kordinatov uzla na zaklade velkosti podstromov
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
                getLavySyn().setTox(pStred - velkost * (getLavySyn().getPravySyn().getVyskaLaveho() + getLavySyn().getPravySyn().getVyskaPraveho() + 2));

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
                getPravySyn().setTox(pStred + velkost * (getPravySyn().getLavySyn().getVyskaLaveho() + getPravySyn().getLavySyn().getVyskaPraveho() + 2));
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

    @Override
    public void posun() {

        int kroky = 10;

        while (getX() != getTox() || getY() != getToy()) {

            if (getX() != getTox()) {
                x += (getTox() - getX()) / kroky;
            }
            if (getY() != getToy()) {
                y += (getToy() - getY()) / kroky;
            }
            kroky--;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(BSTUzol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    //Metoda ktora nastavi nove hodnoty toX a toY 
    //a zavola metodu posun ktora posunie uzol na nove suradnice
    public void setSuradnice(int pX, int pY) {
        tox = pX;
        toy = pY;
        posun();
    }

    public int getTox() {
        return tox;
    }

    public void setTox(int pTox) {
        this.tox = pTox;
    }

    public int getToy() {
        return toy;
    }

    public void setToy(int pToy) {
        this.toy = pToy;
    }

    public int getVyska() {
        return vyska;
    }

    public void setVyska(int pVyska) {
        this.vyska = pVyska;
    }

    public int getVyskaPraveho() {
        return vyskaPraveho;
    }

    public void setVyskaPraveho(int vyskaPraveho) {
        this.vyskaPraveho = vyskaPraveho;
    }

    public int getVyskaLaveho() {
        return vyskaLaveho;
    }

    public void setVyskaLaveho(int vyskaLaveho) {
        this.vyskaLaveho = vyskaLaveho;
    }

    public boolean isRoot() {
        return getRodic() == null;
    }

    public BSTUzol getLavySyn() {
        return lavySyn;
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
