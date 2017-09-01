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
	private List<SimpleRibbonElement> groups = new ArrayList<SimpleRibbonElement>();

	/**
	 * @inheritDoc
	 */
	public void addChild(SimpleRibbonElement group) {
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
	 * @inheritDoc
	 */
	public Element getSimpleRibbonContainerElement() {
		return null;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "Tabs";
	}

}
