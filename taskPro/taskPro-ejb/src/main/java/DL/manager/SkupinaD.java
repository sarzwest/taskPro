/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DL.manager;

import DL.Imanager.ISkupinaD;
import DL.entity.Skupina;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

/**
 *
 * @author papa
 */
/**
 * manager pro praci s skupinami v datove vrstve
 * @author papa
 */
@Stateless
@DeclareRoles({"admin", "kantor", "student"})
@SecurityDomain("moje-domena")
public class SkupinaD extends ObjectManager implements ISkupinaD {

    /**
     * prida novou skupinu
     * @param s - nova skupina
     */
    @Override
    @RolesAllowed({"admin","kantor"})
    public void addSkupina(Skupina s) {
        super.add(s);
    }

    /**
     * odstrani skupinu
     * @param s - skupina k odstraneni
     */
    @Override
    @RolesAllowed({"admin","kantor"})
    public void removeSkupina(Skupina s) {
        super.remove(s);
    }
}
