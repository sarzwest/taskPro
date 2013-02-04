package DL.entity;

import BL.Support.MaintanceB;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author papa
 * @version 1.0
 * @created 11-X-2011 18:14:33
 */
/**
 * zadani, ktere kantor vytvoril studentum/skupinam studentu, studenti/skupiny pak maji vlastni ukoly, na
 * kterych pracuji
 * @author papa
 */
@Entity
@Table(name = "zadani")
@NamedQueries({
    @NamedQuery(name = "Zadani.findByNazev", query = "SELECT z FROM Zadani z WHERE z.nazev = :nazev")
})
public class Zadani implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "nazev")
    private String nazev;
    @Column(name = "deadline")
    @Temporal(TemporalType.DATE)
    private Date deadline;
    @Column(name = "datumZadani")
    @Temporal(TemporalType.DATE)
    private Date datumZadani;
    @Column(name = "popis")
    private String popis;
    @Column(name = "zadaniFile")
    private File zadaniFile;
    @OneToMany(mappedBy = "zadani", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Ukol> m_Ukol;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kantor kantor;
    @ManyToMany(mappedBy = "m_zadani")
    private List<Paralelka> m_paralelka;

    public Zadani() {
        datumZadani = new MaintanceB().getTodayDate();
    }

    public void setKantor(Kantor kantor) {
        this.kantor = kantor;
    }

    public void setM_Ukol(List<Ukol> m_Ukol) {
        this.m_Ukol = m_Ukol;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getDatumZadani() {
        return datumZadani;
    }

    public Date getDeadline() {
        return deadline;
    }

    public int getId() {
        return id;
    }

    public void setM_paralelka(List<Paralelka> m_paralelka) {
        this.m_paralelka = m_paralelka;
    }

    public Kantor getKantor() {
        return kantor;
    }

    public List<Ukol> getM_Ukol() {
        return m_Ukol;
    }

    /**
     * prida novy ukol k zadani
     * @param u - novy ukol
     */
    public void addUkol(Ukol u) {
        if (m_Ukol == null) {
            m_Ukol = new ArrayList<Ukol>();
        }
        m_Ukol.add(u);
    }

    /**
     * prida paralelku k zadani
     * @param p - paralelka
     */
    public void addParalelka(Paralelka p) {
        if (this.m_paralelka == null) {
            m_paralelka = new ArrayList<Paralelka>();
        }
        m_paralelka.add(p);
    }

    public List<Paralelka> getM_paralelka() {
        return m_paralelka;
    }

    public String getNazev() {
        return nazev;
    }

    public String getPopis() {
        return popis;
    }

    public File getZadaniFile() {
        return zadaniFile;
    }
    
    public void setNazev(String newVal) {
        nazev = newVal;
    }
    
    public void setPopis(String newVal) {
        popis = newVal;
    }
    
    public void setZadaniFile(File newVal) {
        zadaniFile = newVal;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zadani)) {
            return false;
        }
        Zadani other = (Zadani) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public String toString() {
        return "DL.entity.Zadani[ id=" + id + " nazev=" + nazev + " ]";
    }
}