package services.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 
 * @author Marcin
 *
 */
public class ManipulateZip {
	/**
	 * 
	 * @param zipFile
	 *            input file
	 * @param outputFolder
	 *            output folder
	 * @throws IOException
	 *             Exception
	 */
	public static void decompressFile(String zipFile) throws IOException {
		String outputFolder = gethPathToFileWithoutExtension(zipFile);
		byte[] buffer = new byte[1024];
		// create output directory is not exists
		File folder = new File(outputFolder);
		if (!folder.exists()) {
			folder.mkdir();
		}

		// get the zip file content
		ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
		// get the zipped file list entry
		ZipEntry ze = zis.getNextEntry();

		while (ze != null) {
			String fileName = ze.getName();
			File newFile = new File(outputFolder + File.separator + fileName);

			// create all non exists folders
			// else you will hit FileNotFoundException for compressed folder
			new File(newFile.getParent()).mkdirs();

			FileOutputStream fos = new FileOutputStream(newFile);

			int len;
			while ((len = zis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			ze = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
	}

	/**
	 * 
	 * @param zipFile
	 *            full path to file
	 * @return path to file without extension of the file
	 */
	private static String gethPathToFileWithoutExtension(String zipFile) {
		return zipFile.substring(0, zipFile.lastIndexOf("."));
	}

	/**
	 * Zip folder
	 * 
	 * @param outputZipFile
	 *            output file
	 * @param sourceFolder
	 *            input folder
	 * @throws IOException
	 *             Exception
	 */

	/**
	 * Compress it
	 * 
	 * @param zipFile
	 *            output ZIP file location
	 * @param sourceFolder
	 *            path to folder
	 * @throws IOException
	 */
	public static void compressFolder(String sourceFolder, String extension) throws IOException {
		byte[] buffer = new byte[1024];
		FileOutputStream fos = new FileOutputStream(sourceFolder + "." + extension);
		ZipOutputStream zos = new ZipOutputStream(fos);

		List<String> fileList = new ArrayList<String>();
		fileList = generateFileList(sourceFolder);

		for (String file : fileList) {
			ZipEntry ze = new ZipEntry(file);
			zos.putNextEntry(ze);

			FileInputStream in = new FileInputStream(sourceFolder + File.separator + file);

			int len;
			while ((len = in.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}

			in.close();
		}
		zos.closeEntry();
		zos.close();
	}

	/**
	 * Traverse a directory and get all files, and add the file into fileList
	 * 
	 * @param node
	 *            file or directory
	 * @param sourceFolder
	 *            file or directory
	 */
	private static List<String> generateFileList(String sourceFolder) {
		File node = new File(sourceFolder);
		List<String> fileList = new ArrayList<String>();
		generateFileList(fileList, node, sourceFolder);
		return fileList;
	}

	private static void generateFileList(List<String> fileList, File node, String sourceFolder) {
		if (node.isFile()) {
			fileList.add(generateZipEntry(node.getAbsoluteFile().toString(), sourceFolder));
		}
		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				generateFileList(fileList, new File(node, filename), sourceFolder);
			}
		}
	}

	/**
	 * Format the file path for zip
	 * 
	 * @param file
	 *            file path
	 * @param sourceFolder
	 *            folder path
	 * @return Formatted file path
	 */
	private static String generateZipEntry(String file, String sourceFolder) {
		return file.substring(sourceFolder.length() + 1, file.length());
	}
}
