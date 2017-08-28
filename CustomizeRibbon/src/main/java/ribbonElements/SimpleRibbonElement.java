package ribbonElements;

import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Simple ribbon control
 * 
 * @author Marcin
 *
 */
public interface SimpleRibbonElement {
	/**
	 * Set attribute of control
	 * 
	 * @param attribute
	 *            key
	 * @param value
	 *            value
	 */
	public void setAttribute(String attribute, String value);

	/**
	 * Get attribute of control
	 * 
	 * @param attribute
	 *            key
	 * @return value
	 */
	public String getAttributeValue(String attribute);

	/**
	 * Get all attributes of control
	 * 
	 * @return attributes
	 */
	public Map<String, String> getAttributes();

	/**
	 * Generate Element necessary to build final XML file that describes
	 * customized Ribbon
	 * 
	 * @return Element
	 */
	public Element getXMLElement();
}
