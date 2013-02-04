/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.IBussiness;

import DL.entity.Kantor;
import DL.entity.Predmet;
import DL.entity.Student;
import javax.ejb.Local;
import java.util.List;

/**
 * Interface IPredmetB specifikuje operace a chovani, ktere bude muset implementovat
 * trida, ktera bude implementovat toto rozhranni. Metody jsou zamereny na 
 * praci s predmetem. Trida je zamerena na chovani na bussiness logice
 * @author papa
 * @author Tom
 */
@Local
public interface IPredmetB {
    
    /**
     * Vytvori predmet podle specifikych vlastnosti, kodu a popisu a preda
     * ho k vlozeni do datove vrstvy.
     * @param kod 
     * @param popis 
     */
    public void addPredmet(String kod, String popis);
    /**
     * Metoda preda do datove vrstvy pozadavek k odstraneni predmetu se specifikym
     * kodem.
     * @param kod 
     */
    public void removePredmet(String kod);
    /**
     * Metoda vytvori paralelku na zaklade parametru a preda ji k zapisu do 
     * datove vrstvy.
     * @param kod - paralelky
     * @param kodPredmetu - kod predmetu
     * @param m_kantor - seznam kantoru, kteri mohou ucit v paralelce
     * @param m_student - seznam studentu, kteri jsou zapsani do paralelky
     */
    public void addParalelka(String kod, Predmet subject, List<Kantor> m_kantor, List<Student> m_student);
    
    /**
     * Metodo pozada datovou vrstu o vyhledani predmetu na zaklade jejich kodu.
     * @param kod 
     * @return List predmetu, ktere maji specificky kod
     */
    public List<Predmet> showSubjects(String kod);
   /**
     * Metoda pozada datovou vrstvu o seznam vsech predmetu a ty vrati uzivateli 
     * v datove strukture List
     * @return List premetu
     */
    public List<Predmet> showSubjects();
    /**
     * Metoda pozada datovou vrstvu o seznam vsech predmetu, ktere jsou prirazeny
     * konkretnimu uciteli.
     * @param k 
     */
     public List<Predmet> showSubjectsByTeacher(Kantor k);
     
}
