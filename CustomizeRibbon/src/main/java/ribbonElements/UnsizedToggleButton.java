package ribbonElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Unsized Toggle Button
 * 
 * @author Marcin
 *
 */
public class UnsizedToggleButton implements SimpleRibbonElement, SimpleRibbonContainer {
	private Map<String, String> attributes = new HashMap<String, String>();
	private Document doc;
	private List<SimpleRibbonElement> groups = new ArrayList<SimpleRibbonElement>();

	/**
	 * constructor
	 * 
	 * @param doc
	 *            reference to the XML document in which button will be created
	 */
	public UnsizedToggleButton(Document doc) {
		this.doc = doc;
		fillMap();
	}

	/**
	 * constructor
	 * 
	 */
	public UnsizedToggleButton() {
		fillMap();
	}

	/**
	 * fill map 'attributes'
	 */
	public void fillMap() {
		attributes.put("description", null); // description
		attributes.put("enabled", null); // enabled state
		attributes.put("getDescription", null); // callback
		attributes.put("getEnabled", null); // callback
		attributes.put("getImage", null); // callback
		attributes.put("getKeytip", null); // callback
		attributes.put("getLabel", null); // callback
		attributes.put("getPressed", null); // callback
		attributes.put("getScreentip", null); // callback
		attributes.put("getShowImage", null); // callback
		attributes.put("getShowLabel", null); // callback
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
		attributes.put("onAction", null); // callback
		attributes.put("screentip", null); // screentip
		attributes.put("showImage", null); // show image
		attributes.put("showLabel", null); // show label
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
		Element toggleButton = doc.createElement("toggleButton");
		for (Entry<String, String> i : attributes.entrySet()) {
			if (i.getValue() != null) {
				toggleButton.setAttribute(i.getKey(), i.getValue());
			}
		}
		return toggleButton;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "ToggleButton";
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Element getSimpleRibbonContainerElement() {
		Element toggleButton = doc.createElement("toggleButton");
		for (Entry<String, String> i : attributes.entrySet()) {
			if (i.getValue() != null) {
				toggleButton.setAttribute(i.getKey(), i.getValue());
			}
		}
		return toggleButton;
	}

	/**
	 * @inheritDoc
	 */
	public void addChild(SimpleRibbonElement group) {
		groups.add(group);
	}

}
