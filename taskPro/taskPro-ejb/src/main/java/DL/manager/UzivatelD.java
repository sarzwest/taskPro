package DL.manager;

import DL.Imanager.IUzivatelD;
import DL.entity.Kantor;
import DL.entity.Student;
import DL.entity.Ukol;
import DL.entity.Uzivatel;
import DL.entity.Zadani;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.Clustered;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * @author papa
 * @version 1.0
 * @created 11-X-2011 18:14:33
 */
/**
 * manager pro praci s uzivateli v datove vrstve
 * @author papa
 */
@Stateless
@DeclareRoles({"admin", "kantor", "student"})
@SecurityDomain("moje-domena")
@Clustered
public class UzivatelD extends ObjectManager implements IUzivatelD, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1686190990717915105L;

	public UzivatelD() {
    }

    /**
     * vrati kantory, kteri vytvorily zadani
     * @param zadani - zadani asociovane s kantorem
     * @return list kantoru
     */
    @Override
    public List<Kantor> getKantor(Zadani zadani) {
        Query q = em.createNamedQuery("Kantor.findByZadaniId");
        q.setParameter("id", zadani.getId());
        List<Kantor> lk = (List<Kantor>) q.getResultList();
        log.log(Level.INFO, lk.toString());
        return lk;
    }

    /**
     * prida uzivatele do db
     * @param u 
     */
    @Override
    @RolesAllowed("admin")
    public void addUzivatel(Uzivatel u) {
        super.add(u);
    }

    /**
     * odstrani uzivatele z db
     * @param u 
     */
    @Override
    @RolesAllowed("admin")
    public void removeUzivatel(Uzivatel u) {
        remove(u);
    }

    /**
     * vyhleda uzivatele podle loginu
     * @param login - login uzivatele
     * @return vyhledany uzivatel
     */
    @Override
    public Uzivatel findByLogin(String login) {
        Query q = em.createNamedQuery("Uzivatel.findByLogin");
        q.setParameter("login", login);
        Uzivatel u = (Uzivatel) q.getSingleResult();
        log.log(Level.INFO, u.toString());
        return u;
    }

    /**
     * upravi uzivatele
     * @param u - upraveny uzivatel
     */
    @Override
    public void updateUzivatel(Uzivatel u) {
            update(u);
    }

    /**
     * vrati vsechny uzivatele
     * @return 
     */
    @Override
    public List<Uzivatel> getAllUzivatel() {
        Query q = em.createNamedQuery("Uzivatel.all");
        List<Uzivatel> ul = q.getResultList();
        log.log(Level.INFO, ul.toString());
        return ul;
    }

    /**
     * vrati vsechny studenty
     * @return 
     */
    @Override
    public List<Student> getAllStudent() {
        Query q = em.createNamedQuery("Student.all");
        List<Student> ls = q.getResultList();
        log.log(Level.INFO, ls.toString());
        return ls;
    }

    /**
     * vrati vsechny kantory
     * @return 
     */
    @Override
    public List<Kantor> getAllKantor() {
        Query q = em.createNamedQuery("Kantor.all");
        List<Kantor> lk = q.getResultList();
        log.log(Level.INFO, lk.toString());
        return lk;
    }

    @Override
    public List<Student> getAllStudentByKantor(Kantor k) {
        Query q = em.createNamedQuery("Student.findByKantorId");
        q.setParameter("id", k.getId());
        List<Student> resultList = q.getResultList();
        for (int i = 0; i < resultList.size(); i++) {
        	Student student = resultList.get(i);
//        for (Student student : resultList) {
            List<Ukol> m_Ukol = student.getM_Ukol();
            for (int j = 0; j < m_Ukol.size(); j++) {
            	Ukol ukol = m_Ukol.get(j);
//            for (Ukol ukol : m_Ukol) {
                ukol = (Ukol) refresh(ukol);
            }
        }
        log.log(Level.INFO, resultList.toString());
        return resultList;
    }
}