/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Support;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Tom
 */
public class MaintanceB implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1972754582598776541L;

	/**
     * Metoda vrátí aktuální datum.
     * @return aktuální datum.
     */
public Date getTodayDate(){
     Date date = Calendar.getInstance().getTime();
     return date;
}
  
}
