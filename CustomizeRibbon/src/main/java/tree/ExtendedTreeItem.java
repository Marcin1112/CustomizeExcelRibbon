package tree;

import javafx.scene.control.TreeItem;
import ribbonElements.SimpleRibbonContainer;
import ribbonElements.SimpleRibbonElement;

/**
 * Item that contain additional information
 * 
 * @author Marcin
 *
 * @param <String>
 *            Name of item
 */
public class ExtendedTreeItem<String> extends TreeItem<String> {
	SimpleRibbonElement simpleRibbonElement;
	SimpleRibbonContainer simpleRibbonContainer;

	/**
	 * Constructor
	 * 
	 * @param itemText
	 *            String
	 */
	public ExtendedTreeItem(String itemText) {
		super(itemText);
	}

	/**
	 * Getter
	 * 
	 * @return SimpleRibbonElement
	 */
	public SimpleRibbonElement getSimpleRibbonElement() {
		return simpleRibbonElement;
	}

	/**
	 * Setter
	 * 
	 * @param simpleRibbonElement
	 *            set SimpleRibbonElement
	 */
	public void setSimpleRibbonElement(SimpleRibbonElement simpleRibbonElement) {
		this.simpleRibbonElement = simpleRibbonElement;
	}

	/**
	 * Getter
	 * 
	 * @return simpleRibbonContainer
	 */
	public SimpleRibbonContainer getSimpleRibbonContainer() {
		return simpleRibbonContainer;
	}

	/**
	 * Setter
	 * 
	 * @param simpleRibbonContainer
	 *            Set simpleRibbonContainer
	 */
	public void setSimpleRibbonContainer(SimpleRibbonContainer simpleRibbonContainer) {
		this.simpleRibbonContainer = simpleRibbonContainer;
	}
}
