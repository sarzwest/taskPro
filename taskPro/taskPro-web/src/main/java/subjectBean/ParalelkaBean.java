/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subjectBean;

import BL.IBussiness.ApplicationLocal;
import BL.IBussiness.IPredmetB;
import BL.IBussiness.IUzivatelB;
import BL.Predmet.PredmetB;
import BL.Uzivatel.UzivatelB;
import DL.entity.Kantor;
import DL.entity.Predmet;
import DL.entity.Student;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Lurtz
 * Beana sloužící k vytvoření paralelky.
 */
@ManagedBean(name = "paralelkaBean")
@RequestScoped
public class ParalelkaBean implements Serializable {

    String kod;
    List<Student> studenti;
    List<Kantor> kantori;
    List<Predmet> predmety;
    IUzivatelB uzivatelB;
    IPredmetB predmetB;
    
    
    List<Student> studentiList;
    List<Kantor> kantoriList;
    Predmet vybranyPredmet;
    Student[] vybraniStudenti;
    Kantor [] vybraniKantori;
    
    
    /** určeno pro vypis erroru a pro nastaveni css pro error zpravu
     */
    String err;
    String cssErr;
    
    
//    @EJB
    @Inject
    ApplicationLocal app;

    @PostConstruct
    public void init() {
        uzivatelB = UzivatelB.getInstance(app);
        predmetB = PredmetB.getInstance(app);
        studenti = uzivatelB.getStudentyAll();

        predmety = predmetB.showSubjects();
        kantori = uzivatelB.getAllKantor();
        studentiList = new ArrayList<Student>();
        kantoriList = new  ArrayList<Kantor>();

    }

    public ParalelkaBean() {
    }

    
    /**
     * 
     * Funkce si vybere data z .jsf stánky (vybraní studenti, vybraní kantoři, kód paralelky a předmet).
     * Vybraná data uloží do databáze 
     */
    public void pridejParalelku() {
        err = "Paralelka přidána do databaze.";
        cssErr = "errAddOK";
        
        for(int i = 0 ; i < vybraniStudenti.length ; i++){
            studentiList.add(vybraniStudenti[i]);
        }
        
        for(int i = 0 ; i < vybraniKantori.length ; i++){
            kantoriList.add(vybraniKantori[i]);
        }
        try{ 
            predmetB.addParalelka(kod, vybranyPredmet, kantoriList, studentiList);  
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
        err = "ERROR ... nepodarilo se pridat paralelku.";
        cssErr = "errAdd";
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
    
    
    public Kantor[] getVybraniKantori() {
        return vybraniKantori;
    }

    public void setVybraniKantori(Kantor[] vybraniKantori) {
        this.vybraniKantori = vybraniKantori;
    }

    public Student[] getVybraniStudenti() {
        return vybraniStudenti;
    }

    public void setVybraniStudenti(Student[] vybraniStudenti) {
        this.vybraniStudenti = vybraniStudenti;
    }


    
    
    public Predmet getVybranyPredmet() {
        return vybranyPredmet;
    }

    public void setVybranyPredmet(Predmet vybranyPredmet) {
        this.vybranyPredmet = vybranyPredmet;
    }

    
    
    public IPredmetB getPredmetB() {
        return predmetB;
    }

    public void setPredmetB(IPredmetB predmetB) {
        this.predmetB = predmetB;
    }

    public void setKantori(List<Kantor> kantori) {
        this.kantori = kantori;
    }

    public void setPredmety(List<Predmet> predmety) {
        this.predmety = predmety;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }

    public List<Kantor> getKantori() {
        return kantori;
    }

    public List<Predmet> getPredmety() {
        return predmety;
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }
}
