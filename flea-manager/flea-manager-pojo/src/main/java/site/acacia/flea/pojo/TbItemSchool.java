package site.acacia.flea.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbItemSchool implements Serializable {

	private static final long serialVersionUID = 3640099108888177739L;

	private Integer schoolId;

	private String schoolName;

	private Date created;

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName == null ? null : schoolName.trim();
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "TbItemSchool [schoolId=" + schoolId + ", schoolName=" + schoolName + ", created=" + created + "]";
	}
}