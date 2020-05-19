import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class Settings {

	// window size
	public static int width = 800;
	public static int height = 600;
	
	// tooltip settings
	public static int tooltipInitialDelay = 1000;
	public static int tooltipDismissDelay = 10000;
	
	
	
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
	
	
	
		// FILES AND PATHS
	
	public static final String FILE_SETTINGS = "settings";
	public static final String FILE_SCHEMA = "screenplay.xsd";
	public static final String FILE_TMP = "tmp";
	public static final String PATH_DEFAULT = "./";
	public static final String PATH_LOGFILES = "logs/";
	public static String pathScreenplays = PATH_DEFAULT;
	public static String pathExported = PATH_DEFAULT;
	
	
	
		// STRINGS
	
	public static final String VERSION = "Gidoi 0.2 (Beta)";
	public static final String URL_REMOTE = "https://github.com/Oedeltroet/Gidoi";
	public static String[] optionsPlace = {"int.", "ext.", "int./ext."};
	public static String[] optionsTime = {"day", "night"};
	
	
	
		// FORMAT
	
	public static PDRectangle pageFormat = PDRectangle.LETTER;
	
	public static float pageMarginTop = 1.0f,
						pageMarginBottom = 1.0f,
						pageMarginRight = 1.0f,
						pageMarginLeft = 1.5f,
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