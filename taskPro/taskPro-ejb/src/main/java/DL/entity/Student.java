package DL.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author papa
 * @version 1.0
 * @created 11-X-2011 18:14:32
 */
/**
 * trida reprezentujici studenta
 * @author papa
 */
@Entity
@Table(name = "student")
@DiscriminatorValue(value = "student")
@PrimaryKeyJoinColumn(name = "id")
@NamedQueries({
    //    @NamedQuery(name = "Uzivatel.findByLogin", query = "SELECT u FROM Uzivatel u WHERE u.login = :login"),
    @NamedQuery(name = "Student.all", query = "SELECT s FROM Student s"),
    @NamedQuery(name="Student.findByKantor", query="SELECT s FROM Student s JOIN s.paralelkas p WHERE p.kantors = :kantor")
})
public class Student extends Uzivatel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "hodnoceni")
    private int hodnoceni;
    @OneToMany(mappedBy = "student", cascade= CascadeType.ALL)
    private List<Ukol> m_Ukol;
    @ManyToOne
    private Skupina skupina;
    @ManyToMany(mappedBy = "m_student")
    @JoinColumn(name="paralelkas")
    private List<Paralelka> paralelkas;

    public Student() {
    }

    public List<Paralelka> getParalelkas() {
        return paralelkas;
    }

    public void setParalelkas(List<Paralelka> paralelkas) {
        this.paralelkas = paralelkas;
    }

    public void setM_paralelka(List<Paralelka> m_paralelka) {
        this.paralelkas = m_paralelka;
    }

    /**
     * prida studentovi novou paralelku
     * @param p - nova paralelka
     */
    public void addParalelka(Paralelka p) {
        if (this.paralelkas == null) {
            paralelkas = new ArrayList<Paralelka>();
        }
        paralelkas.add(p);
    }

    public List<Paralelka> getM_paralelka() {
        return paralelkas;
    }

    public void setM_Ukol(List<Ukol> m_Ukol) {
        this.m_Ukol = m_Ukol;
        for (Ukol ukol : m_Ukol) {
            ukol.setStudent(this);
        }
    }

    public void setSkupina(Skupina skupina) {
        this.skupina = skupina;
    }

    /**
     * prida studentovi novy ukol
     * @param u - novy ukol
     */
    public void addUkol(Ukol u) {
        if (m_Ukol == null) {
            m_Ukol = new ArrayList<Ukol>();
        }
        m_Ukol.add(u);
        u.setStudent(this);
    }

    public Skupina getSkupina() {
        return skupina;
    }

    public List<Ukol> getM_Ukol() {
        return m_Ukol;
    }

    public int getHodnoceni() {
        return hodnoceni;
    }

    /**
     * 
     * @param newVal
     */
    public void setHodnoceni(int newVal) {
        hodnoceni = newVal;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
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
        return "DL.entity.Student[ id=" + getId() + " login=" + getLogin() + " ]";
    }
}