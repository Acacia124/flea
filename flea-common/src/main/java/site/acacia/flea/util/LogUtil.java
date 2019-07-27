/**
 * 
 */
package site.acacia.flea.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import site.acacia.flea.pojo.FileVo;

/**
 * @author 张胤
 *
 *         2018年12月12日-下午4:56:00
 */
public class LogUtil {

	/**
	 * 下载日志文件
	 */
	public static void download(FileVo fileVo) {
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = fileVo.getOs();
		try {
			bis = new BufferedInputStream(new FileInputStream(new File("log/" + fileVo.getFileName())));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			System.out.println(e.getStackTrace()[0]);
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 獲取日誌目錄
	 * 
	 * @param path
	 * @param deep
	 * @return
	 */
	public static List<String> getFile(String path, int deep) {
		// 获得指定文件对象
		File file = new File(path);
		// 获得该文件夹内的所有文件
		File[] array = file.listFiles();

		List<String> list = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			if (array[i].isFile())// 如果是文件
			{
				list.add(array[i].getName());
			} else if (array[i].isDirectory())// 如果是文件夹
			{
				list.add(array[i].getName());
				getFile(array[i].getPath(), deep + 1);
			}
		}
		return list;
	}
}
