package view;

import javafx.fxml.FXML;
import javafx.geometry.Insets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ribbonElements.Group;
import ribbonElements.SimpleRibbonContainer;
import ribbonElements.Tab;
import ribbonElements.Tabs;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import services.RibbonExcelImpl;
import tree.ExtendedTreeItem;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;

public class MainWindowController {
	@FXML
	private TreeView<String> treeView;
	@FXML
	private TreeView<String> listOfAvailableControls;
	@FXML
	private Menu file;
	@FXML
	private MenuItem close;
	@FXML
	private Button add;
	@FXML
	private VBox vBox;

	Map<String, String> propertiesMap = new HashMap<String, String>();

	public void initialize() {
		fillTree();
		fillRibbon();
	}

	// Event Listener on MenuItem[#close].onAction
	@FXML
	public void loadTree(ActionEvent event)
			throws IOException, TransformerException, ParserConfigurationException, SAXException {
		RibbonExcelImpl ribbon = new RibbonExcelImpl();
		ribbon.setPathToExcelFile("C:\\Users\\Marcin\\Desktop\\plik.xlsm");
		ribbon.extractFiles();
		ribbon.createEmptyRibbon();
		ribbon.writeXML(treeView);
		ribbon.buildExcelFile();
		ribbon.deleteDirectory();
	}

	@FXML
	public void addToRibbon(ActionEvent event) {
		ExtendedTreeItem<String> root = (ExtendedTreeItem<String>) treeView.getRoot();
		ExtendedTreeItem<String> selectedItem = (ExtendedTreeItem<String>) listOfAvailableControls.getSelectionModel()
				.getSelectedItem();
		ExtendedTreeItem<String> selectedItemRibbon = (ExtendedTreeItem<String>) treeView.getSelectionModel()
				.getSelectedItem();
		if (selectedItem != null & selectedItemRibbon != null) {
			if (selectedItem.getValue().equals("Tab")) {
				if (selectedItemRibbon.getValue().equals("Ribbon")) { // Add tab
																		// to
																		// ribbon
					ExtendedTreeItem<String> itemChild1 = new ExtendedTreeItem<String>("Tab");
					itemChild1.setSimpleRibbonElement(new Tab());
					itemChild1.setExpanded(true);
					selectedItemRibbon.getChildren().add(itemChild1);
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("An error occurred");
					alert.setContentText("You can add 'Tab' to the 'Ribbon' only.");
					alert.showAndWait();
				}
			} else if (selectedItem.getValue().equals("Group")) { // Add group
																	// to tab
				if (selectedItemRibbon.getValue().equals("Tab")) {
					ExtendedTreeItem<String> itemChild1 = new ExtendedTreeItem<String>("Group");
					itemChild1.setSimpleRibbonElement(new Group());
					itemChild1.setExpanded(true);
					selectedItemRibbon.getChildren().add(itemChild1);
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("An error occurred");
					alert.setContentText("You can add 'Group' to the 'Tab' only.");
					alert.showAndWait();
				}
			} else if (selectedItem.getValue().equals("Button")) { // Add button
																	// to group
				if (selectedItemRibbon.getValue().equals("Group")) {
					ExtendedTreeItem<String> itemChild1 = new ExtendedTreeItem<String>("Button");
					itemChild1.setSimpleRibbonElement(new ribbonElements.Button());
					itemChild1.setExpanded(true);
					selectedItemRibbon.getChildren().add(itemChild1);
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("An error occurred");
					alert.setContentText("You can add 'Button' to the 'Group' only.");
					alert.showAndWait();
				}
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("An error occurred");
			alert.setContentText("You have to select components on the left and right side.");
			alert.showAndWait();
		}
	}

	public void fillTree() {
		ExtendedTreeItem<String> root = new ExtendedTreeItem<String>("Root");
		root.setExpanded(true);
		// create child
		ExtendedTreeItem<String> itemChild1 = new ExtendedTreeItem<String>("Tab");
		itemChild1.setExpanded(true);

		ExtendedTreeItem<String> itemChild2 = new ExtendedTreeItem<String>("Group");
		itemChild2.setExpanded(true);

		ExtendedTreeItem<String> itemChild3 = new ExtendedTreeItem<String>("Button");
		itemChild3.setExpanded(true);

		// root is the parent of itemChild
		root.getChildren().add(itemChild1);
		root.getChildren().add(itemChild2);
		root.getChildren().add(itemChild3);
		listOfAvailableControls.setRoot(root);
		listOfAvailableControls.setShowRoot(false);
	}

	private void fillRibbon() {
		ExtendedTreeItem<String> root = new ExtendedTreeItem<String>("Ribbon");
		root.setExpanded(true);
		treeView.setRoot(root);
		treeView.setShowRoot(true);
		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				ExtendedTreeItem<String> selectedItem = (ExtendedTreeItem<String>) newValue;
				ExtendedTreeItem<String> selectedOldValue = (ExtendedTreeItem<String>) oldValue;
				saveOldValue(selectedOldValue);
				onChange(selectedItem);
			}

		});
	}

	private void saveOldValue(ExtendedTreeItem<String> selectedOldValue) {

	}

	private void onChange(ExtendedTreeItem<String> selectedItem) {
		vBox.getChildren().clear();
		vBox.setPadding(new Insets(10));
		propertiesMap.clear();
		try {
			Map<String, String> map = selectedItem.getSimpleRibbonElement().getAttributes();
			for (Entry<String, String> element : map.entrySet()) {
				propertiesMap.put(element.getKey(), element.getValue());
				Label label1 = new Label(element.getKey());
				label1.setFont(new Font(16));
				label1.setPrefWidth(150);
				TextField txt = new TextField(element.getValue());
				txt.setFont(new Font(16));
				HBox hBox = new HBox();
				hBox.setPadding(new Insets(4));
				hBox.getChildren().add(label1);
				hBox.getChildren().add(txt);
				vBox.getChildren().add(hBox);
			}
		} catch (Exception e) {
		}
	}
}
