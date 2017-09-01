package ribbonElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Tab
 * 
 * @author Marcin
 *
 */
public class Tab implements SimpleRibbonContainer, SimpleRibbonElement {
	private Map<String, String> attributes = new HashMap<String, String>();
	private Document doc;
	private List<SimpleRibbonElement> groups = new ArrayList<SimpleRibbonElement>();

	/**
	 * @inheritDoc
	 */
	public void addChild(SimpleRibbonElement group) {
		groups.add(group);
	}

	/**
	 * constructor
	 * 
	 * 
	 */
	public Tab() {
		fillMap();
	}

	/**
	 * constructor
	 * 
	 * @param doc
	 *            reference to the XML document in which button will be created
	 */
	public Tab(Document doc) {
		this.doc = doc;
		fillMap();
	}

	/**
	 * fill Map 'attributes'
	 */
	public void fillMap() {
		attributes.put("getKeyTip", null); // callback
		attributes.put("getLabel", null); // callback
		attributes.put("getVisible", null); // callback
		attributes.put("id", null); // control identifier
		attributes.put("idMso", null); // built-in control identifier
		attributes.put("idQ", null); // qualified control identifier
		attributes.put("insertAfterMso", null); // identifier of built-in
												// control to insert after
		attributes.put("insertAfterQ", null); // qualified identifier of control
												// to insert after
		attributes.put("insertBeforeMso", null); // identifier of built-in
													// control to insert before
		attributes.put("insertBeforeQ", null); // qualified identifier of
												// control to insert before
		attributes.put("keytip", null); // keytip
		attributes.put("label", null); // label
		attributes.put("tag", null); // tag
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
	public Element getSimpleRibbonContainerElement() {
		Element tab = doc.createElement("tab");
		for (Entry<String, String> i : attributes.entrySet()) {
			if (i.getValue() != null) {
				tab.setAttribute(i.getKey(), i.getValue());
			}
		}
		return tab;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "Tab";
	}

	/**
	 * @inheritDoc
	 */
	public Element getXMLElement() {
		Element group = doc.createElement("tab");
		for (Entry<String, String> i : attributes.entrySet()) {
			if (i.getValue() != null) {
				group.setAttribute(i.getKey(), i.getValue());
			}
		}
		return group;
	}

}
