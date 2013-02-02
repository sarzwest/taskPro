package newentity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the skupina database table.
 * 
 */
@Entity
@Table(name="skupina")
public class Skupina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;

	private String nazev;

	//bi-directional many-to-one association to Paralelka
    @ManyToOne
	@JoinColumn(name="PARALELKA_ID")
	private Paralelka paralelka;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="skupina")
	private List<Student> students;

	//bi-directional many-to-one association to Ukol
	@OneToMany(mappedBy="skupina")
	private List<Ukol> ukols;

    public Skupina() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazev() {
		return this.nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public Paralelka getParalelka() {
		return this.paralelka;
	}

	public void setParalelka(Paralelka paralelka) {
		this.paralelka = paralelka;
	}
	
	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public List<Ukol> getUkols() {
		return this.ukols;
	}

	public void setUkols(List<Ukol> ukols) {
		this.ukols = ukols;
	}
	
}