/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taskBeans;

import BL.IBussiness.ApplicationLocal;
import BL.Ukol.UkolB;
import BL.Uzivatel.UzivatelB;
import DL.entity.Kantor;
import DL.entity.Student;
import DL.entity.Ukol;
import DL.entity.UkolSoubor;
import DL.entity.Uzivatel;
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
import java.util.Iterator;
import javax.enterprise.inject.spi.Bean;
import javax.faces.application.Application;
import javax.faces.el.ValueBinding;

/**
 * Trida slouzi k podpore view studenta, umi zobrazit jeho ukoly, jeho odevzdane
 * ukoly, prirazene ukoly.
 * @author Tom
 */
@ManagedBean(name = "studentTask")
@SessionScoped
public class studentTask implements Serializable {

    UkolB ukolB;
    UzivatelB userB;
    Student student;
    Kantor loggedKantor;
    private LoginBean loginBean;

    public List<Ukol> getSetOfTasks() {
        return setOfTasks;
    }

    public void setSetOfTasks(List<Ukol> setOfTasks) {
        this.setOfTasks = setOfTasks;
    }
    List<Ukol> setOfTasks;

    public Ukol getSelectedTask() {
        return selectedTask;
    }

    public void setSelectedTask(Ukol selectedTask) {
        this.selectedTask = selectedTask;
    }
    Ukol selectedTask;
    @EJB
    ApplicationLocal app;

    @PostConstruct
    public void init() {
        ukolB = (UkolB) UkolB.getInstance(app);
        userB = (UzivatelB) UzivatelB.getInstance(app);
        loadLoginBean();

    }

    private void loadLoginBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application app = context.getApplication();
        // May be deprecated
        ValueBinding binding = app.createValueBinding("#{loginBean}");
        Object value = binding.getValue(context);
        loginBean = (LoginBean) value;
    }

    /**
     * Metoda slouzi k vraceni aktualne prihlaseneho uzivatele (Principala), JSF
     * JAAS framework
     * @return aktualne prihlaseneho Principala
     */
    private Principal getPrincipal() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
    }

    public void taskToStudent() {
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Selected task: ", ((Ukol) event.getObject()).getNazev());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Car Unselected", ((Ukol) event.getObject()).getNazev());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    /**
     * Metoda pozada bussiness logiku o vraceni aktualne zadanych ukolu, na zaklade 
     * studenta, ktery je prihlasen.
     * @return assignedTask  - smerovaci pravidlo
     */
    UploadedFile img;

    public UploadedFile getImg() {
        return img;
    }

    public void setImg(UploadedFile img) {
        this.img = img;
    }

    public String loadAssignedTasks() {
        student = (Student) userB.findUserByLogin(getPrincipal().getName());
        
        setOfTasks = student.getM_Ukol(); // toto yakomentovat
        setOfTasks=ukolB.findAllTaskByStudent(student);
         
        
        Iterator seIt = setOfTasks.iterator();
        while (seIt.hasNext()) {
            Ukol currTask = (Ukol) seIt.next();
            if (!currTask.getStav().equals(Ukol.Stav.PRIJATY)) {
                if (!currTask.getStav().equals(Ukol.Stav.NEVYHOVUJICI)) {
                    seIt.remove();
                }
            }
        }
        
        //setOfTasks = getAllAssignTask(); // toto odkomentovat
        return "assignedtask";
    }

    private List<Ukol> getAllAssignTask() {
        List<Ukol> prijaty = ukolB.findStudentTaskByState("PRIJATY", student);
        List<Ukol> nevyhovujici = ukolB.findStudentTaskByState("NEVYHOVUJICI", student);
        List<Ukol> allTask = prijaty;
        allTask.addAll(nevyhovujici);
        return allTask;
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

    public UkolSoubor getFileTask() {
        return fileTask;
    }

    public void setFileTask(UkolSoubor fileTask) {
        this.fileTask = fileTask;
    }
    UkolSoubor fileTask;

    public String sendNewTask() {
        fileTask = new UkolSoubor();
        return "submittaskStudent";
    }

    public StreamedContent download(UkolSoubor u) {
        File f = u.getFile();
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            InputStream fStream = fs;

            StreamedContent filess = new DefaultStreamedContent(fStream, "application/pdf", f.getName());
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


    public void handleFileUpload(FileUploadEvent event) throws IOException {
        img = event.getFile();
        FacesMessage msg = new FacesMessage("Succesful",
                event.getFile().getFileName() + " is uploaded.");

        InputStream stream = event.getFile().getInputstream();
        File fileAddToTask = inputStreamToFile(stream, event.getFile().getFileName());
        //fileTask.setFile(fileAddToTask);
        //fileTask.setUkol(selectedTask);
        UkolSoubor fileToTask=new UkolSoubor();
        fileToTask.setFile(fileAddToTask);
        fileToTask.setUkol(selectedTask);
        List<UkolSoubor> taskes = selectedTask.getM_ukolSoubor();
        taskes.add(fileToTask);
        selectedTask.setM_ukolSoubor(taskes);
        /*
        file = new DefaultStreamedContent(stream, "application/jpg", event.getFile().getFileName());
        files.add(file);
        
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
         * 
         */
    }
    private StreamedContent file;

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public String getFileName() {
        if (file == null) {
            return "";
        } else {
            return file.getName();
        }
    }

    /**
     * Metoda odevzda ukol.
     */
    public void sendTask() {
        Student loggedStudent;
        if (loginBean.getLoggedPerson() instanceof Student) {
            loggedStudent = (Student) loginBean.getLoggedPerson();
            InputStream taskStream = null;

            /*
            addInfo();
            taskStream = img.getInputstream();
            String fileName=img.getFileName();
            
            ukolB.odevzdejUkol(taskStream, fileName, loggedStudent, selectedTask);
            
             * 
             */
            if (selectedTask.getM_ukolSoubor().isEmpty()) {
                  FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "Submit task output: ", "No files were assigned! Ta"
                                + "sk was rejected."));
            } else {
                ukolB.odevzdejUkol(loggedStudent, selectedTask);


                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Submit task output: ", "Task were submited!"));
            }

            //addWarn();

        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Submit task output: ", "User is not a student.Permision"
                    + "denied!"));
        }
    }

    public void addInfo() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sample info message", "PrimeFaces rocks!"));
    }

    public void addWarn() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Sample warn message", "Watch out for PrimeFaces!"));
    }

    public void addError(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sample error message", "PrimeFaces makes no mistakes"));
    }

    public void addFatal(ActionEvent actionEvent) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sample fatal message", "Fatal Error in System"));
    }
    List<StreamedContent> files = new ArrayList<StreamedContent>();

    public List<StreamedContent> getFiles() {
        return files;
    }

    public void setFiles(List<StreamedContent> files) {
        this.files = files;
    }
}
