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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author papa
 */
/**
 * trida reprezentujici paralelku
 * @author papa
 */
@Entity
//predmet_id a kod tvori dohromady slozene unikatni sloupce
@Table(name = "paralelka", uniqueConstraints=@UniqueConstraint(columnNames={"predmet_id", "kod"}))
@NamedQueries({
    //    @NamedQuery(name = "Uzivatel.findByLogin", query = "SELECT u FROM Uzivatel u WHERE u.login = :login"),
//    @NamedQuery(name = "Paralelka.findByKantor", query = "SELECT p FROM Paralelka p WHERE p.kantors = :kantor")
})
public class Paralelka implements Serializable {

    @ManyToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="kantors")
    private List<Kantor> kantors;
    @JoinColumn(name="predmet_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Predmet predmet;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "kod", nullable = false)
    private String kod;
    @ManyToMany(cascade= CascadeType.ALL)
    private List<Student> m_student;
    @ManyToMany(cascade= CascadeType.ALL)
    private List<Zadani> m_zadani;
    @OneToMany(mappedBy = "paralelka", cascade= CascadeType.ALL)
    private List<Skupina> m_skupina;

    public void setKod(String kod) {
        this.kod = kod;
    }

    public void setM_kantor(List<Kantor> m_kantor) {
        this.kantors = m_kantor;
    }

    /**
     * prida kantora do paralelky
     * @param k - ten kdo uci tuto paralelku
     */
    public void addKantor(Kantor k) {
        if (kantors == null) {
            kantors = new ArrayList<Kantor>();
        }
        kantors.add(k);
    }

    /**
     * prida studenta do paralelky
     * @param s - novy student
     */
    public void addStudent(Student s) {
        if (m_student == null) {
            m_student = new ArrayList<Student>();
        }
        m_student.add(s);
        s.addParalelka(this);
        s.addParalelka(this);
    }

    /**
     * prida zadani do paralelky
     * @param z - nove zadani
     */
    public void addZadani(Zadani z) {
        if (m_zadani == null) {
            m_zadani = new ArrayList<Zadani>();
        }
        m_zadani.add(z);
    }

    public void setM_student(List<Student> m_student) {
        this.m_student = m_student;
        for (Student student : m_student) {
            student.addParalelka(this);
        }
    }

    public void setM_zadani(List<Zadani> m_zadani) {
        this.m_zadani = m_zadani;
    }

    /**
     * prida novou skupinu do paralelky
     * @param s - nova skupina
     */
    public void addSkupina(Skupina s) {
        if (m_skupina == null) {
            m_skupina = new ArrayList<Skupina>();
        }
        m_skupina.add(s);
    }

    public void setM_skupina(List<Skupina> m_skupina) {
        this.m_skupina = m_skupina;
    }

    public List<Skupina> getM_skupina() {
        return m_skupina;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
//        predmet.addParalelka(this);
    }

    public String getKod() {
        return kod;
    }

    public List<Kantor> getM_kantor() {
        return kantors;
    }

    public List<Student> getM_student() {
        return m_student;
    }

    public List<Zadani> getM_zadani() {
        return m_zadani;
    }

    public Predmet getPredmet() {
        return predmet;
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
        if (!(object instanceof Paralelka)) {
            return false;
        }
        Paralelka other = (Paralelka) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DL.entity.Paralelka[ id=" + id + " kod=" + kod + " ]";
    }
}
