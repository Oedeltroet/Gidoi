import java.io.File;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class Settings {
	
	
	
		// STRINGS
	
	public static final String VERSION = "Gidoi 0.2 (Beta)";
	public static final String URL_REMOTE = "https://github.com/Oedeltroet/Gidoi";
	public static String[] optionsPlace = {"int.", "ext.", "int./ext."};
	public static String[] optionsTime = {"day", "night"};
	
	public static final String SETTINGS_KEY_LANGUAGE = "lang";
	public static final String SETTINGS_KEY_AUTO_UPDATE = "auto_update";
	public static final String SETTINGS_KEY_PATH_SCREENPLAYS = "p_screenplays";
	public static final String SETTINGS_KEY_PATH_EXPORT = "p_export";
	public static final String SETTINGS_KEY_GUI_WINDOW_X = "window_x";
	public static final String SETTINGS_KEY_GUI_WINDOW_Y = "window_y";
	public static final String SETTINGS_KEY_GUI_WINDOW_WIDTH = "window_w";
	public static final String SETTINGS_KEY_GUI_WINDOW_HEIGHT = "window_h";
	public static final String SETTINGS_KEY_GUI_WINDOW_MAXIMIZED = "window_m";
	
	
	
		// UPDATE
	
	public static boolean autoUpdate = true;
	
	
	
		// LOCALISATION
	
	public static Locale[] languages = {
			
		Locale.ENGLISH,
		Locale.GERMAN
	};
	
	public static Locale language = Locale.ENGLISH;
	public static ResourceBundle localisation;
	public static String baseBundleName = "localisation.Localisation";
	
	public static String localize(String key) {
		
		try {
			
			return localisation.getString(key);
		}
		
		catch(MissingResourceException e) {
			
			Main.log(Main.errorLog, e);
			
			return key;
		}
	}
	
	
	
		// GUI
	
	// window bounds
	public static final int DEFAULT_WINDOW_X = 50;
	public static final int DEFAULT_WINDOW_Y = 50;
	public static final int DEFAULT_WINDOW_WIDTH = 600; //smaller than minimal size
	public static final int DEFAULT_WINDOW_HEIGHT = 600;
	public static int windowX = DEFAULT_WINDOW_X;
	public static int windowY = DEFAULT_WINDOW_Y;
	public static int windowWidth = DEFAULT_WINDOW_WIDTH;
	public static int windowHeight = DEFAULT_WINDOW_HEIGHT;
	public static int windowMaximized = 0;
	
	// tooltip settings
	public static int tooltipInitialDelay = 1000;
	public static int tooltipDismissDelay = 10000;
	
	
	
		// FILES AND PATHS
	
	public static final String FILE_SETTINGS = "settings";
	public static final String FILE_SCHEMA = "screenplay.xsd";
	public static final String FILE_TMP = "tmp";
	public static final String PATH_DEFAULT = "./";
	public static final String PATH_LOGFILES = "logs/";
	public static String pathScreenplays = PATH_DEFAULT;
	public static String pathExport = PATH_DEFAULT;
	
	public static String checkPath(String path) {
		
		File file = new File(path);
		
		if (!file.exists() || !file.isDirectory()) {
			
			path = PATH_DEFAULT;
		}
		
		return path;
	}
	
	
	
		// FORMAT
	
	public static PDRectangle pageFormat = PDRectangle.LETTER;
	
	public static boolean firstPageNumbered = false;
	
	public static float pageMarginTop = 1.0f,
						pageMarginBottom = 1.0f,
						pageMarginRight = 1.0f,
						pageMarginLeft = 1.5f,
						pageNrMarginTop = 0.5f,
						sceneHeadingIndentLeft = 0.0f,
						sceneHeadingIndentRight = 0.0f,
						characterIndentLeft = 2.0f,
						characterIndentRight = 0.0f,
						wryliesIndentLeft = 1.5f,
						wryliesIndentRight = 2.0f,
						dialogueIndentLeft = 1.0f,
						dialogueIndentRight = 1.5f,
						actionIndentLeft = 0.0f,
						actionIndentRight = 0.0f;
}