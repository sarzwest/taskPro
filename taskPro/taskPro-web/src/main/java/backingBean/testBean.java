/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backingBean;

import BL.IBussiness.ApplicationLocal;
import DL.Imanager.IUkolD;
import DL.Imanager.IUzivatelD;
import DL.Imanager.IZadaniD;
import DL.entity.Admin;
import DL.entity.Kantor;
import DL.entity.Student;
import DL.entity.Ukol;
import DL.entity.UkolSoubor;
import DL.entity.Uzivatel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Tom
 */
@ManagedBean(name = "testBean")
@RequestScoped
public class testBean implements Serializable {

    String something;
    String admintext = "Vytvori v DB admina";
    IUzivatelD uzivD;
    IUkolD ukolD;
    IZadaniD zadaD;

    public String getSomething() {
        return something;
    }

    public String getAdmintext() {
        return admintext;
    }
    

    public void setSomething(String something) {
        this.something = something;
    }
    @EJB
    ApplicationLocal app;

    @PostConstruct
    public void init() {
        uzivD = app.getIUzivatelD();
        ukolD = app.getIUkolD();
        zadaD = app.getIZadaniD();
    }

    /** Creates a new instance of testBean */
    public testBean() {
        something = "po stisknuti tlacitka se obsah zmeni";
    }

    public void callPersist() {
        something = "a";
        Kantor k = (Kantor) uzivD.findByLogin("teach");
        List<Ukol> ukoly = ukolD.findByStavAndKantor(Ukol.Stav.PRIJATY, k);
        System.err.println(ukoly);
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
