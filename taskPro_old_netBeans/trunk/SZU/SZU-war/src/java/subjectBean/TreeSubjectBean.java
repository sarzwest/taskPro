/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subjectBean;

import BL.IBussiness.ApplicationLocal;
import BL.IBussiness.IPredmetB;
import BL.Predmet.PredmetB;
import DL.entity.Kantor;
import DL.entity.Paralelka;
import DL.entity.Predmet;
import DL.entity.Student;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Lurtz
 * Beana vloužící pro výpis dat z databáze do stromu.
 * Vytvoří strom s úrovněmi:
 *  - 1.úroveň - předmety
 *  - 2.úroveň - paralelky
 *  - 3.úroveň - kantoři a studenti
 *  - 4.úroveň - jmena kantorů nebo studentů
 */
@ManagedBean(name = "treeSubjectBean")
@RequestScoped
public class TreeSubjectBean implements Serializable {

    private TreeNode root;
    IPredmetB predmetB;
    @EJB
    ApplicationLocal app;

    @PostConstruct
    public void init() {
        predmetB = PredmetB.getInstance(app);
    }

    public TreeSubjectBean() {
    }

    
    /**
     * 
     * Funkce nejprv načte data z databáze a poté data přiřadí do jednotlivých úrovní.
     * @return String se jmenem své stránky.
     */
    public String loadTreeSubject() {
        root = new DefaultTreeNode("Root", null);
        List<Predmet> predmety = predmetB.showSubjects();
        List<Paralelka> paralelka;
        List<Student> studenti;
        List<Kantor> kantori;

        
        for (int i = 0; i < predmety.size(); i++) {
            final Predmet currentPredmet = predmety.get(i);
            TreeNode node0 = new DefaultTreeNode(currentPredmet.getKod(), root);
            paralelka = currentPredmet.getM_paralelka();
           
            for (int j = 0; j < paralelka.size(); j++) {
                TreeNode node00 = new DefaultTreeNode(paralelka.get(j).getKod(), node0);
                kantori = paralelka.get(j).getM_kantor();
                
                TreeNode node000 = new DefaultTreeNode("Kantori", node00);
                for (Iterator<Kantor> it = kantori.iterator(); it.hasNext();) {
                    Kantor kantor = it.next();
                    TreeNode node0000 = new DefaultTreeNode(kantor.getJmeno() + " " + kantor.getPrijmeni(), node000);
                }
               
                studenti = paralelka.get(j).getM_student();
                TreeNode node001 = new DefaultTreeNode("Studenti", node00);
                for (int k = 0; k < studenti.size(); k++) {
                    TreeNode node0001 = new DefaultTreeNode(studenti.get(k).getJmeno() + " " + studenti.get(k).getPrijmeni(), node001);
                }
            }

        }
        return "checkallsubject";
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
