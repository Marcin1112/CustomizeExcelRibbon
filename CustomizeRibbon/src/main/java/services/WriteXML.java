package services;

import java.io.File;
import java.lang.reflect.GenericArrayType;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import ribbonElements.Button;
import ribbonElements.Group;
import ribbonElements.SimpleRibbonContainer;
import ribbonElements.SimpleRibbonElement;
import ribbonElements.Tab;

/**
 * 
 * @author Marcin Write data to xml file
 */
public class WriteXML {
	/**
	 * Write data to xml file
	 * 
	 * @param path
	 *            path to file
	 * @throws TransformerException
	 *             Exception
	 * @throws ParserConfigurationException
	 *             Exception
	 */
	public static void write(String path) throws TransformerException, ParserConfigurationException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("customUI");
		rootElement.setAttribute("xmlns", "http://schemas.microsoft.com/office/2009/07/customui");
		doc.appendChild(rootElement);

		Element ribbon = doc.createElement("ribbon");
		ribbon.setAttribute("startFromScratch", "false");
		rootElement.appendChild(ribbon);

		Element tabs = doc.createElement("tabs");
		ribbon.appendChild(tabs);

		SimpleRibbonContainer tab = new Tab(doc);
		tab.setAttribute("id", "customTab");
		tab.setAttribute("label", "custom Tab");
		Element tabXML = tab.getSimpleRibbonContainerElement();
		tabs.appendChild(tabXML);

		Group group = new Group(doc);
		group.setAttribute("id", "customGroup");
		group.setAttribute("label", "Custom Group");
		Element groupXML = group.getXMLElement();
		tabXML.appendChild(groupXML);

		SimpleRibbonElement btn = new Button(doc);
		btn.setAttribute("id", "customButton");
		btn.setAttribute("label", "Custom Button");
		btn.setAttribute("onAction", "Callback");
		btn.setAttribute("size", "large");
		btn.setAttribute("imageMso", "HappyFace");
		groupXML.appendChild(btn.getXMLElement());

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
		transformer.transform(source, result);
	}

	/**
	 * Write the content into xml file
	 * 
	 * @param path
	 *            Path to the XML file
	 * @param tree
	 *            TreeView
	 * @throws TransformerException
	 *             Exception
	 * @throws ParserConfigurationException
	 *             Exception
	 */
	public static void writeXML(String path, TreeView<String> tree)
			throws TransformerException, ParserConfigurationException {
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Document doc = generateXML(tree);
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path));
		transformer.transform(source, result);
	}

	/**
	 * 
	 * @param tree
	 *            TreeView
	 * @return Document
	 * @throws ParserConfigurationException
	 *             Exception
	 */
	public static Document generateXML(TreeView<String> tree) throws ParserConfigurationException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("customUI");
		rootElement.setAttribute("xmlns", "http://schemas.microsoft.com/office/2009/07/customui");
		doc.appendChild(rootElement);

		Element ribbon = doc.createElement("ribbon");
		ribbon.setAttribute("startFromScratch", "false");
		rootElement.appendChild(ribbon);

		Element tabs = doc.createElement("tabs");
		ribbon.appendChild(tabs);
		
		for (TreeItem<String> node : tree.getRoot().getChildren()) {
			if (node.getValue().equals("Tab")) {
				SimpleRibbonContainer tab = new Tab(doc);
				tab.setAttribute("id", "customTab");
				tab.setAttribute("label", "custom Tab");
				Element tabXML = tab.getSimpleRibbonContainerElement();
				tabs.appendChild(tabXML);

				for (TreeItem<String> nodeGroup : node.getChildren()) {
					if (nodeGroup.getValue().equals("Group")) {
						Group group = new Group(doc);
						group.setAttribute("id", "customGroup");
						group.setAttribute("label", "Custom Group");
						Element groupXML = group.getXMLElement();
						tabXML.appendChild(groupXML);

						for (TreeItem<String> nodeButton : nodeGroup.getChildren()) {
							if (nodeButton.getValue().equals("Button")) {
								SimpleRibbonElement btn = new Button(doc);
								btn.setAttribute("id", "customButton");
								btn.setAttribute("label", "Custom Button");
								btn.setAttribute("onAction", "Callback");
								btn.setAttribute("size", "large");
								btn.setAttribute("imageMso", "HappyFace");
								groupXML.appendChild(btn.getXMLElement());
							}
						}
					}
				}
			}
		}
		return doc;
	}
}
