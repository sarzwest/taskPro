package DL.Imanager;

import DL.entity.Kantor;
import DL.entity.Student;
import DL.entity.Ukol;
import DL.entity.UkolSoubor;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 * @author papa
 * @version 1.0
 * @created 11-X-2011 18:14:32
 */
/**
 * lokalni rozhrani v datove vrstve pro ukoly
 * @author papa
 */
@Local
public interface IUkolD extends Serializable {

    /**
     * upravi ukol do db
     * @param ukol - upravovany ukol
     */
    public void updateUkol(Ukol ukol);

    /**
     * prida ukol do db
     * @param u - novy ukol
     */
    public void addUkol(Ukol u);
    
    public List<Ukol> findByKantor(Kantor k);
    
    /**
     * vrati ukoly, na kterych pracuje student sam
     * NE VE SKUPINE!!!!
     * @param s
     * @return 
     */
    public List<Ukol> findByStudent(Student s);
    
    public List<Ukol> findByStav(Ukol.Stav stav);
    
    public List<Ukol> findByStavAndStudent(Ukol.Stav stav, Student student);
    
    public List<Ukol> findByStavAndKantor(Ukol.Stav stav, Kantor kantor);
    
    public UkolSoubor nahrajSoubor(UkolSoubor soubor);
}