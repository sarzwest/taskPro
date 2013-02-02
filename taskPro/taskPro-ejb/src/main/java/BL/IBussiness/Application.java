/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.IBussiness;

import DL.Imanager.IPredmetD;
import DL.Imanager.ISkupinaD;
import DL.Imanager.IUkolD;
import DL.Imanager.IUzivatelD;
import DL.Imanager.IZadaniD;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Fascada k pristupu do datove vrstvy, vyuziva dependency injection.
 * @author papa
 * @author Tom
 */
@Stateless
public class Application implements ApplicationLocal {
    
    @Inject
    private IUzivatelD uzivD;
    @Inject
    private IUkolD ukolD;
    @Inject
    private IZadaniD zadaD;
    @Inject
    private IPredmetD predD;
    @Inject
    private ISkupinaD skupD;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public IUzivatelD getIUzivatelD() {
        return uzivD;
    }

    @Override
    public IUkolD getIUkolD() {
        return ukolD;
    }

    @Override
    public IZadaniD getIZadaniD() {
        return zadaD;
    }

    @Override
    public IPredmetD getIPredmetD() {
        return predD;
    }

    @Override
    public ISkupinaD getISkupinaD() {
        return skupD;
    }
    
}