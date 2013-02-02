package newentity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the versionFile database table.
 * 
 */
@Entity
@Table(name="versionFile")
public class VersionFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private String id;

    @Lob()
	@Column(name="DESCRIPTION")
	private String description;

    @Lob()
	@Column(name="EVALUATION")
	private String evaluation;

    @Lob()
	@Column(name="FILE")
	private byte[] file;

	@Column(name="FILENAME")
	private String filename;

	@Column(name="FILESIZE")
	private BigInteger filesize;

	//bi-directional many-to-one association to Version
    @ManyToOne
	@JoinColumn(name="VERSION_ID")
	private Version version;

    public VersionFile() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public byte[] getFile() {
		return this.file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public BigInteger getFilesize() {
		return this.filesize;
	}

	public void setFilesize(BigInteger filesize) {
		this.filesize = filesize;
	}

	public Version getVersion() {
		return this.version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}
	
}