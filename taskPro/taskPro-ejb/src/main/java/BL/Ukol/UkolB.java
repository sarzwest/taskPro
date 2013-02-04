package BL.Ukol;

import BL.IBussiness.ApplicationLocal;
import BL.IBussiness.IUkolB;
import BL.Support.ConvertB;
import BL.Support.MaintanceB;
import DL.Imanager.IUkolD;
import DL.Imanager.IZadaniD;
import DL.entity.Kantor;
import DL.entity.Student;
import DL.entity.Ukol;
import DL.entity.Zadani;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author papa
 * @author Tom
 * @version 1.0
 * @created 11-X-2011 17:51:29
 */
/**
 *Singleton trida, ktera implementuje chovani IUkolB a zprostredkovava sluzby
 * business logiky.
 */
public class UkolB implements IUkolB {
  
    
    private static IUkolB ukolB;
    private ApplicationLocal app;
    private IUkolD ukolData;
    private IZadaniD zadaniData;
    private ConvertB convertor;
    private MaintanceB  maintanceB;

    public static IUkolB getInstance(ApplicationLocal app) {
        if (ukolB == null) {
            ukolB = new UkolB(app);
        }
        return ukolB;
    }

    private UkolB(ApplicationLocal app) {
        this.app = app;
        this.ukolData = (IUkolD) app.getIUkolD();
        this.zadaniData = app.getIZadaniD();
        convertor=new ConvertB();
        maintanceB=new MaintanceB();
    }

    /**
     * Metoda pozada datovou vrstu o vsechny ukoly v systemu a vrati je v datove 
     * strukture List
     * @return List vsech ukolu v databazi
     */
    @Override
    public List<Ukol> getUkolyAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Metoda pozada datovou vrstu o vsechny ukoly daneho ucitele a vrati jejich list
     * @param teacher 
     * @return List ukolu danneho ucitele.
     */
    @Override
    public List<Ukol> getUkolyByTeacher(Kantor teacher) {
      return ukolData.findByKantor(teacher);
    }

    /**
     * Metoda zpracuje vstupni soubor priradiho k uzivateli a k ukolu ktereho se tyka
     * a takto upravenou strukturu preda do datove vrstvy k ulozeni
     * @param soubor
     * @param loginUzivatele
     * @param ukolOdevzdani 
     */
    @Override
    public void odevzdejUkol(Student loggedStudent, Ukol ukolOdevzdani) { 
        ukolOdevzdani.setDatumOdev(maintanceB.getTodayDate());
        if(ukolOdevzdani.getDatumOdev().after(ukolOdevzdani.getDeadline())
            ){
            ukolOdevzdani.setStav(Ukol.Stav.ZPOZDENY);
        }
        else{
            ukolOdevzdani.setStav(Ukol.Stav.ODEVZDANY); 
        }
        ukolData.updateUkol(ukolOdevzdani);
    }

    /**
     * Metoda prida bodove a slovni hodnoceni, nebo jen jedno z nich na základě 
     * vstupních parametrů ke konkrétnému úkolu. Pote pozada datovou vrstvu o vlozeni
     * upravene struktury do databaze.
     * @param ukol
     * @param slovniHodnoceni
     * @param bodyHodnoceni
     */
    @Override
    public void ohodnotUkol(Ukol u) {
        if((u.getHodnoceni()>0)&&(u.getHodnoceni()<6)){
               ukolData.updateUkol(u);
        }
     

    }

    /**
     * Metoda zmeni stav ukolu na stav Ukonceno. Pote pozada datovou vrstvu o 
     * zapis upravene struktury do DB
     * @param stav 
     * @param ukol 
     * 
     */
    @Override
    public void uzavriUkol(Ukol ukol) {
        ukolData.updateUkol(ukol);
    }

    private Ukol makeUkol(ArrayList<Student> vypracovateleUkolu, Zadani temp) {
        
        
        Ukol u = new Ukol();
        //  u.setM_Student(vypracovateleUkolu);
        u.setZadani(temp);
        u.setStav(Ukol.Stav.PRIJATY);
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Metoda priradi ukol skupine studentu a pote zavola datovou vrstu a pozada
     * ji o vlozeni novych udaju
     * @param vypracovateleUkolu
     * @param nazevZadani
     */
    @Override
    public void zadejUkolSkupine(List<Student> vypracovateleUkolu, String nazevZadani, String nazevUkolu) {
        Ukol task = new Ukol();
        Zadani zadaniTemp = zadaniData.getZadani(nazevUkolu);
        // task.setM_Student(vypracovateleUkolu);
        task.setStav(Ukol.Stav.PRIJATY);
        task.setZadani(zadaniTemp);
        task.setNazev(nazevUkolu);
        ukolData.addUkol(task);
    }

    /**
     * Metoda priradi studentovy novy ukol a pote pozada datovou vrstu o zapis
     * upravenych dat do DB
     * @param nazevUkolu 
     * @param nazevZadani 
     * @param vypracovatelUkolu 
     */
    @Override
    public void zadejUkolJednotlivci(Student vypracovatelUkolu, String nazevZadani, String nazevUkolu) {
        Zadani temp = null;
        temp = zadaniData.getZadani(nazevZadani);
        Ukol u = new Ukol();
        u.setZadani(temp);
        u.setStav(Ukol.Stav.PRIJATY);
        ukolData.addUkol(u);
    }

    /**
     * Metoda zada mnozinu ukolu, mnozine studentu, ktere vyucuje urcity kantor.
     * Pote pozada datovou vrstu o vlozeni teto struktury do DB.
     * @param loggedKantor 
     * @param students 
     * @param tasks 
     */
    @Override
    public Ukol zadejUkol(List<Student> students, List<Ukol> tasks, Kantor loggedKantor) {
        Ukol newTask = new Ukol();
        Iterator taskIt = tasks.iterator();
        while (taskIt.hasNext()) {
            Ukol currTask = (Ukol) taskIt.next();
            if(currTask.getDeadline().after(maintanceB.getTodayDate())){
               Iterator studentIt = students.iterator();
            while (studentIt.hasNext()) {
                Student tempStundent = (Student) studentIt.next();
                newTask.setStudent(tempStundent);
                newTask.setDeadline(currTask.getDeadline());
                newTask.setZadani(currTask.getZadani());
                newTask.setNazev(currTask.getNazev());
                newTask.setStav(Ukol.Stav.PRIJATY);
                newTask.setPopis(currTask.getPopis());
                ukolData.addUkol(newTask);
            }
            }
            else {
                return  currTask;
            }
            
         

        }
        return null;
    }

    @Override
    public List<Ukol> findTaskByState(String stav) {
   
            return ukolData.findByStav(Ukol.Stav.valueOf(stav));
       
    }

    @Override
    public List<Ukol> findKantorTaskByState(String stav, Kantor kantor) {
       
       return ukolData.findByStavAndKantor(Ukol.Stav.valueOf(stav), kantor);
    }

    @Override
    public List<Ukol> findStudentTaskByState(String stav, Student student) {
        return ukolData.findByStavAndStudent(Ukol.Stav.valueOf(stav), student);
    }

    @Override
    public List<Ukol> findAllTaskByStudent(Student student) {
        return ukolData.findByStudent(student);
    }

    @Override
    public List<Ukol> findAllTaskByKantor(Kantor kantor) {
      return ukolData.findByKantor(kantor);
    }
  
}