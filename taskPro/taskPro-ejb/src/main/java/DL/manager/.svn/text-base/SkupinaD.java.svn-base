/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DL.manager;

import DL.Imanager.ISkupinaD;
import DL.entity.Skupina;
import javax.ejb.Stateless;

/**
 *
 * @author papa
 */
/**
 * manager pro praci s skupinami v datove vrstve
 * @author papa
 */
@Stateless
public class SkupinaD extends ObjectManager implements ISkupinaD {

    /**
     * prida novou skupinu
     * @param s - nova skupina
     */
    @Override
    public void addSkupina(Skupina s) {
        super.add(s);
    }

    /**
     * odstrani skupinu
     * @param s - skupina k odstraneni
     */
    @Override
    public void removeSkupina(Skupina s) {
        super.remove(s);
    }
}
