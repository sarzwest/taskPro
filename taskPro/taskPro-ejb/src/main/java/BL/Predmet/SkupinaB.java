/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Predmet;

import BL.IBussiness.ApplicationLocal;
import BL.IBussiness.ISkupinaB;
import DL.Imanager.IPredmetD;
import DL.Imanager.ISkupinaD;
import DL.Imanager.IUzivatelD;
import DL.entity.Kantor;
import DL.entity.Paralelka;
import DL.entity.Skupina;
import DL.entity.Student;

import java.io.Serializable;
import java.util.List;

/**
  * Singleton trida, ktera implementuje chovani ISkupinaB a zprostredkovava sluzby
 * business logiky. Je primarne zamerana na praci se skupinami.
 * @author Tom
 */
public class SkupinaB implements ISkupinaB, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5452121113815203596L;
	private static SkupinaB groupB;
      private ApplicationLocal app;
    private IPredmetD predmetData;
    private IUzivatelD userData;
    private ISkupinaD groupData;
      public static ISkupinaB getInstance(ApplicationLocal app) {
        if (groupB == null) {
            groupB = new SkupinaB(app);
        }
        return groupB;
    }
        private SkupinaB(ApplicationLocal app) {
        this.app = app;
        this.userData = app.getIUzivatelD();
        this.predmetData=app.getIPredmetD();
        this.groupData=app.getISkupinaD();
    }
  /**
     * Metoda vytvori skupinu podle zadanych parametru a preda ji k zapisu 
     * do datove vrstvy
     * @param m_student 
     * @param paralelka 
     * @param nazevSkupiny 
     */
    @Override
    public void createGroup(Paralelka paralelka, List<Student> m_student,String nazevSkupiny) {
        Skupina group=new Skupina();
        group.setM_student(m_student);
        group.setNazev(nazevSkupiny);
        group.setParalelka(paralelka);
        groupData.addSkupina(group);
    }
        
}
