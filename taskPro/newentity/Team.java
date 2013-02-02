package newentity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the team database table.
 * 
 */
@Entity
@Table(name="team")
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;

	private String name;

	//bi-directional many-to-one association to Assignmentsubmit
	@OneToMany(mappedBy="team")
	private List<Assignmentsubmit> assignmentsubmits;

	//bi-directional many-to-many association to TaskProUser
    @ManyToMany
	@JoinTable(
		name="student_team"
		, joinColumns={
			@JoinColumn(name="team_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="mStudents_id")
			}
		)
	private List<TaskProUser> taskProUsers;

	//bi-directional many-to-one association to GroupTaskPro
    @ManyToOne
	@JoinColumn(name="GROUP_ID")
	private GroupTaskPro groupTaskPro;

	//bi-directional many-to-many association to Assignment
    @ManyToMany
	@JoinTable(
		name="team_assignment"
		, joinColumns={
			@JoinColumn(name="mTeams_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="mAssignments_id")
			}
		)
	private List<Assignment> assignments;

    public Team() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Assignmentsubmit> getAssignmentsubmits() {
		return this.assignmentsubmits;
	}

	public void setAssignmentsubmits(List<Assignmentsubmit> assignmentsubmits) {
		this.assignmentsubmits = assignmentsubmits;
	}
	
	public List<TaskProUser> getTaskProUsers() {
		return this.taskProUsers;
	}

	public void setTaskProUsers(List<TaskProUser> taskProUsers) {
		this.taskProUsers = taskProUsers;
	}
	
	public GroupTaskPro getGroupTaskPro() {
		return this.groupTaskPro;
	}

	public void setGroupTaskPro(GroupTaskPro groupTaskPro) {
		this.groupTaskPro = groupTaskPro;
	}
	
	public List<Assignment> getAssignments() {
		return this.assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
	
}