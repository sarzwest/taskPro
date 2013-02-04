/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DL.Imanager;

import DL.entity.Paralelka;
import DL.entity.Predmet;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author papa
 */
/**
 * lokalni rozhrani v datove vrstve pro predmety
 * @author papa
 */
@Local
public interface IPredmetD extends Serializable {

    public void addPredmet(Predmet p);

    public void addParalelka(Paralelka p);

    public Predmet findPredmetByKod(String kod);

    public List<Predmet> getAllPredmet();

    public void updatePredmet(Predmet p);
}
