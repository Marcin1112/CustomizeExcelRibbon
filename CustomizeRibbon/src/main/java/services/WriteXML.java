package services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import ribbonElements.ButtonGroup;
import ribbonElements.CheckBox;
import ribbonElements.ComboBox;
import ribbonElements.DialogBoxLauncher;
import ribbonElements.EditBox;
import ribbonElements.Group;
import ribbonElements.Image;
import ribbonElements.Item;
import ribbonElements.LabelControl;
import ribbonElements.Menu;
import ribbonElements.SimpleRibbonContainer;
import ribbonElements.Tab;
import ribbonElements.button.Button;
import ribbonElements.button.UnsizedButton;
import ribbonElements.gallery.Gallery;
import ribbonElements.gallery.UnsizedGallery;
import ribbonElements.separator.MenuSeparator;
import ribbonElements.separator.Separator;
import ribbonElements.toggleButton.ToggleButton;
import ribbonElements.toggleButton.UnsizedToggleButton;
import tree.ExtendedTreeItem;

/**
 * 
 * @author Marcin Write data to xml file
 */
public class WriteXML {
	static int numberOfImages = 0;
	static Map<Integer, Image> images = new HashMap<Integer, Image>();

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
	 * @throws IOException
	 *             Exception
	 */
	public static void writeXML(String path, TreeView<String> tree)
			throws TransformerException, ParserConfigurationException, IOException {
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Document doc = generateXML(tree);
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path + "customUI14.xml"));
		transformer.transform(source, result);

		images.clear();
		numberOfImages = 0;
		refreshImagesMap(tree.getRoot());
		if (numberOfImages > 0) { // there are exist images in a ribbon
			createImagesInRibbon(path);
		}

	}

	/**
	 * Create XML file, containing information about images in ribbon
	 * 
	 * @param path
	 *            path to XML file
	 * @throws ParserConfigurationException
	 *             Exception
	 * @throws TransformerException
	 *             Exception
	 * @throws IOException
	 *             Exception
	 */
	private static void createImagesInRibbon(String path)
			throws ParserConfigurationException, TransformerException, IOException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("Relationships");
		rootElement.setAttribute("xmlns", "http://schemas.openxmlformats.org/package/2006/relationships");
		doc.appendChild(rootElement);

		for (Entry<Integer, Image> e : images.entrySet()) {
			String pathImage = e.getValue().getAttributeValue("pathToFile");
			String id = e.getValue().getAttributeValue("id");

			Element ribbon = doc.createElement("Relationship");
			ribbon.setAttribute("Id", id);
			ribbon.setAttribute("Type", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image");

			int index = pathImage.lastIndexOf("\\");
			ribbon.setAttribute("Target", pathImage.substring(index + 1));
			rootElement.appendChild(ribbon);

			File source = new File(pathImage);
			File dest = new File(path);
			FileUtils.copyFileToDirectory(source, dest);
		}

		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path + "\\_rels\\customUI14.xml.rels"));
		transformer.transform(source, result);
	}

	/**
	 * Generate XML
	 * 
	 * @param tree
	 *            TreeView
	 * @return Document Document
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

		for (TreeItem<String> node : tree.getRoot().getChildren()) {
			if (node.getValue().equals("Ribbon")) {
				Element tabs = doc.createElement("tabs");
				ribbon.appendChild(tabs);
				ExtendedTreeItem<String> nodeTabs = (ExtendedTreeItem<String>) node;
				for (Entry<String, String> singleTab : nodeTabs.getSimpleRibbonContainer().getAttributes().entrySet()) { // set
																															// visiblity
																															// of
																															// tabHome...
					if (singleTab.getValue() != null) {
						Element e = doc.createElement("tab");
						e.setAttribute("idMso", singleTab.getKey());
						e.setAttribute("visible", singleTab.getValue());
						tabs.appendChild(e);
					}
				}

				for (TreeItem<String> nodeR : node.getChildren()) {
					if (nodeR.getValue().equals("Tab")) {
						ExtendedTreeItem<String> node1 = (ExtendedTreeItem<String>) nodeR;
						SimpleRibbonContainer tab = new Tab(doc);
						fillAttributes(node1, tab);
						Element tabXML = tab.getXMLElement();
						tabs.appendChild(tabXML);

						for (TreeItem<String> nodeGroup : nodeR.getChildren()) {
							if (nodeGroup.getValue().equals("Group")) {
								ExtendedTreeItem<String> node2 = (ExtendedTreeItem<String>) nodeGroup;
								Group group = new Group(doc);
								fillAttributes(node2, group);
								Element groupXML = group.getXMLElement();
								tabXML.appendChild(groupXML);

								for (TreeItem<String> nodeButton : nodeGroup.getChildren()) {
									if (nodeButton.getValue().equals("Button")) {
										ExtendedTreeItem<String> node3 = (ExtendedTreeItem<String>) nodeButton;
										Button btn = new Button(doc);
										fillAttributes(node3, btn);
										Element buttonXML = btn.getXMLElement();
										groupXML.appendChild(buttonXML);
									} else if (nodeButton.getValue().equals("Check Box")) {
										ExtendedTreeItem<String> node3 = (ExtendedTreeItem<String>) nodeButton;
										CheckBox btn = new CheckBox(doc);
										fillAttributes(node3, btn);
										Element buttonXML = btn.getXMLElement();
										groupXML.appendChild(buttonXML);
									} else if (nodeButton.getValue().equals("Label Control")) {
										ExtendedTreeItem<String> node3 = (ExtendedTreeItem<String>) nodeButton;
										LabelControl btn = new LabelControl(doc);
										fillAttributes(node3, btn);
										Element buttonXML = btn.getXMLElement();
										groupXML.appendChild(buttonXML);
									} else if (nodeButton.getValue().equals("Toggle Button")) {
										ExtendedTreeItem<String> node3 = (ExtendedTreeItem<String>) nodeButton;
										ToggleButton btn = new ToggleButton(doc);
										fillAttributes(node3, btn);
										Element buttonXML = btn.getXMLElement();
										groupXML.appendChild(buttonXML);
									} else if (nodeButton.getValue().equals("Edit Box")) {
										ExtendedTreeItem<String> node3 = (ExtendedTreeItem<String>) nodeButton;
										EditBox btn = new EditBox(doc);
										fillAttributes(node3, btn);
										Element buttonXML = btn.getXMLElement();
										groupXML.appendChild(buttonXML);
									} else if (nodeButton.getValue().equals("Separator")) {
										ExtendedTreeItem<String> node3 = (ExtendedTreeItem<String>) nodeButton;
										Separator btn = new Separator(doc);
										fillAttributes(node3, btn);
										Element buttonXML = btn.getXMLElement();
										groupXML.appendChild(buttonXML);
									} else if (nodeButton.getValue().equals("Combo Box")) {
										ExtendedTreeItem<String> node3 = (ExtendedTreeItem<String>) nodeButton;
										ComboBox btn = new ComboBox(doc);
										fillAttributes(node3, btn);
										Element buttonXML = btn.getXMLElement();
										groupXML.appendChild(buttonXML);

										for (TreeItem<String> nodeItem : nodeButton.getChildren()) {
											if (nodeItem.getValue().equals("Item")) {
												ExtendedTreeItem<String> nodeIt = (ExtendedTreeItem<String>) nodeItem;
												Item itm = new Item(doc);
												fillAttributes(nodeIt, itm);
												Element itemXML = itm.getXMLElement();
												buttonXML.appendChild(itemXML);
											}
										}
									} else if (nodeButton.getValue().equals("Gallery")) {
										ExtendedTreeItem<String> node3 = (ExtendedTreeItem<String>) nodeButton;
										Gallery btn = new Gallery(doc);
										fillAttributes(node3, btn);
										Element buttonXML = btn.getXMLElement();
										groupXML.appendChild(buttonXML);

										for (TreeItem<String> nodeItem : nodeButton.getChildren()) {
											if (nodeItem.getValue().equals("Item")) {
												ExtendedTreeItem<String> nodeIt = (ExtendedTreeItem<String>) nodeItem;
												Item itm = new Item(doc);
												fillAttributes(nodeIt, itm);
												Element itemXML = itm.getXMLElement();
												buttonXML.appendChild(itemXML);
											}
										}
									} else if (nodeButton.getValue().equals("Dialog Box Launcher")) {
										ExtendedTreeItem<String> node3 = (ExtendedTreeItem<String>) nodeButton;
										DialogBoxLauncher btn = new DialogBoxLauncher(doc);
										fillAttributes(node3, btn);
										Element buttonXML = btn.getXMLElement();
										groupXML.appendChild(buttonXML);

										for (TreeItem<String> nodeItem : nodeButton.getChildren()) {
											if (nodeItem.getValue().equals("Button")) {
												ExtendedTreeItem<String> nodeIt = (ExtendedTreeItem<String>) nodeItem;
												UnsizedButton itm = new UnsizedButton(doc);
												fillAttributes(nodeIt, itm);
												Element itemXML = itm.getXMLElement();
												buttonXML.appendChild(itemXML);
											}
										}
									} else if (nodeButton.getValue().equals("Button Group")) {
										ExtendedTreeItem<String> node3 = (ExtendedTreeItem<String>) nodeButton;
										ButtonGroup btn = new ButtonGroup(doc);
										fillAttributes(node3, btn);
										Element buttonXML = btn.getXMLElement();
										groupXML.appendChild(buttonXML);

										for (TreeItem<String> nodeItem : nodeButton.getChildren()) {
											if (nodeItem.getValue().equals("Button")) { // Unsized
																						// Button
												ExtendedTreeItem<String> nodeIt = (ExtendedTreeItem<String>) nodeItem;
												UnsizedButton itm = new UnsizedButton(doc);
												fillAttributes(nodeIt, itm);
												Element itemXML = itm.getXMLElement();
												buttonXML.appendChild(itemXML);
											} else if (nodeItem.getValue().equals("Gallery")) { // Unsized
																								// Gallery
												ExtendedTreeItem<String> nodeIt = (ExtendedTreeItem<String>) nodeItem;
												UnsizedGallery itm = new UnsizedGallery(doc);
												fillAttributes(nodeIt, itm);
												Element itemXML = itm.getXMLElement();
												buttonXML.appendChild(itemXML);

												for (TreeItem<String> nodeItem2 : nodeItem.getChildren()) {
													if (nodeItem2.getValue().equals("Item")) {
														ExtendedTreeItem<String> nodeIt2 = (ExtendedTreeItem<String>) nodeItem2;
														Item itm2 = new Item(doc);
														fillAttributes(nodeIt2, itm2);
														Element itemXML2 = itm2.getXMLElement();
														itemXML.appendChild(itemXML2);
													}
												}
											} else if (nodeItem.getValue().equals("Toggle Button")) { // Unsized
																										// ToggleButton
												ExtendedTreeItem<String> nodeIt = (ExtendedTreeItem<String>) nodeItem;
												UnsizedToggleButton itm = new UnsizedToggleButton(doc);
												fillAttributes(nodeIt, itm);
												Element itemXML = itm.getXMLElement();
												buttonXML.appendChild(itemXML);
											}
										}

									} else if (nodeButton.getValue().equals("Menu")) {
										ExtendedTreeItem<String> node3 = (ExtendedTreeItem<String>) nodeButton;
										Menu btn = new Menu(doc);
										fillAttributes(node3, btn);
										Element buttonXML = btn.getXMLElement();
										groupXML.appendChild(buttonXML);

										for (TreeItem<String> nodeItem : nodeButton.getChildren()) {
											if (nodeItem.getValue().equals("Button")) { // Unsized
																						// Button
												ExtendedTreeItem<String> nodeIt = (ExtendedTreeItem<String>) nodeItem;
												UnsizedButton itm = new UnsizedButton(doc);
												fillAttributes(nodeIt, itm);
												Element itemXML = itm.getXMLElement();
												buttonXML.appendChild(itemXML);
											} else if (nodeItem.getValue().equals("Check Box")) { // Check
																									// Box
												ExtendedTreeItem<String> nodeIt = (ExtendedTreeItem<String>) nodeItem;
												CheckBox itm = new CheckBox(doc);
												fillAttributes(nodeIt, itm);
												Element itemXML = itm.getXMLElement();
												buttonXML.appendChild(itemXML);
											} else if (nodeItem.getValue().equals("Gallery")) { // Unsized
																								// Gallery
												ExtendedTreeItem<String> nodeIt = (ExtendedTreeItem<String>) nodeItem;
												UnsizedGallery itm = new UnsizedGallery(doc);
												fillAttributes(nodeIt, itm);
												Element itemXML = itm.getXMLElement();
												buttonXML.appendChild(itemXML);

												for (TreeItem<String> nodeItem2 : nodeItem.getChildren()) {
													if (nodeItem2.getValue().equals("Item")) {
														ExtendedTreeItem<String> nodeIt2 = (ExtendedTreeItem<String>) nodeItem2;
														Item itm2 = new Item(doc);
														fillAttributes(nodeIt2, itm2);
														Element itemXML2 = itm2.getXMLElement();
														itemXML.appendChild(itemXML2);
													}
												}
											} else if (nodeItem.getValue().equals("Toggle Button")) { // Unsized
																										// ToggleButton
												ExtendedTreeItem<String> nodeIt = (ExtendedTreeItem<String>) nodeItem;
												UnsizedToggleButton itm = new UnsizedToggleButton(doc);
												fillAttributes(nodeIt, itm);
												Element itemXML = itm.getXMLElement();
												buttonXML.appendChild(itemXML);
											} else if (nodeItem.getValue().equals("Separator")) { // Menu
																									// Separator
												ExtendedTreeItem<String> nodeIt = (ExtendedTreeItem<String>) nodeItem;
												MenuSeparator itm = new MenuSeparator(doc);
												fillAttributes(nodeIt, itm);
												Element itemXML = itm.getXMLElement();
												buttonXML.appendChild(itemXML);
											}
										}

									}
								}
							}
						}
					}
				}
			}
		}
		return doc;
	}

	/**
	 * Fill attributes of TreeView. Used in generateXML method.
	 * 
	 * @param node1
	 *            contain data to copy
	 * @param tab
	 *            here data are paste
	 */
	private static void fillAttributes(ExtendedTreeItem<String> node1, SimpleRibbonContainer tab) {
		for (Entry<String, String> n : node1.getSimpleRibbonContainer().getAttributes().entrySet()) {
			tab.setAttribute(n.getKey(), n.getValue());
		}
	}

	/**
	 * Calculate numberOfImages and fill images Map
	 * 
	 * @param root
	 *            treeView
	 */
	private static void refreshImagesMap(TreeItem<String> root) {
		for (TreeItem<String> child : root.getChildren()) {
			if (child.isLeaf()) {
				if (child.getValue().equals("Image")) {
					ExtendedTreeItem<String> node1 = (ExtendedTreeItem<String>) child;
					Image img = new Image();
					for (Entry<String, String> n : node1.getSimpleRibbonContainer().getAttributes().entrySet()) {
						img.setAttribute(n.getKey(), n.getValue());
					}
					images.put(numberOfImages, img);
					++numberOfImages;
				}
			} else {
				refreshImagesMap(child);
			}
		}
	}
}
