package newentity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the assignment database table.
 * 
 */
@Entity
@Table(name="assignment")
public class Assignment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

    @Temporal( TemporalType.TIMESTAMP)
	private Date dateOfAssignment;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="DATEOFSTART")
	private Date dateofstart;

    @Temporal( TemporalType.TIMESTAMP)
	private Date deadline;

	@Column(name="MAXSCORE")
	private int maxscore;

	private int type;

	//bi-directional many-to-one association to Assignmentsubmit
	@OneToMany(mappedBy="assignment")
	private List<Assignmentsubmit> assignmentsubmits;

	//bi-directional many-to-one association to Subject
    @ManyToOne
	@JoinColumn(name="SUBJECT_ID")
	private Subject subject;

	//bi-directional many-to-one association to Template
    @ManyToOne
	@JoinColumn(name="TEMPLATE_ID")
	private Template template;

	//bi-directional many-to-many association to GroupTaskPro
	@ManyToMany(mappedBy="assignments")
	private List<GroupTaskPro> groupTaskPros;

	//bi-directional many-to-many association to TaskProUser
	@ManyToMany(mappedBy="assignments")
	private List<TaskProUser> taskProUsers;

	//bi-directional many-to-many association to Team
	@ManyToMany(mappedBy="assignments")
	private List<Team> teams;

    public Assignment() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOfAssignment() {
		return this.dateOfAssignment;
	}

	public void setDateOfAssignment(Date dateOfAssignment) {
		this.dateOfAssignment = dateOfAssignment;
	}

	public Date getDateofstart() {
		return this.dateofstart;
	}

	public void setDateofstart(Date dateofstart) {
		this.dateofstart = dateofstart;
	}

	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public int getMaxscore() {
		return this.maxscore;
	}

	public void setMaxscore(int maxscore) {
		this.maxscore = maxscore;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Assignmentsubmit> getAssignmentsubmits() {
		return this.assignmentsubmits;
	}

	public void setAssignmentsubmits(List<Assignmentsubmit> assignmentsubmits) {
		this.assignmentsubmits = assignmentsubmits;
	}
	
	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public Template getTemplate() {
		return this.template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}
	
	public List<GroupTaskPro> getGroupTaskPros() {
		return this.groupTaskPros;
	}

	public void setGroupTaskPros(List<GroupTaskPro> groupTaskPros) {
		this.groupTaskPros = groupTaskPros;
	}
	
	public List<TaskProUser> getTaskProUsers() {
		return this.taskProUsers;
	}

	public void setTaskProUsers(List<TaskProUser> taskProUsers) {
		this.taskProUsers = taskProUsers;
	}
	
	public List<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
}