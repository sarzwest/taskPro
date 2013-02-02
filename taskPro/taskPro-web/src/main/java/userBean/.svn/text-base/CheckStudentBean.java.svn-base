/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userBean;

import BL.IBussiness.ApplicationLocal;
import BL.IBussiness.IUzivatelB;
import BL.Uzivatel.UzivatelB;
import DL.entity.Admin;
import DL.entity.Kantor;
import DL.entity.Student;
import DL.entity.Uzivatel;
import java.security.Principal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * Beana soužící k výpisu a mazání studentů.
 * @author Lurtz
 */

@ManagedBean(name = "checkStudentBean")
@RequestScoped

public class CheckStudentBean {


    
    Uzivatel chosen;
    List<Student> studenti;
    IUzivatelB uzivatelB;
    
    
    @EJB
    ApplicationLocal app;


    
    @PostConstruct
    public void init(){
        uzivatelB = UzivatelB.getInstance(app);
        Uzivatel loggedUser=uzivatelB.findUserByLogin(getPrincipal().getName());
        if(loggedUser instanceof Admin){
            studenti=uzivatelB.getStudentyAll();
        }
        else if(loggedUser instanceof Kantor){
        Kantor loggedKantor=(Kantor) loggedUser;
        studenti=uzivatelB.getStudenstByKantor(loggedKantor);  
        }
      
        //
        
    }
     /**
     * Metoda slouzi k vraceni aktualne prihlaseneho uzivatele (Principala), JSF
     * JAAS framework
     * @return aktualne prihlaseneho Principala
     */
      private Principal getPrincipal() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
    }
    
    public CheckStudentBean() {
       
    }
    /**
     * Metoda pozada business logiku o odstraneni aktualniho vybraneho studenta a aktalizuje
     * seznam studentu v DB 
     * @return checkstudentAdmin - navigacni pravidlo
     */
    public String deleteStudent(){
        uzivatelB.removeUser(chosen.getLogin());
        studenti=uzivatelB.getStudentyAll();
        return "checkstudentAdmin";
    }
    
    
    public String modifyUser(){
        uzivatelB.modifyUser(chosen);
        return "checkstudentAdmin";
    }
    
    
    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }
    
    public Uzivatel getChosen() {
        return chosen;
    }

    public void setChosen(Uzivatel chosen) {
        this.chosen = chosen;
    }
}
