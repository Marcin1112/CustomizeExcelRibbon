package view;

import javafx.fxml.FXML;
import javafx.geometry.Insets;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.stage.FileChooser;
import javafx.stage.Window;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;

/**
 * Main controller
 * 
 * @author Marcin
 *
 */
public class MainWindowController {
	@FXML
	private TreeView<String> treeView;
	@FXML
	private TreeView<String> listOfAvailableControls;
	@FXML
	private Menu file;
	@FXML
	private MenuItem save;
	@FXML
	private MenuItem about;
	@FXML
	private Button add;
	@FXML
	private VBox vBox;

	public void initialize() {
		fillTree();
		fillRibbon();
	}

	/**
	 * Event Listener on MenuItem[#save].onAction
	 * 
	 * @param event
	 *            ActionEvent
	 * @throws IOException
	 *             Exception
	 * @throws TransformerException
	 *             Exception
	 * @throws ParserConfigurationException
	 *             Exception
	 * @throws SAXException
	 *             Exception
	 */
	@FXML
	public void saveTree(ActionEvent event)
			throws IOException, TransformerException, ParserConfigurationException, SAXException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Excel file");
		File file = fileChooser.showOpenDialog(null);

		if (file != null) {
			String path = file.getAbsolutePath();
			
			RibbonExcelImpl ribbon = new RibbonExcelImpl();
			ribbon.setPathToExcelFile(path);
			ribbon.extractFiles();
			ribbon.createEmptyRibbon();
			ribbon.writeXML(treeView);
			ribbon.buildExcelFile();
			ribbon.deleteDirectory();
		}
	}

	/**
	 * Event Listener on MenuItem[#about].onAction
	 * 
	 * @param event
	 *            ActionEvent
	 */
	@FXML
	public void showAutor(ActionEvent event) {
		// TODO
	}

	/**
	 * Add child to the treeView
	 * 
	 * @param event
	 */
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
					alertError("Tab", "Ribbon");
				}
			} else if (selectedItem.getValue().equals("Group")) { // Add group
																	// to tab
				if (selectedItemRibbon.getValue().equals("Tab")) {
					ExtendedTreeItem<String> itemChild1 = new ExtendedTreeItem<String>("Group");
					itemChild1.setSimpleRibbonElement(new Group());
					itemChild1.setExpanded(true);
					selectedItemRibbon.getChildren().add(itemChild1);
				} else {
					alertError("Group", "Tab");
				}
			} else if (selectedItem.getValue().equals("Button")) { // Add button
																	// to group
				if (selectedItemRibbon.getValue().equals("Group")) {
					ExtendedTreeItem<String> itemChild1 = new ExtendedTreeItem<String>("Button");
					itemChild1.setSimpleRibbonElement(new ribbonElements.Button());
					itemChild1.setExpanded(true);
					selectedItemRibbon.getChildren().add(itemChild1);
				} else {
					alertError("Button", "Group");
				}
			} else if (selectedItem.getValue().equals("Check Box")) { // Add
																		// CheckBox
																		// to
																		// group
				if (selectedItemRibbon.getValue().equals("Group")) {
					ExtendedTreeItem<String> itemChild1 = new ExtendedTreeItem<String>("Check Box");
					itemChild1.setSimpleRibbonElement(new ribbonElements.CheckBox());
					itemChild1.setExpanded(true);
					selectedItemRibbon.getChildren().add(itemChild1);
				} else {
					alertError("Check Box", "Group");
				}
			} else if (selectedItem.getValue().equals("Toggle Button")) { // Add
																			// ToggleButton
																			// to
																			// group
				if (selectedItemRibbon.getValue().equals("Group")) {
					ExtendedTreeItem<String> itemChild1 = new ExtendedTreeItem<String>("Toggle Button");
					itemChild1.setSimpleRibbonElement(new ribbonElements.ToggleButton());
					itemChild1.setExpanded(true);
					selectedItemRibbon.getChildren().add(itemChild1);
				} else {
					alertError("Toggle Button", "Group");
				}
			} else if (selectedItem.getValue().equals("Edit Box")) { // Add
																		// EditBox
																		// to
																		// group
				if (selectedItemRibbon.getValue().equals("Group")) {
					ExtendedTreeItem<String> itemChild1 = new ExtendedTreeItem<String>("Edit Box");
					itemChild1.setSimpleRibbonElement(new ribbonElements.EditBox());
					itemChild1.setExpanded(true);
					selectedItemRibbon.getChildren().add(itemChild1);
				} else {
					alertError("Edit Box", "Group");
				}
			} else if (selectedItem.getValue().equals("Separator")) { // Add
																		// Separator
																		// to
																		// group
				if (selectedItemRibbon.getValue().equals("Group")) {
					ExtendedTreeItem<String> itemChild1 = new ExtendedTreeItem<String>("Separator");
					itemChild1.setSimpleRibbonElement(new ribbonElements.Separator());
					itemChild1.setExpanded(true);
					selectedItemRibbon.getChildren().add(itemChild1);
				} else {
					alertError("Separator", "Group");
				}
			} else if (selectedItem.getValue().equals("Dialog Box Launcher")) { // Add
				// DialogBoxLauncher
				// to
				// group
				if (selectedItemRibbon.getValue().equals("Group")) {
					ExtendedTreeItem<String> itemChild1 = new ExtendedTreeItem<String>("Dialog Box Launcher");
					itemChild1.setSimpleRibbonElement(new ribbonElements.DialogBoxLauncher());
					itemChild1.setExpanded(true);
					selectedItemRibbon.getChildren().add(itemChild1);
				} else {
					alertError("Dialog Box Launcher", "Group");
				}
			} else if (selectedItem.getValue().equals("Combo Box")) { // Add
																		// Combo
																		// Box
																		// to
																		// group
				if (selectedItemRibbon.getValue().equals("Group")) {
					ExtendedTreeItem<String> itemChild1 = new ExtendedTreeItem<String>("Combo Box");
					itemChild1.setSimpleRibbonElement(new ribbonElements.ComboBox());
					itemChild1.setExpanded(true);
					selectedItemRibbon.getChildren().add(itemChild1);
				} else {
					alertError("Combo Box", "Group");
				}
			} else if (selectedItem.getValue().equals("Gallery")) { // Add
																	// Gallery
																	// to group
				if (selectedItemRibbon.getValue().equals("Group")) {
					ExtendedTreeItem<String> itemChild1 = new ExtendedTreeItem<String>("Gallery");
					itemChild1.setSimpleRibbonElement(new ribbonElements.Gallery());
					itemChild1.setExpanded(true);
					selectedItemRibbon.getChildren().add(itemChild1);
				} else {
					alertError("Gallery", "Group");
				}
			} else if (selectedItem.getValue().equals("Item")) { // Add Item to
																	// Gallery
				if (selectedItemRibbon.getValue().equals("Gallery")) {
					ExtendedTreeItem<String> itemChild2 = new ExtendedTreeItem<String>("Item");
					itemChild2.setSimpleRibbonElement(new ribbonElements.Item());
					itemChild2.setExpanded(true);
					selectedItemRibbon.getChildren().add(itemChild2);
				} else {
					alertError("Item", "Gallery");
				}
			} else if (selectedItem.getValue().equals("Item")) { // Add Item to
																	// ComboBox
				if (selectedItemRibbon.getValue().equals("Combo Box")) {
					ExtendedTreeItem<String> itemChild2 = new ExtendedTreeItem<String>("Item");
					itemChild2.setSimpleRibbonElement(new ribbonElements.Item());
					itemChild2.setExpanded(true);
					selectedItemRibbon.getChildren().add(itemChild2);
				} else {
					alertError("Item", "Combo Box");
				}
			} else if (selectedItem.getValue().equals("Unsized Button")) { // Add
				// UnsizedButton
				// to
				// DialogBoxLauncher
				if (selectedItemRibbon.getValue().equals("Dialog Box Launcher")) {
					ExtendedTreeItem<String> itemChild2 = new ExtendedTreeItem<String>("Unsized Button");
					itemChild2.setSimpleRibbonElement(new ribbonElements.UnsizedButton());
					itemChild2.setExpanded(true);
					selectedItemRibbon.getChildren().add(itemChild2);
				} else {
					alertError("Unsized Button", "Dialog Box Launcher");
				}
			} else if (selectedItem.getValue().equals("Image")) { // Add image
				if (selectedItemRibbon.getValue().equals("Images")) {
					FileChooser fileChooser = new FileChooser(); // open
																	// fileChooser
					fileChooser.setTitle("Open Picture");
					fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"));
					File file = fileChooser.showOpenDialog(null);

					if (file != null) {
						String path = file.getAbsolutePath();
						ExtendedTreeItem<String> itemChild1 = new ExtendedTreeItem<String>("Image");
						ribbonElements.Image image = new ribbonElements.Image();
						image.setAttribute("pathToFile", path);
						itemChild1.setSimpleRibbonElement(image);
						itemChild1.setExpanded(true);
						selectedItemRibbon.getChildren().add(itemChild1);
					}
				} else {
					alertError("Image", "Images");
				}
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("An error occurred");
			alert.setContentText("You have to select components on the left and right side.");
			alert.showAndWait();
		}
	}

	/**
	 * @param child
	 *            String
	 * @param parent
	 *            String
	 */
	private void alertError(String child, String parent) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("An error occurred");
		alert.setContentText("You can add '" + child + "' to the '" + parent + "' only.");
		alert.showAndWait();
	}

	/**
	 * Initialize listOfAvailableControls
	 */
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

		ExtendedTreeItem<String> itemChild4 = new ExtendedTreeItem<String>("Check Box");
		itemChild4.setExpanded(true);

		ExtendedTreeItem<String> itemChild5 = new ExtendedTreeItem<String>("Image");
		itemChild5.setExpanded(true);

		ExtendedTreeItem<String> itemChild6 = new ExtendedTreeItem<String>("Label Control");
		itemChild6.setExpanded(true);

		ExtendedTreeItem<String> itemChild7 = new ExtendedTreeItem<String>("Toggle Button");
		itemChild7.setExpanded(true);

		ExtendedTreeItem<String> itemChild8 = new ExtendedTreeItem<String>("Edit Box");
		itemChild8.setExpanded(true);

		ExtendedTreeItem<String> itemChild9 = new ExtendedTreeItem<String>("Separator");
		itemChild9.setExpanded(true);

		ExtendedTreeItem<String> itemChild10 = new ExtendedTreeItem<String>("Combo Box");
		itemChild10.setExpanded(true);

		ExtendedTreeItem<String> itemChild11 = new ExtendedTreeItem<String>("Item");
		itemChild11.setExpanded(true);

		ExtendedTreeItem<String> itemChild12 = new ExtendedTreeItem<String>("Gallery");
		itemChild12.setExpanded(true);

		ExtendedTreeItem<String> itemChild13 = new ExtendedTreeItem<String>("Dialog Box Launcher");
		itemChild13.setExpanded(true);

		ExtendedTreeItem<String> itemChild14 = new ExtendedTreeItem<String>("Unsized Button");
		itemChild14.setExpanded(true);

		// root is the parent of itemChild
		root.getChildren().add(itemChild1);
		root.getChildren().add(itemChild2);
		root.getChildren().add(itemChild3);
		root.getChildren().add(itemChild4);
		root.getChildren().add(itemChild5);
		root.getChildren().add(itemChild6);
		root.getChildren().add(itemChild7);
		root.getChildren().add(itemChild8);
		root.getChildren().add(itemChild9);
		root.getChildren().add(itemChild10);
		root.getChildren().add(itemChild11);
		root.getChildren().add(itemChild12);
		root.getChildren().add(itemChild13);
		root.getChildren().add(itemChild14);
		listOfAvailableControls.setRoot(root);
		listOfAvailableControls.setShowRoot(false);
	}

	/**
	 * Initialize treeView
	 */
	private void fillRibbon() {
		ExtendedTreeItem<String> root = new ExtendedTreeItem<String>("Ribbon");
		root.setExpanded(true);
		treeView.setRoot(root);
		treeView.setShowRoot(false);

		ExtendedTreeItem<String> images = new ExtendedTreeItem<String>("Images");
		images.setExpanded(true);
		root.getChildren().add(images);

		ExtendedTreeItem<String> ribbon = new ExtendedTreeItem<String>("Ribbon");
		ribbon.setExpanded(true);
		root.getChildren().add(ribbon);

		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				ExtendedTreeItem<String> selectedItem = (ExtendedTreeItem<String>) newValue;
				ExtendedTreeItem<String> selectedOldValue = (ExtendedTreeItem<String>) oldValue;
				// saveOldValue(selectedOldValue);
				onChange(selectedItem);
			}

		});
	}

	/**
	 * Load data from new selectedItem to propertiesMap and textFields
	 * 
	 * @param selectedItem
	 *            ExtendedTreeItem<String>
	 */
	private void onChange(ExtendedTreeItem<String> selectedItem) {
		vBox.getChildren().clear();
		vBox.setPadding(new Insets(10));
		try {
			Map<String, String> map = selectedItem.getSimpleRibbonElement().getAttributes();
			for (Entry<String, String> element : map.entrySet()) {
				Label label1 = new Label(element.getKey());
				label1.setFont(new Font(16));
				label1.setPrefWidth(150);
				HBox hBox = new HBox();
				hBox.setPadding(new Insets(4));
				hBox.getChildren().add(label1);

				if (element.getKey().equals("enabled") || element.getKey().equals("showImage")
						|| element.getKey().equals("showLabel") || element.getKey().equals("visible")) {
					final String[] values = { "true", "false", "" };
					ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(values));
					choiceBox.getSelectionModel().select(element.getValue());
					choiceBox.setStyle("-fx-font: 16px \"Arial\";"); // setFont(new
																		// Font(16));
					choiceBox.setPrefWidth(200);// label1.setPrefWidth(150);
					choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

						@Override
						public void changed(ObservableValue<? extends Number> observable, Number oldValue,
								Number newValue) {
							String newVal = values[newValue.intValue()];
							if (!newVal.trim().equals("")) {
								selectedItem.getSimpleRibbonElement().setAttribute(element.getKey(), newVal);
							} else {
								selectedItem.getSimpleRibbonElement().setAttribute(element.getKey(), null);
							}
						}
					});
					hBox.getChildren().add(choiceBox);
				} else if (element.getKey().equals("size")) {
					final String[] values = { "large", "normal", "" };
					ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(values));
					choiceBox.getSelectionModel().select(element.getValue());
					choiceBox.setStyle("-fx-font: 16px \"Arial\";"); // setFont(new
																		// Font(16));
					choiceBox.setPrefWidth(200);// label1.setPrefWidth(150);
					choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

						@Override
						public void changed(ObservableValue<? extends Number> observable, Number oldValue,
								Number newValue) {
							String newVal = values[newValue.intValue()];
							if (!newVal.trim().equals("")) {
								selectedItem.getSimpleRibbonElement().setAttribute(element.getKey(), newVal);
							} else {
								selectedItem.getSimpleRibbonElement().setAttribute(element.getKey(), null);
							}

						}
					});
					hBox.getChildren().add(choiceBox);
				} else {
					TextField txt = new TextField(element.getValue());
					// onChange event on textField
					txt.textProperty().addListener(new ChangeListener<String>() {
						@Override
						public void changed(ObservableValue<? extends String> observable, String oldValue,
								String newValue) {
							if (!newValue.trim().equals("")) {
								selectedItem.getSimpleRibbonElement().setAttribute(element.getKey(), newValue);
							} else {
								selectedItem.getSimpleRibbonElement().setAttribute(element.getKey(), null);
							}
						}
					});
					txt.setFont(new Font(16));
					hBox.getChildren().add(txt);
				}
				vBox.getChildren().add(hBox);
			}
		} catch (Exception e) {
		}
	}
}
