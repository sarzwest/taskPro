package newentity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ukol database table.
 * 
 */
@Entity
@Table(name="ukol")
public class Ukol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

    @Temporal( TemporalType.DATE)
	private Date datumOdev;

    @Temporal( TemporalType.DATE)
	private Date datumZadani;

    @Temporal( TemporalType.DATE)
	private Date deadline;

	private int hodnoceni;

	private String hodnoceniSlovy;

	private String nazev;

	private String popis;

	private int stav;

	//bi-directional many-to-one association to Ukolsoubor
	@OneToMany(mappedBy="ukol")
	private List<Ukolsoubor> ukolsoubors;

	//bi-directional many-to-one association to Skupina
    @ManyToOne
	@JoinColumn(name="SKUPINA_ID")
	private Skupina skupina;

	//bi-directional many-to-one association to Uzivatel
    @ManyToOne
	@JoinColumn(name="STUDENT_id")
	private Uzivatel uzivatel;

	//bi-directional many-to-one association to Zadani
    @ManyToOne
	@JoinColumn(name="ZADANI_id")
	private Zadani zadani;

    public Ukol() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatumOdev() {
		return this.datumOdev;
	}

	public void setDatumOdev(Date datumOdev) {
		this.datumOdev = datumOdev;
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

	public int getHodnoceni() {
		return this.hodnoceni;
	}

	public void setHodnoceni(int hodnoceni) {
		this.hodnoceni = hodnoceni;
	}

	public String getHodnoceniSlovy() {
		return this.hodnoceniSlovy;
	}

	public void setHodnoceniSlovy(String hodnoceniSlovy) {
		this.hodnoceniSlovy = hodnoceniSlovy;
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

	public int getStav() {
		return this.stav;
	}

	public void setStav(int stav) {
		this.stav = stav;
	}

	public List<Ukolsoubor> getUkolsoubors() {
		return this.ukolsoubors;
	}

	public void setUkolsoubors(List<Ukolsoubor> ukolsoubors) {
		this.ukolsoubors = ukolsoubors;
	}
	
	public Skupina getSkupina() {
		return this.skupina;
	}

	public void setSkupina(Skupina skupina) {
		this.skupina = skupina;
	}
	
	public Uzivatel getUzivatel() {
		return this.uzivatel;
	}

	public void setUzivatel(Uzivatel uzivatel) {
		this.uzivatel = uzivatel;
	}
	
	public Zadani getZadani() {
		return this.zadani;
	}

	public void setZadani(Zadani zadani) {
		this.zadani = zadani;
	}
	
}