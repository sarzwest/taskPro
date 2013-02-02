package DL.Imanager;

import DL.entity.Kantor;
import DL.entity.Student;
import DL.entity.Uzivatel;
import DL.entity.Zadani;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 * interface pro zmenu informaci v profilu studenta
 * @author papa
 * @version 1.0
 * @created 11-X-2011 18:14:32
 */
/**
 * lokalni rozhrani v datove vrstve pro uzivatele
 * @author papa
 */
@Local
public interface IUzivatelD extends Serializable {

    /**
     * vrati vsechny kantory, kteri vytvorily zadani
     * @param zadani
     * @return 
     */
    public List<Kantor> getKantor(Zadani zadani);

    /**
     * ulozi uzivatele do db
     * @param u 
     */
    public void addUzivatel(Uzivatel u);

    public void removeUzivatel(Uzivatel u);

    public Uzivatel findByLogin(String login);

    public void updateUzivatel(Uzivatel u);

    public List<Uzivatel> getAllUzivatel();

    public List<Student> getAllStudent();

    public List<Kantor> getAllKantor();
    
    public List<Student> getAllStudentByKantor(Kantor k);
}