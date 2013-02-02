/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DL.Imanager;

/**
 *
 * @author papa
 */
/**
 * interface pro praci s databazi
 * @author papa
 */
public interface IObjectManager {  

    public void add(Object o);

    public Object get(Class c, int id);

    public void remove(Object o);

    public void update(Object o);
    
    public Object refresh(Object o);
}
