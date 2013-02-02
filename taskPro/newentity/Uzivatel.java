package newentity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the uzivatel database table.
 * 
 */
@Entity
@Table(name="uzivatel")
public class Uzivatel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String heslo;

	private String jmeno;

	private String login;

	private String mail;

	private String prijmeni;

	private String role;

	//bi-directional one-to-one association to Admin
	@OneToOne(mappedBy="uzivatel")
	private Admin admin;

	//bi-directional one-to-one association to Kantor
	@OneToOne(mappedBy="uzivatel")
	private Kantor kantor;

	//bi-directional many-to-many association to Paralelka
	@ManyToMany(mappedBy="uzivatels1")
	private List<Paralelka> paralelkas1;

	//bi-directional many-to-many association to Paralelka
	@ManyToMany(mappedBy="uzivatels2")
	private List<Paralelka> paralelkas2;

	//bi-directional one-to-one association to Student
	@OneToOne(mappedBy="uzivatel")
	private Student student;

	//bi-directional many-to-one association to Ukol
	@OneToMany(mappedBy="uzivatel")
	private List<Ukol> ukols;

	//bi-directional many-to-one association to Zadani
	@OneToMany(mappedBy="uzivatel")
	private List<Zadani> zadanis;

    public Uzivatel() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHeslo() {
		return this.heslo;
	}

	public void setHeslo(String heslo) {
		this.heslo = heslo;
	}

	public String getJmeno() {
		return this.jmeno;
	}

	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPrijmeni() {
		return this.prijmeni;
	}

	public void setPrijmeni(String prijmeni) {
		this.prijmeni = prijmeni;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public Kantor getKantor() {
		return this.kantor;
	}

	public void setKantor(Kantor kantor) {
		this.kantor = kantor;
	}
	
	public List<Paralelka> getParalelkas1() {
		return this.paralelkas1;
	}

	public void setParalelkas1(List<Paralelka> paralelkas1) {
		this.paralelkas1 = paralelkas1;
	}
	
	public List<Paralelka> getParalelkas2() {
		return this.paralelkas2;
	}

	public void setParalelkas2(List<Paralelka> paralelkas2) {
		this.paralelkas2 = paralelkas2;
	}
	
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public List<Ukol> getUkols() {
		return this.ukols;
	}

	public void setUkols(List<Ukol> ukols) {
		this.ukols = ukols;
	}
	
	public List<Zadani> getZadanis() {
		return this.zadanis;
	}

	public void setZadanis(List<Zadani> zadanis) {
		this.zadanis = zadanis;
	}
	
}