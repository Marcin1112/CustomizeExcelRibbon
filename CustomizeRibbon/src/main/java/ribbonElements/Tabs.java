package ribbonElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Tabs
 * 
 * @author Marcin
 *
 */
public class Tabs implements SimpleRibbonContainer {
	private Map<String, String> attributes = new HashMap<String, String>();
	private List<SimpleRibbonContainer> groups = new ArrayList<SimpleRibbonContainer>();
	private Document doc;

	/**
	 * constructor
	 * 
	 * 
	 */
	public Tabs() {
		fillMap();
	}

	/**
	 * constructor
	 * 
	 * @param doc
	 *            reference to the XML document in which button will be created
	 */
	public Tabs(Document doc) {
		this.doc = doc;
		fillMap();
	}

	/**
	 * fill Map 'attributes'
	 */
	private void fillMap() {
		MsoControls.getTabs().forEach((element) -> attributes.put(element, null));
	}

	/**
	 * @inheritDoc
	 */
	public void addChild(SimpleRibbonContainer group) {
		groups.add(group);
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
	 * toString method
	 */
	@Override
	public String toString() {
		return "Tabs";
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Element getXMLElement() {
		Element group = doc.createElement("tabs");
		return group;
	}

}
