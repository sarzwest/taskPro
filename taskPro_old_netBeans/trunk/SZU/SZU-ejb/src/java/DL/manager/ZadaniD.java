package DL.manager;

import DL.Imanager.IZadaniD;
import DL.entity.Kantor;
import DL.entity.Zadani;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * @author papa
 * @version 1.0
 * @created 11-X-2011 18:14:33
 */
/**
 * manager pro praci s zadanimi v datove vrstve
 * @author papa
 */
@Stateless
public class ZadaniD extends ObjectManager implements IZadaniD {

    public ZadaniD() {
    }

    /**
     * upravi zadani do db
     * @param zadani - upravene zadani
     */
    @Override
    public void updateZadani(Zadani zadani) {
        super.update(zadani);
    }

    /**
     * prida zadani do db
     * @param z - nove zadani
     */
    @Override
    public void addZadani(Zadani z) {
        Kantor k = z.getKantor();
        k.addZadani(z);
        super.add(z);
    }

    /**
     * vrati zadani s danym nazvem
     * @param nazev - nazev zadani
     * @return zadani s danym nazvem
     */
    @Override
    public Zadani getZadani(String nazev) {
        Query q = em.createNamedQuery("Zadani.findByNazev");
        q.setParameter("nazev", nazev);
        Zadani z = (Zadani) q.getSingleResult();
        System.err.println(z.toString());
        return z;
    }
}