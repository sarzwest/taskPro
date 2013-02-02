/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groupBean;

import BL.IBussiness.ApplicationLocal;
import BL.IBussiness.IPredmetB;
import BL.IBussiness.ISkupinaB;
import BL.Predmet.PredmetB;
import BL.Predmet.SkupinaB;
import DL.entity.Kantor;
import DL.entity.Paralelka;
import DL.entity.Predmet;
import DL.entity.Student;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * 
 * Beana, která si slouží pro přidávání studentu do skupin
 * @author Lurtz
 */
@ManagedBean(name = "groupBean")
@SessionScoped
public class GroupBean implements Serializable {
    /** určeno pro vypis erroru a pro nastaveni css pro error zpravu
     */
    String err;
    String cssErr;
    
    Predmet predmetChosen;
    Paralelka paralelkaChosen;
    Student[] vybraniStudenti;
    List<Student> studentiChosen;
    List<Predmet> predmety;
    List<Paralelka> paraleky;
    IPredmetB predmetB;
    List<Student> studenti;
    ISkupinaB skupinaB;
    String groupName;
    @EJB
    ApplicationLocal app;

    @PostConstruct
    public void init() {
        studentiChosen = new ArrayList<Student>();
        predmetB = PredmetB.getInstance(app);
        skupinaB = SkupinaB.getInstance(app);
        predmety = predmetB.showSubjects();
    }

    /**
     * 
     * Funkce, která vrací odkaz na stranku, na kterou budeme která se ma načíst. 
     * Nejtrve se načíta stránka s výberem předmětu, následně stránka pro výběr paralelky a poté stránka pro výběr studentů do skupiny.
     * @return 
     */
    public String vyberStranky() {
        String stranka = "addToGroupKantorContent";
        if (predmetChosen != null) {
            stranka = "addToGroupPKantorContent";
            paraleky = predmetChosen.getM_paralelka();
            if (paralelkaChosen != null) {
                stranka = "addToGroupSKantorContent";
                studenti = paralelkaChosen.getM_student();
            }
        }
        return stranka;
    }

    
    /**
     * 
     * Uloží data o nove skupine do databáze
     */   
    public void uloz() {
        err = "Nová skupina přidána do databaze.";
        cssErr = "errAddOK";
        
        for(int i = 0 ; i < vybraniStudenti.length ; i++){
            studentiChosen.add(vybraniStudenti[i]);
        }

        try{ 
            skupinaB.createGroup(paralelkaChosen, studentiChosen, groupName);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            nastalaChyba();
        }
    }
    
    
    public String vyberStranku(){
        predmetChosen = null;
        paralelkaChosen = null;
        studentiChosen = null;
        paraleky = null;
        studenti = null;
        err = "";
        cssErr = "";
        return "addtogroupKantor";
    }
    
     /**
     * nastavení zpravy a css stylu pri chybě 
     * 
     */
    private void nastalaChyba(){
        err = "ERROR ... nepodarilo se pridat skupinu.";
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
    

    public Student[] getVybraniStudenti() {
        return vybraniStudenti;
    }

    public void setVybraniStudenti(Student[] vybraniStudenti) {
        this.vybraniStudenti = vybraniStudenti;
    }

    
    
    
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }

    public List<Student> getStudentiChosen() {
        return studentiChosen;
    }

    public void setStudentiChosen(List<Student> studentiChosen) {
        this.studentiChosen = studentiChosen;
    }

    public List<Paralelka> getParaleky() {
        return paraleky;
    }

    public void setParaleky(List<Paralelka> paraleky) {
        this.paraleky = paraleky;
    }

    public Paralelka getParalelkaChosen() {
        return paralelkaChosen;
    }

    public void setParalelkaChosen(Paralelka paralelkaChosen) {
        this.paralelkaChosen = paralelkaChosen;
    }

    public List<Predmet> getPredmety() {
        return predmety;
    }

    public void setPredmety(List<Predmet> predmety) {
        this.predmety = predmety;
    }

    public Predmet getPredmetChosen() {
        return predmetChosen;
    }

    public void setPredmetChosen(Predmet predmetChosen) {
        this.predmetChosen = predmetChosen;
    }

    public GroupBean() {
    }
}
