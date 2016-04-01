/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author ondrej
 */
public class Komentare extends JTextPane {

    ArrayList<String> text;
    StyledDocument doc;
    SimpleAttributeSet normal;
    SimpleAttributeSet bold;
    SimpleAttributeSet titulok;
    //String s="Ja som toto zvladol neviem ci funguje az tak dobre ako si myslim ale uvidim teraz";

    public Komentare() {
        text = new ArrayList<String>();
        setEditable(false);

        doc = this.getStyledDocument();
        normal = new SimpleAttributeSet();
        bold = new SimpleAttributeSet();
        titulok=new SimpleAttributeSet();
        
        StyleConstants.setFontSize(normal, 14);
        StyleConstants.setFontSize(bold, 14);
        StyleConstants.setBold(bold, true);

        StyleConstants.setAlignment(titulok, (int) CENTER_ALIGNMENT);
        StyleConstants.setFontSize(titulok, 16);
    }

    private void dodaj() {
        try {
            doc.remove(0, doc.getLength());
            for (int i = 0; i < text.size(); i++) {

                if (i == 0) {
                    doc.insertString(doc.getLength(), text.get(i) + "\n \n", titulok);
                } else {
                    if (i == text.size() - 1) {

                        doc.insertString(doc.getLength(), i+". "+text.get(i) + "\n", bold);
                        

                    } else {

                        doc.insertString(doc.getLength(), i+". "+text.get(i) + "\n", normal);
                        doc.insertString(doc.getLength(), "\n", null);
                    }
                }

            }
        } catch (BadLocationException ex) {
            Logger.getLogger(Komentare.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void pridajKomentar(String pKom) {
        text.add(pKom);
        dodaj();
    }

    public void zmazKomentare() {
        text.clear();
    }
}
