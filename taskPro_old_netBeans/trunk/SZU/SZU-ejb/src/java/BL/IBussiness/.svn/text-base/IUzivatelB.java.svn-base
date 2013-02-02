package BL.IBussiness;

import DL.entity.Kantor;
import DL.entity.Student;
import DL.entity.Uzivatel;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface slouzici pro praci s uzivatelem. Trida se zameruje na praci s uzivatelem
 * na business úrovni.
 * @author papa
 * @author Tom
 * @version 1.0
 * @created 11-X-2011 17:51:29
 */
@Local
public interface IUzivatelB {
    /**
     * Metoda pozada datovou vrstu o vsechny studenty v DB.
     * @return List studentu.
     */
    public List<Student> getStudentyAll();
     /**  
     * Metoda pozada datovou vrstu o vsechny uzivatele v DB
     * @return List vsech uzivatelu v DB
     */
    public List<Uzivatel> getUzivateleAll();
     /**  
     * Metoda pozada datovou vrstu o vsechny ucitele v DB
     * @return List vsech ucitele v DB
     */
    public List<Kantor> getAllKantor();
    /**
     * Metoda upravi uzivateli na zaklade jeho loginu, jeho e-mail a heslo. Pote
     * pozada datovou vrstu o vlozeni takto upravenych udajou do DB
     */
    
    public String readExcel(File f);
    public void updateUdaje(String login, String mail, String heslo);
    /**
     * Metoda pozada datovou vrstu o upraveni aktualniho studenta, podle jeho
     * loginu, ktery se nemeni.
     * @param user 
     */
    public void modifyUser(Uzivatel user);
    /**
     * Metoda nastavi uzivateli vychozi heslo.
     */
    public void resetPassword(Uzivatel user);
     /**
     * metoda pro vkladani studentu a kantoru
     * pokud je cokoli nulove tak hazi EJBException
     * pokud je login a email ne-unikatni tak hazi EJBException
     * @param login
     * @param heslo
     * @param jmeno
     * @param prijmeni
     * @param mail
     * @param status - zda se jedna o studenta ci kantora, pokud "student" tak je to student, cokoli jineho je kantor
     */
    public void addUzivatel(String login, String heslo, String jmeno, String prijmeni, String mail, String status);
    /**
     * Metoda vytvori uzivatele, ktere jsou ve vstupnim souboru. Očekávaný formát je 
     * CSV, poté požádá datovou vrstu o zápis těchto uživatelů do DB
     * @param f 
     */
    public String addUzivatel(InputStream f);
    
    /**
     * Metoda požádá datovou vrstvu o odstranění z databáze uživatele podle jeho loginu.
     * @param login 
     */
    public void removeUser(String login);
    
    /**
     * Metoda požádá datovou vrstu o vyhledání uživatele na základě jeho loginu.
     *@param login 
     * @return Uzivatele nalezeného na základě loginu.
     */
    public Uzivatel findUserByLogin(String login);
    
    public Uzivatel findUserToLogin(String login);
    /**
     * Metoda požádá datovou vrstu o vyhledání všech studentů, které učí kantor.
     * @param teacher 
     * @return List studentů, vyhledaných podle kantora.
     */
     public List<Student> getStudenstByKantor(Kantor teacher);
}