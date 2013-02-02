package newentity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the version database table.
 * 
 */
@Entity
@Table(name="version")
public class Version implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private String id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="DATEOFSUBMIT")
	private Date dateofsubmit;

	@Column(name="NUMBEROFVERSION")
	private int numberofversion;

	//bi-directional many-to-one association to Assignmentsubmit
    @ManyToOne
	@JoinColumn(name="ASSIGNMENTSUBMIT_ID")
	private Assignmentsubmit assignmentsubmit;

	//bi-directional many-to-one association to TaskProUser
    @ManyToOne
	@JoinColumn(name="SUBMITTEDSTUDENT_id")
	private TaskProUser taskProUser;

	//bi-directional many-to-one association to VersionFile
	@OneToMany(mappedBy="version")
	private List<VersionFile> versionFiles;

    public Version() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateofsubmit() {
		return this.dateofsubmit;
	}

	public void setDateofsubmit(Date dateofsubmit) {
		this.dateofsubmit = dateofsubmit;
	}

	public int getNumberofversion() {
		return this.numberofversion;
	}

	public void setNumberofversion(int numberofversion) {
		this.numberofversion = numberofversion;
	}

	public Assignmentsubmit getAssignmentsubmit() {
		return this.assignmentsubmit;
	}

	public void setAssignmentsubmit(Assignmentsubmit assignmentsubmit) {
		this.assignmentsubmit = assignmentsubmit;
	}
	
	public TaskProUser getTaskProUser() {
		return this.taskProUser;
	}

	public void setTaskProUser(TaskProUser taskProUser) {
		this.taskProUser = taskProUser;
	}
	
	public List<VersionFile> getVersionFiles() {
		return this.versionFiles;
	}

	public void setVersionFiles(List<VersionFile> versionFiles) {
		this.versionFiles = versionFiles;
	}
	
}