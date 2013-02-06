/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Support;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Trida podporuje chovani aplikace a slouzi jako support pro jednotlive ulohy
 * @author Tom
 */
public class ConvertB implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8035361408408299939L;

	/**
     * Metoda prevede zadany string na datum.
     * @param datumS 
     * @return Date
     */
    public Date convertStringToDate(String datumS) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date d = null;
        try {
            d = df.parse(datumS);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public File inputStreamToFile(InputStream input, String fileName) {
        File f = new File(fileName);
        byte[] bytes = new byte[1024];
        try {
           FileOutputStream out = new FileOutputStream(f);
            int len;
            try {
                while ((len = input.read(bytes)) > 0) {
                    out.write(bytes, 0, len);
                   
                }
                 out.close();
                    input.close();
                    
            } catch (IOException ex) {
                Logger.getLogger(ConvertB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConvertB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }
}
