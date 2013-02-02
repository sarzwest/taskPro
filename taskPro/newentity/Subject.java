package newentity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the subject database table.
 * 
 */
@Entity
@Table(name="subject")
public class Subject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;

	private String code;

    @Lob()
	private String description;

	//bi-directional many-to-one association to Assignment
	@OneToMany(mappedBy="subject")
	private List<Assignment> assignments;

	//bi-directional many-to-one association to GroupTaskPro
	@OneToMany(mappedBy="subject")
	private List<GroupTaskPro> groupTaskPros;

    public Subject() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Assignment> getAssignments() {
		return this.assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
	
	public List<GroupTaskPro> getGroupTaskPros() {
		return this.groupTaskPros;
	}

	public void setGroupTaskPros(List<GroupTaskPro> groupTaskPros) {
		this.groupTaskPros = groupTaskPros;
	}
	
}