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
import backingBean.LoginBean;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
/**
 *
 * @author Tom
 */
@ManagedBean (name="allKantorsTask")
@SessionScoped
public class kantorsTask {
    UkolB ukolB;
    UzivatelB userB;
      private LoginBean loginBean;
     @EJB
    ApplicationLocal app;

    public List<Ukol> getAllTask() {
        return allTask;
    }

    public void setAllTask(List<Ukol> allTask) {
        this.allTask = allTask;
    }
    
    List<Ukol> allTask;

    public Ukol getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Ukol selectedTask) {
        this.selectedTask = selectedTask;
    }
    
    Ukol selectedTask;
    @PostConstruct
    public void init() {
        ukolB = (UkolB) UkolB.getInstance(app);
        userB = (UzivatelB) UzivatelB.getInstance(app);
        
        loadLoginBean();
        Kantor loggedKantor=(Kantor)loginBean.getLoggedPerson();
        allTask=ukolB.findAllTaskByKantor(loggedKantor);
        createFilterOptions();
    }

    private void loadLoginBean(){
        FacesContext context = FacesContext.getCurrentInstance();
        Application app = context.getApplication();
        // May be deprecated
        ValueBinding binding = app.createValueBinding("#{loginBean}");
        Object value = binding.getValue(context);
        loginBean=(LoginBean) value;
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
       public SelectItem[] getVybraneTasky() {
        return vybraneTasky;
    }

    public void setVybraneTasky(SelectItem[] vybraneTasky) {
        this.vybraneTasky = vybraneTasky;
    }
    SelectItem[] vybraneTasky;
    
    
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
        File f = selectedTask.getZadani().getZadaniFile();
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
    
}


