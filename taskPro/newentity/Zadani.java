package newentity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the zadani database table.
 * 
 */
@Entity
@Table(name="zadani")
public class Zadani implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

    @Temporal( TemporalType.DATE)
	private Date datumZadani;

    @Temporal( TemporalType.DATE)
	private Date deadline;

	private String nazev;

	private String popis;

    @Lob()
	private byte[] zadaniFile;

	//bi-directional many-to-many association to Paralelka
	@ManyToMany(mappedBy="zadanis")
	private List<Paralelka> paralelkas;

	//bi-directional many-to-one association to Ukol
	@OneToMany(mappedBy="zadani")
	private List<Ukol> ukols;

	//bi-directional many-to-one association to Uzivatel
    @ManyToOne
	@JoinColumn(name="KANTOR_id")
	private Uzivatel uzivatel;

    public Zadani() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatumZadani() {
		return this.datumZadani;
	}

	public void setDatumZadani(Date datumZadani) {
		this.datumZadani = datumZadani;
	}

	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getNazev() {
		return this.nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public String getPopis() {
		return this.popis;
	}

	public void setPopis(String popis) {
		this.popis = popis;
	}

	public byte[] getZadaniFile() {
		return this.zadaniFile;
	}

	public void setZadaniFile(byte[] zadaniFile) {
		this.zadaniFile = zadaniFile;
	}

	public List<Paralelka> getParalelkas() {
		return this.paralelkas;
	}

	public void setParalelkas(List<Paralelka> paralelkas) {
		this.paralelkas = paralelkas;
	}
	
	public List<Ukol> getUkols() {
		return this.ukols;
	}

	public void setUkols(List<Ukol> ukols) {
		this.ukols = ukols;
	}
	
	public Uzivatel getUzivatel() {
		return this.uzivatel;
	}

	public void setUzivatel(Uzivatel uzivatel) {
		this.uzivatel = uzivatel;
	}
	
}