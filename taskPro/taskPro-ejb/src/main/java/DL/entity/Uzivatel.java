package DL.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author papa
 * @version 1.0
 * @created 11-X-2011 18:14:32
 * reprezentace uzivatelu
 */
@Entity
@Table(name = "uzivatel")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING, length = 8)
@NamedQueries({
    @NamedQuery(name = "Uzivatel.findByLogin", query = "SELECT u FROM Uzivatel u WHERE u.login = :login"),
    @NamedQuery(name = "Uzivatel.all", query = "SELECT u FROM Uzivatel u")//,
//    @NamedQuery(name="Uzivatel.findByZadani", query="SELECT u FROM Uzavatel u")
})
public class Uzivatel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "login", nullable = false, unique = true)
    private String login;
    @Column(name = "heslo", nullable = false)
    private String heslo;
    @Column(name = "jmeno", nullable = false)
    private String jmeno;
    @Column(name = "mail", nullable = false, unique = true)
    private String mail;
    @Column(name = "prijmeni", nullable = false)
    private String prijmeni;

    public Uzivatel() {
    }

    public String getHeslo() {
        return heslo;
    }

    public String getJmeno() {
        return jmeno;
    }

    protected int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getMail() {
        return mail;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setHeslo(String newVal) {
        heslo = newVal;
    }

    public void setJmeno(String newVal) {
        jmeno = newVal;
    }

    public void setLogin(String newVal) {
        login = newVal;
    }

    public void setMail(String newVal) {
        mail = newVal;
    }

    public void setPrijmeni(String newVal) {
        prijmeni = newVal;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uzivatel)) {
            return false;
        }
        Uzivatel other = (Uzivatel) object;
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
        return "DL.entity.Uzivatel[ id=" + getId() + " login=" + getLogin() + " ]";
    }
}