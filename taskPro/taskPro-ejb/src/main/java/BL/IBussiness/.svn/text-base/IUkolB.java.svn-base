package BL.IBussiness;

import DL.entity.Kantor;
import DL.entity.Student;
import DL.entity.Ukol;
import DL.entity.Uzivatel;
import DL.entity.Zadani;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * @author papa
 * @version 1.0
 * @created 11-X-2011 17:51:28
 * @author Tom
 * Rozhrani pro praci s ukoly na urovni bussiness vrstvy, pouziva se z prezentacni vrstvy
 */
@Local
public interface IUkolB {
    /**
     * Metoda pozada datovou vrstu o vsechny ukoly v systemu a vrati je v datove 
     * strukture List
     * @return List vsech ukolu v databazi
     */
    public List<Ukol> getUkolyAll();
    /**
     * Metoda pozada datovou vrstu o vsechny ukoly daneho ucitele a vrati jejich list
     * @param teacher 
     * @return List ukolu danneho ucitele.
     */
    
    public List<Ukol> getUkolyByTeacher(Kantor teacher);

    /**
     * Metoda zpracuje vstupni soubor priradiho k uzivateli a k ukolu ktereho se tyka
     * a takto upravenou strukturu preda do datove vrstvy k ulozeni
     * @param inputTaskFile 
     * @param fileName 
     * @param loggedStudent 
     * @param ukolOdevzdani 
     */
    public void odevzdejUkol(Student loggedStudent, Ukol ukolOdevzdani);

    /**
     * Metoda prida bodove a slovni hodnoceni, nebo jen jedno z nich na základě 
     * vstupních parametrů ke konkrétnému úkolu. Pote pozada datovou vrstvu o vlozeni
     * upravene struktury do databaze.
     * @param ukol
     * @param slovniHodnoceni
     * @param bodyHodnoceni
     */
    public void ohodnotUkol(Ukol ukol);

    /**
     * Metoda zmeni stav ukolu na stav Ukonceno. Pote pozada datovou vrstvu o 
     * zapis upravene struktury do DB
     * @param stav 
     * @param ukol 
     * 
     */
    public void uzavriUkol(Ukol ukol);

    /**
     * Metoda priradi ukol skupine studentu a pote zavola datovou vrstu a pozada
     * ji o vlozeni novych udaju
     * @param vypracovateleUkolu
     * @param nazevZadani
     */  
    public void zadejUkolSkupine(List<Student> vypracovateleUkolu, String nazevZadani, String nazevUkolu);
    
    /**
     * Metoda priradi studentovy novy ukol a pote pozada datovou vrstu o zapis
     * upravenych dat do DB
     * @param nazevUkolu 
     * @param nazevZadani 
     * @param vypracovatelUkolu 
     */
    public void zadejUkolJednotlivci(Student vypracovatelUkolu, String nazevZadani, String nazevUkolu);
    /**
     * Metoda zada mnozinu ukolu, mnozine studentu, ktere vyucuje urcity kantor.
     * Pote pozada datovou vrstu o vlozeni teto struktury do DB.
     * @param loggedKantor 
     * @param students 
     * @param tasks 
     */
    public Ukol zadejUkol(List<Student> students, List<Ukol> tasks,Kantor loggedKantor);
    /**
     * Metoda vrati ukoly ktere jsou v nejakem pozadovanem stavu
     */
    public List<Ukol> findTaskByState(String stav);
    /**
     * Metoda vrátí úkoly, které jsou v nějakém stavu a byli přiřazeny konkrétním
     * kantorem
     */
    public List<Ukol> findKantorTaskByState(String stav, Kantor kantor);
    /**
     * Metoda vrátí úkoly, které jsou v nějakém stavu a byli přiřazeny konkrétnímu
     * studentovi
     */
    public List<Ukol> findStudentTaskByState(String stav, Student student);
    /**
     * Metoda vrátí všechy úkoly, které jsou přiřazeny konrétnímu studentovi
     */
      public List<Ukol> findAllTaskByStudent(Student student);
      /**
       * Metoda vrati vsechny ukoly, ktery byly prirazeny jednim kantorem.
       * @param kantor 
       */
       public List<Ukol> findAllTaskByKantor(Kantor kantor);
}