import java.io.File;
import java.util.Locale;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.html.*;
import org.w3c.dom.*;

public class GUI implements ActionListener {
	
	public int 		x,
					y,
					width,
					height;
	
	public JFrame	window;
	
	public JDialog	screenplayEditor,
					sceneEditor,
					dialogNewScreenplay,
					dialogOpenFile,
					dialogSettings,
					dialogAddScene,
					dialogAddLocationOrCharacter,
					dialogSceneElement;
	
	public JPanel	statusbar,
					generalSettingsTab,
					screenplayEditorContent,
					screenplayEditorProperties,
					screenplayEditorPropertiesText,
					sceneTabContent,
					sceneTabButtons,
					sceneTabMoveButtons,
					locationTabContent,
					locationTabButtons,
					characterTabContent,
					characterTabButtons,
					sceneEditorContent,
					sceneElementContent,
					sceneElementButtons,
					moveSceneElementButtons,
					dialogNewScreenplayContent,
					dialogOverwriteContent,
					dialogOverwriteOptions,
					dialogOpenFileContent,
					scenePropertiesLocationContent,
					scenePropertiesPlaceContent,
					scenePropertiesTimeContent,
					dialogAddSceneContent,
					dialogAddLocationOrCharacterContent,
					dialogAddSceneElementContent;
	
	public JTabbedPane	dialogSettingsTabs,
						screenplayEditorTabs;
	
	public JToolBar 	toolbar;
	
	public JButton		create,
						save,
						open,
						export,
						settings,
						screenplayEditorButton,
						addScene,
						editScene,
						moveSceneUp,
						moveSceneDown,
						deleteScene,
						addLocation,
						deleteLocation,
						addCharacter,
						deleteCharacter,
						addAction,
						addDialogue,
						editSceneElement,
						moveSceneElementUp,
						moveSceneElementDown,
						deleteSceneElement,
						dialogNewScreenplayConfirm,
						screenplayEditorPropertiesApply,
						scenePropertiesApply,
						dialogOpenFileConfirm,
						dialogAddSceneConfirm,
						dialogAddLocation,
						dialogAddCharacter,
						dialogAddAction,
						dialogAddDialogue,
						dialogEditAction,
						dialogEditDialogue;
	
	public JTextField	screenplayEditorTitle,
						screenplayEditorAuthor,
						dialogNewScreenplayTitle,
						dialogNewScreenplayAuthor,
						dialogNewScreenplayFile,
						scenePropertiesModLocation,
						dialogAddSceneModTime,
						dialogAddLocationOrCharacterText,
						dialogueWrylies;
	
	public JTextArea	dialogAddSceneElementText;
	
	public JLabel		status;
	
	public JList<Element>	sceneList,
							locationList,
							characterList,
							sceneElementList;
	
	public JComboBox<File>	dialogOpenFileBox;
	
	public JComboBox<Element>	scenePropertiesLocation,
								dialogueCharacter;
	
	public JComboBox<String>	scenePropertiesPlace,
								scenePropertiesTime;
	
	public JComboBox<Locale>	language;
	
	public JEditorPane	preview;
	
	public JScrollPane	scrollPane,
						sceneScrollPane,
						locationScrollPane,
						characterScrollPane,
						sceneElementScrollPane,
						dialogueAddSceneElementScrollPane;
	
	public HTMLEditorKit htmlEditor;
	
	public HTMLDocument	htmlDocument;
	
	

	public GUI() {
		
		x = 50;
		y = 50;
		
		width = Settings.width;
		height = Settings.height;
		
		ToolTipManager.sharedInstance().setInitialDelay(Settings.tooltipInitialDelay);
		ToolTipManager.sharedInstance().setDismissDelay(Settings.tooltipDismissDelay);
		
		JOptionPane.setDefaultLocale(Settings.language);
		
		create = new JButton(new ImageIcon(Main.class.getResource("/gfx/new.png")));
		create.addActionListener(this);
		
		save = new JButton(new ImageIcon(Main.class.getResource("/gfx/save.png")));
		save.addActionListener(this);
		
		open = new JButton(new ImageIcon(Main.class.getResource("/gfx/open.png")));
		open.addActionListener(this);
		
		export = new JButton(new ImageIcon(Main.class.getResource("/gfx/export.png")));
		export.addActionListener(this);
		
		settings = new JButton(new ImageIcon(Main.class.getResource("/gfx/settings.png")));
		settings.addActionListener(this);
		
		screenplayEditorButton = new JButton();
		screenplayEditorButton.addActionListener(this);
			
		toolbar = new JToolBar();
		toolbar.setFloatable(false);
		toolbar.add(create);
		toolbar.add(open);
		toolbar.add(save);
		toolbar.add(export);
		toolbar.add(settings);
		toolbar.addSeparator();
		toolbar.add(screenplayEditorButton);
			
		refreshToolbar();
		
		htmlEditor = new HTMLEditorKit();
		htmlEditor.getStyleSheet().addRule("html{background-color:#EEEEEE;}");
		htmlEditor.getStyleSheet().addRule("body{background-color:white;width:8.5in;padding-top:1in;padding-bottom:1in;font-family:Courier;font-size:12p;}");
		htmlEditor.getStyleSheet().addRule("#title{text-align:center;}");
		htmlEditor.getStyleSheet().addRule("#author{text-align:center;}");
		htmlEditor.getStyleSheet().addRule("#screenplay{padding-left:1.5in;padding-right:1in;}");
		htmlEditor.getStyleSheet().addRule(".heading{width:6.0in;}");
		htmlEditor.getStyleSheet().addRule(".action{margin-left:0.0in;width:6.0in;}");
		htmlEditor.getStyleSheet().addRule(".dialogue{}");
		htmlEditor.getStyleSheet().addRule(".role{margin-left:2.0in;margin-right:0in;width:4in;}");
		htmlEditor.getStyleSheet().addRule(".wrylies{margin-left:1.5in;margin-right:2.0in;width:2.5in;}");
		htmlEditor.getStyleSheet().addRule(".line{margin-left:1.0in;margin-right:1.5in;}");
		htmlEditor.getStyleSheet().addRule(".transition{text-align:right}");
		
		preview = new JEditorPane();
		preview.setContentType("text/html");
		preview.setEditorKit(htmlEditor);
		preview.setEditable(false);
		
		scrollPane = new JScrollPane(preview);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		status = new JLabel(Settings.versionStr);
		status.setHorizontalAlignment(SwingConstants.LEFT);
		
		statusbar = new JPanel();
		statusbar.setSize(scrollPane.getWidth(), 16);
		statusbar.setBorder(BorderFactory.createBevelBorder(EtchedBorder.LOWERED));
		statusbar.setLayout(new BoxLayout(statusbar, BoxLayout.X_AXIS));
		statusbar.add(status);
		
		if (Main.splash != null) Main.splash.dispose();
		
		window = new JFrame(Settings.versionStr);
		window.setIconImage(Toolkit.getDefaultToolkit().createImage(Main.class.getResource("/gfx/logo.png")));
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.setBounds(x, y, preview.getSize().width, height);
		window.setMinimumSize(new Dimension(preview.getMinimumSize().width + 32, 100));
		window.add(toolbar, BorderLayout.PAGE_START);
		window.add(scrollPane);
		window.add(statusbar, BorderLayout.SOUTH);
		window.setVisible(true);
		
		window.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				
				if (!Main.saved) {
					
					JOptionPane.setDefaultLocale(Settings.language);
					switch (JOptionPane.showOptionDialog(
							
						new JDialog(dialogNewScreenplay),
						Settings.localize("WRN_UNSAVED_CHANGES"),
						Settings.localize("DLG_WARNING"),
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE,
						null,
						null,
						2))
					{
					
						case 0:
							
							Main.save();
							break;
							
						case 2:
							
							return;
							
						default:
							
							break;
					}
				}
				
				e.getWindow().dispose();
				System.exit(0);
			}
		});
	}
	
	
	
	private Point center(int width, int height, int dialogueWidth, int dialogueHeight) {
		
		return new Point(x + width/2 - dialogueWidth/2, y + height/2 - dialogueHeight/2);
	}
	
	
	
	public void error(Window owner, String messageKey) {
		
		JOptionPane.showMessageDialog(
				
			new JDialog(owner),
			Settings.localize(messageKey),
			Settings.localize("DLG_ERROR"),
			JOptionPane.ERROR_MESSAGE
		);
	}
	
	public void warning(Window owner, String messageKey) {
		
		JOptionPane.showMessageDialog(
				
			new JDialog(owner),
			Settings.localize(messageKey),
			Settings.localize("DLG_WARNING"),
			JOptionPane.WARNING_MESSAGE
		);
	}
	
	public void dialogUpdate() {
		
		switch (JOptionPane.showOptionDialog(
				
			window,
			Settings.localize("UPDATE_AVAILABLE"),
			Settings.localize("DLG_UPDATE"),
			JOptionPane.YES_NO_OPTION,
			JOptionPane.INFORMATION_MESSAGE,
			null,
			null,
			1
		)) {
		
			case 0:
				
				try {
				
					Updater.update();
				}
				
				catch (Exception e) {
					
					Main.log(Main.errorLog, e);
					error(window, "ERR_UPDATE");
				}
				
			default:
				
				break;
		}
	}
	
	public void dialogNewScreenplay() {
		
		dialogNewScreenplayTitle = new JTextField(20);
		dialogNewScreenplayTitle.setEditable(true);
		dialogNewScreenplayTitle.setToolTipText(Settings.localize("TT_PROPERTIES_TITLE"));
		dialogNewScreenplayTitle.setText(Settings.localize("DFT_TITLE"));
		
		dialogNewScreenplayTitle.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
			
				dialogNewScreenplayFile.setText(dialogNewScreenplayTitle.getText() + ".xml");
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
			
				dialogNewScreenplayFile.setText(dialogNewScreenplayTitle.getText() + ".xml");
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
			
				dialogNewScreenplayFile.setText(dialogNewScreenplayTitle.getText() + ".xml");
			}
		});
		
		dialogNewScreenplayAuthor = new JTextField(20);
		dialogNewScreenplayAuthor.setEditable(true);
		dialogNewScreenplayAuthor.setToolTipText(Settings.localize("TT_PROPERTIES_AUTHOR"));
		dialogNewScreenplayAuthor.setText(System.getProperty("user.name"));
		
		dialogNewScreenplayFile = new JTextField(20);
		dialogNewScreenplayFile.setEditable(true);
		dialogNewScreenplayFile.setToolTipText(Settings.localize("TT_PROPERTIES_FILENAME"));
		dialogNewScreenplayFile.setText(dialogNewScreenplayTitle.getText() + ".xml");
		
		dialogNewScreenplayConfirm = new JButton(Settings.localize("BTN_OK"));
		dialogNewScreenplayConfirm.addActionListener(this);
		dialogNewScreenplayConfirm.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		dialogNewScreenplayContent = new JPanel();
		dialogNewScreenplayContent.setLayout(new BoxLayout(dialogNewScreenplayContent, BoxLayout.Y_AXIS));
		dialogNewScreenplayContent.add(dialogNewScreenplayTitle);
		dialogNewScreenplayContent.add(Box.createRigidArea(new Dimension(0, 5)));
		dialogNewScreenplayContent.add(dialogNewScreenplayAuthor);
		dialogNewScreenplayContent.add(Box.createRigidArea(new Dimension(0, 5)));
		dialogNewScreenplayContent.add(dialogNewScreenplayFile);
		dialogNewScreenplayContent.add(Box.createRigidArea(new Dimension(0, 5)));
		dialogNewScreenplayContent.add(dialogNewScreenplayConfirm);
		dialogNewScreenplayContent.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		dialogNewScreenplay = new JDialog(window, Settings.localize("DLG_NEW_SCREENPLAY"));
		dialogNewScreenplay.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialogNewScreenplay.add(dialogNewScreenplayContent);
		dialogNewScreenplay.pack();
		dialogNewScreenplay.setLocation(center(window.getWidth(), window.getHeight(), dialogNewScreenplay.getWidth(), dialogNewScreenplay.getHeight()));
		dialogNewScreenplay.setResizable(false);
		dialogNewScreenplay.setVisible(true);
	}
	
	public void dialogOpenFile() {
		
		Main.files = Main.getFiles(Settings.pathScreenplays, ".xml");
		
		if (Main.files != null && Main.files.length > 0) {
		
			dialogOpenFileBox = new JComboBox<File>(Main.files);
			
			dialogOpenFileConfirm = new JButton(Settings.localize("BTN_OK"));
			dialogOpenFileConfirm.addActionListener(this);
			
			dialogOpenFileContent = new JPanel();
			dialogOpenFileContent.add(dialogOpenFileBox);
			dialogOpenFileContent.add(dialogOpenFileConfirm);
			
			dialogOpenFile = new JDialog(window, Settings.localize("DLG_OPEN_FILE"));
			dialogOpenFile.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			dialogOpenFile.add(dialogOpenFileContent);
			dialogOpenFile.pack();
			dialogOpenFile.setLocation(center(width, height, dialogOpenFile.getWidth(), dialogOpenFile.getHeight()));
			dialogOpenFile.setResizable(false);
			dialogOpenFile.setVisible(true);
		}
	}
	
	public void dialogSettings() {
		
		language = new JComboBox<Locale>();
		language.setRenderer(new LocaleCellRenderer());
		language.addItemListener(new LanguageSelectionListener());
		
		refreshLanguageList();
		
		generalSettingsTab = new JPanel();
		generalSettingsTab.add(language);
		
		dialogSettingsTabs = new JTabbedPane();
		dialogSettingsTabs.addTab(Settings.localize("TAB_GENERAL"), generalSettingsTab);
		
		dialogSettings = new JDialog(window, Settings.localize("DLG_SETTINGS"));
		dialogSettings.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialogSettings.add(dialogSettingsTabs);
		dialogSettings.pack();
		dialogSettings.setLocation(center(width, height, dialogSettings.getWidth(), dialogSettings.getHeight()));
		dialogSettings.setResizable(false);
		dialogSettings.setVisible(true);
	}
	
	public void screenplayEditor() {
		
			// PROPERTIES
		
		screenplayEditorTitle = new JTextField(25);
		screenplayEditorTitle.setText(Main.getAttr("title"));
		
		screenplayEditorAuthor = new JTextField(25);
		screenplayEditorAuthor.setText(Main.getAttr("author"));
		
		screenplayEditorPropertiesText = new JPanel();
		screenplayEditorPropertiesText.setLayout(new BoxLayout(screenplayEditorPropertiesText, BoxLayout.Y_AXIS));
		screenplayEditorPropertiesText.add(screenplayEditorTitle);
		screenplayEditorPropertiesText.add(Box.createRigidArea(new Dimension(0, 5)));
		screenplayEditorPropertiesText.add(screenplayEditorAuthor);
		
		screenplayEditorPropertiesApply = new JButton(Settings.localize("BTN_APPLY"));
		screenplayEditorPropertiesApply.addActionListener(this);
		
		screenplayEditorProperties = new JPanel();
		screenplayEditorProperties.add(screenplayEditorPropertiesText);
		screenplayEditorProperties.add(Box.createRigidArea(new Dimension(5, 0)));
		screenplayEditorProperties.add(screenplayEditorPropertiesApply);
		screenplayEditorProperties.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createTitledBorder(Settings.localize("PNL_PROPERTIES"))));
		
		
		
			// SCENES
		
		sceneList = new JList<Element>();
		sceneList.setFixedCellWidth(300);
		sceneList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sceneList.setCellRenderer(new NodeCellRenderer());
		sceneList.addListSelectionListener(new ScreenplaySelectionListener());
		
		refreshSceneList();
		
		sceneScrollPane = new JScrollPane();
		sceneScrollPane.setViewportView(sceneList);
		sceneScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		addScene = new JButton(Settings.localize("BTN_ADD"));
		addScene.addActionListener(this);
		
		editScene = new JButton(Settings.localize("BTN_EDIT"));
		editScene.addActionListener(this);
		
		moveSceneUp = new JButton("▲");
		moveSceneUp.addActionListener(this);
		
		moveSceneDown = new JButton("▼");
		moveSceneDown.addActionListener(this);
		
		sceneTabMoveButtons = new JPanel();
		sceneTabMoveButtons.setLayout(new GridLayout(1, 2, 0, 0));
		sceneTabMoveButtons.add(moveSceneUp);
		sceneTabMoveButtons.add(moveSceneDown);
		
		deleteScene = new JButton(Settings.localize("BTN_REMOVE"));
		deleteScene.addActionListener(this);
		
		sceneTabButtons = new JPanel();
		sceneTabButtons.setLayout(new GridLayout(4, 1, 0, 5));
		sceneTabButtons.add(addScene);
		sceneTabButtons.add(editScene);
		sceneTabButtons.add(sceneTabMoveButtons);
		sceneTabButtons.add(deleteScene);
		
		sceneTabContent = new JPanel();
		sceneTabContent.add(sceneScrollPane);
		sceneTabContent.add(sceneTabButtons);
		
		
		
			// LOCATIONS
		
		locationList = new JList<Element>();
		locationList.setFixedCellWidth(300);
		locationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		locationList.setCellRenderer(new NodeCellRenderer());
		locationList.addListSelectionListener(new ScreenplaySelectionListener());
		
		refreshLocationList();
		
		locationScrollPane = new JScrollPane();
		locationScrollPane.setViewportView(locationList);
		locationScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		addLocation = new JButton(Settings.localize("BTN_ADD"));
		addLocation.setToolTipText(Settings.localize("TT_ADD_LOCATION"));
		addLocation.addActionListener(this);
		
		deleteLocation = new JButton(Settings.localize("BTN_REMOVE"));
		deleteLocation.addActionListener(this);
		
		locationTabButtons = new JPanel();
		locationTabButtons.setLayout(new GridLayout(2, 1, 0, 5));
		locationTabButtons.add(addLocation);
		locationTabButtons.add(deleteLocation);
		
		locationTabContent = new JPanel();
		locationTabContent.add(locationScrollPane);
		locationTabContent.add(locationTabButtons);
		
		
		
			// CHARACTERS
		
		characterList = new JList<Element>();
		characterList.setFixedCellWidth(300);
		characterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		characterList.setCellRenderer(new NodeCellRenderer());
		characterList.addListSelectionListener(new ScreenplaySelectionListener());
		
		refreshCharacterList();
		
		characterScrollPane = new JScrollPane();
		characterScrollPane.setViewportView(characterList);
		characterScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		addCharacter = new JButton(Settings.localize("BTN_ADD"));
		addCharacter.setToolTipText(Settings.localize("TT_ADD_CHARACTER"));
		addCharacter.addActionListener(this);
		
		deleteCharacter = new JButton(Settings.localize("BTN_REMOVE"));
		deleteCharacter.addActionListener(this);
		
		characterTabButtons = new JPanel();
		characterTabButtons.setLayout(new GridLayout(2, 1, 0, 5));
		characterTabButtons.add(addCharacter);
		characterTabButtons.add(deleteCharacter);
		
		characterTabContent = new JPanel();
		characterTabContent.add(characterScrollPane);
		characterTabContent.add(characterTabButtons);
		
		
		
		screenplayEditorTabs = new JTabbedPane();
		screenplayEditorTabs.addTab(Settings.localize("TAB_SCENES"), sceneTabContent);
		screenplayEditorTabs.addTab(Settings.localize("TAB_LOCATIONS"), locationTabContent);
		screenplayEditorTabs.addTab(Settings.localize("TAB_CHARACTERS"), characterTabContent);
		
		screenplayEditorContent = new JPanel();
		screenplayEditorContent.setLayout(new BoxLayout(screenplayEditorContent, BoxLayout.Y_AXIS));
		screenplayEditorContent.add(screenplayEditorProperties);
		screenplayEditorContent.add(screenplayEditorTabs);
		
		screenplayEditor = new JDialog(window, Settings.localize("DLG_SCREENPLAY_EDITOR"));
		screenplayEditor.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		screenplayEditor.add(screenplayEditorContent);
		
		refreshScreenplayEditor();
		
		screenplayEditor.setLocation(center(width, height, screenplayEditor.getWidth(), screenplayEditor.getHeight()));
		screenplayEditor.setResizable(false);
		screenplayEditor.setVisible(true);
	}
	
	public void sceneEditor(Element scene) {
		
		sceneElementList = new JList<Element>();
		sceneElementList.setFixedCellWidth(300);
		sceneElementList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sceneElementList.setCellRenderer(new NodeCellRenderer());
		sceneElementList.addListSelectionListener(new SceneSelectionListener());
		
		refreshSceneElementList(scene);
		
		sceneElementScrollPane = new JScrollPane();
		sceneElementScrollPane.setViewportView(sceneElementList);
		sceneElementScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		addAction = new JButton(Settings.localize("BTN_ADD_ACTION"));
		addAction.addActionListener(this);
		
		addDialogue = new JButton(Settings.localize("BTN_ADD_DIALOGUE"));
		addDialogue.addActionListener(this);
		
		editSceneElement = new JButton(Settings.localize("BTN_EDIT"));
		editSceneElement.addActionListener(this);
		
		moveSceneElementUp = new JButton("▲");
		moveSceneElementUp.addActionListener(this);
		
		moveSceneElementDown = new JButton("▼");
		moveSceneElementDown.addActionListener(this);
		
		moveSceneElementButtons = new JPanel();
		moveSceneElementButtons.setLayout(new GridLayout(1, 2, 0, 0));
		moveSceneElementButtons.add(moveSceneElementUp);
		moveSceneElementButtons.add(moveSceneElementDown);
		
		deleteSceneElement = new JButton(Settings.localize("BTN_REMOVE"));
		deleteSceneElement.addActionListener(this);
		
		sceneElementButtons = new JPanel();
		sceneElementButtons.setLayout(new GridLayout(5, 1, 0, 5));
		sceneElementButtons.add(addAction);
		sceneElementButtons.add(addDialogue);
		sceneElementButtons.add(editSceneElement);
		sceneElementButtons.add(moveSceneElementButtons);
		sceneElementButtons.add(deleteSceneElement);
		
		sceneElementContent = new JPanel();
		sceneElementContent.add(sceneElementScrollPane);
		sceneElementContent.add(sceneElementButtons);
		
		sceneEditorContent = new JPanel();
		sceneEditorContent.setLayout(new BoxLayout(sceneEditorContent, BoxLayout.Y_AXIS));
		sceneEditorContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		sceneEditorContent.add(sceneProperties(scene));
		sceneEditorContent.add(sceneElementContent);
		
		sceneEditor = new JDialog(screenplayEditor, Settings.localize("DLG_SCENE_EDITOR"));
		sceneEditor.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		sceneEditor.add(sceneEditorContent);

		refreshSceneEditor();
		
		sceneEditor.setLocation(center(width, height, sceneEditor.getWidth(), sceneEditor.getHeight()));
		sceneEditor.setResizable(false);
		sceneEditor.setVisible(true);
	}
	
	public void dialogAddScene() {
		
		dialogAddScene = new JDialog(screenplayEditor, Settings.localize("DLG_NEW_SCENE"));
		dialogAddScene.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialogAddScene.add(sceneProperties(null));
		dialogAddScene.pack();
		dialogAddScene.setLocation(center(width, height, dialogAddScene.getWidth(), dialogAddScene.getHeight()));
		dialogAddScene.setResizable(false);
		dialogAddScene.setVisible(true);
	}
	
	public void dialogAddLocationOrCharacter(boolean location) {
		
	dialogAddLocationOrCharacterText = new JTextField(20);
	dialogAddLocationOrCharacterText.setEditable(true);
	dialogAddLocationOrCharacterText.setText(Settings.localize(location ? "DFT_LOCATION" : "DFT_CHARACTER"));
	
	if (location) {
		
		dialogAddLocation = new JButton(Settings.localize("BTN_OK"));
		dialogAddLocation.addActionListener(this);
	}
	
	else {
		
		dialogAddCharacter = new JButton(Settings.localize("BTN_OK"));
		dialogAddCharacter.addActionListener(this);
	}
	
	dialogAddLocationOrCharacterContent = new JPanel();
	dialogAddLocationOrCharacterContent.add(dialogAddLocationOrCharacterText);
	dialogAddLocationOrCharacterContent.add(location ? dialogAddLocation : dialogAddCharacter);
	
	dialogAddLocationOrCharacter = new JDialog(screenplayEditor, Settings.localize(location ? "DLG_NEW_LOCATION" : "DLG_NEW_CHARACTER"));
	dialogAddLocationOrCharacter.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	dialogAddLocationOrCharacter.add(dialogAddLocationOrCharacterContent);
	dialogAddLocationOrCharacter.pack();
	dialogAddLocationOrCharacter.setLocation(center(width, height, dialogAddLocationOrCharacter.getWidth(), dialogAddLocationOrCharacter.getHeight()));
	dialogAddLocationOrCharacter.setResizable(false);
	dialogAddLocationOrCharacter.setVisible(true);
}
	
	public void dialogSceneElement(String type, Element element) {
		
		boolean add = element == null;
		
		if (!add) {
			
			type = element.getNodeName();
		}
		
		if (type.equals("dialogue")) {
			
			dialogueCharacter = new JComboBox<Element>(Main.getElements(Main.currentDocument, "character", null));
			dialogueCharacter.setRenderer(new NodeCellRenderer());
			
			if (!add && element.hasAttribute("character")) {
				
				for (int i = 0; i < dialogueCharacter.getModel().getSize(); i++) {
					
					if (dialogueCharacter.getItemAt(i).getTextContent().equals(element.getAttribute("character"))) {
						
						dialogueCharacter.setSelectedIndex(i);
					}
				}
			}
			
			dialogueWrylies = new JTextField(20);
			dialogueWrylies.setEditable(true);
			
			if (!add && element.hasAttribute("wrylies")) {
				
				dialogueWrylies.setText(element.getAttribute("wrylies"));
			}
		}
		
		dialogAddSceneElementText = new JTextArea(10, 40);
		dialogAddSceneElementText.setEditable(true);
		dialogAddSceneElementText.setLineWrap(true);
		dialogAddSceneElementText.setWrapStyleWord(true);
		
		if (!add) {
			
			dialogAddSceneElementText.setText(element.getTextContent());
		}
		
		dialogueAddSceneElementScrollPane = new JScrollPane(dialogAddSceneElementText);
		dialogueAddSceneElementScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		dialogueAddSceneElementScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel buttonPanel = new JPanel();
		
		if (type.equals("dialogue")) {
			
			if (add) {
			
				dialogAddDialogue = new JButton(Settings.localize("BTN_OK"));
				dialogAddDialogue.addActionListener(this);
				dialogAddDialogue.setAlignmentX(JButton.RIGHT_ALIGNMENT);
			}
			
			else {
				
				dialogEditDialogue = new JButton(Settings.localize("BTN_APPLY"));
				dialogEditDialogue.addActionListener(this);
				dialogEditDialogue.setAlignmentX(JButton.RIGHT_ALIGNMENT);
			}
			
			buttonPanel.add(add ? dialogAddDialogue : dialogEditDialogue);
		}
		
		else if (type.equals("action")) {
			
			if (add) {
			
				dialogAddAction = new JButton(Settings.localize("BTN_OK"));
				dialogAddAction.addActionListener(this);
				dialogAddAction.setAlignmentX(JButton.RIGHT_ALIGNMENT);
			}
			
			else {
				
				dialogEditAction = new JButton(Settings.localize("BTN_APPLY"));
				dialogEditAction.addActionListener(this);
				dialogEditAction.setAlignmentX(JButton.RIGHT_ALIGNMENT);
			}
			
			buttonPanel.add(add ? dialogAddAction : dialogEditAction);
		}
		
		dialogAddSceneElementContent = new JPanel();
		dialogAddSceneElementContent.setLayout(new BoxLayout(dialogAddSceneElementContent, BoxLayout.Y_AXIS));
		
		if (type.equals("dialogue")) {
			
			dialogAddSceneElementContent.add(dialogueCharacter);
			dialogAddSceneElementContent.add(Box.createRigidArea(new Dimension(0, 5)));
			dialogAddSceneElementContent.add(dialogueWrylies);
			dialogAddSceneElementContent.add(Box.createRigidArea(new Dimension(0, 5)));
		}
		
		dialogAddSceneElementContent.add(dialogueAddSceneElementScrollPane);
		dialogAddSceneElementContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialogAddSceneElementContent.add(buttonPanel);
		
		dialogSceneElement = new JDialog(sceneEditor);
		
		if (type.equals("action")) {
			
			dialogSceneElement.setTitle(Settings.localize(add ? "DLG_NEW_ACTION" : "DLG_EDIT_ACTION"));
		}
		
		else if (type.equals("dialogue")) {
			
			dialogSceneElement.setTitle(Settings.localize(add ? "DLG_NEW_DIALOGUE" : "DLG_EDIT_DIALOGUE"));
		}
		
		dialogSceneElement.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialogSceneElement.add(dialogAddSceneElementContent);
		dialogSceneElement.pack();
		dialogSceneElement.setLocation(center(width, height, dialogSceneElement.getSize().width, dialogSceneElement.getSize().height));
		dialogSceneElement.setResizable(false);
		dialogSceneElement.setVisible(true);
	}
	
	private JPanel sceneProperties(Element scene) {
		
		// LOCATION
		
		scenePropertiesLocation = new JComboBox<Element>(Main.getElements(Main.currentDocument, "location", null));
		scenePropertiesLocation.setRenderer(new NodeCellRenderer());
		scenePropertiesLocation.setToolTipText(Settings.localize("TT_PROPERTIES_LOCATION"));
		
		if (scene != null && !scene.getAttribute("location").equals("")) {
			
			for (int i = 0; i <= scenePropertiesLocation.getComponentCount(); i++) {
				
				if (scenePropertiesLocation.getItemAt(i).getTextContent().equals(scene.getAttribute("location"))) {
					
					scenePropertiesLocation.setSelectedIndex(i);
					break;
				}
			}
		}
		
		// LOCATION MODIFIER
		
		scenePropertiesModLocation = new JTextField(20);
		scenePropertiesModLocation.setEditable(true);
		scenePropertiesModLocation.setToolTipText(Settings.localize("TT_PROPERTIES_MOD_LOCATION"));
		
		if (scene != null) {
			
			scenePropertiesModLocation.setText(scene.getAttribute("mod-location"));
		}
		
		scenePropertiesLocationContent = new JPanel();
		scenePropertiesLocationContent.setLayout(new BoxLayout(scenePropertiesLocationContent, BoxLayout.Y_AXIS));
		scenePropertiesLocationContent.setBorder(new CompoundBorder(BorderFactory.createTitledBorder(Settings.localize("PNL_LOCATION")), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		scenePropertiesLocationContent.add(scenePropertiesLocation);
		scenePropertiesLocationContent.add(Box.createRigidArea(new Dimension(0, 5)));
		scenePropertiesLocationContent.add(scenePropertiesModLocation);
		
			// PLACE
		
		scenePropertiesPlace = new JComboBox<String>(Settings.optionsPlace);
		scenePropertiesPlace.addItem("-");
		scenePropertiesPlace.setToolTipText(Settings.localize("TT_PROPERTIES_PLACE"));
		
		if (scene != null) {
			
			if (scene.getAttribute("place").equals("")) {
			
				scenePropertiesPlace.setSelectedIndex(scenePropertiesPlace.getComponentCount());
			}
			
			else {
				
				for (int i = 0; i <= scenePropertiesPlace.getComponentCount(); i++) {
					
					if (scenePropertiesPlace.getItemAt(i).equals(scene.getAttribute("place"))) {
						
						scenePropertiesPlace.setSelectedIndex(i);
						break;
					}
				}
			}
		}

		scenePropertiesPlaceContent = new JPanel();
		scenePropertiesPlaceContent.setBorder(BorderFactory.createTitledBorder(Settings.localize("PNL_PLACE")));
		scenePropertiesPlaceContent.add(scenePropertiesPlace);
		
			// TIME
		
		scenePropertiesTime = new JComboBox<String>(Settings.optionsTime);
		scenePropertiesTime.addItem("-");
		scenePropertiesTime.setToolTipText(Settings.localize("TT_PROPERTIES_TIME"));
		
		if (scene != null) {
			
			if (scene.getAttribute("time").equals("")) {
			
				scenePropertiesPlace.setSelectedIndex(scenePropertiesPlace.getComponentCount()-1);
			}
			
			else {
				
				for (int i = 0; i <= scenePropertiesPlace.getComponentCount(); i++) {
					
					if (scenePropertiesPlace.getItemAt(i).equals(scene.getAttribute("time"))) {
						
						scenePropertiesPlace.setSelectedIndex(i);
						break;
					}
				}
			}
		}
		
		scenePropertiesTimeContent = new JPanel();
		scenePropertiesTimeContent.setBorder(BorderFactory.createTitledBorder(Settings.localize("PNL_TIME")));
		scenePropertiesTimeContent.add(scenePropertiesTime);
		
		if (scene == null) {
			
			dialogAddSceneConfirm = new JButton(Settings.localize("BTN_OK"));
			dialogAddSceneConfirm.setAlignmentY(JButton.BOTTOM_ALIGNMENT);  // not working :(
			dialogAddSceneConfirm.addActionListener(this);
		}
		
		else {
			
			scenePropertiesApply = new JButton(Settings.localize("BTN_APPLY"));
			scenePropertiesApply.setAlignmentY(JButton.BOTTOM_ALIGNMENT);  // not working :(
			scenePropertiesApply.addActionListener(this);
		}
		
		JPanel placeTimePanel = new JPanel();
		placeTimePanel.add(scenePropertiesPlaceContent);
		placeTimePanel.add(scenePropertiesTimeContent);
		placeTimePanel.add(Box.createRigidArea(new Dimension(5, 0)));
		placeTimePanel.add(scene == null ? dialogAddSceneConfirm : scenePropertiesApply);
		
		JPanel res = new JPanel();
		res.setLayout(new BoxLayout(res, BoxLayout.Y_AXIS));
		res.setBorder(new EmptyBorder(5, 5, 5, 5));
		res.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		res.add(scenePropertiesLocationContent);
		res.add(placeTimePanel);
		
		return res;
	}
	
	
	
	public void refreshToolbar() {
		
		create.setToolTipText(Settings.localize("NEW"));
		
		save.setEnabled(!Main.saved);
		save.setToolTipText(Settings.localize("SAVE"));
		
		open.setToolTipText(Settings.localize("OPEN"));
		
		export.setEnabled(Main.currentDocument != null);
		export.setToolTipText(Settings.localize("EXPORT"));
		
		settings.setToolTipText(Settings.localize("SETTINGS"));
		
		screenplayEditorButton.setEnabled(Main.currentDocument != null);
		screenplayEditorButton.setText(Settings.localize("SCREENPLAY_EDITOR"));
	}
	
	public void refreshWindowTitle() {
		
		String title = "";
		if (!Main.saved) title += "*";
		title += Main.getAttr("title") + " - " + Settings.versionStr;
		window.setTitle(title);
	}
	
	public void refreshPreview() {
		
		String text = "";
		
		// title
		text += "<p id='title'><b>" + Main.getAttr("title").toUpperCase() + "</b><p>";
		
		// author
		if (Main.getAttr("author") != "") {
			
			text += "<p id='author'>written by<br>" + Main.getAttr("author") + "</p>";
		}
		
		text += "<div id='screenplay'";
		NodeList scenes = Main.currentDocument.getDocumentElement().getChildNodes();
		
		for (int i = 0; i < scenes.getLength(); i++) {
			
			Node currentScene = scenes.item(i);
			
			text += "<br>";
			text += "<div class='scene'>";
			
			if (currentScene.hasAttributes()) {
			
					// scene heading
				
				text += "<p class='heading'>";
				
				// int. / ext. (optional but recommended)
				if (currentScene.getAttributes().getNamedItem("place") != null) {
					
					text += currentScene.getAttributes().getNamedItem("place").getTextContent().toUpperCase();
				}
				
				// location (required)
				text += currentScene.getAttributes().getNamedItem("location").getTextContent().toUpperCase();
				
				// location modifier (optional)
				if (currentScene.getAttributes().getNamedItem("mod-location") != null) {
					
					text += " (" + currentScene.getAttributes().getNamedItem("mod-location").getTextContent().toUpperCase() + ")";
				}
				
				// time (optional but recommended)
				if (currentScene.getAttributes().getNamedItem("time") != null) {
					
					text += " - " + currentScene.getAttributes().getNamedItem("time").getTextContent().toUpperCase();
				}
				
				text += "</p>";
			}
			
			NodeList elements = currentScene.getChildNodes();
			
			for (int j = 0; j < elements.getLength(); j++) {
				
				Node currentElement = elements.item(j);
				
					// action
				
				if (currentElement.getNodeName().equals("action")) {
					
					text += "<p class='action'>" + currentElement.getTextContent() + "</p>";
				}
				
					// dialogue
				
				else if (currentElement.getNodeName().equals("dialogue")) {
					
					text += "<br>";
					
					text += "<div class='dialogue'>";
					
					if (currentElement.hasAttributes()) {
						
						// character (required)
						text += "<div class='role'>" + currentElement.getAttributes().getNamedItem("character").getTextContent().toUpperCase();
						
						// extension (optional)
						if (currentElement.getAttributes().getNamedItem("extension") != null) {
							
							text += " (" + currentElement.getAttributes().getNamedItem("extension").getTextContent() + ")";
						}
						
						text += "</div>";
						
						// parentheticals (optional)
						if (currentElement.getAttributes().getNamedItem("wrylies") != null) {
							
							text += "<div class='wrylies'>" + "(" + currentElement.getAttributes().getNamedItem("wrylies").getTextContent() + ")" + "</div>";
						}
						
						// line (necessary)
						text += "<div class='line'>" + currentElement.getTextContent() + "</div>";
					}
					
					// close dialogue tag
					text += "</div>";
				}
			}
			
			// close scene tag
			text += "</div>";
		}
		
		// close screenplay tag
		text += "</div>";
		
		try {
			
			preview.setText(text);
		}
		
		catch (Exception e) {
			
			Main.log(Main.errorLog, e);
			error(window, "ERR_PREVIEW");
		}
	}
	
	public void refreshScreenplayEditor() {
		
			// ADD SCENE
		
		addScene.setEnabled(locationList.getModel().getSize() > 0);
		
		addScene.setToolTipText(
		Settings.localize(
				
			addScene.isEnabled()
			
				? sceneList.isSelectionEmpty()
						
					? "TT_CREATE_SCENE_APPEND"
					: "TT_CREATE_SCENE_INSERT"
						
				: "TT_CREATE_SCENE_NO_LOCATION"
		));
		
			// EDIT SCENE
		
		editScene.setEnabled(!sceneList.isSelectionEmpty());
		
		editScene.setToolTipText(
		Settings.localize(
				
			sceneList.isSelectionEmpty()
			
				? "TT_EDIT_SCENE_NOTHING_SELECTED"
				: "TT_EDIT_SCENE"
		));
		
			// MOVE SCENE UP
		
		moveSceneUp.setEnabled(!sceneList.isSelectionEmpty() && sceneList.getSelectedIndex() > 0);
		
		moveSceneUp.setToolTipText(
		Settings.localize(
				
			sceneList.isSelectionEmpty()
			
				? "TT_MOVE_SCENE_NOTHING_SELECTED"
				: sceneList.getSelectedIndex() == 0
				
					? "TT_MOVE_SCENE_UP_ALREADY_FIRST"
					: "TT_MOVE_SCENE_UP"
		));
		
			// MOVE SCENE DOWN
		
		moveSceneDown.setEnabled(!sceneList.isSelectionEmpty() && sceneList.getSelectedIndex() < (sceneList.getModel().getSize() - 1));
		
		moveSceneDown.setToolTipText(
		Settings.localize(
				
			sceneList.isSelectionEmpty()
			
				? "TT_MOVE_SCENE_NOTHING_SELECTED"
				: sceneList.getSelectedIndex() >= (sceneList.getModel().getSize() - 1)
				
					? "TT_MOVE_SCENE_DOWN_ALREADY_LAST"
					: "TT_MOVE_SCENE_DOWN"
		));
		
			// DELETE SCENE
	
		deleteScene.setEnabled(!sceneList.isSelectionEmpty());
		
		deleteScene.setToolTipText(
		Settings.localize(
				
			sceneList.isSelectionEmpty()
			
				? "TT_REMOVE_SCENE_NOTHING_SELECTED"
				: "TT_REMOVE_SCENE"
		));
		
			// DELETE LOCATION
		
		if (locationList.isSelectionEmpty()) {
			
			deleteLocation.setEnabled(false);
			deleteLocation.setToolTipText(Settings.localize("TT_REMOVE_LOCATION_NOTHING_SELECTED"));
		}
		
		else {
			
			deleteLocation.setEnabled(true);
			deleteLocation.setToolTipText(Settings.localize("TT_REMOVE_LOCATION"));
			
			Element[] scenes = Main.getElements(Main.currentDocument, "scene", null);
			
			for (int i = 0; i < scenes.length; i++) {
				
				// check if location is being used for existing scenes
				if (scenes[i].getAttribute("location").equals(locationList.getSelectedValue().getTextContent())) {
					
					deleteLocation.setEnabled(false);
					deleteLocation.setToolTipText(Settings.localize("TT_REMOVE_LOCATION_USED_FOR_SCENE"));
					
					break;
				}
			}
		}
		
			// DELETE CHARACTER
		
		if (characterList.isSelectionEmpty()) {
			
			deleteCharacter.setEnabled(false);
			deleteCharacter.setToolTipText(Settings.localize("TT_REMOVE_CHARACTER_NOTHING_SELECTED"));
		}
		
		else {
			
			deleteCharacter.setEnabled(true);
			deleteCharacter.setToolTipText(Settings.localize("TT_REMOVE_CHARACTER"));
			
			Element[] scenes = Main.getElements(Main.currentDocument, "scene", null);
			
			for (int i = 0; i < scenes.length; i++) {
			
				Element[] dialogues = Main.getElements(Main.currentDocument, "dialogue", scenes[i]);
			
				for (int j = 0; j < dialogues.length; j++) {
					
					// check if location is being used for existing scenes
					if (dialogues[j].getAttribute("character").equals(characterList.getSelectedValue().getTextContent())) {
						
						deleteCharacter.setEnabled(false);
						deleteCharacter.setToolTipText(Settings.localize("TT_REMOVE_CHARACTER_USED_FOR_DIALOGUE") + (i + 1) + ".");
						
						break;
					}
				}
				
				if (!deleteCharacter.isEnabled()) {
					
					break;
				}
			}
		}
		
		
		
		screenplayEditor.pack();
	}

	public void refreshSceneEditor() {
		
			// ADD ACTION
		
		addAction.setToolTipText(
		Settings.localize(
				
			sceneElementList.isSelectionEmpty()
			
				? "TT_CREATE_ACTION_APPEND"
				: "TT_CREATE_ACTION_INSERT"
		));
		
			// ADD DIALOGUE
		
		addDialogue.setEnabled(characterList.getModel().getSize() > 0);
		
		addDialogue.setToolTipText(
		Settings.localize(
				
			addDialogue.isEnabled()
				
				? sceneElementList.isSelectionEmpty()
				
					? "TT_CREATE_DIALOGUE_APPEND"
					: "TT_CREATE_DIALOGUE_INSERT"
						
				: "TT_CREATE_DIALOGUE_NO_CHARACTER"
		));
		
			// EDIT
		
		if (sceneElementList.isSelectionEmpty()) {
			
			editSceneElement.setEnabled(false);
			editSceneElement.setToolTipText(Settings.localize("TT_EDIT_SCENE_ELEMENT_NOTHING_SELECTED"));
		}
		
		else {
			
			editSceneElement.setEnabled(true);
			
			if (sceneElementList.getSelectedValue().getNodeName().equals("action")) {
				
				editSceneElement.setToolTipText(Settings.localize("TT_EDIT_ACTION"));
			}
			
			else if (sceneElementList.getSelectedValue().getNodeName().equals("dialogue")) {
				
				editSceneElement.setToolTipText(Settings.localize("TT_EDIT_DIALOGUE"));
			}
		}
		
			// MOVE SCENE ELEMENT UP
		
		moveSceneElementUp.setEnabled(!sceneElementList.isSelectionEmpty() && sceneElementList.getSelectedIndex() > 0);
		
		moveSceneElementUp.setToolTipText(
		Settings.localize(
				
			sceneElementList.isSelectionEmpty()
			
				? "TT_MOVE_SCENE_ELEMENT_NOTHING_SELECTED"
				: sceneElementList.getSelectedIndex() == 0
				
					? "TT_MOVE_SCENE_ELEMENT_UP_ALREADY_FIRST"
					: "TT_MOVE_SCENE_ELEMENT_UP"
		));
		
			// MOVE SCENE ELEMENT DOWN
		
		moveSceneElementDown.setEnabled(!sceneElementList.isSelectionEmpty() && sceneElementList.getSelectedIndex() < (sceneElementList.getModel().getSize() - 1));
		
		moveSceneElementDown.setToolTipText(
		Settings.localize(
				
			sceneElementList.isSelectionEmpty()
			
				? "TT_MOVE_SCENE_ELEMENT_NOTHING_SELECTED"
				: sceneElementList.getSelectedIndex() >= (sceneElementList.getModel().getSize() - 1)
				
					? "TT_MOVE_SCENE_ELEMENT_DOWN_ALREADY_LAST"
					: "TT_MOVE_SCENE_ELEMENT_DOWN"
		));
		
			// DELETE SCENE ELEMENT
		
		deleteSceneElement.setEnabled(!sceneElementList.isSelectionEmpty());
		
		deleteSceneElement.setToolTipText(
		Settings.localize(
				
			sceneElementList.isSelectionEmpty()
			
				? "TT_REMOVE_SCENE_ELEMENT_NOTHING_SELECTED"
				: "TT_REMOVE_SCENE_ELEMENT"
		));
		
		
		
		sceneEditor.pack();
	}
	
	public void refreshLanguageList() {
		
		((LanguageSelectionListener) language.getItemListeners()[0]).setEnabled(false);
		language.setModel(new DefaultComboBoxModel<Locale>(Settings.languages));
		language.setSelectedItem(Settings.language);
		((LanguageSelectionListener) language.getItemListeners()[0]).setEnabled(true);
	}
	
	public void refreshSceneList() {
		
		sceneList.setModel(getListModel("scene", null));
	}
	
	public void refreshLocationList() {
		
		locationList.setModel(getListModel("location", null));
	}
	
	public void refreshCharacterList() {
		
		characterList.setModel(getListModel("character", null));
	}
	
	public void refreshSceneElementList(Element scene) {
		
		sceneElementList.setModel(getListModel(null, scene));
	}
	
	private DefaultListModel<Element> getListModel(String element, Element scene) {
		
		DefaultListModel<Element> model = new DefaultListModel<Element>();
		Element[] scenes = Main.getElements(Main.currentDocument, element, scene);
		
		for (int i = 0; i < scenes.length; i++) {
			
			model.addElement(scenes[i]);
		}
		
		return model;
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		
			// MENU / TOOLBAR
		
		// open dialog to create a new screenplay
		if (e.getSource().equals(create)) {
			
			dialogNewScreenplay();
		}
		
		// confirm creating a new screenplay
		if (e.getSource().equals(dialogNewScreenplayConfirm)) {
			
			if (dialogNewScreenplayFile.getText().matches(Settings.validFilenamePatternXML)) {
			
				if (!Main.checkFilename(dialogNewScreenplayFile.getText(), dialogNewScreenplayTitle.getText(), dialogNewScreenplayAuthor.getText())) {
					
					// screenplay exists. overwrite?
					switch (JOptionPane.showOptionDialog(
							
						new JDialog(dialogNewScreenplay),
						Settings.localize("WRN_OVERWRITE"),
						Settings.localize("DLG_WARNING"),
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE,
						null,
						null,
						2))
					{
					
						case 0:
							
							Main.delete(dialogNewScreenplayTitle.getText());
							
							if (Main.checkFilename(dialogNewScreenplayFile.getText(), dialogNewScreenplayTitle.getText(), dialogNewScreenplayAuthor.getText())) {
								
								dialogNewScreenplay.dispose();
							}
							
							break;
							
						default:
							
							break;
					}
				}
				
				else {
					
					dialogNewScreenplay.dispose();
				}
			}
			
			else {
				
				error(dialogNewScreenplay, "ERR_INVALID_FILENAME");
			}
		}
		
		
		// save current screenplay
		if (e.getSource().equals(save)) {
			
			Main.save();
		}

		// open dialog to load a screenplay
		if (e.getSource().equals(open)) {
			
			dialogOpenFile();
		}
		
		// confirm loading a screenplay
		if (e.getSource().equals(dialogOpenFileConfirm)) {
			
			if (!Main.saved) {
				
				switch (JOptionPane.showOptionDialog(
						
					new JDialog(dialogOpenFile),
					Settings.localize("WRN_UNSAVED_CHANGES"),
					Settings.localize("DLG_WARNING"),
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE,
					null,
					null,
					2))
				{
				
					case 0:
						
						Main.save();
						break;
						
					case 2:
						
						return;
						
					default:
						
						break;
				}
			}
				
			Main.load((File)dialogOpenFileBox.getSelectedItem());
			dialogOpenFile.dispose();
		}
		
		// export the screenplay to a pdf file
		if (e.getSource().equals(export)) {
			
			Exporter.exportToPDF();
		}
		
		// open the settings dialog
		if (e.getSource().equals(settings)) {
			
			dialogSettings();
		}
		
		// confirm changes to properties of current screenplay
		if (e.getSource().equals(screenplayEditorPropertiesApply)) {
			
			Main.setAttr("title", screenplayEditorTitle.getText());
			Main.setAttr("author", screenplayEditorAuthor.getText());
		}
		
		// open the screenplay editor
		if (e.getSource().equals(screenplayEditorButton)) {
			
			screenplayEditor();
		}
		
		
		
			// SCREENPLAY Editor
		
		// open dialogue to add a new scene to the screenplay
		if (e.getSource().equals(addScene)) {
			
			dialogAddScene();
		}
		
		// confirm adding a new scene
		if (e.getSource().equals(dialogAddSceneConfirm)) {
			
			if (!(scenePropertiesLocation.getComponentCount() < 1)) {
				
				Main.addScene(
						
					sceneList.getSelectedValue(),
					(Element) scenePropertiesLocation.getSelectedItem(),
					scenePropertiesModLocation.getText(),
					(String) scenePropertiesPlace.getSelectedItem(),
					(String) scenePropertiesTime.getSelectedItem()
				);
				
				dialogAddScene.dispose();
			}
		}
		
		// open the scene Editor to edit the selected scene
		if (e.getSource().equals(editScene)) {
			
			sceneEditor(sceneList.getSelectedValue());
		}
		
		// apply changes to the properties of the selected scene
		if (e.getSource().equals(scenePropertiesApply)) {
			
			Main.editSceneProperties(
					
				sceneList.getSelectedValue(),
				scenePropertiesLocation.getItemAt(scenePropertiesLocation.getSelectedIndex()).getTextContent(),
				scenePropertiesModLocation.getText(),
				scenePropertiesPlace.getItemAt(scenePropertiesPlace.getSelectedIndex()),
				scenePropertiesTime.getItemAt(scenePropertiesTime.getSelectedIndex())
			);
		}
		
		// move the selected scene towards the beginning
		if (e.getSource().equals(moveSceneUp)) {
			
			Element scene = sceneList.getSelectedValue();
			Main.moveElement(scene, null, false);
			sceneList.setSelectedValue(scene, true);
		}
		
		// move the selected scene towards the end
		if (e.getSource().equals(moveSceneDown)) {
			
			Element scene = sceneList.getSelectedValue();
			Main.moveElement(scene, null, true);
			sceneList.setSelectedValue(scene, true);
		}
		
		// delete the selected scene from the screenplay
		if (e.getSource().equals(deleteScene)) {
			
			switch (JOptionPane.showOptionDialog(
					
				new JDialog(screenplayEditor),
				Settings.localize("WRN_ARE_YOU_SURE"),
				Settings.localize("DLG_WARNING"),
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				null,
				1))
			{
			
				case 0:
					
					Main.removeElement(sceneList.getSelectedValue(), null);
					break;
					
				default:
					
					break;
			}
		}
		
		// open dialogue to add a new location to the screenplay
		if (e.getSource().equals(addLocation)) {
			
			dialogAddLocationOrCharacter(true);
		}
		
		// confirm adding a new location to the screenplay
		if (e.getSource().equals(dialogAddLocation)) {
			
			String text = dialogAddLocationOrCharacterText.getText();
			
			if (!text.equals("")) {
				
				boolean exists = false;
				
				Element[] locations = Main.getElements(Main.currentDocument, "location", null);
				
				for (int i = 0; i < locations.length; i++) {
					
					if (locations[i].getTextContent().equals(text)) {
						
						exists = true;
						break;
					}
				}
				
				if (!exists) {
					
					Main.addLocationOrCharacter(true, dialogAddLocationOrCharacterText.getText());
					dialogAddLocationOrCharacter.dispose();
				}
				
				else {
					
					warning(dialogAddLocationOrCharacter, "WRN_LOCATION_EXISTS");
				}
			}
		}
		
		// open dialogue to add a new character to the screenplay
		if (e.getSource().equals(addCharacter)) {
			
			dialogAddLocationOrCharacter(false);
		}
		
		// confirm adding a new character to the screenplay
		if (e.getSource().equals(dialogAddCharacter)) {
			
			String text = dialogAddLocationOrCharacterText.getText();
			
			if (!text.equals("")) {
				
				boolean exists = false;
				
				Element[] characters = Main.getElements(Main.currentDocument, "character", null);
				
				for (int i = 0; i < characters.length; i++) {
					
					if (characters[i].getTextContent().equals(text)) {
						
						exists = true;
						break;
					}
				}
				
				if (!exists) {
					
					Main.addLocationOrCharacter(false, dialogAddLocationOrCharacterText.getText());
					dialogAddLocationOrCharacter.dispose();
				}
				
				else {
					
					warning(dialogAddLocationOrCharacter, "WRN_CHARACTER_EXISTS");
				}
			}
		}
		
		// delete the selected location from the screenplay
		if (e.getSource().equals(deleteLocation)) {
			
			switch (JOptionPane.showOptionDialog(
					
				new JDialog(screenplayEditor),
				Settings.localize("WRN_ARE_YOU_SURE"),
				Settings.localize("DLG_WARNING"),
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				null,
				1))
			{
			
				case 0:
					
					Main.removeElement(locationList.getSelectedValue(), null);
					break;
					
				default:
					
					break;
			}
		}
		
		// delete the selected location from the screenplay
		if (e.getSource().equals(deleteCharacter)) {
			
			switch (JOptionPane.showOptionDialog(
					
				new JDialog(screenplayEditor),
				Settings.localize("WRN_ARE_YOU_SURE"),
				Settings.localize("DLG_WARNING"),
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				null,
				1))
			{
			
				case 0:
					
					Main.removeElement(characterList.getSelectedValue(), null);
					break;
					
				default:
					
					break;
			}
		}
		
		// open dialogue to add a new action to the scene
		if (e.getSource().equals(addAction)) {
			
			dialogSceneElement("action", null);
		}
		
		// open dialogue to add a new dialogue to the scene
		if (e.getSource().equals(addDialogue)) {
			
			dialogSceneElement("dialogue", null);
		}
		
		// confirm adding a new action to the scene
		if (e.getSource().equals(dialogAddAction)) {
			
			if (dialogAddSceneElementText.getText() != "") {
				
				Main.addAction(sceneList.getSelectedValue(), sceneElementList.getSelectedValue(), dialogAddSceneElementText.getText());
				dialogSceneElement.dispose();
			}
		}
		
		// confirm adding a new dialogue to the scene
		if (e.getSource().equals(dialogAddDialogue)) {
			
			if (dialogAddSceneElementText.getText() != "") {
				
				Main.addDialogue(sceneList.getSelectedValue(), sceneElementList.getSelectedValue(), dialogueCharacter.getItemAt(dialogueCharacter.getSelectedIndex()), dialogAddSceneElementText.getText(), dialogueWrylies.getText());
				dialogSceneElement.dispose();
			}
		}
		
		// open dialogue to edit the selected scene element
		if (e.getSource().equals(editSceneElement)) {
			
			dialogSceneElement("", sceneElementList.getSelectedValue());
		}
		
		// apply changes to the selected action
		if (e.getSource().equals(dialogEditAction)) {
			
			Main.editAction(sceneElementList.getSelectedValue(), dialogAddSceneElementText.getText());
			dialogSceneElement.dispose();
		}
		
		// apply changes to the selected dialogue
		if (e.getSource().equals(dialogEditDialogue)) {
			
			Main.editDialogue(sceneElementList.getSelectedValue(), dialogueCharacter.getItemAt(dialogueCharacter.getSelectedIndex()), dialogAddSceneElementText.getText(), dialogueWrylies.getText());
			dialogSceneElement.dispose();
		}
		
		// move the selected scene element towards the beginning
		if (e.getSource().equals(moveSceneElementUp)) {
			
			Element element = sceneElementList.getSelectedValue();
			Main.moveElement(element, sceneList.getSelectedValue(), false);
			sceneElementList.setSelectedValue(element, true);
		}
		
		// move the selected scene element towards the end
		if (e.getSource().equals(moveSceneElementDown)) {
			
			Element element = sceneElementList.getSelectedValue();
			Main.moveElement(element, sceneList.getSelectedValue(), true);
			sceneElementList.setSelectedValue(element, true);
		}
		
		// remove the selected scene element from the scene
		if (e.getSource().equals(deleteSceneElement)) {
			
			Main.removeElement(sceneElementList.getSelectedValue(), sceneList.getSelectedValue());
		}
	}
}