package DL.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author papa
 * @version 1.0
 * @created 11-X-2011 18:14:32
 */
/**
 * trida reprezentujici kantora(ucitele)
 * @author papa
 */
@Entity
@Table(name = "kantor")
@DiscriminatorValue(value = "kantor")
@PrimaryKeyJoinColumn(name = "id")
@NamedQueries({
    @NamedQuery(name = "Kantor.findByZadaniId", query = "SELECT k FROM Kantor k INNER JOIN k.m_Zadani zadani WHERE zadani.id = :id"),
    @NamedQuery(name = "Kantor.all", query = "SELECT k FROM Kantor k")
})
public class Kantor extends Uzivatel implements Serializable {

    private static final long serialVersionUID = 1L;
    @OneToMany(mappedBy = "kantor", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Zadani> m_Zadani;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "kantors", cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Paralelka> paralelkas;

    public Kantor() {
    }

    public List<Paralelka> getParalelkas() {
        return paralelkas;
    }

    public void setParalelkas(List<Paralelka> paralelkas) {
        this.paralelkas = paralelkas;
    }

    /**
     * prida paralelku kantorovi
     * @param p 
     */
    public void addParalelka(Paralelka p) {
        if (this.paralelkas == null) {
            paralelkas = new ArrayList<Paralelka>();
        }
        paralelkas.add(p);
        p.addKantor(this);//asi
    }

    public List<Paralelka> getM_paralelka() {
        return paralelkas;
    }

    /**
     * prida dalsi zadani od kantora
     * @param z 
     */
    public void addZadani(Zadani z) {
        if (m_Zadani == null) {
            m_Zadani = new ArrayList<Zadani>();
        }
        m_Zadani.add(z);
    }

    public void setM_Zadani(List<Zadani> m_Zadani) {
        this.m_Zadani = m_Zadani;
    }

    public List<Zadani> getM_Zadani() {
        return m_Zadani;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kantor)) {
            return false;
        }
        Kantor other = (Kantor) object;
        if (getId() != other.getId()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getId();
        return hash;
    }

    @Override
    public String toString() {
        return "DL.entity.Kantor[ id=" + getId() + " login=" + getLogin() + " ]";
    }
}