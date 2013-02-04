package DL.manager;

import DL.Imanager.IUkolD;
import DL.entity.Kantor;
import DL.entity.Student;
import DL.entity.Ukol;
import DL.entity.Ukol.Stav;
import DL.entity.UkolSoubor;
import DL.entity.Zadani;
import java.io.File;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * @author papa
 * @version 1.0
 * @created 11-X-2011 18:14:32
 */
/**
 * manager pro praci s ukoly v datove vrstve
 * @author papa
 */
@Stateless
public class UkolD extends ObjectManager implements IUkolD {

    public UkolD() {
    }

    /**
     * upraveny ukol ulozi do db
     * @param ukol - upraveny ukol
     */
    @Override
    public void updateUkol(Ukol ukol) {
        List<UkolSoubor> m_ukolSoubor = ukol.getM_ukolSoubor();
        for (UkolSoubor soubor : m_ukolSoubor) {
            soubor.setUkol(ukol);
        }
        update(ukol);
    }

    /**
     * prida ukol do db
     * @param u - novy ukol
     */
    @Override
    public void addUkol(Ukol u) {
        Student s = u.getStudent();
        if(s != null){
            s.addUkol(u);
        }
        super.add(u);
    }

    @Override
    public List<Ukol> findByKantor(Kantor k) {
        Query q = em.createNamedQuery("Ukol.findByKantor");
        q.setParameter("kantor", k);
        List<Ukol> lu = q.getResultList();
        return lu;
    }

    @Override
    public List<Ukol> findByStudent(Student s) {
        Query q = em.createNamedQuery("Ukol.findByStudent");
        q.setParameter("student", s);
        List<Ukol> lu = q.getResultList();
        return lu;
    }

    @Override
    public List<Ukol> findByStav(Stav stav) {
        Query q = em.createNamedQuery("Ukol.findByStav");
        q.setParameter("stav", stav);
        List<Ukol> lu = q.getResultList();
        return lu;
    }

    @Override
    public List<Ukol> findByStavAndStudent(Stav stav, Student student) {
        Query q = em.createNamedQuery("Ukol.findByStavAndStudent");
        q.setParameter("stav", stav);
        q.setParameter("student", student);
        List<Ukol> lu = q.getResultList();
        return lu;
    }

    @Override
    public List<Ukol> findByStavAndKantor(Stav stav, Kantor kantor) {
        Query q = em.createNamedQuery("Ukol.findByStavAndKantor");
        q.setParameter("stav", stav);
        q.setParameter("kantor", kantor);
        List<Ukol> lu = q.getResultList();
        return lu;
    }

    @Override
    public UkolSoubor nahrajSoubor(UkolSoubor soubor) {
        soubor = (UkolSoubor) get(UkolSoubor.class, soubor.getId());
        File file = soubor.getFile();
        return soubor;
    }
}