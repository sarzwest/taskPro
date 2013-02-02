package newentity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the groupTaskPro database table.
 * 
 */
@Entity
@Table(name="groupTaskPro")
public class GroupTaskPro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;

	private String code;

	@Column(name="DAYOFTEACH")
	private String dayofteach;

	@Column(name="STARTTIME")
	private Time starttime;

	@Column(name="STOPTIME")
	private Time stoptime;

	//bi-directional many-to-one association to TaskProUser
    @ManyToOne
	@JoinColumn(name="KANTOR_id")
	private TaskProUser taskProUser;

	//bi-directional many-to-one association to Subject
    @ManyToOne
	@JoinColumn(name="SUBJECT_ID")
	private Subject subject;

	//bi-directional many-to-many association to Assignment
    @ManyToMany
	@JoinTable(
		name="groupTaskPro_assignment"
		, joinColumns={
			@JoinColumn(name="mGroups_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="mAssignments_id")
			}
		)
	private List<Assignment> assignments;

	//bi-directional many-to-many association to TaskProUser
	@ManyToMany(mappedBy="groupTaskPros2")
	private List<TaskProUser> taskProUsers;

	//bi-directional many-to-one association to Team
	@OneToMany(mappedBy="groupTaskPro")
	private List<Team> teams;

    public GroupTaskPro() {
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

	public String getDayofteach() {
		return this.dayofteach;
	}

	public void setDayofteach(String dayofteach) {
		this.dayofteach = dayofteach;
	}

	public Time getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Time starttime) {
		this.starttime = starttime;
	}

	public Time getStoptime() {
		return this.stoptime;
	}

	public void setStoptime(Time stoptime) {
		this.stoptime = stoptime;
	}

	public TaskProUser getTaskProUser() {
		return this.taskProUser;
	}

	public void setTaskProUser(TaskProUser taskProUser) {
		this.taskProUser = taskProUser;
	}
	
	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public List<Assignment> getAssignments() {
		return this.assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
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