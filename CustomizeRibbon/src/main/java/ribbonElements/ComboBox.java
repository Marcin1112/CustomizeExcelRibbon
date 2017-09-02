package ribbonElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Combo Box
 * 
 * @author Marcin
 *
 */
public class ComboBox implements SimpleRibbonElement, SimpleRibbonContainer {
	private Map<String, String> attributes = new HashMap<String, String>();
	private Document doc;
	private List<SimpleRibbonElement> groups = new ArrayList<SimpleRibbonElement>();

	/**
	 * constructor
	 * 
	 * @param doc
	 *            reference to the XML document in which button will be created
	 */
	public ComboBox(Document doc) {
		this.doc = doc;
		fillMap();
	}

	/**
	 * constructor
	 * 
	 */
	public ComboBox() {
		fillMap();
	}

	/**
	 * fill map 'attributes'
	 */
	public void fillMap() {
		attributes.put("enabled", null); // enabled state
		attributes.put("getEnabled", null); // callback
		attributes.put("getImage", null); // callback
		attributes.put("getItemCount", null); // callback
		attributes.put("getItemID", null); // callback
		attributes.put("getItemImage", null); // callback
		attributes.put("getItemLabel", null); // callback
		attributes.put("getItemScreentip", null); // callback
		attributes.put("getItemSupertip", null); // callback
		attributes.put("getKeyTip", null); // callback
		attributes.put("getLabel", null); // callback
		attributes.put("getScreentip", null); // callback
		attributes.put("getShowImage", null); // callback
		attributes.put("getShowLabel", null); // callback
		attributes.put("getSupertip", null); // callback
		attributes.put("getText", null); // callback
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
		attributes.put("invalidateContentOnDrop", null); // invalidate content
															// on drop
		attributes.put("keytip", null); // keytip
		attributes.put("label", null); // label
		attributes.put("maxLength", null); // maximum input string length
		attributes.put("onChange", null); // callback
		attributes.put("screentip", null); // screentip
		attributes.put("showImage", null); // show image
		attributes.put("showItemImage", null); // show item image
		attributes.put("showLabel", null); // show label
		attributes.put("sizeString", null); // size string
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
		Element comboBox = doc.createElement("comboBox");
		for (Entry<String, String> i : attributes.entrySet()) {
			if (i.getValue() != null) {
				comboBox.setAttribute(i.getKey(), i.getValue());
			}
		}
		return comboBox;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "Combo Box";
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Element getSimpleRibbonContainerElement() {
		Element comboBox = doc.createElement("comboBox");
		for (Entry<String, String> i : attributes.entrySet()) {
			if (i.getValue() != null) {
				comboBox.setAttribute(i.getKey(), i.getValue());
			}
		}
		return comboBox;
	}

	/**
	 * @inheritDoc
	 */
	public void addChild(SimpleRibbonElement group) {
		groups.add(group);
	}

}