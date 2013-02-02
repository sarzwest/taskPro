/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userBean;

import BL.IBussiness.ApplicationLocal;
import BL.IBussiness.IUzivatelB;
import BL.Uzivatel.UzivatelB;
import DL.entity.Uzivatel;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Beana pro přidávání nových studenů.
 * @author Lurtz
 * 
 */
@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean implements Serializable {

    int id;
    String login;
    String heslo;
    String jmeno;
    String prijmeni;
    String email;
    String status;
    
    /** určeno pro vypis erroru a pro nastaveni css pro error zpravu
     */
    String err;
    String cssErr;
    
    Uzivatel uzivatel;

    public Uzivatel getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
    }
    
    /**instance v bussiness vrstve starajici se o uzivatele*/
    IUzivatelB uzivatelB;
    
    @EJB
    ApplicationLocal app;

    @PostConstruct
    public void init(){
        uzivatelB = UzivatelB.getInstance(app);
    }
    
    
    
    public UserBean() {
      
    }

    
    /**
     * prida uživatele do databaze,
     * zavola funkci s bussiness vrstve addUzivatel
     * 
     */
    public void pridejUzivatele() {
        err = "Student přidán do databaze";
        cssErr = "errAddUserOK";
        try {
            uzivatelB.addUzivatel(login, heslo, jmeno, prijmeni, email, status);    
        }catch(Exception e){
            System.err.println(e.getMessage());
            nastalaChyba();
        }
    }

    
    /**
     * nastavení zpravy a css stylu pri chybě 
     * 
     */
    private void nastalaChyba(){
        err = "ERROR ... zadané hodnoty v databázi už existují.";
        cssErr = "errAddUser";
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
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
