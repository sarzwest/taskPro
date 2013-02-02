package newentity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the paralelka database table.
 * 
 */
@Entity
@Table(name="paralelka")
public class Paralelka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;

	private String kod;

	//bi-directional many-to-one association to Predmet
    @ManyToOne
	@JoinColumn(name="predmet_id")
	private Predmet predmet;

	//bi-directional many-to-many association to Uzivatel
    @ManyToMany
	@JoinTable(
		name="paralelka_kantor"
		, joinColumns={
			@JoinColumn(name="paralelkas_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="kantors_id")
			}
		)
	private List<Uzivatel> uzivatels1;

	//bi-directional many-to-many association to Uzivatel
    @ManyToMany
	@JoinTable(
		name="paralelka_student"
		, joinColumns={
			@JoinColumn(name="paralelkas_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="m_student_id")
			}
		)
	private List<Uzivatel> uzivatels2;

	//bi-directional many-to-many association to Zadani
    @ManyToMany
	@JoinTable(
		name="paralelka_zadani"
		, joinColumns={
			@JoinColumn(name="m_paralelka_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="m_zadani_id")
			}
		)
	private List<Zadani> zadanis;

	//bi-directional many-to-one association to Skupina
	@OneToMany(mappedBy="paralelka")
	private List<Skupina> skupinas;

    public Paralelka() {
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

	public Predmet getPredmet() {
		return this.predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}
	
	public List<Uzivatel> getUzivatels1() {
		return this.uzivatels1;
	}

	public void setUzivatels1(List<Uzivatel> uzivatels1) {
		this.uzivatels1 = uzivatels1;
	}
	
	public List<Uzivatel> getUzivatels2() {
		return this.uzivatels2;
	}

	public void setUzivatels2(List<Uzivatel> uzivatels2) {
		this.uzivatels2 = uzivatels2;
	}
	
	public List<Zadani> getZadanis() {
		return this.zadanis;
	}

	public void setZadanis(List<Zadani> zadanis) {
		this.zadanis = zadanis;
	}
	
	public List<Skupina> getSkupinas() {
		return this.skupinas;
	}

	public void setSkupinas(List<Skupina> skupinas) {
		this.skupinas = skupinas;
	}
	
}