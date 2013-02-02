package DL.Imanager;

import DL.entity.Kantor;
import DL.entity.Zadani;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 * interface pro kantory, kteri chteji vytvorit zadani
 * @author papa
 * @version 1.0
 * @created 11-X-2011 18:14:32
 */
/**
 * lokalni rozhrani v datove vrstve pro zadani
 * @author papa
 */
@Local
public interface IZadaniD extends Serializable {

    /**
     * upravi zmenene zadani do db
     * @param zadani
     */
    public void updateZadani(Zadani zadani);

    /**
     * ulozi nove zadani do db
     * @param z 
     */
    public void addZadani(Zadani z);

    /**
     * vrati zadani podle jejiho nazvu
     * @param nazev
     * @return 
     */
    public Zadani getZadani(String nazev);
}