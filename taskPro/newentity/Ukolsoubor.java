package newentity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the UKOLSOUBOR database table.
 * 
 */
@Entity
@Table(name="UKOLSOUBOR")
public class Ukolsoubor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private String id;

	private String description;

    @Lob()
	private byte[] file;

	//bi-directional many-to-one association to Ukol
    @ManyToOne
	@JoinColumn(name="UKOL_id")
	private Ukol ukol;

    public Ukolsoubor() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getFile() {
		return this.file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public Ukol getUkol() {
		return this.ukol;
	}

	public void setUkol(Ukol ukol) {
		this.ukol = ukol;
	}
	
}