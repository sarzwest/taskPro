/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Predmet;

import BL.IBussiness.ApplicationLocal;
import BL.IBussiness.IPredmetB;
import DL.Imanager.IPredmetD;
import DL.Imanager.IUzivatelD;
import DL.entity.Kantor;
import DL.entity.Paralelka;
import DL.entity.Predmet;
import DL.entity.Student;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author papa
 */
/**
  * Singleton trida, ktera implementuje chovani IPredmetB a zprostredkovava sluzby
 * business logiky. Je primarne zamerana na praci s predmety
 * @author Tom
 */
public class PredmetB implements IPredmetB{
    
    private static IPredmetB predmetB;
    
    private ApplicationLocal app;
    private IPredmetD predmet;
    private IUzivatelD userData;

    public static IPredmetB getInstance(ApplicationLocal app) {
        if (predmetB == null) {
            predmetB = new PredmetB(app);
        }
        return predmetB;
    }

    private PredmetB(ApplicationLocal app) {
        this.app = app;
        this.predmet = (IPredmetD) app.getIPredmetD();
        this.userData = app.getIUzivatelD();
    }
    /**
     * Vytvori predmet podle specifikych vlastnosti, kodu a popisu a preda
     * ho k vlozeni do datove vrstvy.
     * @param kod 
     * @param popis 
     */
    @Override
    public void addPredmet(String kod, String popis) {
       Predmet p=new Predmet();
       p.setKod(kod);
       p.setPopisek(popis);
       predmet.addPredmet(p); 
    }
 /**
     * Metoda vytvori paralelku na zaklade parametru a preda ji k zapisu do 
     * datove vrstvy.
     * @param kod - paralelky
     * @param kodPredmetu - kod predmetu
     * @param m_kantor - seznam kantoru, kteri mohou ucit v paralelce
     * @param m_student - seznam studentu, kteri jsou zapsani do paralelky
     */
    @Override
    public void addParalelka(String kod, Predmet subject, List<Kantor> m_kantor, List<Student> m_student) {
        Predmet p=null;
        Paralelka paralel=new Paralelka();
        paralel.setKod(kod);
        paralel.setPredmet(subject);
        paralel.setM_kantor(m_kantor);
        paralel.setM_student(m_student);
        predmet.addParalelka(paralel);      
    }
      /**
     * Metodo pozada datovou vrstu o vyhledani predmetu na zaklade jejich kodu.
     * @param kod 
     * @return List predmetu, ktere maji specificky kod
     */
    @Override  
    public List<Predmet> showSubjects(String kod) {
        return (List<Predmet>) predmet.findPredmetByKod(kod);
    }
 /**
     * Metoda pozada datovou vrstvu o seznam vsech predmetu a ty vrati uzivateli 
     * v datove strukture List
     * @return List premetu
     */
    @Override
    public List<Predmet> showSubjects() {
        return predmet.getAllPredmet();
    }
/**
     * Metoda preda do datove vrstvy pozadavek k odstraneni predmetu se specifikym
     * kodem.
     * @param kod 
     */
    @Override
    public void removePredmet(String kod) {
        throw new UnsupportedOperationException("Not supported yet.");
    }    /**
     * Metoda pozada datovou vrstvu o seznam vsech predmetu, ktere jsou prirazeny
     * konkretnimu uciteli.
     * @param k 
     */
    @Override
    public List<Predmet> showSubjectsByTeacher(Kantor k){
        List<Paralelka> kantorsParalels=k.getParalelkas();
        List<Predmet> kantrosSubject=new LinkedList<Predmet>();
        Iterator paralelIt=kantorsParalels.iterator();
        Iterator subjectIt=kantrosSubject.iterator();
        while(paralelIt.hasNext()){
            Paralelka currParalel=(Paralelka) paralelIt.next();
            if(!kantrosSubject.contains(currParalel.getPredmet())){
                kantrosSubject.add(currParalel.getPredmet());
            }      
        }
        return kantrosSubject;
    }
    
}
