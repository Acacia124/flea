package site.acacia.flea.exception;

/**
 * @author 张胤
 *
 *         2018年10月17日-下午5:20:02
 */
public class Unauthorized extends Exception {

	private static final long serialVersionUID = 4170289622121687816L;

	public Unauthorized(String msg) {
		super(msg);
	}
}
