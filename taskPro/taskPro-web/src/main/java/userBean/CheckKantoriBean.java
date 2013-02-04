/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userBean;
import BL.IBussiness.ApplicationLocal;
import BL.IBussiness.IUzivatelB;
import BL.Uzivatel.UzivatelB;
import DL.entity.Kantor;
import DL.entity.Student;
import DL.entity.Uzivatel;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * Beana soužící k výpisu a mazání kantoru.
 * @author Lurtz
 */
@ManagedBean(name = "checkKantoriBean")
@RequestScoped
public class CheckKantoriBean implements Serializable {
    
    Uzivatel chosen;
    List<Kantor> kantori;
    IUzivatelB uzivatelB;
    
    
    
    
    //    @EJB
    @Inject
    ApplicationLocal app;

    @PostConstruct
    public void init(){
        uzivatelB = UzivatelB.getInstance(app);
        kantori = uzivatelB.getAllKantor();
        
    }

    public CheckKantoriBean() {
    }
    
    
    /**
     * Metoda pozada business logiku o odstraneni aktualniho vybraneho kantora a aktalizuje
     * seznam kantoru v DB 
     * @return checkstudentAdmin - navigacni pravidlo
     */
    public String deleteKantor(){
        uzivatelB.removeUser(chosen.getLogin());
        kantori = uzivatelB.getAllKantor();
        return "checkkantoriAdmin";
    }
    
    public String modifyUser(){
        uzivatelB.modifyUser(chosen);
        return "checkkantoriAdmin";
    }

    
     /**
     * Metoda slouzi k vraceni aktualne prihlaseneho uzivatele (Principala), JSF
     * JAAS framework
     * @return aktualne prihlaseneho Principala
     */
    private Principal getPrincipal() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
    }

    public List<Kantor> getKantori() {
        return kantori;
    }

    public void setKantori(List<Kantor> kantori) {
        this.kantori = kantori;
    }
        
    
    public Uzivatel getChosen() {
        return chosen;
    }

    public void setChosen(Uzivatel chosen) {
        this.chosen = chosen;
    }
    
}
