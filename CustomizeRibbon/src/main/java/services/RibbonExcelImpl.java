package services;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javafx.scene.control.TreeView;

/**
 * Customize Ribbon
 * 
 * @author Marcin
 *
 */
public class RibbonExcelImpl extends RibbonExcel {
	/**
	 * constructor
	 */
	public RibbonExcelImpl() {

	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void extractFiles() throws IOException {
		ManipulateZip.decompressFile(getPathToExcelFile());
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void deleteDirectory() {
		deleteDirectory(new File(outputFolder));
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void writeXML(TreeView<String> tree) throws TransformerException, ParserConfigurationException {
		// WriteXML.write(outputFolder + "\\customUI\\" + "customUI14.xml");
		String path = outputFolder + "\\customUI\\" + "customUI14.xml";
		WriteXML.writeXML(path, tree);
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void createEmptyRibbon()
			throws TransformerException, ParserConfigurationException, SAXException, IOException {
		// create folder "customUI"
		File folder = new File(outputFolder + "\\customUI");
		if (!folder.exists()) {
			folder.mkdir();
		}

		// add new target to the DOM of .rels file
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		/* parse existing file to DOM */
		Document document = documentBuilder.parse(new File(outputFolder + "\\_rels\\.rels"));
		Element root = document.getDocumentElement();
		Element relationship = document.createElement("Relationship");
		relationship.setAttribute("Target", "/customUI/customUI14.xml");
		relationship.setAttribute("Type", "http://schemas.microsoft.com/office/2007/relationships/ui/extensibility");
		relationship.setAttribute("Id", "R68a3494077934b04");
		root.appendChild(relationship);

		// save changes in .rels file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(outputFolder + "\\_rels\\.rels");
		transformer.transform(source, result);
	}

	/**
	 * recursive method to delete directory and all files and subfolders
	 * 
	 * @param dir
	 */
	private void deleteDirectory(File dir) {
		if (dir.isDirectory()) {
			File[] children = dir.listFiles();
			for (int i = 0; i < children.length; i++) {
				deleteDirectory(children[i]);
			}
		}
		dir.delete();
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public void buildExcelFile() throws IOException {
		ManipulateZip.compressFolder(outputFolder, extensionOfFile);
	}
}
