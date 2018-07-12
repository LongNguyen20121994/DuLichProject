/**
 * 
 */
package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.rarfile.FileHeader;

/**
 * @author MinhQuang
 *
 */
public class ExtractZipRar {

	public static void extractZipFiles(String destinationFolder, File filename) {
		ZipInputStream zipInputStream = null;
		FileOutputStream fileoutputstream = null;
		try {

			// destination folder to extract the contents
			byte[] buf = new byte[5000000];
			ZipEntry zipentry;
			zipInputStream = new ZipInputStream(new FileInputStream(filename));
			zipentry = zipInputStream.getNextEntry();
			while (zipentry != null) {

				// for each entry to be extracted
				String entryName = zipentry.getName();
				int n;
				File newFile = new File(entryName);
				String directory = newFile.getParent();
				System.out.println(entryName);
				System.out.println(directory);

				// to creating the parent directories
				if (directory == null) {
					if (newFile.isDirectory()) {
						break;
					}
				} else {
					new File(destinationFolder + directory).mkdirs();
				}

				if (!zipentry.isDirectory()) {
					System.out.println("File to be extracted....." + entryName);
					fileoutputstream = new FileOutputStream(destinationFolder + entryName);
					while ((n = zipInputStream.read(buf, 0, 1024)) > -1) {
						fileoutputstream.write(buf, 0, n);
					}
				}
				if (zipInputStream != null)
					zipInputStream.closeEntry();
				zipentry = zipInputStream.getNextEntry();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (fileoutputstream != null)
					fileoutputstream.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static boolean extractRARFile(String destinationFolder, File fileNameRAR) {
		Archive arch;
		try {
			arch = new Archive(fileNameRAR);
			List<FileHeader> headers = arch.getFileHeaders();
			Iterator<FileHeader> iterator = headers.iterator();
			FileHeader fh;

			while (iterator.hasNext()) {
				fh = iterator.next();

				String filePath = destinationFolder + fh.getFileNameString();
				File folder = new File(filePath);
				if (!folder.exists()) {
					folder.mkdirs();
					folder.delete();
					folder.createNewFile();
				}
				// Extract as a file
				arch.extractFile(fh, new FileOutputStream(new File(destinationFolder + fh.getFileNameString())));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		ExtractZipRar rar = new ExtractZipRar();
		String destination = "D:\\test\\";
		File file = new File("D:\\test.zip");
		// rar.extractZipFiles(destination, file);

		rar.extractRARFile(destination, file);

		System.out.println("okkkkkkkkkkkkkk");

	}

}
