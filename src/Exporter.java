import java.io.File;
import java.util.Calendar;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.w3c.dom.Element;

public class Exporter {
	
	private static int fontSize;
	private static float x, y, dpi, spacing;
	private static String filename, text;
	private static File file;
	private static Calendar today;
	private static Element[] scenes;
	private static PDFont font;
	private static PDDocument document;
	private static PDDocumentInformation properties;
	private static PDPageContentStream stream;
	private static PDPage page;
	
	// TODO cont'd
	
	private static void newPage() {
		
		try {
			
			page = new PDPage(Settings.pageFormat);
			stream = new PDPageContentStream(document, page);
			stream.setFont(PDType1Font.COURIER, fontSize);
			y = page.getMediaBox().getHeight() - (Settings.pageMarginTop * dpi) - fontSize;
		}
		
		catch(Exception e) {
			
			Main.log(Main.errorLog, e);
			Main.gui.error(Main.gui.window, "ERR_EXPORT");
		}
	}
	
	private static void newLines(int lines) {
		
		if (y - (lines * spacing) < Settings.pageMarginBottom * dpi) {
			
			try {
				
				stream.close();
				document.addPage(page);
				
				newPage();
			}
			
			catch (Exception e) {
				
				Main.log(Main.errorLog, e);
				Main.gui.error(Main.gui.window, "ERR_EXPORT");
			}
		}
		
		else y -= (lines * spacing);
	}
	
	private static void printTextBlock(String text, float width, boolean dialogue) {
		
		Scanner scanner = new Scanner(text);
		scanner.useDelimiter(" ");
		
		String line = "", next = "";
		
		try {
			
			while (scanner.hasNext()) {
				
				next = scanner.next();
				
				if (font.getStringWidth(line + next) / 1000 * fontSize > width * dpi) {
					
					newLines(1);
					
					stream.beginText();
					stream.newLineAtOffset(x, y);
					stream.showText(line.substring(0, line.length() - 1));
					stream.endText();
					
					line = "";
				}
				
				line += (next + " ");
			}
			
			if (!line.equals("")) {
				
				newLines(1);
				
				stream.beginText();
				stream.newLineAtOffset(x, y);
				stream.showText(line.substring(0, line.length() - 1));
				stream.endText();
			}
			
			scanner.close();
		}
		
		catch (Exception e) {
			
			Main.log(Main.errorLog, e);
			Main.gui.error(Main.gui.window, "ERR_EXPORT");
		}
	}
	
	private static void printTitlePage() {
		
		try {
		
			page = new PDPage(Settings.pageFormat);
			stream = new PDPageContentStream(document, page);
			stream.setFont(PDType1Font.COURIER_BOLD, fontSize);
			
			// title
			text = Main.getAttr("title").toUpperCase();
			x = (page.getMediaBox().getWidth() - (font.getStringWidth(text) / 1000 * fontSize)) / 2;
			y = 8.5f * dpi;
			
			stream.beginText();
			stream.newLineAtOffset(x, y);
			stream.showText(text);
			stream.endText();
			
			// author
			text = "written by";
			x = (page.getMediaBox().getWidth() - (font.getStringWidth(text) / 1000 * fontSize)) / 2;
			y -= spacing * 2;
			
			stream.setFont(PDType1Font.COURIER, fontSize);
			stream.beginText();
			stream.newLineAtOffset(x, y);
			stream.showText(text);
			stream.endText();
			
			text = Main.getAttr("author");
			x = (page.getMediaBox().getWidth() - (font.getStringWidth(text) / 1000 * fontSize)) / 2;
			y -= spacing;
			
			stream.beginText();
			stream.newLineAtOffset(x, y);
			stream.showText(text);
			stream.endText();
			
			stream.close();
			document.addPage(page);
			
		}
		
		catch (Exception e) {
			
			Main.log(Main.errorLog, e);
			Main.gui.error(Main.gui.window, "ERR_EXPORT");
		}
	}
	
	private static void printSceneHeader(Element scene) {
		
		x = (Settings.pageMarginLeft + Settings.sceneHeadingIndentLeft) * dpi;
		String text = "";
		
		if (scene.hasAttribute("place")) {
			
			text += scene.getAttribute("place") + " ";
		}
		
		if (scene.hasAttribute("location")) {
			
			text += scene.getAttribute("location");
		}
		
		if (scene.hasAttribute("mod-location")) {
			
			text += " (" + scene.getAttribute("mod-location") + ")";
		}
		
		if (scene.hasAttribute("time")) {
			
			text += " - " + scene.getAttribute("time");
		}
		
		try {
		
			stream.beginText();
			stream.newLineAtOffset(x, y);
			stream.showText(text.toUpperCase());
			stream.endText();
		}
		
		catch (Exception e) {
			
			Main.log(Main.errorLog, e);
			Main.gui.error(Main.gui.window, "ERR_EXPORT");
		}
	}
	
	private static void printSceneElement(Element element) {
		
		if (element.getNodeName().equals("dialogue")) {
			
			newLines(2);
			
			if (element.hasAttribute("character")) {
				
				x = (Settings.pageMarginLeft + Settings.characterIndentLeft) * dpi;
				
				try {
					
					stream.beginText();
					stream.newLineAtOffset(x, y);
					stream.showText(element.getAttribute("character").toUpperCase());
					stream.endText();
					
					if (element.hasAttribute("wrylies")) {
						
						newLines(1);
						x = (Settings.pageMarginLeft + Settings.wryliesIndentLeft) * dpi;
						
						stream.beginText();
						stream.newLineAtOffset(x, y);
						stream.showText("(" + element.getAttribute("wrylies") + ")");
						stream.endText();
					}
					
					x = (Settings.pageMarginLeft + Settings.dialogueIndentLeft) * dpi;
					printTextBlock(element.getTextContent(), page.getMediaBox().getWidth() / dpi - ((Settings.pageMarginLeft + Settings.dialogueIndentLeft) + (Settings.pageMarginRight + Settings.dialogueIndentRight)), true);
				}
				
				catch (Exception e) {
					
					Main.log(Main.errorLog, e);
					Main.gui.error(Main.gui.window, "ERR_EXPORT");
				}
			}
		}
		
		else if (element.getNodeName().equals("action")) {
			
			newLines(1);
			x = (Settings.pageMarginLeft + Settings.actionIndentLeft) * dpi;
			printTextBlock(element.getTextContent(), page.getMediaBox().getWidth() / dpi - ((Settings.pageMarginLeft + Settings.actionIndentLeft) + (Settings.pageMarginRight + Settings.actionIndentRight)), false);
		}
	}
	
	

	public static void exportToPDF() {
		
		if (Main.currentDocument != null) {
			
			Main.gui.status.setText("Exporting...");
			 
			try {
				
				// create pdf document
				document = new PDDocument();
				
				// create file and path
				new File(Settings.pathExported).mkdir();
				filename = Settings.pathExported + Main.getAttr("title") + ".pdf";
				file = new File(filename);
				
				
				
				// properties
				properties = new PDDocumentInformation();
				properties.setTitle(Main.getAttr("title"));
				properties.setAuthor(Main.getAttr("author"));
				properties.setCreator(Settings.VERSION);
				
				today = Calendar.getInstance();
				
				properties.setCreationDate(
						
					file.exists()
					
						? PDDocument.load(file).getDocumentInformation().getCreationDate()
						: today
				);
				
				properties.setModificationDate(today);
				document.setDocumentInformation(properties);
				
				
				
				font = PDType1Font.COURIER;
				fontSize = 12;
				dpi = 72;
				spacing = fontSize * 1.5f;
				
				printTitlePage();
				
				
				
				scenes = Main.getElements(Main.currentDocument, "scene", null);
				
				if (scenes.length > 0) {
					
					newPage();
					y += 4 * spacing;  // new line compensation for first scene header
					
					for (int i = 0; i < scenes.length; i++) {
						
						Element scene = scenes[i];
						
						newLines(4);
						printSceneHeader(scene);
						
						if (scene.hasChildNodes()) {
							
							Element sceneElement = (Element) scene.getFirstChild();
							
							do {
								
								printSceneElement(sceneElement);
								sceneElement = (Element) sceneElement.getNextSibling();
							}
							
							while (sceneElement != null);
						}
					}
					
					stream.close();
					document.addPage(page);
				}
				
				
				
				stream.close();
				document.save(filename);
				document.close();
				
				Main.gui.status.setText("Successfully exported to " + filename);
			}
			
			catch(Exception e) {
				
				Main.log(Main.errorLog, e);
				Main.gui.error(Main.gui.window, "ERR_EXPORT");
			}
		}
	}
}