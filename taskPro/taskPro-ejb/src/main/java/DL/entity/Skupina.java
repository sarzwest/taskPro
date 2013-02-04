/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DL.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author papa
 */
/**
 * trida reprezentujici skupiny v paralelce
 * @author papa
 */
@Entity
@Table(name = "skupina")
public class Skupina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "nazev", nullable = false, unique = true)
    private String nazev;
    @ManyToOne
    private Paralelka paralelka;
    @OneToMany(mappedBy = "skupina")
    private List<Student> m_student;
    @OneToMany(mappedBy = "skupina")
    private List<Ukol> m_ukol;

    public void setM_student(List<Student> m_student) {
        this.m_student = m_student;
    }

    public void setM_ukol(List<Ukol> m_ukol) {
        this.m_ukol = m_ukol;
    }

    /**
     * prida studenta do skupiny
     * @param s - novy student
     */
    public void addStudent(Student s) {
        if (m_student == null) {
            m_student = new ArrayList<Student>();
        }
        m_student.add(s);
    }

    /**
     * prida novy ukol skupine
     * @param u - novy ukol
     */
    public void addUkol(Ukol u) {
        if (m_ukol == null) {
            m_ukol = new ArrayList<Ukol>();
        }
        m_ukol.add(u);
    }

    public List<Ukol> getM_ukol() {
        return m_ukol;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public void setParalelka(Paralelka paralelka) {
        this.paralelka = paralelka;
    }

    public List<Student> getM_student() {
        return m_student;
    }

    public String getNazev() {
        return nazev;
    }

    public Paralelka getParalelka() {
        return paralelka;
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
        if (!(object instanceof Skupina)) {
            return false;
        }
        Skupina other = (Skupina) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DL.entity.Skupina[ id=" + id + " nazev=" + nazev + " ]";
    }
}
