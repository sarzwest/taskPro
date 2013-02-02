/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subjectBean;

import BL.IBussiness.ApplicationLocal;
import BL.Predmet.PredmetB;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


/**
 *
 * @author Lurtz
 */



@ManagedBean(name = "subjectBean")
@RequestScoped
/**
 * 
 *  Beana pro pridavani predmetu
 */
public class SubjectBean implements Serializable {

    String kod;
    String popis;

    
        /** určeno pro vypis erroru a pro nastaveni css pro error zpravu
     */
    String err;
    String cssErr;
    
    
    
    PredmetB predmetB;
    
    @EJB
    ApplicationLocal app;

    @PostConstruct
    public void init(){
        predmetB = (PredmetB) PredmetB.getInstance(app);
    }
    
    
    public SubjectBean() {
        
    
    }
    
    
    /**
     * 
     * funkce volajici funkci ... z bussiness vrstvy pro pridani do databaze
     */
    
    public void pridejPredmet(){
        err = "Předmět přidán do databaze.";
        cssErr = "errAddOK";
        
        
        try{ 
            predmetB.addPredmet(kod, popis);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            nastalaChyba();
        }

    
    }
    
    
    
    /**
     * nastavení zpravy a css stylu pri chybě 
     * 
     */
    private void nastalaChyba(){
        err = "ERROR ... nepodarilo se pridat předmět.";
        cssErr = "errAdd";
    }
    

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }
 
    
       public void setErr(String err) {
        this.err = err;
    }

    public String getErr() {
        return err;
    }

    public String getCssErr() {
        return cssErr;
    }

    public void setCssErr(String cssErr) {
        this.cssErr = cssErr;
    }
    
    
}
