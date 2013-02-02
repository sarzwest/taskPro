package DL.entity;

import BL.Support.MaintanceB;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
 * @created 11-X-2011 18:14:32
 */
/**
 * ukoly, ktere studenti/skupiny studentu vypracovavaji
 * @author papa
 */
@Entity
@Table(name = "ukol")
@NamedQueries({
    @NamedQuery(name="Ukol.findByKantor", query="SELECT u FROM Ukol u, Zadani z WHERE u.zadani.kantor = :kantor"),
    @NamedQuery(name="Ukol.findByStudent", query="SELECT u FROM Ukol u WHERE u.student = :student"),
    @NamedQuery(name="Ukol.findByStav", query="SELECT u FROM Ukol u WHERE u.stav = :stav"),
    @NamedQuery(name="Ukol.findByStavAndStudent", query="SELECT u FROM Ukol u WHERE u.stav = :stav AND u.student = :student"),
    @NamedQuery(name="Ukol.findByStavAndKantor", query="SELECT u FROM Ukol u WHERE u.stav = :stav AND u.zadani.kantor = :kantor")
})
public class Ukol implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "datumZadani", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datumZadani;
    @Column(name = "deadline")
    @Temporal(TemporalType.DATE)
    private Date deadline;
    @Column(name = "datumOdev")
    @Temporal(TemporalType.DATE)
    private Date datumOdev;
    @Column(name = "popis")
    private String popis;
    @Column(name = "hodnoceni")
    private int hodnoceni;
    @Column(name = "hodnoceniSlovy")
    private String hodnoceniSlovy;
    @Column(name = "nazev")
    private String nazev;
    @Column(name = "stav")
    private Stav stav;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Zadani zadani;
    @ManyToOne(cascade= CascadeType.ALL)
    private Student student;
    @ManyToOne
    private Skupina skupina;
    @OneToMany(mappedBy = "ukol", cascade= CascadeType.ALL, fetch= FetchType.LAZY)
  //bi-directional many-to-one association to Ukolsoubor
//  	@OneToMany(mappedBy="ukol")
    private List<UkolSoubor> m_ukolSoubor;

    /**
     * v jakem stavu se nachazi ukol
     */
    public static enum Stav {

        /**
         * prijaty - kantor pridelil ukol studentovi
         */
        PRIJATY,
        /**
         * Ukol byl zaslan po deadlinu.
         */
        ZPOZDENY,
        /**
         * Ukol po hodnoceni v radnem terminu
         */
        OZNAMKOVANY,
        /**
         * nevyhovujici - kantor ho zkontroloval, ale studenti jej musi predelat
         */
        NEVYHOVUJICI,
        /**
         * Ukol byl zaslan ke kontrole v radnem terminu
         */
        ODEVZDANY,
        /**
         * Ukol je spravne vypracovan, ale byl odevzdan po deadlinu
         */
        OZNAMKOVANY_ZPOZDENY,
        
    };

    public void setZadani(Zadani zadani) {
        this.zadani = zadani;
    }

    public void setM_ukolSoubor(List<UkolSoubor> m_ukolSoubor) {
        this.m_ukolSoubor = m_ukolSoubor;
    }

    public List<UkolSoubor> getM_ukolSoubor() {
        return m_ukolSoubor;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public String getPopis() {
        return popis;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public Zadani getZadani() {
        return zadani;
    }

    public Ukol() {
        datumZadani = new MaintanceB().getTodayDate();
    }

    public void setSkupina(Skupina skupina) {
        this.skupina = skupina;
    }

    public Date getDatumZadani() {
        return datumZadani;
    }

    public Skupina getSkupina() {
        return skupina;
    }

    public Date getDatumOdev() {
        return datumOdev;
    }

    public int getHodnoceni() {
        return hodnoceni;
    }

    public String getHodnoceniSlovy() {
        return hodnoceniSlovy;
    }

    public String getNazev() {
        return nazev;
    }

    public Stav getStav() {
        return stav;
    }

    public void setDatumOdev(Date newVal) {
        datumOdev = newVal;
    }

    public void setHodnoceni(int newVal) {
        hodnoceni = newVal;
    }

    public void setHodnoceniSlovy(String newVal) {
        hodnoceniSlovy = newVal;
    }

    public void setNazev(String newVal) {
        nazev = newVal;
    }

    public void setStav(Stav newVal) {
        stav = newVal;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ukol)) {
            return false;
        }
        Ukol other = (Ukol) object;
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
        return "DL.entity.Ukol[ id=" + id + " nazev=" + nazev + " ]";
    }
}