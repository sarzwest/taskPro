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
        for (int i = 0; i < lp.size(); i++) {
        	Predmet predmet = lp.get(i);
//        for (Predmet predmet : lp) {
            List<Paralelka> m_paralelka = predmet.getM_paralelka();
            for (int j = 0; j < m_paralelka.size(); j++) {
            	Paralelka paralelka = m_paralelka.get(j);
//            for (Paralelka paralelka : m_paralelka) {
                paralelka = (Paralelka) refresh(paralelka);
                List<Kantor> m_kantor = paralelka.getM_kantor();
                for (int k = 0; k < m_kantor.size(); k++) {
                	Kantor kantor = m_kantor.get(k);
//                for (Kantor kantor : m_kantor) {
                    kantor = (Kantor) refresh(kantor);
                }
                List<Skupina> m_skupina = paralelka.getM_skupina();
                for (int k = 0; k < m_skupina.size(); k++) {
                	Skupina skupina = m_skupina.get(k);
//                for (Skupina skupina : m_skupina) {
                    skupina = (Skupina) refresh(skupina);
                }
                List<Student> m_student = paralelka.getM_student();
                for (int k = 0; k < m_student.size(); k++) {
                	Student student = m_student.get(k);
//                for (Student student : m_student) {
                    student = (Student) refresh(student);
                }
            }
        }
        log.log(Level.INFO, lp.toString());
        return lp;
//        Query q = em.createNamedQuery("Predmet.all");
//        List<Predmet> lp = q.getResultList();
//        for (Predmet predmet : lp) {
//            List<Paralelka> m_paralelka = predmet.getM_paralelka();
//            for (Paralelka paralelka : m_paralelka) {
//                paralelka = (Paralelka) refresh(paralelka);
//                List<Kantor> m_kantor = paralelka.getM_kantor();
//                for (Kantor kantor : m_kantor) {
//                    kantor = (Kantor) refresh(kantor);
//                }
//                List<Skupina> m_skupina = paralelka.getM_skupina();
//                for (Skupina skupina : m_skupina) {
//                    skupina = (Skupina) refresh(skupina);
//                }
//                List<Student> m_student = paralelka.getM_student();
//                for (Student student : m_student) {
//                    student = (Student) refresh(student);
//                }
//            }
//        }
//        log.log(Level.INFO, lp.toString());
//        return lp;
    }

    /**
     * upraveny predmet ulozi do db
     * @param p - upraveny predmet
     */
    @Override
    public void updatePredmet(Predmet p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	@Override
	public void printParalelkaByKantor() {
		Query q = em.createNamedQuery("Student.findByKantorId");
		Kantor k = new Kantor();
		k.setId(801);
        q.setParameter("id", 801);
         List l = q.getResultList();
        System.out.println(l);
	}
}
