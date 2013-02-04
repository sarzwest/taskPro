/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backingBean;

import BL.IBussiness.ApplicationLocal;
import BL.IBussiness.IUzivatelB;
import BL.Uzivatel.UzivatelB;
import DL.entity.Admin;
import DL.entity.Kantor;
import DL.entity.Student;
import DL.entity.Uzivatel;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;

/** 
 * Beana, ktera si udrzuje informace o aktualne prihlasem uzivateli. Zprostredkovava
 * logout z frameworku JAAS
 * @author Tom
 */
@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    public Uzivatel getLoggedPerson() {
        return loggedPerson;
    }

    public void setLoggedPerson(Uzivatel loggedPerson) {
        this.loggedPerson = loggedPerson;
    }
      Uzivatel loggedPerson;
      /**instance v bussiness vrstve starajici se o uzivatele*/
    IUzivatelB uzivatelB;
    /**
     * Metoda odhlasi uzivatele z aplikace a udela ivalidate sessions.
     */
        public String logout() {
        FacesContext facescontext = FacesContext.getCurrentInstance();
        HttpServletRequest ref = (HttpServletRequest) facescontext.getExternalContext().getRequest();
        try {      
             ref.logout();
            HttpSession session =(HttpSession) facescontext.getExternalContext().getSession(false);
            session.invalidate();           
            return "logout";
        } catch (ServletException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
     @EJB
    ApplicationLocal app;

    @PostConstruct
    public void init(){
        uzivatelB = UzivatelB.getInstance(app);
    }
    /**
     * Metoda urci o jakou instanci uzivatele se aktualne jedna, zda-li je prihlasen 
     * administrator, student nebo ucitel.
     * @return loginStudent - je prihlasen student
     * @return loginKantro je prihlasen kantor
     * @return loginAdmin je prihlaen admin
     */
    public String recognizeLogin(){
       Principal p = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
       loggedPerson=uzivatelB.findUserByLogin(p.getName());
        if(loggedPerson instanceof Student)
                return "loginStudent";
        else if(loggedPerson instanceof Kantor)
                return "loginKantor";
        else if(loggedPerson instanceof Admin)
            return "loginAdmin";
        else return "logingStudent";       
    }    
    /**
     * Metoda pozada bussiness logiku aby upravila prave prihlaseneho uzivatele.
     */
    public void modifyLoggedUser(){
        uzivatelB.modifyUser(loggedPerson);
    }
    /**
     * Metoda resetuje heslo uzivateli a nastavi mu defaultni heslo
     */
    public void resetPassoword(){
        
    }
}
