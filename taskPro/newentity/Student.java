package newentity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@Table(name="student")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int hodnoceni;

	//bi-directional one-to-one association to Uzivatel
	@OneToOne
	@JoinColumn(name="id")
	private Uzivatel uzivatel;

	//bi-directional many-to-one association to Skupina
    @ManyToOne
	@JoinColumn(name="SKUPINA_ID")
	private Skupina skupina;

    public Student() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHodnoceni() {
		return this.hodnoceni;
	}

	public void setHodnoceni(int hodnoceni) {
		this.hodnoceni = hodnoceni;
	}

	public Uzivatel getUzivatel() {
		return this.uzivatel;
	}

	public void setUzivatel(Uzivatel uzivatel) {
		this.uzivatel = uzivatel;
	}
	
	public Skupina getSkupina() {
		return this.skupina;
	}

	public void setSkupina(Skupina skupina) {
		this.skupina = skupina;
	}
	
}