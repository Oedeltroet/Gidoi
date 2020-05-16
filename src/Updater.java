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
			
			File file;
			String code;
			FileOutputStream output;
			
			if (System.getProperty("os.name").toLowerCase().startsWith("linux")) {
				
				file = new File(Settings.tmpFile + ".sh");
				code = "#!/bin/sh\nmv tmp/* tmp/.* .\nrmdir tmp\njava -jar Gidoi.jar";
				
				output = new FileOutputStream(file);
				output.write(code.getBytes());
				output.close();
				file.setExecutable(true);
				
				new ProcessBuilder(file.toURI().getPath()).start();
				System.exit(0);
			}
			
			else if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
				
				file = new File(Settings.tmpFile + ".bat");
				code = "mv tmp/* tmp/.* . & rmdir tmp & java -jar Gidoi.jar";
				
				output = new FileOutputStream(file);
				output.write(code.getBytes());
				output.close();
				file.setExecutable(true);
				
				Runtime.getRuntime().exec("cmd /c" + file.getName());
				System.exit(0);
			}
		}
		
		catch (Exception e) {
			
			Main.log(Main.errorLog, e);
		}
	}
}