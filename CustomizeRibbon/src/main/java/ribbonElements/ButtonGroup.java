package ribbonElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Button Group
 * 
 * @author Marcin
 *
 */
public class ButtonGroup implements SimpleRibbonElement, SimpleRibbonContainer {
	private Map<String, String> attributes = new HashMap<String, String>();
	private Document doc;
	private List<SimpleRibbonElement> groups = new ArrayList<SimpleRibbonElement>();

	/**
	 * constructor
	 * 
	 * @param doc
	 *            reference to the XML document in which button will be created
	 */
	public ButtonGroup(Document doc) {
		this.doc = doc;
		fillMap();
	}

	/**
	 * constructor
	 * 
	 */
	public ButtonGroup() {
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
		Element buttonGroup = doc.createElement("buttonGroup");
		for (Entry<String, String> i : attributes.entrySet()) {
			if (i.getValue() != null) {
				buttonGroup.setAttribute(i.getKey(), i.getValue());
			}
		}
		return buttonGroup;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "Button Group";
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Element getSimpleRibbonContainerElement() {
		Element buttonGroup = doc.createElement("buttonGroup");
		for (Entry<String, String> i : attributes.entrySet()) {
			if (i.getValue() != null) {
				buttonGroup.setAttribute(i.getKey(), i.getValue());
			}
		}
		return buttonGroup;
	}

	/**
	 * @inheritDoc
	 */
	public void addChild(SimpleRibbonElement group) {
		groups.add(group);
	}
	
}
