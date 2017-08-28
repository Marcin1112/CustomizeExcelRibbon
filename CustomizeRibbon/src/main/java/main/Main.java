package main;

import services.RibbonExcelImpl;

public class Main {
	public static void main(String[] args) {
		try {
			RibbonExcelImpl ribbon = new RibbonExcelImpl();
			ribbon.setPathToExcelFile("C:\\Users\\Marcin\\Desktop\\plik.xlsm");
			ribbon.extractFiles();
			ribbon.createEmptyRibbon();
			//ribbon.writeXML();
			ribbon.buildExcelFile();
			ribbon.deleteDirectory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
