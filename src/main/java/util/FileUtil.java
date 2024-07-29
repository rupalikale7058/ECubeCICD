package util;

import java.io.File;

public class FileUtil {
	public static void recursiveDelete(File file) {
		if (!file.exists())
			return;
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				recursiveDelete(f);
			}
		}
		file.delete();
	}

	public static String getFilePath(String fileName) {
		// Define the base directory where your files are located
		String baseDirectory = System.getProperty("user.dir") + "\\src\\test\\resources\\uploadfiles";

		String filePath = baseDirectory + File.separator + fileName;
		return filePath;

	}
}
