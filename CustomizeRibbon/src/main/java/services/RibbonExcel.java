package services;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.FilenameUtils;
import org.xml.sax.SAXException;

import javafx.scene.control.TreeView;

/**
 * Customize Ribbon
 * 
 * @author Marcin
 *
 */
abstract class RibbonExcel {
	protected String pathToExcelFile;
	protected String extensionOfFile;
	protected String outputFolder;

	/**
	 * Extract files and subfolders from Excel file
	 * 
	 * @throws IOException
	 *             Exception
	 */
	public abstract void extractFiles() throws IOException;

	/**
	 * delete directory with extracted files from Excel archive
	 */
	public abstract void deleteDirectory();

	/**
	 * customize Ribbon by write CustomUI.xml file
	 * 
	 * @param tree
	 *            TreeView
	 * @throws TransformerException
	 *             Exception
	 * @throws ParserConfigurationException
	 *             Exception
	 */
	public abstract void writeXML(TreeView<String> tree) throws TransformerException, ParserConfigurationException;

	/**
	 * create folder "customUI" and add new target to the .rels file
	 * 
	 * @throws TransformerException
	 *             Exception
	 * @throws ParserConfigurationException
	 *             Exception
	 * @throws SAXException
	 *             Exception
	 * @throws IOException
	 *             Exception
	 */
	public abstract void createEmptyRibbon()
			throws TransformerException, ParserConfigurationException, SAXException, IOException;

	/**
	 * compress folder to zip format (Excel file)
	 * 
	 * @throws IOException
	 *             Exception
	 */
	public abstract void buildExcelFile() throws IOException;

	/**
	 * Getter
	 * 
	 * @return path to file
	 */
	public String getPathToExcelFile() {
		return pathToExcelFile;
	}

	/**
	 * Setter
	 * 
	 * @param pathToExcelFile
	 *            path to file
	 */
	public void setPathToExcelFile(String pathToExcelFile) {
		this.pathToExcelFile = pathToExcelFile;
		extensionOfFile = FilenameUtils.getExtension(pathToExcelFile);
		outputFolder = pathToExcelFile.substring(0, pathToExcelFile.lastIndexOf("."));
	}
}
