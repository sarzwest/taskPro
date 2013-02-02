package BL.Uzivatel;

import BL.IBussiness.ApplicationLocal;
import BL.IBussiness.IUzivatelB;
import DL.Imanager.IUzivatelD;
import DL.entity.Admin;
import DL.entity.Kantor;
import DL.entity.Paralelka;
import DL.entity.Student;
import DL.entity.Uzivatel;
import DL.entity.Zadani;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.*;
import jxl.read.biff.BiffException;
/**
 * @author papa
 * @author Martin Tomasek
 * @version 1.0
 * @created 11-X-2011 17:51:29
 */

 /**
 * Singleton trida, ktera implementuje chovani IUzivatelB a zprostredkovava sluzby
 * business logiky.
 */
public class UzivatelB implements IUzivatelB {
    
    private static IUzivatelB uzivatelB;

    private ApplicationLocal app;
    private IUzivatelD userData;
    
    public static IUzivatelB getInstance(ApplicationLocal app){
        if(uzivatelB == null)
            uzivatelB = new UzivatelB(app);
        return uzivatelB;
    }

    private UzivatelB(ApplicationLocal app) {
        this.app = app;
        this.userData = (IUzivatelD) app.getIUzivatelD();
    }
 /**
     * Metoda upravi uzivateli na zaklade jeho loginu, jeho e-mail a heslo. Pote
     * pozada datovou vrstu o vlozeni takto upravenych udajou do DB
     */
    @Override
    public void updateUdaje(String login, String mail, String heslo) {
        //najdi ho podle loginu v databazi
        Uzivatel u = userData.findByLogin(login);
        //predelej mail a heslo
        u.setMail(mail);
        u.setHeslo(heslo);
        //updatuj do databaze
        userData.updateUzivatel(u);
    }
 /**
     * Metoda pozada datovou vrstu o vsechny studenty v DB.
     * @return List studentu.
     */
    @Override
    public List<Student> getStudentyAll() {
        List<Student> students=userData.getAllStudent();
        return students;
    }
    /**
     * Metoda pozada datovou vrstu o kantora, ktery vytvoril specificke zadani
     * @param z 
     */
    public void getKantor(Zadani z) {
        app.getIUzivatelD().getKantor(z);
    }
    
    @Override
    public String readExcel(File f){
        Workbook workbook=null;
        String status="ok";
        try {
            workbook= Workbook.getWorkbook(f);
            Sheet sheet=workbook.getSheet(0);
            //prvni je slupec druhy je radek
            int radek=0;
            while(true){
                Uzivatel newUser=new Uzivatel();
                addUzivatel(sheet.getCell(0, radek).getContents(),
                        sheet.getCell(1,radek).getContents(),
                        sheet.getCell(2,radek).getContents(),sheet.getCell(3,radek).getContents(),
                        sheet.getCell(4,radek).getContents(),
                        sheet.getCell(5,radek).getContents());
               
            radek++;
            }
            
            
           
        } catch (IOException ex) {
            Logger.getLogger(UzivatelB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(UzivatelB.class.getName()).log(Level.SEVERE, null, ex);
        } catch(JXLException e){
            status=null;
             Logger.getLogger(UzivatelB.class.getName()).log(Level.SEVERE, null, e);
        } catch(Exception e){
            status="unknow";
        }
        finally {
           workbook.close();
            return status;
        }
        
    }
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
    @Override
    public void addUzivatel(String login, String heslo, String jmeno, String prijmeni, String mail, String status) {
        Uzivatel u=null;
        if (status.equals("student")) {
            u = new Student();
        } else if(status.equals("teacher")){
            u = new Kantor();
        }
        else if(status.equals("admin")){
            u=new Admin();
        }
        u.setLogin(login);
        u.setHeslo(heslo);
        u.setMail(mail);
        u.setJmeno(jmeno);
        u.setPrijmeni(prijmeni);
        userData.addUzivatel(u);
    }
/**  
     * Metoda pozada datovou vrstu o vsechny uzivatele v DB
     * @return List vsech uzivatelu v DB
     */
    @Override
    public List<Uzivatel> getUzivateleAll() {
        List<Uzivatel> users=userData.getAllUzivatel();
        return users;
    }
 /**  
     * Metoda pozada datovou vrstu o vsechny ucitele v DB
     * @return List vsech ucitele v DB
     */
    @Override
    public List<Kantor> getAllKantor() {
       List<Kantor> kantors=userData.getAllKantor();
       return kantors;
    }
 /**
     * Metoda vytvori uzivatele, ktere jsou ve vstupnim souboru. Očekávaný formát je 
     * CSV, poté požádá datovou vrstu o zápis těchto uživatelů do DB
     * @param f 
     */
    @Override
    public String addUzivatel(InputStream f) {
           String radkaCSV;
String actualString[] = null;
            BufferedReader br=new BufferedReader(new InputStreamReader(f));
        try {
           
            while((radkaCSV=br.readLine())!=null){
                 actualString= radkaCSV.split(";");
                addUzivatel(actualString[0], actualString[1], actualString[2], 
                        actualString[3], actualString[4], actualString[5]);

            }
           
        } catch (IOException ex) {
            Logger.getLogger(UzivatelB.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception e){
            return  actualString[0];
        }
         return null;
    }
 /**
     * Metoda požádá datovou vrstu o vyhledání uživatele na základě jeho loginu.
     *@param login 
     * @return Uzivatele nalezeného na základě loginu.
     */
    @Override
    public Uzivatel findUserByLogin(String login) {
        Uzivatel tempUser=userData.findByLogin(login);
        return tempUser;
    }

    @Override
    public Uzivatel findUserToLogin(String login) {
        List<Uzivatel> uzivatele= getUzivateleAll();
        Uzivatel tempUser;
        for (Iterator<Uzivatel> it = uzivatele.iterator(); it.hasNext();) {
            Uzivatel uzivatel = it.next();
            if(uzivatel.getLogin().equals(login))
               return uzivatel;
        }
       
       return null;
    }
   /**
     * Metoda požádá datovou vrstvu o odstranění z databáze uživatele podle jeho loginu.
     * @param login 
     */
    @Override
    public void removeUser(String login) {
        Uzivatel user;
        user=userData.findByLogin(login);
        userData.removeUzivatel(user);
    }
 /**
     * Metoda pozada datovou vrstu o upraveni aktualniho studenta, podle jeho
     * loginu, ktery se nemeni.
     * @param user 
     */
    @Override
    public void modifyUser(Uzivatel user) {
       //   Uzivatel temp=findUserByLogin(user.getLogin());
     userData.updateUzivatel(user);
    }
/**
     * Metoda nastavi uzivateli vychozi heslo.
     */
    @Override
    public void resetPassword(Uzivatel user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
     /**
     * Metoda požádá datovou vrstu o vyhledání všech studentů, které učí kantor.
     * @param teacher 
     * @return List studentů, vyhledaných podle kantora.
     */
    @Override
    public List<Student> getStudenstByKantor(Kantor teacher){
        /*
         * 
       
        List<Student> studentsByKantor=new LinkedList<Student>();
        List<Paralelka> teacherParalel=teacher.getParalelkas();
        List<Student> studentsInAllParalel=new LinkedList<Student>();
        Iterator teachersParaIt=teacherParalel.iterator();
        while(teachersParaIt.hasNext()){
            Paralelka currParalel=(Paralelka) teachersParaIt.next();
           studentsInAllParalel.addAll(currParalel.getM_student());     
        }
        
        Iterator studentsInParaIt=studentsInAllParalel.iterator();
        while(studentsInParaIt.hasNext()){
            Student currStudent=(Student) studentsInParaIt.next();
            if(!studentsByKantor.contains(currStudent)){
                studentsByKantor.add(currStudent);
            }
        }
         * 
         *   return studentsByKantor;
         */
        return userData.getAllStudentByKantor(teacher);
      
    }
}