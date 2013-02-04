/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taskBeans;

import BL.IBussiness.ApplicationLocal;
import BL.Ukol.UkolB;
import BL.Uzivatel.UzivatelB;
import BL.Zadani.ZadaniB;
import DL.entity.Kantor;
import DL.entity.Student;
import DL.entity.Ukol;
import DL.entity.UkolSoubor;
import DL.entity.Uzivatel;
import DL.entity.Zadani;
import backingBean.LoginBean;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import javax.enterprise.inject.spi.Bean;
import javax.faces.application.Application;
import javax.inject.Inject;

/**
 *
 * @author Tom
 */
@ManagedBean(name = "taskBean")
@SessionScoped
public class taskBean {
    ZadaniB zadaniBus ;

    public Zadani getNewTask() {
        return newTask;
    }

    public void setNewTask(Zadani newTask) {
        this.newTask = newTask;
    }
    Zadani newTask;
       
    UploadedFile img;

    public UploadedFile getImg() {
        return img;
    }

    public void setImg(UploadedFile img) {
        this.img = img;
    }

    /** určeno pro vypis erroru a pro nastaveni css pro error zpravu
     */
    String err;
    String cssErr;
    
    //    @EJB
    @Inject
    ApplicationLocal app;
    @PostConstruct
    public void init(){
        zadaniBus = (ZadaniB) ZadaniB.getInstance(app);
        newTask=new Zadani();
    }
    List<Zadani>tasks;

    public List<Zadani> getTasks() {
        return tasks;
    }

    public void setTasks(List<Zadani> tasks) {
        this.tasks = tasks;
    }
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    
    
     public void setErr(String err) {
        this.err = err;
    }

    public String getErr() {
        return err;
    }

    public String getCssErr() {
        return cssErr;
    }

    public void setCssErr(String cssErr) {
        this.cssErr = cssErr;
    }
    
    
    
    
    String taskName;
    String taskDescription;
    Date deadline;
    /**
     * Metoda preda business logice parametry, s kterymi se ma vytvorit nove zadani.
     */
    public void createTask(){
        err = "Nový úkol přidán do databaze.";
        cssErr = "errAddOK";
        
        try{ 
        Principal p = getPrincipal();          
        zadaniBus.vlozZadani(newTask, p.getName());
        FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Creating task: ", "Task"+newTask.getNazev()+ " was created"));
        }
        catch(Exception e){
         
           FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "Creating task: ", "Task"+newTask.getNazev()+ " was not created."));
        }
    }
    
    
    /**
     * nastavení zpravy a css stylu pri chybě 
     * 
     */
    private void nastalaChyba(){
        err = "ERROR ... nepodarilo se přidat nový úkol.";
        cssErr = "errAdd";
    }
    
    
 /**
     * Metoda slouzi k vraceni aktualne prihlaseneho uzivatele (Principala), JSF
     * JAAS framework
     * @return aktualne prihlaseneho Principala
     */
    private Principal getPrincipal() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
    }
    /**
     * Metoda pozada business logiku o seznam vsech zadani, ktere vytvoril prave
     * prihlaseny uzivatel (kantor)
     * @return edittaskKantr - navigační pravidlo
     */
    public String loadAllTasks(){
        Principal p = getPrincipal();       
        tasks=zadaniBus.getAllKantorTask(p.getName());
        return "edittaskKantor";
    }
    public String modifyTask(){
        zadaniBus.modifTaskByKantor(getPrincipal().getName(), tasks);
        return loadAllTasks();
    }
    
      public void handleFileUpload(FileUploadEvent event) throws IOException {
        img = event.getFile();
  

        InputStream stream = event.getFile().getInputstream();
        File fileAddToTask = inputStreamToFile(stream, event.getFile().getFileName());
        newTask.setZadaniFile(fileAddToTask);
        /*
        file = new DefaultStreamedContent(stream, "application/jpg", event.getFile().getFileName());
        files.add(file);
        
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
         * 
         */
    }
        public File inputStreamToFile(InputStream input, String fileName) {
        File f = new File(fileName);
        byte[] bytes = new byte[1024];
        try {
            FileOutputStream out = new FileOutputStream(f);
            int len;
            try {
                while ((len = input.read(bytes)) > 0) {
                    out.write(bytes, 0, len);

                }
                out.close();
                input.close();

            } catch (IOException ex) {
            }
        } catch (FileNotFoundException ex) {
        }
        return f;
    }
     public StreamedContent download() {
   
      File f =newTask.getZadaniFile();
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
