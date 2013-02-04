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
import java.security.Principal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * Trida umoznuje pridani zadani jednotlivym studentum ci mnozine techto studentu
 * @author Tom
 */
@ManagedBean(name = "assignTask")
@SessionScoped
public class AssignTask {
    PredmetB subjectB;
    UzivatelB userB;
    Kantor loggedKantor;
    List<Paralelka> kantorParalel;

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }
     private StreamedContent file;  
    public Ukol getTaskChosenToRate() {
        return taskChosenToRate;
    }
    /**
     * Metoda prevede file soubor na downloadstreamContent type,
     * slouzi k moznosti stahnout si soubory, ktere nahral uziatel
     */
 

    public void setTaskChosenToRate(Ukol taskChosenToRate) {
        this.taskChosenToRate = taskChosenToRate;
    }
    Ukol taskChosenToRate;
    public Predmet[] getKantrosSubjectToFilter() {
        return kantrosSubjectToFilter;
    }
    
    public void setKantrosSubjectToFilter(Predmet[] kantrosSubjectToFilter) {
        this.kantrosSubjectToFilter = kantrosSubjectToFilter;
    }
    Predmet[] kantrosSubjectToFilter;

    public List<Predmet> getKantorSubjects() {
        return kantorSubjects;
    }
    
    public void setKantorSubjects(List<Predmet> kantorSubjects) {
        this.kantorSubjects = kantorSubjects;
    }
    List<Predmet> kantorSubjects;

    public Paralelka[] getParalelkaAsSubject() {
        return paralelkaAsSubject;
    }
    
    public void setParalelkaAsSubject(Paralelka[] paralelkaAsSubject) {
        this.paralelkaAsSubject = paralelkaAsSubject;
    }
    Paralelka[] paralelkaAsSubject;
    
    public List<Student> getStudentsChosen() {
        return studentsChosen;
    }
    
    public void setStudentsChosen(List<Student> studentsChosen) {
        this.studentsChosen = studentsChosen;
    }
    List<Student> studentsChosen;
    
    public Student[] getCurrChosen() {
        return currChosen;
    }
    
    public void setCurrChosen(Student[] currChosen) {
        this.currChosen = currChosen;
    }
    Student[] currChosen;
    Paralelka temp;

    public boolean isDisplayTaskList() {
        return displayTaskList;
    }

    public void setDisplayTaskList(boolean displayTaskList) {
        this.displayTaskList = displayTaskList;
    }
    boolean displayTaskList;
    public Paralelka getTemp() {
        return temp;
    }
    
    public void setTemp(Paralelka temp) {
        this.temp = temp;
    }

    public Paralelka[] getParalelChosen() {
        return paralelChosen;
    }
    
    public void setParalelChosen(Paralelka[] paralelChosen) {
        this.paralelChosen = paralelChosen;
    }
    Paralelka[] paralelChosen;
    
    public List<Predmet> getSubjectChosen() {
        return subjectChosen;
    }
    
    public void setSubjectChosen(List<Predmet> subjectChosen) {
        this.subjectChosen = subjectChosen;
    }
    List<Predmet> subjectChosen;
    
    public List<Paralelka> getFiltredParalelBySubject() {
        return filtredParalelBySubject;
    }
    
    public void setFiltredParalelBySubject(List<Paralelka> filtredParalelBySubject) {
        this.filtredParalelBySubject = filtredParalelBySubject;
    }
    List<Paralelka> filtredParalelBySubject;
    
    public List<Paralelka> getKantorParalel() {
        return kantorParalel;
    }
    
    public void setKantorParalel(List<Paralelka> kantorParalel) {
        this.kantorParalel = kantorParalel;
    }
    
    public List<Zadani> getKantorsTask() {
        return kantorsTask;
    }
    
    public void setKantorsTask(List<Zadani> kantorsTask) {
        this.kantorsTask = kantorsTask;
    }
    
    public Zadani[] getTaskChosen() {
        return taskChosen;
    }
    
    public void setTaskChosen(Zadani[] taskChosen) {
        this.taskChosen = taskChosen;
    }
    
    public List<Ukol> getFinalTaskList() {
        return finalTaskList;
    }
    
    public void setFinalTaskList(List<Ukol> finalTaskList) {
        this.finalTaskList = finalTaskList;
    }
    List<Ukol> finalTaskList;
    List<Zadani> kantorsTask;
    Zadani[] taskChosen;
    //    @EJB
    @Inject
    ApplicationLocal app;
    UkolB taskB;
    
    public void init() {
        displayTaskList=true;
        userB = (UzivatelB) UzivatelB.getInstance(app);
        subjectB = (PredmetB) PredmetB.getInstance(app);
        taskB = (UkolB) UkolB.getInstance(app);
        loggedKantor = (Kantor) userB.findUserByLogin(getPrincipal().getName());
        kantorParalel = loggedKantor.getParalelkas();
        kantorsTask = loggedKantor.getM_Zadani();
        studentsChosen = userB.getStudenstByKantor(loggedKantor);
        filtredParalelBySubject = loggedKantor.getParalelkas();
        kantorSubjects = subjectB.showSubjectsByTeacher(loggedKantor);   
        vybraneTasky = createFilterOptions();
    }

    /**
     * Metoda vyfiltruje paralelky a studenty na aktualne zvolenem predmetu ci skupine predmetu
     * metoda zajisti odstraneni zobrazeni duplicitnich zazanmu
     */
    public void filterBySubject() {
        List<Paralelka> filteredParalel = new LinkedList<Paralelka>();
        List<Paralelka> allFilteredParalel = new LinkedList<Paralelka>();
        for (int j = 0; j < kantrosSubjectToFilter.length; j++) {
            allFilteredParalel.addAll(kantrosSubjectToFilter[j].getM_paralelka());
        }
        Iterator allFilteredParalelIt = allFilteredParalel.iterator();
        while (allFilteredParalelIt.hasNext()) {
            Paralelka currPara = (Paralelka) allFilteredParalelIt.next();
            if (kantorParalel.contains(currPara)) {
                filteredParalel.add(currPara);
            }
        }
        
        paralelChosen = new Paralelka[filteredParalel.size()];
        for (int j = 0; j < paralelChosen.length; j++) {
            paralelChosen[j] = filteredParalel.get(j);
        }
        setFiltredParalelBySubject(filteredParalel);
        filterByParalel();
    }
    public void filterByStudent(){
        studentsChosen=Arrays.asList(currChosen);
    }

    /**
     * Metoda vyfiltruje studenty na prave zvolene paralelce ci skupine paralelek,
     * metoda zajisti odstraneni zobrazeni duplicitnich zazanum.
     */
    public void filterByParalel() {
        List<Student> studentsInAllFiltredPara = new LinkedList<Student>();
        List<Student> filteredStudenstInPara = new LinkedList<Student>();
        for (int j = 0; j < paralelChosen.length; j++) {
            studentsInAllFiltredPara.addAll(paralelChosen[j].getM_student());
        }
        Iterator studentsInFiltredParaIt = studentsInAllFiltredPara.iterator();
        while (studentsInFiltredParaIt.hasNext()) {
            Student currStudent = (Student) studentsInFiltredParaIt.next();
            if (!filteredStudenstInPara.contains(currStudent)) {
                filteredStudenstInPara.add(currStudent);
            }
        }
        setStudentsChosen(filteredStudenstInPara);
    }

    /**
     * Metoda priradi ukol, ci ukoly skupine studentu ci samotnemu studentovi.
     */
    public String assigenTask() {
        List<Ukol> finalTaskListTemp = new LinkedList<Ukol>();
        for (int j = 0; j < taskChosen.length; j++) {            
            Ukol currTask = new Ukol();
            currTask.setZadani(taskChosen[j]);
            currTask.setDeadline(taskChosen[j].getDeadline());
            currTask.setNazev(taskChosen[j].getNazev());
            currTask.setPopis(taskChosen[j].getPopis());
            currTask.setStav(Ukol.Stav.PRIJATY);
            finalTaskListTemp.add(currTask);
        }
        setFinalTaskList(finalTaskListTemp);        
        return "assigntaskconfirm";
    }

    /**
     * Metoda pozada bussiness vrstu o vytvoreni ukolu pro aktualne vybrane studenty,
     * pro aktualne vybrany seznam ukolu a pro ucitele, ktery tento ukol zadal.
     */
    public void confirmTask() {
       if(studentsChosen.isEmpty()){
           FacesContext.getCurrentInstance().addMessage(null,
                   new FacesMessage(FacesMessage.SEVERITY_WARN,"Assigned task output", "No student selected!"));  
       }
       else if(finalTaskList.isEmpty()){
           FacesContext.getCurrentInstance().addMessage(null,
                   new FacesMessage(FacesMessage.SEVERITY_WARN,"Assigned task output", "No task selected!"));  
       }
       else{
                 Ukol returnedTask=taskB.zadejUkol(studentsChosen, finalTaskList, loggedKantor);    
                  if(returnedTask!=null){
                       FacesContext.getCurrentInstance().addMessage(null,
                   new FacesMessage(FacesMessage.SEVERITY_FATAL,"Assigned task output: ", "Task "+returnedTask.getNazev()+""
                               + " was not assigned. Other task up to this was assigned"
                               + ""));  
                  }
                  else{
                        FacesContext.getCurrentInstance().addMessage(null,
                   new FacesMessage(FacesMessage.SEVERITY_INFO,"Assigned task output: ", "Task were "
                           + "sucessfully assigned!"));  
                  }
       }
      
  
        
           
       
        //return "ok";
    }

    public String loadClass() {
            init();
        return "assigntaskKantor";
    }

    public String loadToRate() {
        init();
        displayTaskList=false;
        //studentsChosen.get(1).getM_Ukol().get(1).
        return "assignedtorate";
    }

    public void tasksByStudents() {
      
        List<Ukol> finalTaskListTemp = new LinkedList<Ukol>();
        for (int j = 0; j < currChosen.length; j++) {
            
            List<Ukol> studentsTask = currChosen[j].getM_Ukol();
            Iterator studentsTaskIt = studentsTask.iterator();
            while (studentsTaskIt.hasNext()) {
                Ukol currTaskStudent = (Ukol) studentsTaskIt.next();
                if (currTaskStudent.getZadani().getKantor() == loggedKantor) {
                    finalTaskListTemp.add(currTaskStudent);
                }
            }
            //  finalTaskListTemp.add(currTask);
        }
       /*
         * 
         *   List<Ukol> finalTaskListTemp = new LinkedList<Ukol>();
        for (int j = 0; j < currChosen.length; j++){
            finalTaskListTemp.addAll(taskB.findAllTaskByStudent(currChosen[j]));
        }
         */
        
        setFinalTaskList(finalTaskListTemp);
        displayTaskList=true;
          
    }

    /**
     * Metoda slouzi k vraceni aktualne prihlaseneho uzivatele (Principala), JSF
     * JAAS framework
     * @return aktualne prihlaseneho Principala
     */
    private Principal getPrincipal() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
    }
    public String rateTask(){
       
        taskB.ohodnotUkol(taskChosenToRate);
         if(taskChosenToRate.getHodnoceniSlovy()==null){
               FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Evaluation task: ", "Task was evaluate, "
                                + "but without comment."));
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Evaluation task: ", "Task was sucessfully evaluate,"));
        }
        return "assignedtoratesum";
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

    public SelectItem[] getVybraneTasky() {
        return vybraneTasky;
    }

    public void setVybraneTasky(SelectItem[] vybraneTasky) {
        this.vybraneTasky = vybraneTasky;
    }
      
      
      
          SelectItem[] vybraneTasky; 
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
       public StreamedContent downloadZadani() {
        File f = taskChosenToRate.getZadani().getZadaniFile();
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
