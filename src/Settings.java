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
	
	
	
	// paths, patterns and strings
	public static String versionStr = "Gidoi 0.1 (Beta)";
	public static String settingsFile = "settings";
	public static String schemaFile = "screenplay.xsd";
	public static String tmpFile = "tmp";
	public static String pathScreenplays = "screenplays/";
	public static String pathExported = "exported/";
	public static String pathLogfiles = "logs/";
	public static String validFilenamePatternXML = "[^./]+\\.xml";
	public static String urlRepository = "https://github.com/Oedeltroet/Gidoi";
	
	public static String[] optionsPlace = {"int.", "ext.", "int./ext."};
	public static String[] optionsTime = {"day", "night"};
	
	
	
		// FORMAT SETTINGS
	
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