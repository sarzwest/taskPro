package userBean;

import BL.IBussiness.ApplicationLocal;
import BL.IBussiness.IUzivatelB;
import BL.Uzivatel.UzivatelB;
import DL.entity.Uzivatel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimeType;
import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * Trida se zameruje na nacteni souboru a predani je do business logiky.
 */
@ManagedBean(name = "fileBean")
public class FileBean {

    /**instance v bussiness vrstve starajici se o uzivatele*/
    IUzivatelB uzivatelB;
    @EJB
    ApplicationLocal app;

    @PostConstruct
    public void init() {
        uzivatelB = UzivatelB.getInstance(app);
    }
    UploadedFile img;

    /**
     * Metoda nacte aktualni soubor a odesle ho do business logiky ke zpracovani.
     */
    public void handleFileUpload(FileUploadEvent event) {
        try {

            img = event.getFile();
            InputStream stream;
            
                stream = event.getFile().getInputstream();
           MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

            File users = inputStreamToFile(stream, event.getFile().getFileName());
            String mimeType=mimeTypesMap.getContentType("neco.csv");
            String mime2=mimeTypesMap.getContentType("neco.xsl");
            String fileName=users.getName();
          
            String result = null;
            try {
             if(fileName.endsWith("csv")){
             result = uzivatelB.addUzivatel(img.getInputstream());
           }
           else if(fileName.endsWith("xls")){
          uzivatelB.readExcel(users);
           }
                  
                
                if (result != null) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_FATAL, "AddUser: ", "Users from was not added"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "AddUser: ", "All users was added!"));
                }

            } 
                catch (IOException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "AddUser: ", "File not found."));
                Logger.getLogger(FileBean.class.getName()).log(Level.SEVERE, null, ex);
            }




        } catch (IOException ex) {
            Logger.getLogger(FileBean.class.getName()).log(Level.SEVERE, null, ex);
        }




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
            }
        } catch (FileNotFoundException ex) {
        }
        return f;
    }
}