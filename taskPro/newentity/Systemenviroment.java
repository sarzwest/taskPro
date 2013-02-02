package newentity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SYSTEMENVIROMENT database table.
 * 
 */
@Entity
@Table(name="SYSTEMENVIROMENT")
public class Systemenviroment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private String id;

    @Lob()
	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="KEYOFSETTING")
	private String keyofsetting;

	@Column(name="VALUEOFSETTING")
	private String valueofsetting;

    public Systemenviroment() {
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

	public String getKeyofsetting() {
		return this.keyofsetting;
	}

	public void setKeyofsetting(String keyofsetting) {
		this.keyofsetting = keyofsetting;
	}

	public String getValueofsetting() {
		return this.valueofsetting;
	}

	public void setValueofsetting(String valueofsetting) {
		this.valueofsetting = valueofsetting;
	}

}