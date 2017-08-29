package ribbonElements;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
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
	 * Generate Element necessary to build final XML file that describes
	 * customized Ribbon
	 * 
	 * @return Element
	 */
	public Element getSimpleRibbonContainerElement();

	/**
	 * Add child to the object
	 * 
	 * @param element
	 *            a child
	 */
	public void addChild(SimpleRibbonElement element);

	/**
	 * Get list of available childreen
	 * 
	 * @return List<String>
	 */
	public List<String> getListOfAvailableChildreen();

}
