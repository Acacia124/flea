import java.util.UUID;

/**
 * 
 */

/**
 * @author 张胤
 *
 *         2019年1月26日-下午1:01:48
 */
public class Main {
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replace("-", "").toLowerCase());
	}
}
