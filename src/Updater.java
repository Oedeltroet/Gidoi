import java.io.File;
import java.io.FileOutputStream;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.lib.StoredConfig;

public class Updater {

	private static Git git;
	
	public static void refresh() {
		
		try {
			
			// check repository
			git = Git.open(new File("."));
			
			// check origin
			StoredConfig config = git.getRepository().getConfig();
			config.load();
			
			if (
				config.getString("remote", "origin", "url") == null ||
				!config.getString("remote", "origin", "url").equals(Settings.urlRepository)
			) {
				
				throw new Exception();
			}
		}
		
		// if not...
		catch (Exception e1) {
			
			try {
				
				Main.gui.status.setText("Cloning...");
				
				// clone origin into tmp dir
				Git.cloneRepository().setURI(Settings.urlRepository).setDirectory(new File("tmp")).call();
				
				Main.gui.status.setText("Updating...");
				
				// get current version
				update();
			}
			
			catch (Exception e2) {
				
				Main.log(Main.errorLog, e2);
				Main.gui.error(Main.gui.window, "ERR_UPDATE");
			}
		}
	}
	
	public static boolean check() {
		
		try {
			
			git.fetch().call();
			
			return !git.getRepository().resolve("HEAD").equals(git.getRepository().resolve("FETCH_HEAD"));
		}
		
		catch (Exception e) {
			
			Main.log(Main.errorLog, e);
			Main.gui.error(Main.gui.window, "ERR_UPDATE");
		}
		
		return false;
	}
	
	public static void update() {
		
		try {
		
			writeTmpFile();
			
			new ProcessBuilder("javac", Settings.tmpFile + ".java").start().waitFor();
			new ProcessBuilder("java", "tmp").start();
		
			System.exit(0);
		}
		
		catch (Exception e) {
			
			Main.log(Main.errorLog, e);
		}
	}
	
	private static void writeTmpFile() throws Exception {
			
		if (System.getProperty("os.name").toLowerCase().startsWith("linux")) {
			
			new ProcessBuilder("cd", Settings.rootPath).start().waitFor();
		}
		
		File tmp = new File(Settings.tmpFile + ".java");
		
		String code =
				
		"public class tmp {" +
				
			"public static void main(String[] args) {" +
			
				"try { " +
				
					"Runtime.getRuntime().exec(\"cmd /c start /wait mv -f tmp/* . & rmdir tmp\");" +
					"Runtime.getRuntime().exec(\"java -jar Gidoi.jar\");" +
				"}" +
					
				"catch (Exception e) {e.printStackTrace();}" +
			"}" +
		"}";
		
		FileOutputStream output = new FileOutputStream(tmp);
		output.write(code.getBytes());
		output.close();
	}
}