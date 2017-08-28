package tree;

import javafx.scene.control.TreeItem;
import ribbonElements.SimpleRibbonContainer;
import ribbonElements.SimpleRibbonElement;

public class ExtendedTreeItem<String> extends TreeItem<String> {
	SimpleRibbonElement simpleRibbonElement;
	SimpleRibbonContainer simpleRibbonContainer;
	public ExtendedTreeItem(String itemText){
		super(itemText);
	}
	public SimpleRibbonElement getSimpleRibbonElement() {
		return simpleRibbonElement;
	}
	public void setSimpleRibbonElement(SimpleRibbonElement simpleRibbonElement) {
		this.simpleRibbonElement = simpleRibbonElement;
	}
	public SimpleRibbonContainer getSimpleRibbonContainer() {
		return simpleRibbonContainer;
	}
	public void setSimpleRibbonContainer(SimpleRibbonContainer simpleRibbonContainer) {
		this.simpleRibbonContainer = simpleRibbonContainer;
	}
}
