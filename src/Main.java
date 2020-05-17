import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.xml.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.validation.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Main {
	
	public static Splashscreen splash;
	public static GUI gui;
	public static boolean saved, startupErrors;
	public static File currentFile, schemaFile, settingsFile, errorLog, validationLog;
	public static File[] files;
	public static SchemaFactory schemaFactory;
	public static Schema schema;
	public static TransformerFactory transformerFactory;
	public static Transformer transformer;
	public static DocumentBuilderFactory builderFactory;
	public static DocumentBuilder builder;
	public static Document currentDocument;

	
	
	public static void main(String[] args) {
		
		startupErrors = false;
		
		
		
			// SPLASHSCREEN
		splash = new Splashscreen();
		
		
			// TMP FILES
		
		try {
			
			String path = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()).getAbsolutePath();
			
			File tmpFile = 
					
				System.getProperty("os.name").toLowerCase().startsWith("windows")
				
					? new File(Settings.tmpFile + ".bat")
					: new File(path, Settings.tmpFile + ".sh");
			
			if (tmpFile.exists()) {
				
				tmpFile.delete();
			}
			
			
			
				// LINUX COMPATIBILITY
			
			if (System.getProperty("os.name").toLowerCase().startsWith("linux")) {
				
				if (!System.getProperty("user.dir").equals(path)) {
				
					try {
						
						FileOutputStream output = new FileOutputStream(tmpFile);
						output.write(("#!/bin/sh\ncd " + path + "\njava -jar Gidoi.jar").getBytes());
						output.close();
						
						tmpFile.setExecutable(true);

						new ProcessBuilder(tmpFile.toURI().getPath()).start();
					}
					
					catch (Exception e) {
						
						e.printStackTrace();
					}
					
					finally {
						
						System.exit(0);
					}
				}
			}
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
			// LOGS
		
		try {
			
			clearLogs();
		}
		
		catch (IOException ioe) {
			
			ioe.printStackTrace();
		}
		
		catch (SecurityException se) {
			
			se.printStackTrace();
		}
		
		
		
			// REFRESH
		
		Updater.refresh();
		
		
		
			// SETTINGS
		
		try {
			
			settingsFile = new File(Settings.settingsFile);
		
			if (!settingsFile.exists()) {
				
				saveSettings();
			}
			
			loadSettings();
		}
		
		catch (Exception e) {
			
			log(errorLog, e);
		}
		
		
		
			// LOCALISATION
		
		Settings.localisation = ResourceBundle.getBundle(Settings.baseBundleName);
		Locale.setDefault(Settings.language);
		
		
		
			// SCHEMA

		schemaFile = new File(Settings.schemaFile);
		saved = true;
		
		
		
		try {
			
			// XML parser
			builderFactory = DocumentBuilderFactory.newInstance();
			builder = builderFactory.newDocumentBuilder();
			
			// XML transformer
			transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
			
			// schema file
			createSchemaFile();
			
			new File(Settings.pathScreenplays).mkdir();
			files = getFiles(Settings.pathScreenplays, ".xml");
		}
		
		catch (Exception e) {
			
			log(errorLog, e);
			startupErrors = true;
		}
		
		
		
			// GUI
		
		gui = new GUI();
		gui.refreshToolbar();
		
		
		
			// ERRORS
		
		if (startupErrors) {
			
			gui.error(gui.window, "ERR_INIT");
		}

		
		
			// UPDATE
		
		if (Updater.check()) gui.dialogUpdate();
	}
	
	
	
	public static void clearLogs() throws IOException, SecurityException {
		
		new File(Settings.pathLogfiles).mkdir();
		
		
		
		errorLog = new File (Settings.pathLogfiles + "error.txt");
		
		if (errorLog.exists()) {
			
			errorLog.delete();
		}
		
		errorLog.createNewFile();
		
		
		
		validationLog = new File (Settings.pathLogfiles + "validation.txt");
		
		if (validationLog.exists()) {
			
			validationLog.delete();
		}
		
		validationLog.createNewFile();
	}
	
	public static void log(File logfile, Exception e) {
		
		try {
			
			PrintStream output = new PrintStream(new FileOutputStream(logfile, true));
			
			output.println(LocalDateTime.now().toString());
			
			if (e != null) {
				
				e.printStackTrace(output);
			}
			
			output.println();
			output.close();
		}
		
		catch (IOException ioe) {
			
			e.printStackTrace();
		}
	}
	
	public static void loadSettings() throws Exception {
		
		FileInputStream input = new FileInputStream(settingsFile);
		Properties settings = new Properties();
		
		try {
			
			settings.load(input);
		}
		
		catch (Exception e) {
			
			log(errorLog, e);
			
			if (gui != null) {
				
				gui.error(gui.window, "ERR_LOAD_SETTINGS");
			}
		}
		
		finally {
			
			input.close();
		}
		
		if (settings.containsKey("gl")) {
			
			Locale language = new Locale((String) settings.get("gl"));
			
			if (Arrays.asList(Settings.languages).contains(language)) {
				
				Settings.language = language;
				Locale.setDefault(Settings.language);
			}
		}
	}
	
	public static void saveSettings() throws Exception {
		
		Properties settings = new Properties();
		
		settings.setProperty("gl", Settings.language.toLanguageTag());
		
		FileOutputStream output = new FileOutputStream(settingsFile);
		
		try {
			
			settings.store(output, null);
		}
		
		catch (Exception e) {
			
			log(errorLog, e);
			
			if (gui != null) {
				
				gui.error(gui.window, "ERR_SAVE_SETTINGS");
			}
		}
		
		finally {
			
			output.close();
		}
	}
	
	public static void createSchemaFile() throws Exception {
		
		Document document = builder.newDocument();
		
		Element elemSchema = document.createElement("xs:schema"),
				elemScreenplay = document.createElement("xs:element"),
				ctScreenplay = document.createElement("xs:complexType"),
				choiceScreenplay = document.createElement("xs:choice"),
				attrTitle = document.createElement("xs:attribute"),
				attrAuthor = document.createElement("xs:attribute"),
				attrLanguage = document.createElement("xs:attribute"),
				stLang = document.createElement("xs:simpleType"),
				rstLang = document.createElement("xs:restriction"),
				elemLocation = document.createElement("xs:element"),
				elemCharacter = document.createElement("xs:element"),
				elemScene = document.createElement("xs:element"),
				ctScene = document.createElement("xs:complexType"),
				attrLocation = document.createElement("xs:attribute"),
				attrModLocation = document.createElement("xs:attribute"),
				attrPlace = document.createElement("xs:attribute"),
				attrTime = document.createElement("xs:attribute"),
				choiceSceneElem = document.createElement("xs:choice"),
				elemAction = document.createElement("xs:element"),
				elemDialogue = document.createElement("xs:element"),
				ctDialogue = document.createElement("xs:complexType"),
				extDialogue = document.createElement("xs:extension"),
				attrCharacter = document.createElement("xs:attribute"),
				attrWrylies = document.createElement("xs:attribute");
		
			// LOCATION
		
		elemLocation.setAttribute("name", "location");
		elemLocation.setAttribute("type", "xs:string");
		elemLocation.setAttribute("minOccurs", "0");
		elemLocation.setAttribute("maxOccurs", "unbounded");
		
			// CHARACTER
		
		elemCharacter.setAttribute("name", "character");
		elemCharacter.setAttribute("type", "xs:string");
		elemCharacter.setAttribute("minOccurs", "0");
		elemCharacter.setAttribute("maxOccurs", "unbounded");
		
			// ACTION
		
		elemAction.setAttribute("name", "action");
		elemAction.setAttribute("type", "xs:string");
		elemAction.setAttribute("minOccurs", "0");
		elemAction.setAttribute("maxOccurs", "unbounded");
		
			// CHARACTER (ATTRIBUTE)
		
		attrCharacter.setAttribute("name", "character");
		attrCharacter.setAttribute("type", "xs:string");
		attrCharacter.setAttribute("use", "required");
		
			// WRYLIES
		
		attrWrylies.setAttribute("name", "wrylies");
		attrWrylies.setAttribute("type", "xs:string");
		attrWrylies.setAttribute("use", "optional");
		
			// DIALOGUE
		
		extDialogue.setAttribute("base", "xs:string");
		extDialogue.appendChild(attrCharacter);
		extDialogue.appendChild(attrWrylies);
		
		ctDialogue.setAttribute("name", "ct-dialogue");
		ctDialogue.appendChild(document.createElement("xs:simpleContent").appendChild(extDialogue).getParentNode());
		
		elemDialogue.setAttribute("name", "dialogue");
		elemDialogue.setAttribute("type", "ct-dialogue");
		elemDialogue.setAttribute("minOccurs", "0");
		elemDialogue.setAttribute("maxOccurs", "unbounded");
		
			// SCENE ELEMENT CHOICE
		
		choiceSceneElem.setAttribute("minOccurs", "0");
		choiceSceneElem.setAttribute("maxOccurs", "unbounded");
		choiceSceneElem.appendChild(elemAction);
		choiceSceneElem.appendChild(elemDialogue);
		
			// LOCATION (ATTRIBUTE)
		
		attrLocation.setAttribute("name", "location");
		attrLocation.setAttribute("type", "xs:string");
		attrLocation.setAttribute("use", "required");
		
			// LOCATION MODIFIER
		
		attrModLocation.setAttribute("name", "mod-location");
		attrModLocation.setAttribute("type", "xs:string");
		attrModLocation.setAttribute("use", "optional");
		
			// PLACE
		
		attrPlace.setAttribute("name", "place");
		attrPlace.setAttribute("type", "xs:string");
		attrPlace.setAttribute("use", "optional");
		
			// TIME
		
		attrTime.setAttribute("name", "time");
		attrTime.setAttribute("type", "xs:string");
		attrTime.setAttribute("use", "optional");
		
			// SCENE
		
		ctScene.appendChild(choiceSceneElem);
		ctScene.appendChild(attrLocation);
		ctScene.appendChild(attrModLocation);
		ctScene.appendChild(attrPlace);
		ctScene.appendChild(attrTime);
		
		elemScene.setAttribute("name", "scene");
		elemScene.setAttribute("minOccurs", "0");
		elemScene.setAttribute("maxOccurs", "unbounded");
		elemScene.appendChild(ctScene);
		
			// TITLE
		
		attrTitle.setAttribute("name", "title");
		attrTitle.setAttribute("type", "xs:string");
		attrTitle.setAttribute("use", "required");
		
			// AUTHOR
		
		attrAuthor.setAttribute("name", "author");
		attrAuthor.setAttribute("type", "xs:string");
		attrAuthor.setAttribute("use", "optional");
		
			// LANGUAGE
		
		rstLang.setAttribute("base", "xs:string");
		
		/* for (int i = 0; i < Settings.resources.length; i++) {
			
			if (Settings.resources[i].getLocale() != null) {
			
				Element e = document.createElement("xs:enumeration");
				e.setAttribute("value", Settings.resources[i].getLocale().getCountry().toString());
				rstLang.appendChild(e);
			}
		} */
		
		stLang.setAttribute("name", "language");
		stLang.setAttribute("final", "restriction");
		stLang.appendChild(rstLang);
		
		attrLanguage.setAttribute("name", "language");
		attrLanguage.setAttribute("type", "language");
		attrLanguage.setAttribute("use", "optional");
		
			// SCREENPLAY
		
		choiceScreenplay.setAttribute("minOccurs", "0");
		choiceScreenplay.setAttribute("maxOccurs", "unbounded");
		choiceScreenplay.appendChild(elemCharacter);
		choiceScreenplay.appendChild(elemLocation);
		choiceScreenplay.appendChild(elemScene);
		
		ctScreenplay.appendChild(choiceScreenplay);
		ctScreenplay.appendChild(attrTitle);
		ctScreenplay.appendChild(attrAuthor);
		
		elemScreenplay.setAttribute("name", "screenplay");
		elemScreenplay.appendChild(ctScreenplay);
		
			//SCHEMA
		
		elemSchema.setAttribute("xmlns:xs", "http://www.w3.org/2001/XMLSchema");
		elemSchema.appendChild(ctDialogue);
		elemSchema.appendChild(stLang);
		elemSchema.appendChild(elemScreenplay);
		
		document.appendChild(elemSchema);
		
		try {
			
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(schemaFile);
			transformer.transform(source,  result);
			
			schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		    schema = schemaFactory.newSchema(new StreamSource(schemaFile));
		}
		
		catch (Exception e) {
			
			log(errorLog, e);
		}
	}
	
	public static boolean validate(Document document) {
		
		boolean valid = true;
		
		Validator validator = schema.newValidator();
		
		try {
			
			validator.validate(new DOMSource(document));
			
			List<Element> scenes = Arrays.asList(getElements(document, "scene", null));
			List<Element> locations = Arrays.asList(getElements(document, "location", null));
			List<Element> characters = Arrays.asList(getElements(document, "character", null));
			
			Iterator<Element> sceneIterator = scenes.iterator();
			
			if (sceneIterator.hasNext()) {
				
				// at least one scene but no valid locations
				if (locations.size() < 1) {
					
					log(validationLog, new Exception("No locations, but at least one scene."));
					
					return false;
				}
				
				// iterate through scenes
				while (sceneIterator.hasNext()) {
					
					Element scene = sceneIterator.next();
					Iterator<Element> locationIterator = locations.iterator();
					
					// iterate through locations
					while (locationIterator.hasNext()) {
						
						// valid location
						if (scene.getAttribute("location").equals(locationIterator.next().getTextContent())) {
							
							break;
						}
						
						// invalid location
						if (!locationIterator.hasNext()) {
							
							log(validationLog, new Exception(scene.getAttribute("location") + " is not a valid location."));
							valid = false;
						}
					}
					
					List<Element> dialogues = Arrays.asList(getElements(document, "dialogue", scene));
					Iterator<Element> dialogueIterator = dialogues.iterator();
					
					// iterate through dialogues
					while (dialogueIterator.hasNext()) {
						
						// at least one dialogue but no valid characters
						if (characters.size() < 1) {
							
							log(validationLog, new Exception("No characters, but at least one dialogue."));
							return false;
						}
						
						Element dialogue = dialogueIterator.next();
						Iterator<Element> characterIterator = characters.iterator();
						
						// iterate through characters
						while (characterIterator.hasNext()) {
							
							// valid character
							if (dialogue.getAttribute("character").equals(characterIterator.next().getTextContent())) {
								
								break;
							}
							
							// invalid character
							if (!characterIterator.hasNext()) {
								
								log(validationLog, new Exception(dialogue.getAttribute("character") + " is not a valid character."));
								return false;
							}
						}
					}
				}
			}
		}
		
		catch (SAXException spe) {
			
			log(validationLog, spe);
			valid = false;
		}
		
		catch (Exception e) {
			
			log(errorLog, e);
			gui.error(gui.window, "ERR_VALIDATION_FAILED");
		}
		
		return valid;
	}
	
	public static boolean checkFilename(String filename, String title, String author) {
		
		File file = new File(Settings.pathScreenplays + filename);
		
		if (file.exists()) {
			
			return false;
		}
		
		create(file);
		setAttr("title", title);
		setAttr("author", author);
		save();
		
		return true;
	}
	
	public static void create(File file) {
		
		try {
				
			file.createNewFile();
			gui.status.setText(file.getName() + " successfully created.");
			
			currentFile = file;
			gui.status.setText("current file is " + file.getName());
			
			currentDocument = builder.newDocument();
			Element screenplay = currentDocument.createElement("screenplay");
			currentDocument.appendChild(screenplay);
			
			gui.save.setEnabled(true);
			gui.refreshToolbar();
			gui.refreshPreview();
		}
		
		catch(IOException e) {
			
			log(errorLog, e);
			gui.error(gui.window, "ERR_CREATE_SCREENPLAY");
		}
	}
	
	public static void delete(String title) {
		
		File file = new File(Settings.pathScreenplays + title + ".xml");
		
		if (file.exists()) {
			
			file.delete();
			gui.status.setText(file.getName() + " successfully deleted.");
		}
	}
	
	public static void save() {
		
		if (!saved) {
			
			if (validate(currentDocument)) {
				
				gui.status.setText("current document is valid.");
			
				try {
					
					DOMSource source = new DOMSource(currentDocument);
					StreamResult result = new StreamResult(currentFile);
					transformer.transform(source,  result);
					
					saved = true;
					gui.save.setEnabled(false);
					gui.refreshWindowTitle();
					
					gui.status.setText(currentFile + " successfully saved.");
				}
				
				catch(Exception e) {
					
					log(errorLog, e);
					gui.error(gui.window, "ERR_SAVE_SCREENPLAY");
				}
			}
		}
	}
	
	public static void unsave() {
		
		saved = false;
		gui.save.setEnabled(true);
		gui.refreshWindowTitle();
	}
	
	public static void load(File file) {
		
		try {
			
			Document document = builder.parse(file);
			
			gui.status.setText(file + " successfully parsed.");
			
			if (validate(document)) {
				
				gui.status.setText(file + " is valid.");
			
				currentFile = file;
				currentDocument = document;
				
				saved = true;
				gui.save.setEnabled(false);
				gui.refreshWindowTitle();
				gui.refreshToolbar();
				gui.refreshPreview();
				gui.preview.setCaretPosition(0);
			}
			
			else {
				
				gui.status.setText(file + " is invalid.");
				gui.error(gui.window, "ERR_INVALID_FILE");
			}
		}
		
		catch (Exception e) {
			
			log(errorLog, e);
			gui.error(gui.window, "ERR_LOAD_SCREENPLAY");
		}
	}
	
	public static File[] getFiles(String path, String suffix) {
		
		FileFilter filter = new FileFilter() {
			
			public boolean accept(File file) {
	
				return (file.getName().endsWith(suffix));
			}
		};
		
		return new File(path).listFiles(filter);
	}
	
	
	
	public static Element[] getElements(Document document, String name, Element scene) {
		
		LinkedList<Element> list = new LinkedList<Element>();
		
		// scene = null -> get screenplay elements
		if (document != null
		&& (scene != null
		&& scene.hasChildNodes()
		|| document.getDocumentElement().hasChildNodes())) {
			
			NodeList nodes = scene != null ? scene.getChildNodes() : document.getDocumentElement().getChildNodes();
			
			for (int i = 0; i < nodes.getLength(); i++) {
			
				// name = null -> get all elements
				if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE
				&& (name == null
				|| nodes.item(i).getNodeName().equals(name))) {
					
					list.add((Element) nodes.item(i));
				}
			}
		}
		
		return list.toArray(new Element[list.size()]);
	}
	
	public static String getAttr(String attr) {
		
		if (currentDocument != null) {
			
			return currentDocument.getDocumentElement().getAttribute(attr);
		}
		
		return "";
	}
	
	public static void setAttr(String attr, String value) {
		
		if (!value.equals(getAttr(attr))) {
		
			currentDocument.getDocumentElement().setAttribute(attr, value);
			gui.status.setText(attr + " set to " + value);
			
			unsave();
			gui.refreshPreview();
		}
	}
	
	public static void addScene(Node refScene, Element location, String locationModifier, String place, String time) {
		
		Element scene = currentDocument.createElement("scene");
		
		scene.setAttribute("location", location.getTextContent());
		
		if (!locationModifier.equals("")) {
			
			scene.setAttribute("mod-location", locationModifier);
		}
		
		if (Arrays.asList(Settings.optionsPlace).contains(place)) {
			
			scene.setAttribute("place", place);
		}
		
		if (Arrays.asList(Settings.optionsTime).contains(time)) {
			
			scene.setAttribute("time", time);
		}
		
		try {
			
			currentDocument.getDocumentElement().insertBefore(scene, refScene);
			
			unsave();
			gui.refreshSceneList();
			gui.refreshScreenplayEditor();
			gui.refreshPreview();
		}
		
		catch (Exception e) {
			
			log(errorLog, e);
			gui.error(gui.screenplayEditor, "ERR_INSERT_SCENE");
		}
	}
	
	public static void editSceneProperties(Element scene, String location, String locationModifier, String place, String time) {
		
		// params: value = change attr, "" = remove attr, null = keep attr
		
		if (location != null && !location.equals("")) {
			
			scene.setAttribute("location", location);
		}
		
		if (locationModifier != null) {
			
			if (locationModifier.equals("")) {
				
				scene.removeAttribute("mod-location");
			}
			
			else {
				
				scene.setAttribute("mod-location", locationModifier);
			}
		}
		
		if (place != null) {
			
			if (place.equals("-")) {
				
				scene.removeAttribute("place");
			}
			
			else if (Arrays.asList(Settings.optionsPlace).contains(place)) {
				
				scene.setAttribute("place", place);
			}
		}
		
		if (time != null) {
			
			if (locationModifier.equals("-")) {
				
				scene.removeAttribute("time");
			}
			
			else if (Arrays.asList(Settings.optionsTime).contains(time)) {
				
				scene.setAttribute("time", time);
			}
		}
		
		unsave();
		gui.refreshSceneList();
		gui.refreshScreenplayEditor();
		gui.refreshPreview();
	}
	
	public static void moveElement(Element element, Element scene, boolean forward) {
		
		// does the element exist at all?
		if (element != null) {
			
			// is the element a scene or a scene element?
			if (scene != null || (scene == null && element.getNodeName().equals("scene"))) {
				
				Node parent = element.getParentNode();
			
				// if the element is a scene, does it belong to the current screenplay? (it should)
				if (scene == null && parent.equals(currentDocument.getDocumentElement())
				// if the element is a scene element, does it belong to the passed scene? (it should)
				|| scene != null && parent.equals(scene)) {
					
					// move through the element's siblings...
					while (forward && element.getNextSibling() != null || !forward && element.getPreviousSibling() != null) {
						
						Node sibling = forward ? element.getNextSibling() : element.getPreviousSibling();
						
						// ...until you find another scene or scene element
						if (scene != null || sibling.getNodeName().equals(element.getNodeName())) {
							
							boolean append = false;
							
							if (forward) {
								
								if (sibling.getNextSibling() != null) {
										
									sibling = sibling.getNextSibling();
								}
								
								else {
									
									append = true;
								}
							}
								
							parent.removeChild(element);
							
							if (append) {
								
								parent.appendChild(element);
							}
							
							else {
								
								parent.insertBefore(element, sibling);
							}
							
							unsave();
							
							if (scene == null) {
								
								gui.refreshSceneList();
								gui.refreshScreenplayEditor();
							}
							
							else {
								
								gui.refreshSceneElementList(scene);
								gui.refreshSceneEditor();
							}
							
							gui.refreshPreview();
							
							break;
						}
					}
				}
			}
		}
	}
	
	public static void addLocationOrCharacter(boolean location, String name) {
		
		Element element = currentDocument.createElement(location ? "location" : "character");
		element.setTextContent(name);
		
		try {
			
			currentDocument.getDocumentElement().appendChild(element);
			
			unsave();
			
			if (location) gui.refreshLocationList();
			else gui.refreshCharacterList();
			
			gui.refreshScreenplayEditor();
		}
		
		catch (Exception e) {
			
			log(errorLog, e);
			gui.error(gui.screenplayEditor, "ERR_ADD_ELEMENT");
		}
	}
	
	public static void removeElement(Element element, Element scene) {
		
		if (element != null) {
			
			try {
				
				if (scene == null) {
					
					currentDocument.getDocumentElement().removeChild(element);
				}
				
				else {
					
					scene.removeChild(element);
				}
				
				unsave();

				String type = element.getNodeName();
				
				switch (type) {
				
					case "scene": gui.refreshSceneList(); gui.refreshScreenplayEditor(); break;
					case "location": gui.refreshLocationList(); gui.refreshScreenplayEditor(); break;
					case "character": gui.refreshCharacterList(); gui.refreshScreenplayEditor(); break;
					
					case "action":
					case "dialogue": gui.refreshSceneElementList(scene); gui.refreshSceneEditor(); break;
					
					default: break;
				}
				
				gui.refreshPreview();
			}
			
			catch(Exception e) {
				
				log(errorLog, e);
				gui.error(gui.screenplayEditor, "ERR_REMOVE_ELEMENT");
			}
		}
	}
	
	public static void addAction(Element scene, Element ref, String text) {
		
		if (scene != null) {
		
			Element action = currentDocument.createElement("action");
			action.setTextContent(text);
			
			try {
			
				scene.insertBefore(action, ref);
				
				unsave();
				gui.refreshSceneElementList(scene);
				gui.refreshSceneEditor();
				gui.refreshPreview();
			}
			
			catch (Exception e) {
				
				log(errorLog, e);
				gui.error(gui.screenplayEditor, "ERR_ADD_ELEMENT");
			}
		}
	}
	
	public static void editAction(Element element, String text) {
		
		if (element != null && element.getNodeName().equals("action")) {
			
			if (!text.equals(element.getTextContent())) {
				
				element.setTextContent(text);
				
				unsave();
				gui.refreshSceneElementList((Element) element.getParentNode());
				gui.refreshSceneEditor();
				gui.refreshPreview();
			}
		}
	}
	
	public static void addDialogue(Element scene, Element ref, Element character, String line, String wrylies) {
		
		if (scene != null && character != null && line != null) {
		
			Element dialogue = currentDocument.createElement("dialogue");
			
			dialogue.setAttribute("character", character.getTextContent());
			
			if (wrylies != null && !wrylies.equals("")) {
				
				dialogue.setAttribute("wrylies", wrylies);
			}
			
			dialogue.setTextContent(line);
			
			try {
			
				scene.insertBefore(dialogue, ref);
				
				unsave();
				gui.refreshSceneElementList(scene);
				gui.refreshSceneEditor();
				gui.refreshPreview();
			}
			
			catch (Exception e) {
				
				log(errorLog, e);
				gui.error(gui.screenplayEditor, "ERR_ADD_ELEMENT");
			}
		}
	}
	
	public static void editDialogue(Element element, Element character, String line, String wrylies) {
		
		if (element != null && element.getNodeName().equals("dialogue")) {
			
			boolean changed = false;
			
			if (!element.getAttribute("character").equals(character.getTextContent())) {
				
				element.setAttribute("character", character.getTextContent());
				changed = true;
			}
			
			if (!element.getTextContent().equals(line)) {
				
				element.setTextContent(line);
				changed = true;
			}
			
			if (!element.getAttribute("wrylies").equals(wrylies)) {
				
				element.setAttribute("wrylies", wrylies);
				changed = true;
			}
			
			if (changed) {
				
				unsave();
				gui.refreshSceneElementList((Element) element.getParentNode());
				gui.refreshSceneEditor();
				gui.refreshPreview();
			}
		}
	}
}