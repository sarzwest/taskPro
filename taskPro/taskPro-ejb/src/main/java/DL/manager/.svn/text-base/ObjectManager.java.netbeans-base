/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DL.manager;

import DL.Imanager.IObjectManager;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author papa
 */
/**
 * implementace IObjectManager pro praci s databazi na nejuniverzalnejsi urovni
 * @author papa
 */
public class ObjectManager implements IObjectManager {

    @PersistenceContext(unitName = "SZU-ejbPU")
    protected EntityManager em;
    protected Logger log;

    public ObjectManager() {
        log = Logger.getLogger("Persistence");
        Handler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        log.addHandler(handler);
    }

    /**
     * ulozi objekt do db
     * @param o 
     */
    @Override
    public void add(Object o) {
        o = em.merge(o);
        em.persist(o);
        log.log(Level.INFO, o.toString());
    }

    /**
     * odstrani objekt z db
     * @param o 
     */
    @Override
    public void remove(Object o) {
        o = em.merge(o);
        em.remove(o);
        log.log(Level.INFO, o.toString());
    }

    /**
     * vrati objekt z db
     * @param c
     * @param id
     * @return 
     */
    @Override
    public Object get(Class c, int id) {
        Object o = em.find(c, id);
        log.log(Level.INFO, o.toString());
        return o;
    }
    
    /**
     * vrati objekt z db
     * @param c
     * @param id
     * @return 
     */
    public Object get(Class c, long id) {
        Object o = em.find(c, id);
        log.log(Level.INFO, o.toString());
        return o;
    }

    /**
     * vlozi upraveny objekt z db
     * @param o 
     */
    @Override
    public void update(Object o) {
        o = em.merge(o);
        em.persist(o);
        log.log(Level.INFO, o.toString());
    }

    @Override
    public Object refresh(Object o) {
        o = em.merge(o);
        em.refresh(o);
        return o;
    }
}
