package main;

import java.io.File;
import java.io.IOException;

import javax.swing.plaf.FileChooserUI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import services.zip.ManipulateZip;
import services.zip.RibbonExcelImpl;
import services.zip.WriteXML;

public class Main {
	public static void main(String[] args) {
		try {
			RibbonExcelImpl ribbon = new RibbonExcelImpl();
			ribbon.setPathToExcelFile("C:\\Users\\Marcin\\Desktop\\plik.xlsm");
			ribbon.extractFiles();
			ribbon.createEmptyRibbon();
			ribbon.writeXML();
			ribbon.buildExcelFile();
			ribbon.deleteDirectory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
