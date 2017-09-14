package ribbonElements.separator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ribbonElements.SimpleRibbonContainer;

/**
 * Menu Separator
 * 
 * @author Marcin
 *
 */
public class MenuSeparator implements SimpleRibbonContainer {
	private Map<String, String> attributes = new HashMap<String, String>();
	private Document doc;
	private List<SimpleRibbonContainer> groups = new ArrayList<SimpleRibbonContainer>();

	/**
	 * constructor
	 * 
	 * @param doc
	 *            reference to the XML document in which button will be created
	 */
	public MenuSeparator(Document doc) {
		this.doc = doc;
		fillMap();
	}

	/**
	 * constructor
	 * 
	 */
	public MenuSeparator() {
		fillMap();
	}

	/**
	 * fill map 'attributes'
	 */
	public void fillMap() {
		attributes.put("getVisible", null); // callback
		attributes.put("id", null); // control identifier
		attributes.put("idQ", null); // qualified control identifier
		attributes.put("insertAfterMso", null); // identifier of built-in
												// control to insert after
		attributes.put("insertAfterQ", null); // qualified identifier of control
												// to insert after
		attributes.put("insertBeforeMso", null); // identifier of built-in
													// control to insert before
		attributes.put("insertBeforeQ", null); // qualified identifier of
												// control to insert before
		attributes.put("visible", null); // control visibility
	}

	/**
	 * @inheritDoc
	 */
	public void setAttribute(String attribute, String value) {
		attributes.put(attribute, value);
	}

	/**
	 * @inheritDoc
	 */
	public String getAttributeValue(String attribute) {
		return attributes.get(attribute);
	}

	/**
	 * @inheritDoc
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}

	/**
	 * @inheritDoc
	 */
	public Element getXMLElement() {
		Element menuSeparator = doc.createElement("menuSeparator");
		for (Entry<String, String> i : attributes.entrySet()) {
			if (i.getValue() != null) {
				menuSeparator.setAttribute(i.getKey(), i.getValue());
			}
		}
		return menuSeparator;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "Menu Separator";
	}

	/**
	 * @inheritDoc
	 */
	public void addChild(SimpleRibbonContainer group) {
		groups.add(group);
	}

}
