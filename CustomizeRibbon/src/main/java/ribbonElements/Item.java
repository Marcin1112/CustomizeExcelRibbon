package ribbonElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * item
 * 
 * @author Marcin
 *
 */
public class Item implements SimpleRibbonElement, SimpleRibbonContainer {
	private Map<String, String> attributes = new HashMap<String, String>();
	private Document doc;
	private List<SimpleRibbonElement> groups = new ArrayList<SimpleRibbonElement>();

	/**
	 * constructor
	 * 
	 * @param doc
	 *            reference to the XML document in which button will be created
	 */
	public Item(Document doc) {
		this.doc = doc;
		fillMap();
	}

	/**
	 * constructor
	 * 
	 */
	public Item() {
		fillMap();
	}

	/**
	 * fill map 'attributes'
	 */
	public void fillMap() {
		attributes.put("id", null); // control identifier
		attributes.put("image", null); // custom image identifier
		attributes.put("imageMso", null); // built-in image identifier
		attributes.put("label", null); // label
		attributes.put("screentip", null); // screentip
		attributes.put("supertip", null); // supertip

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
		Element item = doc.createElement("item");
		for (Entry<String, String> i : attributes.entrySet()) {
			if (i.getValue() != null) {
				item.setAttribute(i.getKey(), i.getValue());
			}
		}
		return item;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "Item";
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Element getSimpleRibbonContainerElement() {
		Element item = doc.createElement("item");
		for (Entry<String, String> i : attributes.entrySet()) {
			if (i.getValue() != null) {
				item.setAttribute(i.getKey(), i.getValue());
			}
		}
		return item;
	}

	/**
	 * @inheritDoc
	 */
	public void addChild(SimpleRibbonElement group) {
		groups.add(group);
	}

}
