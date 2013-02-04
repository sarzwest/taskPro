/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DL.entity;

import java.io.File;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author papa
 */
@Entity
public class UkolSoubor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Basic(fetch= FetchType.LAZY)
    @Column(name="file", nullable=false)
    private File file;
    @Column(name="description")
    private String description;
    private Ukol ukol;

    public Long getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setUkol(Ukol ukol) {
        this.ukol = ukol;
    }

    public Ukol getUkol() {
        return ukol;
    }

    public String getDescription() {
        return description;
    }

    public File getFile() {
        return file;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UkolSoubor)) {
            return false;
        }
        UkolSoubor other = (UkolSoubor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DL.entity.UkolSoubor[ id=" + id + " ]";
    }
    
}
