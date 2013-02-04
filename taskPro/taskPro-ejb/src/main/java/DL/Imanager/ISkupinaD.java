/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DL.Imanager;

/**
 *
 * @author papa
 */
import DL.entity.Skupina;
import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author papa
 */
/**
 * lokalni rozhrani v datove vrstve pro skupiny v paralelkach
 * @author papa
 */
@Local
public interface ISkupinaD extends Serializable {
    
    public void addSkupina(Skupina s);
    
    public void removeSkupina(Skupina s);
}
