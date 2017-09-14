package ribbonElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Standard button
 * 
 * @author Marcin
 *
 */
public class DialogBoxLauncher implements SimpleRibbonContainer {
	private Map<String, String> attributes = new HashMap<String, String>();
	private Document doc;
	private List<SimpleRibbonContainer> groups = new ArrayList<SimpleRibbonContainer>();

	/**
	 * constructor
	 * 
	 * @param doc
	 *            reference to the XML document in which button will be created
	 */
	public DialogBoxLauncher(Document doc) {
		this.doc = doc;
		fillMap();
	}

	/**
	 * constructor
	 * 
	 */
	public DialogBoxLauncher() {
		fillMap();
	}

	/**
	 * fill map 'attributes'
	 */
	public void fillMap() {
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
		Element dialogBoxLauncher = doc.createElement("dialogBoxLauncher");
		for (Entry<String, String> i : attributes.entrySet()) {
			if (i.getValue() != null) {
				dialogBoxLauncher.setAttribute(i.getKey(), i.getValue());
			}
		}
		return dialogBoxLauncher;
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "Dialog Box Launcher";
	}

	/**
	 * @inheritDoc
	 */
	public void addChild(SimpleRibbonContainer group) {
		groups.add(group);
	}
	
}
