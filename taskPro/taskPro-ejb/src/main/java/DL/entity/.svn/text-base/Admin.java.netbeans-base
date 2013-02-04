/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DL.entity;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author papa
 */
/**
 * trida reprezentujici admina
 * @author papa
 */
@Entity
@Table(name = "admin")
@DiscriminatorValue(value = "admin")
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends Uzivatel implements Serializable {

    private static final long serialVersionUID = 1L;

    public Admin() {
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admin)) {
            return false;
        }
        Admin other = (Admin) object;
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
        return "DL.entity.Admin[ id=" + getId() + " login=" + getLogin() + " ]";
    }
}
