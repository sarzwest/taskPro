package newentity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the predmet database table.
 * 
 */
@Entity
@Table(name="predmet")
public class Predmet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;

	private String kod;

	private String popisek;

	//bi-directional many-to-one association to Paralelka
	@OneToMany(mappedBy="predmet")
	private List<Paralelka> paralelkas;

    public Predmet() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKod() {
		return this.kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getPopisek() {
		return this.popisek;
	}

	public void setPopisek(String popisek) {
		this.popisek = popisek;
	}

	public List<Paralelka> getParalelkas() {
		return this.paralelkas;
	}

	public void setParalelkas(List<Paralelka> paralelkas) {
		this.paralelkas = paralelkas;
	}
	
}