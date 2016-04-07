package backend.BST;

import backend.FarbaUzlu;
import backend.Struktury;
import backend.Uzol;
import java.awt.Graphics;
import java.util.ArrayList;
import ui.Scena;

/**
 * Trieda ktorá predstavuje údajovú štruktúru binarného vyhladávacieho stromu.
 * Dedi vsetky vlastnosti triedy Struktury a implementuje abstarktnie metody na
 * vkladanie,mazanie, hladanie uzlov, zmazanie celej struktury (clean) a vypis
 * uzlov. Taktiez implementuje aj metodu na vykreslenie údajovej štruktúry.
 * Obsahuje aj metody na prepocitanie vysky stromu a suradnic každého uzla.
 * Vytvara sa z triede BSTPanel ktora predstavuje panel na ktorom sa vsetko
 * odohráva a zobrazuje.
 *
 * @author ondrej
 */
public class BST extends Struktury {

    private BSTUzol koren, novy;
    public BSTPanel panel;
    private Graphics g;

    /**
     * Konštruktor pre vytvorenie údajovej štruktúry binarny vyhľadávaci strom.
     * Najprv zavola konštruktor triede Struktury kde nastavi meno na BST. Potom
     * nastavime panel z ktorého chceme ovladat aplikaciu a nastavime hodnotu
     * korena na null, čo znamená že je údajová štruktúra prázna a hodnotu novy
     * taktiež na null lebo nič nevnašame.
     *
     * @param pPanel - Panel na ktorom sa všetko odohráva a zobrazuje
     */
    public BST(BSTPanel pPanel) {
        super("BST");
        panel = pPanel;

        koren = novy = null;

    }

    /**
     * Metoda ktorá vracia to čo je uložene v premenej koren.
     *
     * @return - vracia hodnotu korena
     */
    public BSTUzol getKoren() {
        return koren;
    }

    /**
     * Metoda ktorá nastavuje na ktorý uzol má ukazovať premenna koren.
     *
     * @param koren - hodnota t.j. uzol na ktorý má ukazovať premenná koren
     */
    public void setKoren(BSTUzol koren) {
        this.koren = koren;
    }

    /**
     * Implementovana abstaktna metoda ktorá nakresli štruktúru. Volana je z
     * triede scena ktorá jej posiela parameter grafického kontextu obrazka,
     * ktorý uložíme do premennej g typu Gaphics Nasledne v poradi postorder
     * zavoláme pre kazdy uzol metodu na vykreslenie
     *
     * @param pG - grafický kontext ktorý využívame na vykreslenie štruktúry
     */
    @Override
    public void NakresliStrukturu(Graphics pG) {
        g = pG;
        if (novy != null) {
            novy.nakresli(g);
        }
        if (koren != null) {
            postorderV(koren);
        }
    }

    /**
     * Metoda ktorá je volaná z metode Nakresli štruktúru. Jej úlohov je v
     * poradi postOrder pre každý uzol zavolať jeho metodu 'nakresli' na
     * vykreslenie uzla.
     *
     * @param pUzol - uzol z ktorého začneme vykresľovať, zvičajne koren stromu
     */
    private void postorderV(BSTUzol pUzol) {

        if (pUzol.getLavySyn() != null) {
            postorderV(pUzol.getLavySyn());
        }
        if (pUzol.getPravySyn() != null) {
            postorderV(pUzol.getPravySyn());
        }
        pUzol.nakresli(g);

    }

    //*********koniec vykreslenia********
    /**
     * Pri zmene štruktury ako je vloženie uzla alebo zmazanie tak sa prepocita
     * aj jeho vyska tak že sa zavolá sukromna metoda v tejto triede
     */
    public void prepocitajVyskuStromu() {
        if (koren != null) {
            prepocitajVyskuStromu(koren);
        }
    }

    /**
     * V poradi postorder sa prejde cez strom a zavola sa pre kazdy uzol metoda
     * na prepocitanie vysky uzla a jeho podstromov
     */
    private void prepocitajVyskuStromu(BSTUzol pUzol) {
        if (pUzol.getLavySyn() != null) {
            prepocitajVyskuStromu(pUzol.getLavySyn());
        }
        if (pUzol.getPravySyn() != null) {
            prepocitajVyskuStromu(pUzol.getPravySyn());
        }
        pUzol.prepocitajVysku();
    }

    // ***** koniec prepoctu vysky ******
    /**
     * Zavolame pre koren metodu na prepocitanie suradnic Medota toho uzlu
     * prejde celim stromom a nastavi suradnice kazdeho uzlu koren bude mat
     * nastavenu suradnicu x na stred scene a y na 50px a potom pre kazdy uzol
     * nastavi take suradnice ktore prepocita na zaklade jeho podstromov
     */
    public void prepocitanieSuradnic() {
        if (koren != null) {
            koren.prepocitaj(panel.scena.getWidth() / 2);
        }
    }

    /**
     * Metoda vytvorý inštanciu triedy BSTUzol na ktorú bude ukazovať premenná
     * 'novy' a bude sa vykresľovať osobitne od stromu. Potom vytvorý inštanciu
     * triedy BSTVloz ktorá predstavuje algoritmus na vloženie uzla do
     * štruktúry. Ako parametre posiela údajový štruktúru a uzol ktorý sa vkladá
     * t.j. na ktorý ukazuje premenná 'novy'
     *
     * @param pHod - hodnota ktorú bude obsahovať nový uzol
     */
    @Override
    public void vloz(int pHod) {
        novy = new BSTUzol(panel.scena.getWidth() / 2, 10, pHod, null, null, null);
        new BSTVloz(this, novy);

    }

    /**
     * Metoda vytvorý inštanciu triedy BSTNajdi, ktorá predstavuje algoritmus na
     * hľadanie v strome. Ako parametre posiela údajovú štruktúru v ktorej ma
     * hľadat a hodnotu ktorú ma hľadat.
     *
     * @param pHod - hladaná hodnota
     */
    @Override
    public void najdi(int pHod) {
        new BSTNajdi(this, pHod);
    }

    /**
     * Metoda vytvorý inštanciu triedy BSTDelete, ktorá predstavuje algoritmus
     * na zmazanie uzla v strome. Ako parametre posiela údajovú štruktúru z
     * ktorej sa ma zmazať uzol, a hodnota uzlu ktorý sa ma zmazať
     *
     * @param pHod - hodnota uzlu ktorý sa ma zmazať
     */
    @Override
    public void zmaz(int pHod) {
        new BSTDelete(this, pHod);
    }

    /**
     * Metoda nastavy hodnotu null pre premenné koren a novy
     */
    @Override
    public void clean() {
        koren = null;
        novy = null;
    }

    /**
     * Metoda ktorá zavolá metodu na vypis hodnoty všetkých uzlov v poradí
     * určenom parametrom.
     *
     * @param pPoradie - textový retazec ktorý urcuje poradie vypisu, môže byť
     * preorder, inorder, postorder
     */
    @Override
    public ArrayList<Uzol> vypis(String pPoradie) {
        ArrayList<Uzol> zoznam=new ArrayList<Uzol>();
        switch (pPoradie) {
            
            case "inorder": {
                panel.kom.zmazKomentare();
                panel.kom.pridajKomentar("Vypis prvkov v poradi inOrder");
                pinOrder(zoznam, koren);
                break;
            }
            case "preorder": {
                panel.kom.zmazKomentare();
                panel.kom.pridajKomentar("Vypis prvkov v poradi preOrder");
                ppreOrder(zoznam, koren, true);
                break;
            }
            case "postorder": {
                panel.kom.zmazKomentare();
                panel.kom.pridajKomentar("Vypis prvkov v poradi postOrder");
                ppostOrder(zoznam, koren);
                break;
            }
            default:{}
        }
        return zoznam;
    }

    /**
     * Rekurzivna metoda ktorá vypiše hodnoty všetkých uzlov v poradi inorder.
     * Najprv pojde rekurzívne do celkom najlavejšiho podstromu tj uzla v
     * strome, potom vypiše uzol a potom pojde do praveho podstromu, ked to bude
     * iba uzol, tak ho vypiše, ked nie tak pojde do je vypisať jeho podstromy.
     * Nasledne sa vracame ku korenu podstromu a ho vypise a zopakujeme pre
     * pravy podstrom
     *
     * @param root - uzol z ktorého začne vypisovať
     */
    public void inorderRec(BSTUzol root) {
        if (root != null) {
            inorderRec(root.getLavySyn());
            System.err.print(root.getHod() + " ");
            inorderRec(root.getPravySyn());
        }
    }

    /**
     * Rekurzívna metoda ktorá vypiše hodnoty všetkých uzlov v poradí preorder.
     * Najprv vypise koren stromu alebo podstromu a potom skuša lavý podstrom,
     * ked už lavy neexistuje tak skuša pravý.
     *
     * @param root - uzol z ktorého začne vypisovať
     */
    public void preorder(BSTUzol root) {
        if (root != null) {
            System.err.print(root.getHod() + " ");
            inorderRec(root.getLavySyn());
            inorderRec(root.getPravySyn());
        }
    }

    /**
     * Rekurzívna metoda ktorá vypiše hodnoty všetkých uzlov v poradí postorder.
     * Najprv skuša lavý podstrom a tak až pokim existuje lavy uzol, ked
     * neexistuje lavy uzol tak skuša pravy podstrom až pokim existuje pravy
     * uzol, ked už nebude ani pravy tak sa vracia späť a vypise koren
     * podstromu.
     *
     * @param root - uzol z ktorého začne vypisovať
     */
    public void postorder(BSTUzol root) {
        if (root != null) {

            inorderRec(root.getLavySyn());
            inorderRec(root.getPravySyn());
            System.err.print(root.getHod() + " ");
        }
    }

    /**
     * Metoda nastavi hodnotu null pre premennu novy aby viac nevykresľovalo
     * osobitný uzol.
     */
    public void zmazNovy() {
        novy = null;
    }

    //Metody preorder, inorder a postorder na vypis prvkov
    public ArrayList preOrder(boolean pvypis) {
        ArrayList<Uzol> preorder = new ArrayList<Uzol>();
        
        if(koren!=null)
        ppreOrder(preorder, koren, pvypis);
        return preorder;
    }

    private void ppreOrder(ArrayList<Uzol> pPreOrder, BSTUzol pUzol, boolean pvypis) {
        pPreOrder.add(pUzol);
        if (pvypis) {
            pUzol.setFarbu(FarbaUzlu.najdeny);

           // panel.kom.pridajKomentar("" + pUzol.getStringHod());
            Scena.pause(1000);

        }

        if (pUzol.getLavySyn() != null) {
            ppreOrder(pPreOrder, pUzol.getLavySyn(), pvypis);
        }
        if (pUzol.getPravySyn() != null) {
            ppreOrder(pPreOrder, pUzol.getPravySyn(), pvypis);
        }
        pUzol.setFarbu(FarbaUzlu.normalny);
    }

    private void pinOrder(ArrayList<Uzol> pInOrder, BSTUzol pUzol) {

        if (pUzol.getLavySyn() != null) {
            pinOrder(pInOrder, pUzol.getLavySyn());
        }
        pInOrder.add(pUzol);
        pUzol.setFarbu(FarbaUzlu.najdeny);

        //panel.kom.pridajKomentar("" + pUzol.getStringHod());
        Scena.pause(1000);

        if (pUzol.getPravySyn() != null) {
            pinOrder(pInOrder, pUzol.getPravySyn());
        }
        pUzol.setFarbu(FarbaUzlu.normalny);
    }

    private void ppostOrder(ArrayList<Uzol> ppostOrder, BSTUzol pUzol) {

        if (pUzol.getLavySyn() != null) {
            ppostOrder(ppostOrder, pUzol.getLavySyn());
        }

        if (pUzol.getPravySyn() != null) {
            ppostOrder(ppostOrder, pUzol.getPravySyn());
        }
        ppostOrder.add(pUzol);
        pUzol.setFarbu(FarbaUzlu.najdeny);

        //panel.kom.pridajKomentar("" + pUzol.getStringHod());
        Scena.pause(1000);
        pUzol.setFarbu(FarbaUzlu.normalny);
    }
}
