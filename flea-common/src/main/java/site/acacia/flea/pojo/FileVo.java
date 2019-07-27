package site.acacia.flea.pojo;

import java.io.OutputStream;
import java.io.Serializable;

/**
 * @author 张胤
 *
 *         2018年12月12日-下午6:30:31
 */
public class FileVo implements Serializable {

	private static final long serialVersionUID = 220441274091706549L;

	String fileName;
	OutputStream os;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public OutputStream getOs() {
		return os;
	}

	public void setOs(OutputStream os) {
		this.os = os;
	}
}
