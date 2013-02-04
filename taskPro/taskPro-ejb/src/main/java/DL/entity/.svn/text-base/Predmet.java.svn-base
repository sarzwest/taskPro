/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DL.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author papa
 */
/**
 * trida reprezentujici predmet
 * @author papa
 */
@Entity
@Table(name = "predmet")
@NamedQueries({
    @NamedQuery(name = "Predmet.findByKod", query = "SELECT p FROM Predmet p WHERE p.kod = :kod"),
    @NamedQuery(name = "Predmet.all", query = "SELECT p FROM Predmet p")
})
public class Predmet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "kod", nullable = false, unique = true)
    private String kod;
    @Column(name = "popisek")
    private String popisek;
    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Paralelka> m_paralelka;

    public void setKod(String kod) {
        this.kod = kod;
    }

    public void setM_paralelka(List<Paralelka> m_paralelka) {
        this.m_paralelka = m_paralelka;
    }

    public void setPopisek(String popisek) {
        this.popisek = popisek;
    }

    public String getKod() {
        return kod;
    }

    /**
     * prida novou paralelku
     * @param p - nova paralelka
     */
    public void addParalelka(Paralelka p) {
        if (this.m_paralelka == null) {
            m_paralelka = new ArrayList<Paralelka>();
        }
        m_paralelka.add(p);
        p.setPredmet(this);
    }

    public List<Paralelka> getM_paralelka() {
        return m_paralelka;
    }

    public String getPopisek() {
        return popisek;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Predmet)) {
            return false;
        }
        Predmet other = (Predmet) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DL.entity.Predmet[ id=" + id + " kod=" + kod + " ]";
    }
}
