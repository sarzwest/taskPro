/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taskBeans;

import BL.IBussiness.ApplicationLocal;
import BL.Predmet.PredmetB;
import BL.Ukol.UkolB;
import BL.Uzivatel.UzivatelB;
import DL.entity.Kantor;
import DL.entity.Paralelka;
import DL.entity.Predmet;
import DL.entity.Student;
import DL.entity.Ukol;
import DL.entity.UkolSoubor;
import DL.entity.Zadani;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.Principal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Lurtz
 */
@ManagedBean(name = "allTaskBean")
@RequestScoped
public class allTaskBean implements Serializable{
    UkolB ukolB;
    UzivatelB userB;
    Student student;
    SelectItem[] vybraneTasky;
    List<Ukol> allTask;
    Ukol vybrany;

    //    @EJB
    @Inject
    ApplicationLocal app;
    
    @PostConstruct
    public void init() {
        ukolB = (UkolB) UkolB.getInstance(app);
        userB = (UzivatelB) UzivatelB.getInstance(app);
        student = (Student) userB.findUserByLogin(getPrincipal().getName());
//        allTask = ukolB.getUkolyAll(); // toto odkomentuj
        // allTask = student.getM_Ukol();  // toto yakomentuj
        allTask=ukolB.findAllTaskByStudent(student);
                vybraneTasky = createFilterOptions();
    }
    public String initAllTask(){
        ukolB = (UkolB) UkolB.getInstance(app);
        userB = (UzivatelB) UzivatelB.getInstance(app);
        student = (Student) userB.findUserByLogin(getPrincipal().getName());
//        allTask = ukolB.getUkolyAll(); // toto odkomentuj
        // allTask = student.getM_Ukol();  // toto yakomentuj
        allTask=ukolB.findAllTaskByStudent(student);
                vybraneTasky = createFilterOptions();
                return "alltaskStudent";
    }



    private Principal getPrincipal() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
    }
     public StreamedContent download(UkolSoubor u){
         File f=u.getFile();
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            InputStream fStream=fs;
           
            StreamedContent filess=new DefaultStreamedContent(fStream, "application/pdf",f.getName());
           return filess;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(studentTask.class.getName()).log(Level.SEVERE, null, ex);
        } 
      return null;
     }
       public StreamedContent downloadZadani() {
        File f = vybrany.getZadani().getZadaniFile();
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            InputStream fStream = fs;

            StreamedContent filess = new DefaultStreamedContent(fStream, "application/pdf", f.getName());
            return filess;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(studentTask.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(studentTask.class.getName()).log(Level.SEVERE, null, e);
        }
        f = new File("FileNotFound.txt");
        try {
            f.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            writer.write("Soubor se zadanim nebyl prilozen");

            writer.close();
            fs = new FileInputStream(f);
            InputStream fStream = fs;
            StreamedContent filess = new DefaultStreamedContent(fStream, "application/text", f.getName());
            return filess;
        } catch (IOException ex) {
            Logger.getLogger(submittedTaskBean.class.getName()).log(Level.SEVERE, null, ex);
        }


        return null;
    }

   private SelectItem[] createFilterOptions()  {  
        SelectItem[] options = new SelectItem[7];  
  
        options[0] = new SelectItem("", "");
        options[1] = new SelectItem("PRIJATY","PRIJATY");
        options[2] = new SelectItem("ZPOZDENY","ZPOZDENY");
        options[3] = new SelectItem("OZNAMKOVANY","OZNAMKOVANY");
        options[4] = new SelectItem("NEVYHOVUJICI","NEVYHOVUJICI");
        options[5] = new SelectItem("ODEVZDANY","ODEVZDANY");
        options[6] = new SelectItem("OZNAMKOVANY_ZPOZDENY","OZNAMKOVANY ZPOZDENY"); 
        return options;  
    } 
    
    public List<Ukol> getAllTask() {
        return allTask;
    }

    public void setAllTask(List<Ukol> allTask) {
        this.allTask = allTask;
    }
    
    
    public Ukol getVybrany() {
        return vybrany;
    }

    public void setVybrany(Ukol vybrany) {
        this.vybrany = vybrany;
    }

    
    public SelectItem[] getVybraneTasky() {
        return vybraneTasky;
    }

    public void setVybraneTasky(SelectItem[] vybraneTasky) {
        this.vybraneTasky = vybraneTasky;
    }
}
