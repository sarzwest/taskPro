package newentity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the taskProUser database table.
 * 
 */
@Entity
@Table(name="taskProUser")
public class TaskProUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String email;

	private String login;

	private String name;

	private String password;

	private String role;

	private String surname;

	//bi-directional many-to-one association to Assignmentsubmit
	@OneToMany(mappedBy="taskProUser")
	private List<Assignmentsubmit> assignmentsubmits;

	//bi-directional many-to-one association to GroupTaskPro
	@OneToMany(mappedBy="taskProUser")
	private List<GroupTaskPro> groupTaskPros1;

	//bi-directional many-to-many association to GroupTaskPro
    @ManyToMany
	@JoinTable(
		name="groupTaskPro_student"
		, joinColumns={
			@JoinColumn(name="mStudents_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="mGroups_ID")
			}
		)
	private List<GroupTaskPro> groupTaskPros2;

	//bi-directional many-to-many association to Assignment
    @ManyToMany
	@JoinTable(
		name="student_assignment"
		, joinColumns={
			@JoinColumn(name="mStudents_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="mAssignment_id")
			}
		)
	private List<Assignment> assignments;

	//bi-directional many-to-many association to Team
	@ManyToMany(mappedBy="taskProUsers")
	private List<Team> teams;

	//bi-directional many-to-one association to Template
	@OneToMany(mappedBy="taskProUser")
	private List<Template> templates;

	//bi-directional many-to-one association to Version
	@OneToMany(mappedBy="taskProUser")
	private List<Version> versions;

    public TaskProUser() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Assignmentsubmit> getAssignmentsubmits() {
		return this.assignmentsubmits;
	}

	public void setAssignmentsubmits(List<Assignmentsubmit> assignmentsubmits) {
		this.assignmentsubmits = assignmentsubmits;
	}
	
	public List<GroupTaskPro> getGroupTaskPros1() {
		return this.groupTaskPros1;
	}

	public void setGroupTaskPros1(List<GroupTaskPro> groupTaskPros1) {
		this.groupTaskPros1 = groupTaskPros1;
	}
	
	public List<GroupTaskPro> getGroupTaskPros2() {
		return this.groupTaskPros2;
	}

	public void setGroupTaskPros2(List<GroupTaskPro> groupTaskPros2) {
		this.groupTaskPros2 = groupTaskPros2;
	}
	
	public List<Assignment> getAssignments() {
		return this.assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
	
	public List<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	public List<Template> getTemplates() {
		return this.templates;
	}

	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}
	
	public List<Version> getVersions() {
		return this.versions;
	}

	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}
	
}