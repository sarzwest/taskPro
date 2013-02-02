/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DL.manager;

import DL.Imanager.IPredmetD;
import DL.entity.Kantor;
import DL.entity.Paralelka;
import DL.entity.Predmet;
import DL.entity.Skupina;
import DL.entity.Student;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author papa
 */
/**
 * manager pro praci s predmety v datove vrstve
 * @author papa
 */
@Stateless
public class PredmetD extends ObjectManager implements IPredmetD {

    /**
     * prida predmet
     * @param p - novy predmet
     */
    @Override
    public void addPredmet(Predmet p) {
        super.add(p);
    }

    /**
     * prida paralelku do predmetu
     * @param p - nova paralelka
     */
    @Override
    public void addParalelka(Paralelka p) {
        List<Kantor> m_kantor = p.getM_kantor();
//        for (Kantor kantor : m_kantor) {
//            kantor.addParalelka(p);
//        }
        Predmet pr = p.getPredmet();
        pr.addParalelka(p);
        super.add(p);
    }

    /**
     * najde predmet dle jeho kodu
     * @param kod - kod predmetu
     * @return predmet se zadanym kodem
     */
    @Override
    public Predmet findPredmetByKod(String kod) {
        Query q = em.createNamedQuery("Predmet.findByKod");
        q.setParameter("kod", kod);
        Predmet p = (Predmet) q.getSingleResult();
        log.log(Level.INFO, p.toString());
        return p;
    }

    /**
     * vrati vsechny predmety
     * @return - vsechny predmety
     */
    @Override
    public List<Predmet> getAllPredmet() {
        Query q = em.createNamedQuery("Predmet.all");
        List<Predmet> lp = q.getResultList();
        for (Predmet predmet : lp) {
            List<Paralelka> m_paralelka = predmet.getM_paralelka();
            for (Paralelka paralelka : m_paralelka) {
                paralelka = (Paralelka) refresh(paralelka);
                List<Kantor> m_kantor = paralelka.getM_kantor();
                for (Kantor kantor : m_kantor) {
                    kantor = (Kantor) refresh(kantor);
                }
                List<Skupina> m_skupina = paralelka.getM_skupina();
                for (Skupina skupina : m_skupina) {
                    skupina = (Skupina) refresh(skupina);
                }
                List<Student> m_student = paralelka.getM_student();
                for (Student student : m_student) {
                    student = (Student) refresh(student);
                }
            }
        }
        log.log(Level.INFO, lp.toString());
        return lp;
    }

    /**
     * upraveny predmet ulozi do db
     * @param p - upraveny predmet
     */
    @Override
    public void updatePredmet(Predmet p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
