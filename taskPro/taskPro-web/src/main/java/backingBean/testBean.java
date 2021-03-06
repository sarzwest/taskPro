/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backingBean;

import BL.IBussiness.ApplicationLocal;
import DL.Imanager.IPredmetD;
import DL.Imanager.IUkolD;
import DL.Imanager.IUzivatelD;
import DL.Imanager.IZadaniD;
import DL.entity.Admin;
import DL.entity.Student;
import DL.entity.Ukol;
import DL.entity.UkolSoubor;
import DL.entity.Uzivatel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author Tom
 */
@ManagedBean(name = "testBean")
@SessionScoped
public class testBean implements Serializable {

    String something;
    String admintext = "Vytvori v DB admina";
    IUzivatelD uzivD;
    IUkolD ukolD;
    IZadaniD zadaD;
    IPredmetD predD;

    public String getSomething() {
        return something;
    }

    public String getAdmintext() {
        return admintext;
    }
    

    public void setSomething(String something) {
        this.something = something;
    }
//    @EJB
    @Inject
    ApplicationLocal app;

    @PostConstruct
    public void init() {
        uzivD = app.getIUzivatelD();
        ukolD = app.getIUkolD();
        zadaD = app.getIZadaniD();
        predD = app.getIPredmetD();
    }

    /** Creates a new instance of testBean */
    public testBean() {
    	
//        something = "po stisknuti tlacitka se obsah zmeni";
    }
    
    /**
     * testovaci metoda
     */
    public String ttestBean(){
    	predD.printParalelkaByKantor();
    	something = "po sti";
    	return "assigntaskKantor";
    }

    public void callPersist() {
        something = "a";
        UkolSoubor soubor = ukolD.findByStav(Ukol.Stav.ODEVZDANY).get(0).getM_ukolSoubor().get(0);
        soubor = ukolD.nahrajSoubor(soubor);
    }

    public void createAdmin() {
        try {
            Uzivatel admin = new Admin();
            admin.setHeslo("admin");
            admin.setJmeno("Hlavni");
            admin.setPrijmeni("Admin");
            admin.setLogin("admin");
            admin.setMail("admin@fel.cvut.cz");
            uzivD.addUzivatel(admin);
            admintext = "Uzivatel \"admin\" heslo \"admin\" uspesne pridan do databaze.";
        }catch(Exception e){
            admintext = e.getLocalizedMessage();
        }
    }
}
