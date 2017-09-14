package ribbonElements;

import java.util.Map;

import org.w3c.dom.Element;

/**
 * Simple ribbon container
 * 
 * @author Marcin
 *
 */
public interface SimpleRibbonContainer {
	/**
	 * Set attribute of container
	 * 
	 * @param attribute
	 *            key
	 * @param value
	 *            value
	 */
	public void setAttribute(String attribute, String value);

	/**
	 * Get attribute of container
	 * 
	 * @param attribute
	 *            key
	 * @return value
	 */
	public String getAttributeValue(String attribute);

	/**
	 * Get all attributes of container
	 * 
	 * @return attributes
	 */
	public Map<String, String> getAttributes();

	/**
	 * Add child to the object
	 * 
	 * @param element
	 *            a child
	 */
	public void addChild(SimpleRibbonContainer element);

	/**
	 * Generate Element necessary to build final XML file that describes
	 * customized Ribbon
	 * 
	 * @return Element
	 */
	public Element getXMLElement();
}
