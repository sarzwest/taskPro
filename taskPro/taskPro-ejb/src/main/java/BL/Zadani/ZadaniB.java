package BL.Zadani;

import BL.IBussiness.ApplicationLocal;
import BL.IBussiness.IZadaniB;
import BL.Support.ConvertB;
import BL.Support.MaintanceB;
import DL.Imanager.IUzivatelD;
import DL.Imanager.IZadaniD;
import DL.entity.Kantor;
import DL.entity.Paralelka;
import DL.entity.Ukol;
import DL.entity.Uzivatel;
import DL.entity.Zadani;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author papa
 * @version 1.0
 * @created 11-X-2011 17:51:30
 */ 
 /**
 * Singleton trida, ktera implementuje chovani IZadaniB a zprostredkovava sluzby
 * business logiky.
 */
public class ZadaniB implements IZadaniB, Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 940321506967151172L;

	private static IZadaniB zadaniB;

    private ApplicationLocal app;
    private IZadaniD zadaniData;
    private IUzivatelD userData;
    private ConvertB converter;
    private MaintanceB maintanceB;

    public static IZadaniB getInstance(ApplicationLocal app){
        if(zadaniB == null)
            zadaniB = new ZadaniB(app);
        return zadaniB;
    }
    
    private ZadaniB(ApplicationLocal app) {
        this.app = app;
        zadaniData = (IZadaniD)app.getIZadaniD();
        userData = (IUzivatelD)app.getIUzivatelD();
         maintanceB=new MaintanceB();
    }
     /**
     * Metoda vytvoří zadání nastaví mu všechna potřebná data a poté požádá
     * datovou vrstu o vložení tohoto zadání.
     * 
     * @param deadline
     * @param nazev
     * @param popis
     * @param zadaniFile
     */
    @Override
    public void vlozZadani(Zadani newTask, String zadavatel) {
   
    newTask.setKantor(findAndCreateKantor(zadavatel));
    zadaniData.addZadani(newTask);
    
    
    }
    
    private Kantor findAndCreateKantor(String login){
        Uzivatel u=userData.findByLogin(login);
        Kantor k=null;
    if(u instanceof Kantor){
        k=(Kantor) u;
    }
    return k;
    }
 
     /**
     * Metoda upraví zadání a požádá datovou vrstu o vložení tohoto zadání.
     */

    @Override
    public void modifyTask(Date deadline, String nazev, String popis, File zadaniFile, String zadavatel, List<Ukol> ukoly, List<Paralelka> paralelka) {
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
 /**
     * Metoda požádá datovou vrstvu o vrácení všech zadání, která jsou vytvořena 
     * specifickým kantorem.
     * @param kantorLogin 
     * @return list zadani podle kantora
     */
    @Override
    public List<Zadani> getAllKantorTask(String kantorLogin) {
      Kantor k=(Kantor) userData.findByLogin(kantorLogin);
      return k.getM_Zadani();
    }
 /**
     * Metoda pozada datovou vrstu o upraveni mnoziny ukolu, kterou vytvořil právě
     * přihlášený kantor.
     */
    @Override
    public void modifTaskByKantor(String loggedKantorLogin, List<Zadani> tasks) {
       Iterator tasksIter=tasks.iterator();
       Zadani z=new Zadani();
       Kantor loggedKantor=findAndCreateKantor(loggedKantorLogin);
       while(tasksIter.hasNext()){
           z=(Zadani) tasksIter.next();
           
               zadaniData.updateZadani(z);
           
       }
        
    }
}