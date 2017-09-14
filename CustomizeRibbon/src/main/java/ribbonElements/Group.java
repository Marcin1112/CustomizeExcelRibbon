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
public class Group implements SimpleRibbonContainer{
	private Map<String, String> attributes = new HashMap<String, String>();
	private Document doc;
	private List<SimpleRibbonContainer> groups = new ArrayList<SimpleRibbonContainer>();

	/**
	 * @inheritDoc
	 */
	public void addChild(SimpleRibbonContainer group) {
		groups.add(group);
	}

	/**
	 * constructor
	 * 
	 * @param doc
	 *            reference to the XML document in which button will be created
	 */
	public Group(Document doc) {
		this.doc = doc;
		fillMap();
	}

	/**
	 * constructor
	 * 
	 */
	public Group() {
		fillMap();
	}

	/**
	 * fill map 'attributes'
	 */
	public void fillMap() {
		attributes.put("getImage", null); // callback
		attributes.put("getKeytip", null); // callback
		attributes.put("getLabel", null); // callback
		attributes.put("getScreentip", null); // callback
		attributes.put("getSuperTip", null); // callback
		attributes.put("getVisible", null); // callback
		attributes.put("id", null); // control identifier
		attributes.put("idMso", null); // built-in control identifier
		attributes.put("idQ", null); // qualified control identifier
		attributes.put("image", null); // custom image identifier
		attributes.put("imageMso", null); // built-in image identifier
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
		attributes.put("screentip", null); // screentip
		attributes.put("supertip", null); // supertip
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
	public Element getXMLElement() {
		Element group = doc.createElement("group");
		for (Entry<String, String> i : attributes.entrySet()) {
			if (i.getValue() != null) {
				group.setAttribute(i.getKey(), i.getValue());
			}
		}
		return group;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "Group";
	}
}
