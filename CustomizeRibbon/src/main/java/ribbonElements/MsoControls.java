package ribbonElements;

import java.util.ArrayList;
import java.util.List;

public class MsoControls {
	private static List<String> tabs = new ArrayList<String>();

	public static List<String> getTabs() {
		String[] tab = { "TabHome", "TabInsert", "TabPageLayoutExcel", "TabFormulas", "TabData", "TabReview", "TabView",
				"TabDeveloper", "TabAddIns", "TabPrintPreview", "TabBackgroundRemoval", "TabSmartArtToolsDesign",
				"TabSmartArtToolsFormat", "TabChartToolsDesign", "TabChartToolsLayout", "TabChartToolsFormat",
				"TabDrawingToolsFormat", "TabPictureToolsFormat", "TabPivotTableToolsOptions",
				"TabPivotTableToolsDesign", "TabHeaderAndFooterToolsDesign", "TabTableToolsDesignExcel",
				"TabPivotChartToolsDesign", "TabPivotChartToolsLayout", "TabPivotChartToolsFormat",
				"TabPivotChartToolsAnalyze", "TabInkToolsPens", "TabSparklineDesign", "TabSlicerDesign",
				"TabEquationToolsDesign", "TabInfo", "TabRecent", "TabNew", "TabPrint", "TabShare", "TabHelp" };
		for (String element : tab) {
			tabs.add(element);
		}
		return tabs;
	}
}
