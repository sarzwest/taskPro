package newentity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the kantor database table.
 * 
 */
@Entity
@Table(name="kantor")
public class Kantor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	//bi-directional one-to-one association to Uzivatel
	@OneToOne
	@JoinColumn(name="id")
	private Uzivatel uzivatel;

    public Kantor() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Uzivatel getUzivatel() {
		return this.uzivatel;
	}

	public void setUzivatel(Uzivatel uzivatel) {
		this.uzivatel = uzivatel;
	}
	
}