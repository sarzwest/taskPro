package newentity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ASSIGNMENTSUBMIT database table.
 * 
 */
@Entity
@Table(name="ASSIGNMENTSUBMIT")
public class Assignmentsubmit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private String id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="DATEOFEVALUATION")
	private Date dateofevaluation;

    @Lob()
	@Column(name="EVALUATION")
	private String evaluation;

	@Column(name="GRADE")
	private int grade;

	private int stateOfTask;

	//bi-directional many-to-one association to Assignment
    @ManyToOne
	@JoinColumn(name="ASSIGNMENT_id")
	private Assignment assignment;

	//bi-directional many-to-one association to TaskProUser
    @ManyToOne
	@JoinColumn(name="STUDENT_id")
	private TaskProUser taskProUser;

	//bi-directional many-to-one association to Team
    @ManyToOne
	@JoinColumn(name="TEAM_ID")
	private Team team;

	//bi-directional many-to-one association to Version
	@OneToMany(mappedBy="assignmentsubmit")
	private List<Version> versions;

    public Assignmentsubmit() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateofevaluation() {
		return this.dateofevaluation;
	}

	public void setDateofevaluation(Date dateofevaluation) {
		this.dateofevaluation = dateofevaluation;
	}

	public String getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public int getGrade() {
		return this.grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getStateOfTask() {
		return this.stateOfTask;
	}

	public void setStateOfTask(int stateOfTask) {
		this.stateOfTask = stateOfTask;
	}

	public Assignment getAssignment() {
		return this.assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}
	
	public TaskProUser getTaskProUser() {
		return this.taskProUser;
	}

	public void setTaskProUser(TaskProUser taskProUser) {
		this.taskProUser = taskProUser;
	}
	
	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public List<Version> getVersions() {
		return this.versions;
	}

	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}
	
}