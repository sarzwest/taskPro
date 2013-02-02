package newentity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the template database table.
 * 
 */
@Entity
@Table(name="template")
public class Template implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private String id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="DATEOFCREATION")
	private Date dateofcreation;

    @Lob()
	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="NAME")
	private String name;

	//bi-directional many-to-one association to Templatefile
	@OneToMany(mappedBy="template")
	private List<Templatefile> templatefiles;

	//bi-directional many-to-one association to Assignment
	@OneToMany(mappedBy="template")
	private List<Assignment> assignments;

	//bi-directional many-to-one association to TaskProUser
    @ManyToOne
	@JoinColumn(name="KANTOR_id")
	private TaskProUser taskProUser;

    public Template() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateofcreation() {
		return this.dateofcreation;
	}

	public void setDateofcreation(Date dateofcreation) {
		this.dateofcreation = dateofcreation;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Templatefile> getTemplatefiles() {
		return this.templatefiles;
	}

	public void setTemplatefiles(List<Templatefile> templatefiles) {
		this.templatefiles = templatefiles;
	}
	
	public List<Assignment> getAssignments() {
		return this.assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
	
	public TaskProUser getTaskProUser() {
		return this.taskProUser;
	}

	public void setTaskProUser(TaskProUser taskProUser) {
		this.taskProUser = taskProUser;
	}
	
}