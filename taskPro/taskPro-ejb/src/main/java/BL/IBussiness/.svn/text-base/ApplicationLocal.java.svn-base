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
import javax.ejb.Local;

/**
 * Interface k fasade, ktera zajistuje pristup do datove vrstvy.
 * @author Tom
 * @author papa
 */
@Local
public interface ApplicationLocal {
    /**
     * Metoda vrati instanci tridy, ktera zprostredkuje pristup
     * do datove vrstvy do tridy IUzivatelD
     * @return instance tridy UzivatelD
     */
    public IUzivatelD getIUzivatelD();
    /**
     * Metoda vrati instanci tridy, ktera zprostredkuje pristup
     * do datove vrstvy do tridy IUkolD
     * @return instance tridy IUkolD
     */
    public IUkolD getIUkolD();
    /**
     * Metoda vrati instanci tridy, ktera zprostredkuje pristup
     * do datove vrstvy do tridy IZadaniD
     * @return instance tridy IZadaniD
     */
    public IZadaniD getIZadaniD();
        /**
     * Metoda vrati instanci tridy, ktera zprostredkuje pristup
     * do datove vrstvy do tridy IPredmetD
     * @return instance tridy IpredmetD
     */
    public IPredmetD getIPredmetD();
        /**
     * Metoda vrati instanci tridy, ktera zprostredkuje pristup
     * do datove vrstvy do tridy ISkupinaD
     * @return instance tridy ISkupinaD
     */
    public ISkupinaD getISkupinaD();
}
