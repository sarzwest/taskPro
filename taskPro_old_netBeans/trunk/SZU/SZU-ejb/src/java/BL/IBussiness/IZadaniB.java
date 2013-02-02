package BL.IBussiness;

import DL.entity.Kantor;
import DL.entity.Paralelka;
import DL.entity.Ukol;
import DL.entity.Zadani;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface IZadaniB specifikuje chovaní a operace třídy, která ho bude implementovat.
 * Interface se zaměřuje na práci a operaci se zadáními. 
 * @author papa
 * @author Tom
 * @version 1.0
 * @created 11-X-2011 17:51:29
 */
@Local
public interface IZadaniB {

    /**
     * Metoda vytvoří zadání nastaví mu všechna potřebná data a poté požádá
     * datovou vrstu o vložení tohoto zadání.
     * 
     * @param deadline
     * @param nazev
     * @param popis
     * @param zadaniFile
     */
    public void vlozZadani(Zadani newTask, String zadavatel);
    /**
     * Metoda upraví zadání a požádá datovou vrstu o vložení tohoto zadání.
     */
    public void modifyTask(Date deadline, String nazev, String popis, File zadaniFile, String zadavatel, List<Ukol> ukoly, List<Paralelka> paralelka);
    /**
     * Metoda požádá datovou vrstvu o vrácení všech zadání, která jsou vytvořena 
     * specifickým kantorem.
     * @param kantorLogin 
     * @return list zadani podle kantora
     */
 
    public List<Zadani> getAllKantorTask(String kantorLogin);
    /**
     * Metoda pozada datovou vrstu o upraveni mnoziny ukolu, kterou vytvořil právě
     * přihlášený kantor.
     */
       public void modifTaskByKantor(String loggedKantorLogin,List<Zadani> tasks);
}