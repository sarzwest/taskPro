package newentity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the TEMPLATEFILE database table.
 * 
 */
@Entity
@Table(name="TEMPLATEFILE")
public class Templatefile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private String id;

    @Lob()
	@Column(name="FILE")
	private byte[] file;

	@Column(name="FILENAME")
	private String filename;

	@Column(name="FILESIZE")
	private BigInteger filesize;

	//bi-directional many-to-one association to Template
    @ManyToOne
	@JoinColumn(name="TEMPLATE_ID")
	private Template template;

    public Templatefile() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Template getTemplate() {
		return this.template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}
	
}